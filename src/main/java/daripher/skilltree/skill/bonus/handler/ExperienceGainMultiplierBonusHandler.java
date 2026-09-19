package daripher.skilltree.skill.bonus.handler;

import net.neoforged.fml.common.EventBusSubscriber;

import daripher.skilltree.SkillTreeMod;
import daripher.skilltree.skill.SkillBonusProvider;
import daripher.skilltree.skill.bonus.player.ExperienceGainMultiplierBonus;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.living.LivingExperienceDropEvent;
import net.neoforged.neoforge.event.entity.player.ItemFishedEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;

import java.util.List;

@EventBusSubscriber(modid = SkillTreeMod.MOD_ID)
public class ExperienceGainMultiplierBonusHandler {
    @SubscribeEvent
    public static void applyMobExpBonus(LivingExperienceDropEvent event) {
        Player player = event.getAttackingPlayer();
        if (player == null) {
            return;
        }
        float multiplier = 1f;
        multiplier += getExperienceMultiplierBonus(player, ExperienceGainMultiplierBonus.ExperienceSource.MOBS);
        event.setDroppedExperience((int) (event.getDroppedExperience() * multiplier));
    }

    @SubscribeEvent
    public static void applyOreMiningExpBonus(BlockEvent.BreakEvent event) {
        if (!event.getState().is(Tags.Blocks.ORES)) {
            return;
        }
        // NeoForge 1.21.1 no longer exposes mutable block XP on BreakEvent.
        // Reintroduce this through a loot/XP hook during the gameplay pass.
        getExperienceMultiplierBonus(event.getPlayer(), ExperienceGainMultiplierBonus.ExperienceSource.ORE);
    }

    @SubscribeEvent
    public static void applyFishingExpBonus(ItemFishedEvent event) {
        Player player = event.getEntity();
        if (player.level().isClientSide) {
            return;
        }
        float multiplier = getExperienceMultiplierBonus(player, ExperienceGainMultiplierBonus.ExperienceSource.FISHING);
        if (multiplier == 0) {
            return;
        }
        int baseExp = player.getRandom().nextInt(6) + 1;
        int extraExp = (int) (baseExp * multiplier);
        if (extraExp == 0) {
            return;
        }
        ExperienceOrb expOrb = new ExperienceOrb(player.level(), player.getX(), player.getY() + 0.5D, player.getZ() + 0.5D, extraExp);
        player.level().addFreshEntity(expOrb);
    }

    private static float getExperienceMultiplierBonus(Player player, ExperienceGainMultiplierBonus.ExperienceSource source) {
        float multiplier = 0f;
        List<ExperienceGainMultiplierBonus> skillBonuses = SkillBonusProvider.getSkillBonuses(player, ExperienceGainMultiplierBonus.class);
        for (ExperienceGainMultiplierBonus bonus : skillBonuses) {
            if (bonus.getSource() == source) {
                multiplier += bonus.getMultiplier();
            }
        }
        return multiplier;
    }
}
