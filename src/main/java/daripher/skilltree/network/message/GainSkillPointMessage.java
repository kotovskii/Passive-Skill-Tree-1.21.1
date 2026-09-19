package daripher.skilltree.network.message;

import daripher.skilltree.capability.skill.IPlayerSkills;
import daripher.skilltree.capability.skill.PlayerSkillsProvider;
import daripher.skilltree.config.ServerConfig;
import daripher.skilltree.exp.ExpHelper;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class GainSkillPointMessage implements CustomPacketPayload {
    public static final Type<GainSkillPointMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath("skilltree", "gain_skill_point"));
    public static final StreamCodec<RegistryFriendlyByteBuf, GainSkillPointMessage> STREAM_CODEC = StreamCodec.of((buf, msg) -> {
    }, buf -> new GainSkillPointMessage());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void receive(GainSkillPointMessage message, IPayloadContext context) {
        if (!(context.player() instanceof ServerPlayer player)) {
            return;
        }
        context.enqueueWork(() -> handlePacket(player));
    }

    private static void handlePacket(ServerPlayer player) {
        IPlayerSkills capability = PlayerSkillsProvider.get(player);
        int skills = capability.getPlayerSkills().size();
        int points = capability.getSkillPoints();
        int level = skills + points;
        if (level >= ServerConfig.max_skill_points) {
            return;
        }
        int cost = ServerConfig.getSkillPointCost(level);
        if (ExpHelper.getPlayerExp(player) < cost) {
            return;
        }
        player.giveExperiencePoints(-cost);
        capability.grantSkillPoints(1);
        PacketDistributor.sendToPlayer(player, new SyncPlayerSkillsMessage(player));
    }
}
