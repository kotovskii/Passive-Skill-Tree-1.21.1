package daripher.skilltree.capability.skill;

import daripher.skilltree.SkillTreeMod;
import daripher.skilltree.network.message.SyncPlayerSkillsMessage;
import daripher.skilltree.network.message.SyncServerDataMessage;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerChangedDimensionEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerRespawnEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.NotNull;

@EventBusSubscriber(modid = SkillTreeMod.MOD_ID)
public class PlayerSkillsProvider {
    public static final DeferredRegister<AttachmentType<?>> REGISTRY = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, SkillTreeMod.MOD_ID);
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<PlayerSkills>> PLAYER_SKILLS =
            REGISTRY.register("player_skills", () -> AttachmentType.serializable(PlayerSkills::new).copyOnDeath().build());

    @SubscribeEvent
    public static void syncSkills(PlayerLoggedInEvent event) {
        if (event.getEntity().level().isClientSide) {
            return;
        }
        syncServerDataAndPlayerSkills((ServerPlayer) event.getEntity());
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void restoreSkillsAttributeModifiers(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }
        get(player).getPlayerSkills().forEach(skill -> skill.learn(player, false));
    }

    @SubscribeEvent
    public static void sendTreeResetMessage(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }
        if (event.getEntity().level().isClientSide) {
            return;
        }
        IPlayerSkills capability = get(player);
        if (capability.isTreeReset()) {
            player.sendSystemMessage(Component.translatable("skilltree.message.reset").withStyle(ChatFormatting.YELLOW));
            capability.setTreeReset(false);
        }
    }

    @SubscribeEvent
    public static void syncPlayerSkills(PlayerRespawnEvent event) {
        if (event.getEntity().level().isClientSide) {
            return;
        }
        syncPlayerSkills((ServerPlayer) event.getEntity());
    }

    @SubscribeEvent
    public static void syncPlayerSkills(PlayerChangedDimensionEvent event) {
        if (event.getEntity().level().isClientSide) {
            return;
        }
        syncPlayerSkills((ServerPlayer) event.getEntity());
    }

    private static void syncServerDataAndPlayerSkills(ServerPlayer player) {
        PacketDistributor.sendToPlayer(player, new SyncServerDataMessage());
        syncPlayerSkills(player);
    }

    private static void syncPlayerSkills(ServerPlayer player) {
        PacketDistributor.sendToPlayer(player, new SyncPlayerSkillsMessage(player));
    }

    public static @NotNull IPlayerSkills get(Player player) {
        return player.getData(PLAYER_SKILLS.get());
    }

    public static boolean hasSkills(@NotNull Player player) {
        return player.hasData(PLAYER_SKILLS.get());
    }
}
