package daripher.skilltree.network.message;

import daripher.skilltree.capability.skill.IPlayerSkills;
import daripher.skilltree.capability.skill.PlayerSkillsProvider;
import daripher.skilltree.data.reloader.SkillsReloader;
import daripher.skilltree.skill.PassiveSkill;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class LearnSkillMessage implements CustomPacketPayload {
    public static final Type<LearnSkillMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath("skilltree", "learn_skill"));
    public static final StreamCodec<RegistryFriendlyByteBuf, LearnSkillMessage> STREAM_CODEC = StreamCodec.of((buf, msg) -> msg.encode(buf), LearnSkillMessage::decode);
    private ResourceLocation skillId;

    public LearnSkillMessage(PassiveSkill passiveSkill) {
        skillId = passiveSkill.getId();
    }

    private LearnSkillMessage() {
    }

    public static LearnSkillMessage decode(FriendlyByteBuf buf) {
        LearnSkillMessage message = new LearnSkillMessage();
        message.skillId = ResourceLocation.parse(buf.readUtf());
        return message;
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void receive(LearnSkillMessage message, IPayloadContext context) {
        if (!(context.player() instanceof ServerPlayer player)) {
            return;
        }
        context.enqueueWork(() -> handlePacket(message, player));
    }

    private static void handlePacket(LearnSkillMessage message, ServerPlayer player) {
        IPlayerSkills capability = PlayerSkillsProvider.get(player);
        PassiveSkill skill = SkillsReloader.getSkillById(message.skillId);
        if (skill == null) {
            return;
        }
        if (capability.learnSkill(skill)) {
            skill.learn(player, true);
        }
        PacketDistributor.sendToPlayer(player, new SyncPlayerSkillsMessage(player));
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeUtf(skillId.toString());
    }
}
