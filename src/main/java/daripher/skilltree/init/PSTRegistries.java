package daripher.skilltree.init;

import daripher.skilltree.init.predicate.PSTDamagePredicates;
import daripher.skilltree.init.predicate.PSTEnchantmentPredicates;
import daripher.skilltree.init.predicate.PSTItemPredicates;
import daripher.skilltree.init.predicate.PSTLivingEntityPredicates;
import daripher.skilltree.init.predicate.PSTMobEffectPredicates;
import daripher.skilltree.skill.bonus.SkillBonus;
import daripher.skilltree.skill.bonus.event.SkillEventListener;
import daripher.skilltree.skill.bonus.function.FloatFunction;
import daripher.skilltree.skill.bonus.item.ItemBonus;
import daripher.skilltree.skill.bonus.multiplier.LivingMultiplier;
import daripher.skilltree.skill.bonus.predicate.damage.DamageCondition;
import daripher.skilltree.skill.bonus.predicate.effect.MobEffectPredicate;
import daripher.skilltree.skill.bonus.predicate.enchantment.EnchantmentCondition;
import daripher.skilltree.skill.bonus.predicate.item.ItemStackPredicate;
import daripher.skilltree.skill.bonus.predicate.living.LivingEntityPredicate;
import daripher.skilltree.skill.requirement.SkillRequirement;
import net.minecraft.core.Registry;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

public class PSTRegistries {
    public static final Supplier<Registry<SkillBonus.Serializer>> SKILL_BONUSES = makeRegistry(PSTSkillBonuses.REGISTRY);
    public static final Supplier<Registry<LivingMultiplier.Serializer>> LIVING_MULTIPLIERS = makeRegistry(PSTLivingMultipliers.REGISTRY);
    public static final Supplier<Registry<LivingEntityPredicate.Serializer>> LIVING_CONDITIONS = makeRegistry(PSTLivingEntityPredicates.REGISTRY);
    public static final Supplier<Registry<DamageCondition.Serializer>> DAMAGE_CONDITIONS = makeRegistry(PSTDamagePredicates.REGISTRY);
    public static final Supplier<Registry<ItemStackPredicate.Serializer>> ITEM_CONDITIONS = makeRegistry(PSTItemPredicates.REGISTRY);
    public static final Supplier<Registry<EnchantmentCondition.Serializer>> ENCHANTMENT_CONDITIONS = makeRegistry(PSTEnchantmentPredicates.REGISTRY);
    public static final Supplier<Registry<SkillEventListener.Serializer>> EVENT_LISTENERS = makeRegistry(PSTEventListeners.REGISTRY);
    public static final Supplier<Registry<FloatFunction.Serializer>> FLOAT_FUNCTIONS = makeRegistry(PSTFloatFunctions.REGISTRY);
    public static final Supplier<Registry<SkillRequirement.Serializer>> SKILL_REQUIREMENTS = makeRegistry(PSTSkillRequirements.REGISTRY);
    public static final Supplier<Registry<ItemBonus.Serializer>> ITEM_BONUSES = makeRegistry(PSTItemBonuses.REGISTRY);
    public static final Supplier<Registry<MobEffectPredicate.Serializer>> MOB_EFFECT_PREDICATES = makeRegistry(PSTMobEffectPredicates.REGISTRY);

    public static void bootstrap() {
    }

    private static <T> Supplier<Registry<T>> makeRegistry(DeferredRegister<T> deferredRegister) {
        Registry<T> registry = deferredRegister.makeRegistry(builder -> {
        });
        return () -> registry;
    }
}
