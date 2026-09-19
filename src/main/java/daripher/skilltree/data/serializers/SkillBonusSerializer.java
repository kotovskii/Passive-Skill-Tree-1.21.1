package daripher.skilltree.data.serializers;

import com.google.gson.*;
import daripher.skilltree.SkillTreeMod;
import daripher.skilltree.init.PSTRegistries;
import daripher.skilltree.skill.bonus.SkillBonus;
import daripher.skilltree.skill.bonus.player.BrokenSkillBonus;
import net.minecraft.resources.ResourceLocation;

import java.lang.reflect.Type;
import java.util.Objects;

public class SkillBonusSerializer implements JsonSerializer<SkillBonus<?>>, JsonDeserializer<SkillBonus<?>> {
    @Override
    public SkillBonus<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObj = (JsonObject) json;
        String type = jsonObj.get("type").getAsString();
        ResourceLocation serializerId = ResourceLocation.parse(type);
        SkillBonus.Serializer serializer = PSTRegistries.SKILL_BONUSES.get().get(serializerId);
        if (serializer == null) {
            return new BrokenSkillBonus("Unknown skill bonus: " + serializerId);
        }
        try {
            return serializer.deserialize(jsonObj);
        } catch (RuntimeException exception) {
            SkillTreeMod.LOGGER.warn("Couldn't load skill bonus {}, preserving skill with a broken bonus", serializerId, exception);
            return new BrokenSkillBonus("Couldn't load skill bonus " + serializerId + ": " + exception.getMessage());
        }
    }

    @Override
    public JsonElement serialize(SkillBonus<?> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        ResourceLocation serializerId = PSTRegistries.SKILL_BONUSES.get().getKey(src.getSerializer());
        Objects.requireNonNull(serializerId);
        json.addProperty("type", serializerId.toString());
        src.getSerializer().serialize(json, src);
        return json;
    }
}
