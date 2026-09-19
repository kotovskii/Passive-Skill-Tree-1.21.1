package daripher.skilltree.init;

import net.neoforged.fml.common.EventBusSubscriber;

import daripher.skilltree.SkillTreeMod;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;

@EventBusSubscriber(modid = SkillTreeMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class PSTBrewingRecipes {
    @SubscribeEvent
    public static void addRecipes(RegisterBrewingRecipesEvent event) {
        event.getBuilder().addMix(Potions.FIRE_RESISTANCE, Items.FERMENTED_SPIDER_EYE, PSTPotions.LIQUID_FIRE_1);
        event.getBuilder().addMix(PSTPotions.LIQUID_FIRE_1, Items.GLOWSTONE_DUST, PSTPotions.LIQUID_FIRE_2);
    }
}
