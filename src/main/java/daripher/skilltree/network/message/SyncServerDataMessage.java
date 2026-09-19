package daripher.skilltree.network.message;

import daripher.skilltree.data.reloader.SkillTreesReloader;
import daripher.skilltree.data.reloader.SkillsReloader;
import daripher.skilltree.SkillTreeMod;
import daripher.skilltree.network.NetworkHelper;
import daripher.skilltree.skill.PassiveSkill;
import daripher.skilltree.skill.PassiveSkillTree;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.ArrayList;
import java.util.List;

public class SyncServerDataMessage implements CustomPacketPayload {
    public static final Type<SyncServerDataMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath("skilltree", "sync_server_data"));
    public static final StreamCodec<RegistryFriendlyByteBuf, SyncServerDataMessage> STREAM_CODEC = StreamCodec.of((buf, msg) -> msg.encode(buf), SyncServerDataMessage::decode);
    private final List<PassiveSkill> skills = new ArrayList<>();
    private final List<PassiveSkillTree> skillTrees = new ArrayList<>();

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static SyncServerDataMessage decode(FriendlyByteBuf buf) {
        SyncServerDataMessage message = new SyncServerDataMessage();
        message.skills.addAll(NetworkHelper.readPassiveSkills(buf));
        message.skillTrees.addAll(NetworkHelper.readPassiveSkillTrees(buf));
        return message;
    }

    public static void receive(SyncServerDataMessage message, IPayloadContext context) {
        context.enqueueWork(() -> {
            SkillsReloader.loadFromNetwork(message.skills);
            SkillTreesReloader.loadFromNetwork(message.skillTrees);
            SkillTreeMod.LOGGER.info("Received server skill tree data: {} skills, {} skill trees", message.skills.size(), message.skillTrees.size());
        });
    }

    public void encode(FriendlyByteBuf buf) {
        NetworkHelper.writePassiveSkills(buf, SkillsReloader.getSkills().values());
        NetworkHelper.writePassiveSkillTrees(buf, SkillTreesReloader.getSkillTrees().values());
    }
}
