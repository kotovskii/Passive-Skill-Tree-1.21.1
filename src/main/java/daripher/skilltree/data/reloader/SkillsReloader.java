package daripher.skilltree.data.reloader;

import com.google.gson.*;
import daripher.skilltree.SkillTreeMod;
import daripher.skilltree.data.serializers.SkillBonusSerializer;
import daripher.skilltree.data.serializers.SkillRequirementSerializer;
import daripher.skilltree.network.NetworkHelper;
import daripher.skilltree.skill.PassiveSkill;
import daripher.skilltree.skill.bonus.SkillBonus;
import daripher.skilltree.skill.requirement.SkillRequirement;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@EventBusSubscriber(modid = SkillTreeMod.MOD_ID)
public class SkillsReloader extends SimpleJsonResourceReloadListener {
    public static final Gson GSON = new GsonBuilder().registerTypeAdapter(ResourceLocation.class, new ResourceLocation.Serializer())
            .registerTypeAdapter(SkillBonus.class, new SkillBonusSerializer())
            .registerTypeAdapter(SkillRequirement.class, new SkillRequirementSerializer())
            .registerTypeAdapter(MutableComponent.class, new MutableComponentAdapter()).setPrettyPrinting().create();
    private static final Map<ResourceLocation, PassiveSkill> SKILLS = new HashMap<>();

    public SkillsReloader() {
        super(GSON, "skills");
    }

    @SubscribeEvent
    public static void reloadSkills(AddReloadListenerEvent event) {
        event.addListener(new SkillsReloader());
    }

    public static Map<ResourceLocation, PassiveSkill> getSkills() {
        return SKILLS;
    }

    public static @Nullable PassiveSkill getSkillById(ResourceLocation id) {
        return SKILLS.get(id);
    }

    public static void loadFromByteBuf(FriendlyByteBuf buf) {
        loadFromNetwork(NetworkHelper.readPassiveSkills(buf));
    }

    public static void loadFromNetwork(Collection<PassiveSkill> skills) {
        SKILLS.clear();
        skills.forEach(s -> SKILLS.put(s.getId(), s));
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> map, @NotNull ResourceManager resourceManager, @NotNull ProfilerFiller profilerFiller) {
        SKILLS.clear();
        map.forEach(this::readSkill);
        SkillTreesReloader.pruneUnavailableSkills();
    }

    protected void readSkill(ResourceLocation id, JsonElement json) {
        try {
            PassiveSkill skill = GSON.fromJson(json, PassiveSkill.class);
            SKILLS.put(skill.getId(), skill);
        } catch (Exception exception) {
            String errorMessage = "Couldn't load passive skill: " + id;
            SkillTreeMod.LOGGER.error(errorMessage, exception);
        }
    }

    private static class MutableComponentAdapter implements JsonSerializer<MutableComponent>, JsonDeserializer<MutableComponent> {
        @Override
        public MutableComponent deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            if (json == null || json.isJsonNull()) {
                return Component.literal("");
            }
            if (!json.isJsonPrimitive()) {
                return Component.literal(flattenLegacyComponent(json));
            }
            return Component.literal(json.getAsString());
        }

        @Override
        public JsonElement serialize(MutableComponent component, Type type, JsonSerializationContext context) {
            return new JsonPrimitive(component.getString());
        }

        private String flattenLegacyComponent(JsonElement json) {
            if (json == null || json.isJsonNull()) {
                return "";
            }
            if (json.isJsonPrimitive()) {
                return json.getAsString();
            }
            if (json.isJsonArray()) {
                StringBuilder builder = new StringBuilder();
                json.getAsJsonArray().forEach(element -> appendComponentText(builder, flattenLegacyComponent(element)));
                return builder.toString();
            }
            JsonObject object = json.getAsJsonObject();
            StringBuilder builder = new StringBuilder();
            if (object.has("text")) {
                appendComponentText(builder, object.get("text").getAsString());
            } else if (object.has("translate")) {
                appendComponentText(builder, object.get("translate").getAsString());
            }
            if (object.has("with")) {
                appendComponentText(builder, flattenLegacyComponent(object.get("with")));
            }
            if (object.has("extra")) {
                appendComponentText(builder, flattenLegacyComponent(object.get("extra")));
            }
            return builder.length() == 0 ? object.toString() : builder.toString();
        }

        private void appendComponentText(StringBuilder builder, String text) {
            if (text == null || text.isBlank()) {
                return;
            }
            if (builder.length() > 0) {
                builder.append(' ');
            }
            builder.append(text);
        }
    }
}
