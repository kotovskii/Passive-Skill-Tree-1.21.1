package daripher.skilltree.skill.bonus.handler;

import net.neoforged.fml.common.EventBusSubscriber;

import daripher.skilltree.SkillTreeMod;
import daripher.skilltree.skill.SkillBonusProvider;
import daripher.skilltree.skill.bonus.player.DamageConversionBonus;
import daripher.skilltree.skill.bonus.predicate.damage.DamageCondition;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EventBusSubscriber(modid = SkillTreeMod.MOD_ID)
public class DamageConversionBonusHandler {
    // recursion protection
    private static boolean isProcessingConversion;

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void dealConvertedDamage(LivingDamageEvent.Pre event) {
        if (isProcessingConversion) {
            return;
        }
        DamageSource originalDamageSource = event.getSource();
        if (!(originalDamageSource.getEntity() instanceof Player player)) {
            return;
        }
        List<DamageConversionBonus> skillBonuses = SkillBonusProvider.getSkillBonuses(player, DamageConversionBonus.class);
        if (skillBonuses.isEmpty()) {
            return;
        }
        LivingEntity target = event.getEntity();
        float originalDamageAmount = event.getNewDamage();
        float totalConvertedPercentage = 0f;
        Map<DamageCondition, Float> convertedDamageMap = new HashMap<>();
        for (DamageConversionBonus skillBonus : skillBonuses) {
            if (!skillBonus.canConvertDamage(originalDamageSource)) {
                continue;
            }
            DamageCondition resultDamageSource = skillBonus.getResultDamageCondition();
            float conversionRate = skillBonus.getConversionRate(originalDamageSource, player, target);
            convertedDamageMap.put(resultDamageSource, convertedDamageMap.getOrDefault(resultDamageSource, 0f) + conversionRate);
            totalConvertedPercentage += conversionRate;
        }
        if (convertedDamageMap.isEmpty()) {
            return;
        }
        try {
            isProcessingConversion = true;
            convertedDamageMap.forEach(((damageCondition, conversionPercentage) -> {
                DamageSource newDamageSource = damageCondition.createDamageSource(player);
                float damagePayload = conversionPercentage * originalDamageAmount;
                SkillBonusHandlerUtils.hurtIgnoringInvulnerabilityTime(target, newDamageSource, damagePayload);
            }));
        } finally {
            isProcessingConversion = false;
        }
        event.setNewDamage(originalDamageAmount * (1 - totalConvertedPercentage));
    }
}
