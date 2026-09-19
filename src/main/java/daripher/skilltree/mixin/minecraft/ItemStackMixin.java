package daripher.skilltree.mixin.minecraft;

import daripher.skilltree.skill.bonus.handler.ItemDurabilityLossPreventionBonusHandler;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Inject(method = "hurtAndBreak(ILnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Consumer;)V", at = @At("HEAD"), cancellable = true, remap = false)
    public void preventDurabilityLoss(int amount, ServerLevel level, LivingEntity entity, Consumer<Item> onBroken, CallbackInfo callbackInfo) {
        if (!(entity instanceof Player player)) {
            return;
        }
        @SuppressWarnings("DataFlowIssue") ItemStack itemStack = (ItemStack) (Object) this;
        if (ItemDurabilityLossPreventionBonusHandler.shouldPreventItemDurabilityLoss(player, itemStack, entity.getRandom())) {
            callbackInfo.cancel();
        }
    }
}
