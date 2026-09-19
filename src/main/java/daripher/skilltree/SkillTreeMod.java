package daripher.skilltree;

import daripher.skilltree.compat.attributeslib.AttributesLibCompatibility;
import daripher.skilltree.compat.curios.CuriosCompatibility;
import daripher.skilltree.compat.ironsspellbooks.IronsSpellbooksCompat;
import daripher.skilltree.config.ClientConfig;
import daripher.skilltree.config.ServerConfig;
import daripher.skilltree.init.*;
import daripher.skilltree.init.predicate.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.loading.FMLEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(SkillTreeMod.MOD_ID)
public class SkillTreeMod {
    public static final String MOD_ID = "skilltree";
    public static final Logger LOGGER = LogManager.getLogger(SkillTreeMod.MOD_ID);

    public SkillTreeMod(IEventBus eventBus, ModContainer modContainer) {
        registerModRegistries(eventBus);
        registerConfigs(modContainer);
        registerCompatibilities();
    }

    private static void registerModRegistries(IEventBus eventBus) {
        PSTRegistries.bootstrap();
        PSTItems.REGISTRY.register(eventBus);
        PSTMobEffects.REGISTRY.register(eventBus);
        PSTCreativeTabs.REGISTRY.register(eventBus);
        PSTSkillBonuses.REGISTRY.register(eventBus);
        PSTLivingEntityPredicates.REGISTRY.register(eventBus);
        PSTLivingMultipliers.REGISTRY.register(eventBus);
        PSTDamagePredicates.REGISTRY.register(eventBus);
        PSTItemPredicates.REGISTRY.register(eventBus);
        PSTEnchantmentPredicates.REGISTRY.register(eventBus);
        PSTEventListeners.REGISTRY.register(eventBus);
        PSTLootModifiers.REGISTRY.register(eventBus);
        PSTFloatFunctions.REGISTRY.register(eventBus);
        PSTPotions.REGISTRY.register(eventBus);
        PSTSkillRequirements.REGISTRY.register(eventBus);
        PSTBlocks.REGISTRY.register(eventBus);
        PSTMenuTypes.REGISTRY.register(eventBus);
        PSTRecipeSerializers.REGISTRY.register(eventBus);
        PSTItemBonuses.REGISTRY.register(eventBus);
        PSTRecipeTypes.REGISTRY.register(eventBus);
        PSTMobEffectPredicates.REGISTRY.register(eventBus);
        daripher.skilltree.capability.skill.PlayerSkillsProvider.REGISTRY.register(eventBus);
    }

    private static void registerConfigs(ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.SERVER, ServerConfig.SPEC);
        modContainer.registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC);
    }

    private static void registerCompatibilities() {
        if (FMLEnvironment.dist == Dist.CLIENT && ModList.get().isLoaded("apothic_attributes")) {
            AttributesLibCompatibility.INSTANCE.register();
        }
        if (ModList.get().isLoaded("curios")) {
            CuriosCompatibility.INSTANCE.register();
        }
        if (ModList.get().isLoaded("irons_spellbooks")) {
            IronsSpellbooksCompat.INSTANCE.register();
        }
    }
}
