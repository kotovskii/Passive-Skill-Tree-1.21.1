package daripher.skilltree.data.reloader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import daripher.skilltree.SkillTreeMod;
import daripher.skilltree.network.NetworkHelper;
import daripher.skilltree.skill.PassiveSkill;
import daripher.skilltree.skill.PassiveSkillTree;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@EventBusSubscriber(modid = SkillTreeMod.MOD_ID)
public class SkillTreesReloader extends SimpleJsonResourceReloadListener {
    public static final Gson GSON = new GsonBuilder().registerTypeAdapter(ResourceLocation.class, new ResourceLocation.Serializer())
            .setPrettyPrinting().create();
    private static final Map<ResourceLocation, PassiveSkillTree> SKILL_TREES = new HashMap<>();

    public SkillTreesReloader() {
        super(GSON, "skill_trees");
    }

    @SubscribeEvent
    public static void addReloadListener(AddReloadListenerEvent event) {
        event.addListener(new SkillTreesReloader());
    }

    public static Map<ResourceLocation, PassiveSkillTree> getSkillTrees() {
        return SKILL_TREES;
    }

    public static PassiveSkillTree getSkillTreeById(ResourceLocation id) {
        return SKILL_TREES.getOrDefault(id, new PassiveSkillTree(id));
    }

    public static @Nullable ResourceLocation getDefaultSkillTreeId() {
        return getSkillTrees().keySet().stream().findAny().orElse(null);
    }

    public static void loadFromByteBuf(FriendlyByteBuf buf) {
        loadFromNetwork(NetworkHelper.readPassiveSkillTrees(buf));
    }

    public static void loadFromNetwork(Collection<PassiveSkillTree> skillTrees) {
        SKILL_TREES.clear();
        skillTrees.forEach(t -> SKILL_TREES.put(t.getId(), t));
        pruneUnavailableSkills();
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> map, @NotNull ResourceManager resourceManager, @NotNull ProfilerFiller profilerFiller) {
        SKILL_TREES.clear();
        map.forEach(this::readSkillTree);
        pruneUnavailableSkills();
    }

    protected void readSkillTree(ResourceLocation id, JsonElement json) {
        try {
            PassiveSkillTree tree = GSON.fromJson(json, PassiveSkillTree.class);
            SKILL_TREES.put(tree.getId(), tree);
        } catch (Exception exception) {
            String errorMessage = "Couldn't load passive skill tree: " + id;
            SkillTreeMod.LOGGER.error(errorMessage, exception);
        }
    }

    public static void pruneUnavailableSkills() {
        Set<ResourceLocation> availableSkillIds = SkillsReloader.getSkills().keySet();
        if (availableSkillIds.isEmpty()) {
            return;
        }
        SKILL_TREES.values().forEach(tree -> pruneUnavailableTreeSkills(tree, availableSkillIds));
        SkillsReloader.getSkills().values().forEach(skill -> pruneUnavailableConnections(skill, availableSkillIds));
    }

    private static void pruneUnavailableTreeSkills(PassiveSkillTree tree, Set<ResourceLocation> availableSkillIds) {
        List<ResourceLocation> removedSkillIds = removeUnavailableIds(tree.getSkillIds(), availableSkillIds);
        if (!removedSkillIds.isEmpty()) {
            SkillTreeMod.LOGGER.warn("Pruned {} unavailable skills from skill tree {}: {}", removedSkillIds.size(), tree.getId(), removedSkillIds);
        }
    }

    private static void pruneUnavailableConnections(PassiveSkill skill, Set<ResourceLocation> availableSkillIds) {
        pruneUnavailableConnections(skill, "direct", skill.getDirectConnections(), availableSkillIds);
        pruneUnavailableConnections(skill, "long", skill.getLongConnections(), availableSkillIds);
        pruneUnavailableConnections(skill, "one-way", skill.getOneWayConnections(), availableSkillIds);
    }

    private static void pruneUnavailableConnections(PassiveSkill skill, String type, List<ResourceLocation> connections, Set<ResourceLocation> availableSkillIds) {
        List<ResourceLocation> removedSkillIds = removeUnavailableIds(connections, availableSkillIds);
        if (!removedSkillIds.isEmpty()) {
            SkillTreeMod.LOGGER.warn("Pruned {} unavailable {} connections from skill {}: {}", removedSkillIds.size(), type, skill.getId(), removedSkillIds);
        }
    }

    private static List<ResourceLocation> removeUnavailableIds(List<ResourceLocation> ids, Set<ResourceLocation> availableSkillIds) {
        List<ResourceLocation> removedSkillIds = new ArrayList<>();
        if (ids == null) {
            return removedSkillIds;
        }
        ids.removeIf(skillId -> {
            boolean unavailable = !availableSkillIds.contains(skillId);
            if (unavailable) {
                removedSkillIds.add(skillId);
            }
            return unavailable;
        });
        return removedSkillIds;
    }
}
