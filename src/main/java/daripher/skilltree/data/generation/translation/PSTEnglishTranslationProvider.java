package daripher.skilltree.data.generation.translation;

import daripher.skilltree.SkillTreeMod;
import daripher.skilltree.init.*;
import daripher.skilltree.init.predicate.PSTDamagePredicates;
import daripher.skilltree.init.predicate.PSTEnchantmentPredicates;
import daripher.skilltree.init.predicate.PSTItemPredicates;
import daripher.skilltree.init.predicate.PSTLivingEntityPredicates;
import daripher.skilltree.skill.bonus.player.ExperienceGainMultiplierBonus;
import daripher.skilltree.skill.bonus.player.LootAmountModifierBonus;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;

public class PSTEnglishTranslationProvider extends PSTTranslationProvider {
    public PSTEnglishTranslationProvider(DataGenerator gen) {
        super(gen, SkillTreeMod.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // skill trees
        add("skilltree:hunter", "Hunter");
        add("skilltree:alchemist", "Alchemist");
        add("skilltree:cook", "Cook");
        // skills
        addSkill("alchemist", 1, "Alchemist");
        addSkills("alchemist", 2, 26, 29, "Poison Chance");
        addSkill("alchemist", 32, "Immunity Bypass");
        addSkill("alchemist", 49, "Weakness Chance");
        addSkill("alchemist", 52, "Wither Chance");
        addSkill("alchemist", 55, "Nausea Chance");
        addSkill("alchemist", 58, "Spreading Plague");
        addSkills("alchemist", 5, 8, 11, "Harmful Effects Duration");
        addSkill("alchemist", 14, "Deep Infection");
        addSkills("alchemist", 22, 25, 34, "Slowness Chance");
        addSkill("alchemist", 37, "Cruelty");
        addSkills("alchemist", 38, 41, 44, "Poison Damage");
        addSkill("alchemist", 60, "Melting Flesh");
        addSkills("alchemist", 3, 27, 30, "Beneficial Effects Duration");
        addSkill("alchemist", 33, "Overdose");
        addSkills("alchemist", 47, 50, 53, "Potion Healing");
        addSkill("alchemist", 56, "Limb Regeneration");
        addSkills("alchemist", 6, 9, 12, "Incoming Healing");
        addSkill("alchemist", 15, "Immune Response");
        addSkills("alchemist", 20, 23, 28, "Maximum Health");
        addSkill("alchemist", 35, "Mutation");
        addSkills("alchemist", 39, 42, 45, "Movement Speed");
        addSkill("alchemist", 61, "Adrenalin");
        addSkills("alchemist", 4, 17, 18, "Projectile Damage");
        addSkill("alchemist", 19, "More Gunpowder!");
        addSkills("alchemist", 48, 51, 54, "Additional Projectile Chance");
        addSkill("alchemist", 57, "Double Flasks");
        addSkills("alchemist", 7, 10, 13, "Magic Damage");
        addSkill("alchemist", 16, "Antimagic Field");
        addSkills("alchemist", 21, 24, 31, "Projectile Speed");
        addSkill("alchemist", 36, "Powerful Throw");
        addSkills("alchemist", 40, 43, 46, "Fire Damage");
        addSkill("alchemist", 59, "Incineration");

        addSkill("cook", 1, "Cook");
        addSkills("cook", 2, 26, 29, "Unarmed Damage");
        addSkill("cook", 32, "Heavy Punch");
        addSkills("cook", 49, 52, 55, "Unarmed Attack Speed");
        addSkill("cook", 58, "Iron Fist");
        addSkills("cook", 5, 8, 11, "Attack Damage");
        addSkill("cook", 14, "Spicy!");
        addSkills("cook", 22, 25, 34, "Melee Damage");
        addSkill("cook", 37, "Healthy Body");
        addSkills("cook", 38, 41, 44, "Unarmed Critical Hit Chance");
        addSkill("cook", 60, "Living Weapon");
        addSkills("cook", 4, 17, 18, "Maximum Health");
        addSkill("cook", 19, "Healthy Diet");
        addSkills("cook", 48, 51, 54, "Food Healing");
        addSkill("cook", 57, "Pile of Muscles");
        addSkills("cook", 7, 10, 13, "Incoming Healing");
        addSkill("cook", 16, "Accelerated Metabolism");
        addSkills("cook", 21, 24, 31, "Healing on Hit");
        addSkill("cook", 36, "Lifesteal");
        addSkills("cook", 40, 43, 46, "Saturation Chance");
        addSkill("cook", 59, "Comfort");
        addSkills("cook", 3, 27, 30, "Movement Speed While Eating");
        addSkill("cook", 33, "Snack");
        addSkills("cook", 47, 50, 53, "Food Usage Speed");
        addSkill("cook", 56, "Ambidexterity");
        addSkills("cook", 6, 9, 12, "Double Fishing Loot Chance");
        addSkill("cook", 15, "Sea Blessing");
        addSkills("cook", 20, 23, 28, "Experience From Fishing");
        addSkill("cook", 35, "Experienced Fisherman");
        addSkills("cook", 39, 42, 45, "Damage Taken While Fishing");
        addSkill("cook", 61, "Unseen Fisherman");

        addSkill("hunter", 1, "Hunter");
        addSkills("hunter", 2, 26, 29, "Leather Armor Durability");
        addSkill("hunter", 32, "Armor Fitting");
        addSkills("hunter", 49, 52, 55, "Damage Avoidance");
        addSkill("hunter", 58, "Acrobatics");
        addSkills("hunter", 5, 8, 11, "Armor");
        addSkill("hunter", 14, "Tanned Leather");
        addSkills("hunter", 22, 25, 34, "Movement Speed");
        addSkill("hunter", 37, "Elusiveness");
        addSkills("hunter", 38, 41, 44, "Projectile Damage Avoidance");
        addSkill("hunter", 60, "Double Layer Armor");
        addSkills("hunter", 4, 17, 18, "Projectile Speed");
        addSkill("hunter", 19, "Penetrating Shot");
        addSkills("hunter", 48, 51, 54, "Bow Shooting Speed");
        addSkill("hunter", 57, "Rapid Shot");
        addSkills("hunter", 7, 10, 13, "Movement Speed While Shooting");
        addSkill("hunter", 16, "Trained Archer");
        addSkills("hunter", 21, 24, 31, "Critical Projectile Damage");
        addSkill("hunter", 36, "Heavy Arrows");
        addSkills("hunter", 40, 43, 46, "Projectile Damage");
        addSkill("hunter", 59, "Ballistic Effect");
        addSkills("hunter", 3, 27, 30, "Mobs Loot Duplication");
        addSkill("hunter", 33, "Hunter's Trophy");
        addSkills("hunter", 47, 50, 53, "Chest Loot Duplication");
        addSkill("hunter", 56, "Treasure Hunt");
        addSkills("hunter", 6, 9, 12, "Arrow Retrieval Chance");
        addSkill("hunter", 15, "Careful Shot");
        addSkills("hunter", 20, 23, 28, "Experience From Mobs");
        addSkill("hunter", 35, "Experienced Hunter");
        addSkills("hunter", 39, 42, 45, "Luck");
        addSkill("hunter", 61, "Lucky Beggar");
        // skill bonuses
        add(PSTSkillBonuses.DAMAGE, "Damage");
        add(PSTSkillBonuses.CRIT_DAMAGE, "Critical Damage Multiplier");
        add(PSTSkillBonuses.CRIT_DAMAGE, "damage", "Critical %s Damage Multiplier");
        add(PSTSkillBonuses.CRIT_CHANCE, "Critical Hit Chance");
        add(PSTSkillBonuses.CRIT_CHANCE, "damage", "%s Critical Hit Chance");
        add(PSTSkillBonuses.BLOCK_BREAK_SPEED, "Block break speed");
        add(PSTSkillBonuses.REPAIR_EFFICIENCY, "Repaired %s: %s");
        add(PSTSkillBonuses.REPAIR_EFFICIENCY, "bonus", "Durability restored");
        add(PSTSkillBonuses.FREE_ENCHANTMENT, "chance", "Chance to enchant %s for free");
        add(PSTSkillBonuses.FREE_ENCHANTMENT, "Enchant %s for free");
        add(PSTSkillBonuses.JUMP_HEIGHT, "Jump Height");
        add(PSTSkillBonuses.INCOMING_HEALING, "Incoming Healing");
        add(PSTSkillBonuses.LOOT_DUPLICATION, "Chance to get %s %s");
        add(PSTSkillBonuses.LOOT_DUPLICATION, "multiplier", "+%s%%");
        add(PSTSkillBonuses.LOOT_DUPLICATION, "guaranteed", "You always get %s %s");
        add(PSTSkillBonuses.LOOT_DUPLICATION, "guaranteed.none", "You get no %s");
        add(PSTSkillBonuses.LOOT_DUPLICATION, "double", "double");
        add(PSTSkillBonuses.LOOT_DUPLICATION, "triple", "triple");
        add(PSTSkillBonuses.LOOT_DUPLICATION, "none", "no");
        add(PSTSkillBonuses.GAINED_EXPERIENCE, "Experience from %s");
        add(PSTSkillBonuses.INFLICT_IGNITE, "player", "You catch fire for %s");
        add(PSTSkillBonuses.INFLICT_IGNITE, "player.chance", "Chance to catch fire for %s");
        add(PSTSkillBonuses.INFLICT_IGNITE, "enemy", "Set enemies on fire for %s");
        add(PSTSkillBonuses.INFLICT_IGNITE, "enemy.chance", "Chance to set enemies on fire for %s");
        add(PSTSkillBonuses.ARROW_RETRIEVAL, "Arrow retrieval chance");
        add(PSTSkillBonuses.HEALTH_RESERVATION, "Health Reservation");
        add(PSTSkillBonuses.ALL_ATTRIBUTES, "All Attributes");
        add(PSTSkillBonuses.INFLICT_EFFECT, "player", "Gain %s%s");
        add(PSTSkillBonuses.INFLICT_EFFECT, "player.chance", "Chance to gain %s%s");
        add(PSTSkillBonuses.INFLICT_EFFECT, "enemy", "Inflict %s%s");
        add(PSTSkillBonuses.INFLICT_EFFECT, "enemy.chance", "Chance to inflict %s%s");
        add(PSTSkillBonuses.INFLICT_EFFECT, "seconds", " for %s seconds");
        add(PSTSkillBonuses.INFLICT_EFFECT, "minutes", " for %s minutes");
        add(PSTSkillBonuses.INFLICT_EFFECT, "stacks", "%s, stacks up to %s times");
        add(PSTSkillBonuses.CANT_USE_ITEM, "Can not use %s");
        add(PSTSkillBonuses.HEALING, "player", "Recover %s life");
        add(PSTSkillBonuses.HEALING, "player.chance", "Chance to recover %s life");
        add(PSTSkillBonuses.HEALING, "enemy", "Enemies recover %s life");
        add(PSTSkillBonuses.HEALING, "enemy.chance", "Chance for enemies to recover %s life");
        add(PSTSkillBonuses.INFLICT_DAMAGE, "player", "+%s %s taken");
        add(PSTSkillBonuses.INFLICT_DAMAGE, "player.chance", "Chance to take %s %s");
        add(PSTSkillBonuses.INFLICT_DAMAGE, "enemy", "+%s %s Damage inflicted");
        add(PSTSkillBonuses.INFLICT_DAMAGE, "enemy.chance", "Chance to inflict %s %s Damage");
        add(PSTSkillBonuses.IGNORE_EFFECT_IMMUNITY, "You can apply %s ignoring immunity");
        add(PSTSkillBonuses.LETHAL_POISON, "Your poisons are lethal");
        add(PSTSkillBonuses.DAMAGE_TAKEN, "%s taken");
        add(PSTSkillBonuses.DAMAGE_AVOIDANCE, "Chance to avoid %s");
        add(PSTSkillBonuses.DAMAGE_CONVERSION, "%s%% of %s is being converted to %s");
        add(PSTSkillBonuses.GRANT_ITEM, "Grants %s when learned");
        add(PSTSkillBonuses.GRANT_ITEM, "amount", "Grants %sx %s when learned");
        add(PSTSkillBonuses.EFFECT_DURATION, "player", "Duration of %s on you");
        add(PSTSkillBonuses.EFFECT_DURATION, "enemy", "Duration of inflicted %s");
        add(PSTSkillBonuses.PROJECTILE_DUPLICATION, "chance", "Chance to fire additional projectile");
        add(PSTSkillBonuses.PROJECTILE_DUPLICATION, "amount", "Fire %s additional projectiles");
        add(PSTSkillBonuses.PROJECTILE_DUPLICATION, "Fire an additional projectile");
        add(PSTSkillBonuses.SELF_SPLASH_IMMUNE, "Your splash potions do not affect you");
        add(PSTSkillBonuses.PROJECTILE_SPEED, "Projectile Speed");
        add(PSTSkillBonuses.ITEM_DURABILITY_LOSS_AVOIDANCE, "chance", "Chance to prevent %s durability loss");
        add(PSTSkillBonuses.ITEM_DURABILITY_LOSS_AVOIDANCE, "Prevent %s durability loss");
        add(PSTSkillBonuses.ITEM_USAGE_SPEED, "positive", "You use %s %s%% faster");
        add(PSTSkillBonuses.ITEM_USAGE_SPEED, "negative", "You use %s %s%% slower");
        add(PSTSkillBonuses.ITEM_USE_MOVEMENT_SPEED, "positive", "Reduces movement speed penalty from using %s by %s%%");
        add(PSTSkillBonuses.ITEM_USE_MOVEMENT_SPEED, "remove", "Removes movement speed penalty from using %s");
        add(PSTSkillBonuses.ITEM_USE_MOVEMENT_SPEED, "negative", "Increases movement speed penalty from using %s by %s%%");
        add(PSTSkillBonuses.MORE_ITEM_BONUSES, "one", "You can upgrade %s an additional time using advanced workbench");
        add(PSTSkillBonuses.MORE_ITEM_BONUSES, "You can upgrade %s %s additional times using advanced workbench");
        add(PSTSkillBonuses.RECIPE_UNLOCK, "Unlocks Recipe: %s");
        add(PSTSkillBonuses.GAIN_EXPERIENCE, "Gain %s experience");
        add(PSTSkillBonuses.GAIN_EXPERIENCE, "chance", "Chance to gain %s experience");
        add(PSTSkillBonuses.VANILLA_RECIPE_UNLOCK, "You can create %s on advanced workbench");
        add(PSTSkillBonuses.CRAFTED_ITEM_BONUS, "%s created on advanced workbench gain:");
        add(PSTSkillBonuses.CRAFTED_ITEM_BONUS, "list_item_prefix", " • ");
        add(PSTSkillBonuses.STEALTH, "You are %s%% harder to detect");
        add(PSTSkillBonuses.STEALTH, "negative", "You are %s%% easier to detect");
        add(PSTSkillBonuses.EFFECT_IMMUNITY, "You are immune to %s");
        add(PSTSkillBonuses.REMOVE_EFFECT, "player", "Dispel %s from self");
        add(PSTSkillBonuses.REMOVE_EFFECT, "player.chance", "Chance to dispel %s from self");
        add(PSTSkillBonuses.REMOVE_EFFECT, "enemy", "Dispel %s from an enemy");
        add(PSTSkillBonuses.REMOVE_EFFECT, "enemy.chance", "Chance to dispel %s from an enemy");
        add("skill_bonus.skilltree.curio_slots", "Curio Slots");
        add("skill_bonus.skilltree.grant_spell", "Grants %s level %s");
        add("skill_bonus.skilltree.spell_level", "%s level");
        // experience sources
        add(ExperienceGainMultiplierBonus.ExperienceSource.MOBS.getDescriptionId(), "Mobs");
        add(ExperienceGainMultiplierBonus.ExperienceSource.ORE.getDescriptionId(), "Ores");
        add(ExperienceGainMultiplierBonus.ExperienceSource.FISHING.getDescriptionId(), "Fishing");
        // loot conditions
        add(LootAmountModifierBonus.LootType.MOBS.getDescriptionId(), "mobs loot");
        add(LootAmountModifierBonus.LootType.FISHING.getDescriptionId(), "fishing loot");
        add(LootAmountModifierBonus.LootType.GEMS.getDescriptionId(), "gems from ore");
        add(LootAmountModifierBonus.LootType.CHESTS.getDescriptionId(), "loot in chests");
        add(LootAmountModifierBonus.LootType.ORE.getDescriptionId(), "loot from ore");
        add(LootAmountModifierBonus.LootType.ARCHAEOLOGY.getDescriptionId(), "loot from archaeology");
        // living conditions
        add(PSTLivingEntityPredicates.HAS_ITEM_EQUIPPED, "target.player", "with");
        add(PSTLivingEntityPredicates.HAS_ITEM_EQUIPPED, "target.enemy", "if enemy has");
        add(PSTLivingEntityPredicates.HAS_ITEM_EQUIPPED, "%s %s %s equipped");
        add(PSTLivingEntityPredicates.HAS_EFFECT, "target.player", "you are");
        add(PSTLivingEntityPredicates.HAS_EFFECT, "target.enemy", "enemy is");
        add(PSTLivingEntityPredicates.HAS_EFFECT, "%s if %s affected by %s");
        add(PSTLivingEntityPredicates.HAS_EFFECT, "amplifier", "%s if %s affected by %s or higher");
        add(PSTLivingEntityPredicates.BURNING, "target.player", "you are");
        add(PSTLivingEntityPredicates.BURNING, "target.enemy", "an enemy is");
        add(PSTLivingEntityPredicates.BURNING, "reverse.target.player", "you are not");
        add(PSTLivingEntityPredicates.BURNING, "reverse.target.enemy", "an enemy is not");
        add(PSTLivingEntityPredicates.BURNING, "%s if %s burning");
        add(PSTLivingEntityPredicates.FISHING, "target.player", "you are");
        add(PSTLivingEntityPredicates.FISHING, "target.enemy", "enemy is");
        add(PSTLivingEntityPredicates.FISHING, "%s if %s fishing");
        add(PSTLivingEntityPredicates.UNDERWATER, "target.player", "you are");
        add(PSTLivingEntityPredicates.UNDERWATER, "target.enemy", "enemy is");
        add(PSTLivingEntityPredicates.UNDERWATER, "%s if %s under water");
        add(PSTLivingEntityPredicates.DUAL_WIELDING, "target.player", "you have");
        add(PSTLivingEntityPredicates.DUAL_WIELDING, "target.enemy", "enemy has");
        add(PSTLivingEntityPredicates.DUAL_WIELDING, "%s if %s %s in both hands");
        add(PSTLivingEntityPredicates.HAS_ITEM_IN_HAND, "target.player", "with");
        add(PSTLivingEntityPredicates.HAS_ITEM_IN_HAND, "target.enemy", "if enemy has");
        add(PSTLivingEntityPredicates.HAS_ITEM_IN_HAND, "%s %s %s in hand");
        add(PSTLivingEntityPredicates.CROUCHING, "target.player", "while crouching");
        add(PSTLivingEntityPredicates.CROUCHING, "target.enemy", "if an enemy is crouching");
        add(PSTLivingEntityPredicates.CROUCHING, "reverse.target.player", "while not crouching");
        add(PSTLivingEntityPredicates.CROUCHING, "reverse.target.enemy", "if an enemy is not crouching");
        add(PSTLivingEntityPredicates.CROUCHING, "%s %s");
        add(PSTLivingEntityPredicates.UNARMED, "target.player", "while unarmed");
        add(PSTLivingEntityPredicates.UNARMED, "target.enemy", "if enemy is unarmed");
        add(PSTLivingEntityPredicates.UNARMED, "%s %s");
        add(PSTLivingEntityPredicates.NUMERIC_VALUE, "more", "more than %s");
        add(PSTLivingEntityPredicates.NUMERIC_VALUE, "less", "less than %s");
        add(PSTLivingEntityPredicates.NUMERIC_VALUE, "equal", "equal to %s");
        add(PSTLivingEntityPredicates.NUMERIC_VALUE, "at_least", "at least %s");
        add(PSTLivingEntityPredicates.NUMERIC_VALUE, "at_most", "at most %s");
        add(PSTLivingEntityPredicates.ALL_ARMOR, "target.player", "if all your armor is");
        add(PSTLivingEntityPredicates.ALL_ARMOR, "target.enemy", "if all target's armor is");
        add(PSTLivingEntityPredicates.ALL_ARMOR, "%s %s %s");
        // effect predicates
        add("effect_type.beneficial", "beneficial effect");
        add("effect_type.beneficial.plural", "beneficial effects");
        add("effect_type.harmful", "harmful effect");
        add("effect_type.harmful.plural", "harmful effects");
        add("effect_type.neutral", "neutral effect");
        add("effect_type.neutral.plural", "neutral effects");
        add("effect_type.any", "effect");
        add("effect_type.any.plural", "effects");
        // event listeners
        add(PSTEventListeners.ATTACK, "%s on hit");
        add(PSTEventListeners.ATTACK, "damage", "%s on %s hit");
        add(PSTEventListeners.BLOCK, "%s on block");
        add(PSTEventListeners.BLOCK, "damage", "%s on %s block");
        add(PSTEventListeners.EVASION, "%s on evasion");
        add(PSTEventListeners.ITEM_USED, "%s on %s use");
        add(PSTEventListeners.DAMAGE_TAKEN, "%s when you take %s");
        add(PSTEventListeners.ON_KILL, "%s on kill");
        add(PSTEventListeners.ON_KILL, "damage", "%s on %s kill");
        add(PSTEventListeners.SKILL_LEARNED, "%s when you learn this");
        add(PSTEventListeners.SKILL_REMOVED, "%s when this skill is removed");
        add(PSTEventListeners.TICKING, "second", "%s every second");
        add(PSTEventListeners.TICKING, "seconds", "%s every %s seconds");
        add(PSTEventListeners.TICKING, "minute", "%s every minute");
        add(PSTEventListeners.TICKING, "minutes", "%s every %s minutes");
        add(PSTEventListeners.CRITICAL_HIT, "%s on critical hit");
        // damage conditions
        add(PSTDamagePredicates.PROJECTILE, "Projectile Damage");
        add(PSTDamagePredicates.PROJECTILE, "type", "Projectile");
        add(PSTDamagePredicates.MELEE, "Melee Damage");
        add(PSTDamagePredicates.MELEE, "type", "Melee");
        add(PSTDamagePredicates.MAGIC, "Magic Damage");
        add(PSTDamagePredicates.MAGIC, "type", "Magic");
        add(PSTDamagePredicates.NONE, "Damage");
        add(PSTDamagePredicates.FALL, "Fall Damage");
        add(PSTDamagePredicates.FALL, "type", "Fall");
        add(PSTDamagePredicates.FIRE, "Fire Damage");
        add(PSTDamagePredicates.FIRE, "type", "Fire");
        add(PSTDamagePredicates.POISON, "Poison Damage");
        add(PSTDamagePredicates.POISON, "type", "Poison");
        add(PSTDamagePredicates.THORNS, "Thorns Damage");
        add(PSTDamagePredicates.THORNS, "type", "Thorns");
        // death messages
        deathMessage("poison", "%1$s died from poison");
        deathMessage("poison.player", "%1$s was poisoned by %2$s");
        // enchantment conditions
        add(PSTEnchantmentPredicates.WEAPON, "Weapon Enchantments");
        add(PSTEnchantmentPredicates.ARMOR, "Armor Enchantments");
        add(PSTEnchantmentPredicates.NONE, "Enchantments");
        // item conditions
        add(PSTItemPredicates.NONE, "Item");
        add(PSTItemPredicates.NONE, "plural", "Items");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "weapon", "Weapon");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "weapon.plural", "Weapons");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "ranged_weapon", "Ranged Weapon");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "ranged_weapon.plural", "Ranged Weapons");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "bow", "Bow");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "bow.plural", "Bows");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "crossbow", "Crossbow");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "crossbow.plural", "Crossbows");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "melee_weapon", "Melee Weapon");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "melee_weapon.plural", "Melee Weapons");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "sword", "Sword");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "sword.plural", "Swords");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "trident", "Trident");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "trident.plural", "Tridents");
        addItemTag(PSTTags.Items.RINGS, "Ring");
        addItemTag(PSTTags.Items.RINGS, "plural", "Rings");
        addItemTag(PSTTags.Items.NECKLACES, "Necklace");
        addItemTag(PSTTags.Items.NECKLACES, "plural", "Necklaces");
        addItemTag(PSTTags.Items.LEATHER_ARMOR, "Leather Armor");
        addItemTag(PSTTags.Items.LEATHER_ARMOR, "plural", "Leather Armor");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "armor", "Armor");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "helmet", "Helmet");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "helmet.plural", "Helmets");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "chestplate", "Chestplate");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "chestplate.plural", "Chestplates");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "leggings", "Leggings");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "boots", "Boots");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "shield", "Shield");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "shield.plural", "Shields");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "any", "Equipment");
        add(PSTItemPredicates.POTIONS, "any", "Potion");
        add(PSTItemPredicates.POTIONS, "any.plural", "Potions");
        add(PSTItemPredicates.POTIONS, "beneficial", "Beneficial Potion");
        add(PSTItemPredicates.POTIONS, "beneficial.plural", "Beneficial Potions");
        add(PSTItemPredicates.POTIONS, "harmful", "Harmful Potion");
        add(PSTItemPredicates.POTIONS, "harmful.plural", "Harmful Potions");
        add(PSTItemPredicates.POTIONS, "neutral", "Neutral Potion");
        add(PSTItemPredicates.POTIONS, "neutral.plural", "Neutral Potions");
        add(PSTItemPredicates.FOOD, "Food");
        addItemTag(PSTTags.Items.JEWELRY, "Jewelry");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "tool", "Tool");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "tool.plural", "Tools");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "axe", "Axe");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "axe.plural", "Axes");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "hoe", "Hoe");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "hoe.plural", "Hoes");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "pickaxe", "Pickaxe");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "pickaxe.plural", "Pickaxes");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "shovel", "Shovel");
        add(PSTItemPredicates.EQUIPMENT_TYPE, "shovel.plural", "Shovels");
        add(PSTItemPredicates.ENCHANTED, "Enchanted %s");
        // value providers
        add(PSTFloatFunctions.ATTRIBUTE_VALUE, "multiplier.player.plural", "%s per %s %s");
        add(PSTFloatFunctions.ATTRIBUTE_VALUE, "multiplier.player", "%s per 1 %s");
        add(PSTFloatFunctions.ATTRIBUTE_VALUE, "multiplier.enemy.plural", "%s per %s enemy's %s");
        add(PSTFloatFunctions.ATTRIBUTE_VALUE, "multiplier.enemy", "%s per 1 enemy's %s");

        add(PSTFloatFunctions.ATTRIBUTE_VALUE, "condition.player", "%s if %s is %s");
        add(PSTFloatFunctions.ATTRIBUTE_VALUE, "condition.enemy", "%s if enemy's %s is %s");

        add(PSTFloatFunctions.ATTRIBUTE_VALUE, "requirement", "Reach %s %s");

        add(PSTFloatFunctions.DISTANCE_TO_TARGET, "multiplier.player.plural", "%s per %s blocks between you and enemy");
        add(PSTFloatFunctions.DISTANCE_TO_TARGET, "multiplier.player", "%s per block between you and enemy");

        add(PSTFloatFunctions.DISTANCE_TO_TARGET, "condition.player", "%s if distance to target is %s");

        add(PSTFloatFunctions.EFFECT_AMOUNT, "multiplier.player.plural", "%s per %s %s on you");
        add(PSTFloatFunctions.EFFECT_AMOUNT, "multiplier.player", "%s per %s on you");
        add(PSTFloatFunctions.EFFECT_AMOUNT, "multiplier.enemy.plural", "%s per %s %s on target");
        add(PSTFloatFunctions.EFFECT_AMOUNT, "multiplier.enemy", "%s per %s on target");

        add(PSTFloatFunctions.EFFECT_AMOUNT, "condition.player", "%s while affected by %s %s");
        add(PSTFloatFunctions.EFFECT_AMOUNT, "condition.player.any", "%s while affected by any %s");
        add(PSTFloatFunctions.EFFECT_AMOUNT, "condition.player.none", "%s while not affected by any %s");
        add(PSTFloatFunctions.EFFECT_AMOUNT, "condition.enemy", "%s if target is affected by %s %s");
        add(PSTFloatFunctions.EFFECT_AMOUNT, "condition.enemy.any", "%s if target is affected by any %s");
        add(PSTFloatFunctions.EFFECT_AMOUNT, "condition.enemy.none", "%s if target is not affected by any %s");

        add(PSTFloatFunctions.EFFECT_AMOUNT, "requirement", "Have %s %s on you");

        add(PSTLivingEntityPredicates.NUMERIC_VALUE, "equal.effect_amount", "exactly %s");

        add(PSTFloatFunctions.ENCHANTMENT_AMOUNT, "multiplier.player.plural", "%s per %s enchantments on your %s");
        add(PSTFloatFunctions.ENCHANTMENT_AMOUNT, "multiplier.player", "%s per enchantment on your %s");
        add(PSTFloatFunctions.ENCHANTMENT_AMOUNT, "multiplier.enemy.plural", "%s per %s enchantments on target's %s");
        add(PSTFloatFunctions.ENCHANTMENT_AMOUNT, "multiplier.enemy", "%s per enchantment on target's %s");

        add(PSTFloatFunctions.ENCHANTMENT_AMOUNT, "enchantment", "enchantment");
        add(PSTFloatFunctions.ENCHANTMENT_AMOUNT, "enchantment.plural", "enchantments");

        add(PSTFloatFunctions.ENCHANTMENT_AMOUNT, "condition.player", "%s if you have %s %s on %s");
        add(PSTFloatFunctions.ENCHANTMENT_AMOUNT, "condition.player.any", "%s if your %s is enchanted");
        add(PSTFloatFunctions.ENCHANTMENT_AMOUNT, "condition.player.none", "%s if your %s is not enchanted");
        add(PSTFloatFunctions.ENCHANTMENT_AMOUNT, "condition.enemy", "%s if target has %s %s on %s");
        add(PSTFloatFunctions.ENCHANTMENT_AMOUNT, "condition.enemy.any", "%s if target's %s is enchanted");
        add(PSTFloatFunctions.ENCHANTMENT_AMOUNT, "condition.enemy.none", "%s if target's %s is not enchanted");

        add(PSTFloatFunctions.ENCHANTMENT_AMOUNT, "requirement", "Have %s %s on your %s");

        add(PSTLivingEntityPredicates.NUMERIC_VALUE, "equal.enchantment_amount", "exactly %s");

        add(PSTFloatFunctions.ENCHANTMENT_LEVELS, "multiplier.player.plural", "%s per %s enchantment levels on your %s");
        add(PSTFloatFunctions.ENCHANTMENT_LEVELS, "multiplier.player", "%s per enchantment level on your %s");
        add(PSTFloatFunctions.ENCHANTMENT_LEVELS, "multiplier.enemy.plural", "%s per %s enchantment levels on target's %s");
        add(PSTFloatFunctions.ENCHANTMENT_LEVELS, "multiplier.enemy", "%s per enchantment level on target's %s");

        add(PSTFloatFunctions.ENCHANTMENT_LEVELS, "level", "level");
        add(PSTFloatFunctions.ENCHANTMENT_LEVELS, "level.plural", "levels");

        add(PSTFloatFunctions.ENCHANTMENT_LEVELS, "condition.player", "%s if you have %s enchantment %s on %s");
        add(PSTFloatFunctions.ENCHANTMENT_LEVELS, "condition.player.any", "%s if your %s is enchanted");
        add(PSTFloatFunctions.ENCHANTMENT_LEVELS, "condition.player.none", "%s if your %s is not enchanted");
        add(PSTFloatFunctions.ENCHANTMENT_LEVELS, "condition.enemy", "%s if target has %s enchantment %s on %s");
        add(PSTFloatFunctions.ENCHANTMENT_LEVELS, "condition.enemy.any", "%s if target's %s is enchanted");
        add(PSTFloatFunctions.ENCHANTMENT_LEVELS, "condition.enemy.none", "%s if target's %s is not enchanted");

        add(PSTFloatFunctions.ENCHANTMENT_LEVELS, "requirement", "Have %s enchantment %s on your %s");

        add(PSTFloatFunctions.EQUIPMENT_DURABILITY, "multiplier.player.plural", "%s per %s durability of your %s");
        add(PSTFloatFunctions.EQUIPMENT_DURABILITY, "multiplier.player", "%s per durability of your %s");
        add(PSTFloatFunctions.EQUIPMENT_DURABILITY, "multiplier.enemy.plural", "%s per %s durability of target's %s");
        add(PSTFloatFunctions.EQUIPMENT_DURABILITY, "multiplier.enemy", "%s per durability of target's %s");

        add(PSTFloatFunctions.EQUIPMENT_DURABILITY, "condition.player", "%s if your %s has %s durability");
        add(PSTFloatFunctions.EQUIPMENT_DURABILITY, "condition.enemy", "%s if target's has %s %s durability");

        add(PSTFloatFunctions.EQUIPMENT_DURABILITY, "requirement", "Have %s durability on your %s");

        add(PSTLivingEntityPredicates.NUMERIC_VALUE, "equal.equipment_durability", "exactly %s");

        add(PSTFloatFunctions.FOOD_LEVEL, "point", "hunger point");
        add(PSTFloatFunctions.FOOD_LEVEL, "point.plural", "hunger points");
        add(PSTFloatFunctions.FOOD_LEVEL, "multiplier.player", "%s per current %s");
        add(PSTFloatFunctions.FOOD_LEVEL, "multiplier.player.plural", "%s per %s current %s");
        add(PSTFloatFunctions.FOOD_LEVEL, "multiplier.player.missing", "%s per missing %s");
        add(PSTFloatFunctions.FOOD_LEVEL, "multiplier.player.plural.missing", "%s per %s missing %s");
        add(PSTFloatFunctions.FOOD_LEVEL, "multiplier.enemy", "%s per target's current %s");
        add(PSTFloatFunctions.FOOD_LEVEL, "multiplier.enemy.plural", "%s per %s target's current %s");
        add(PSTFloatFunctions.FOOD_LEVEL, "multiplier.enemy.missing", "%s per target's missing %s");
        add(PSTFloatFunctions.FOOD_LEVEL, "multiplier.enemy.plural.missing", "%s per %s target's missing %s");
        add(PSTFloatFunctions.FOOD_LEVEL, "condition.player", "%s if you have %s %s");
        add(PSTFloatFunctions.FOOD_LEVEL, "condition.enemy", "%s if target has %s %s");
        add(PSTFloatFunctions.FOOD_LEVEL, "condition.player.missing", "%s if you are missing %s %s");
        add(PSTFloatFunctions.FOOD_LEVEL, "condition.enemy.missing", "%s if target is missing %s %s");
        add(PSTFloatFunctions.FOOD_LEVEL, "condition.player.full", "%s if you are not hungry");
        add(PSTFloatFunctions.FOOD_LEVEL, "condition.enemy.full", "%s if target is not hungry");
        add(PSTFloatFunctions.FOOD_LEVEL, "condition.player.not_full", "%s if you are hungry");
        add(PSTFloatFunctions.FOOD_LEVEL, "condition.enemy.not_full", "%s if target is hungry");
        add(PSTFloatFunctions.FOOD_LEVEL, "requirement", "Have %s %s");
        add(PSTLivingEntityPredicates.NUMERIC_VALUE, "equal.food_level", "exactly %s");

        add(PSTFloatFunctions.HEALTH_LEVEL, "point", "health point");
        add(PSTFloatFunctions.HEALTH_LEVEL, "point.plural", "health points");
        add(PSTFloatFunctions.HEALTH_LEVEL, "percentage", "health");
        add(PSTFloatFunctions.HEALTH_LEVEL, "percentage.plural", "health");
        add(PSTFloatFunctions.HEALTH_LEVEL, "multiplier.player", "%s per current %s");
        add(PSTFloatFunctions.HEALTH_LEVEL, "multiplier.player.plural", "%s per %s current %s");
        add(PSTFloatFunctions.HEALTH_LEVEL, "multiplier.player.missing", "%s per missing %s");
        add(PSTFloatFunctions.HEALTH_LEVEL, "multiplier.player.plural.missing", "%s per %s missing %s");
        add(PSTFloatFunctions.HEALTH_LEVEL, "multiplier.enemy", "%s per target's current %s");
        add(PSTFloatFunctions.HEALTH_LEVEL, "multiplier.enemy.plural", "%s per %s target's current %s");
        add(PSTFloatFunctions.HEALTH_LEVEL, "multiplier.enemy.missing", "%s per target's missing %s");
        add(PSTFloatFunctions.HEALTH_LEVEL, "multiplier.enemy.plural.missing", "%s per %s target's missing %s");
        add(PSTFloatFunctions.HEALTH_LEVEL, "condition.player", "%s if you have %s %s");
        add(PSTFloatFunctions.HEALTH_LEVEL, "condition.enemy", "%s if target has %s %s");
        add(PSTFloatFunctions.HEALTH_LEVEL, "condition.player.missing", "%s if you are missing %s %s");
        add(PSTFloatFunctions.HEALTH_LEVEL, "condition.enemy.missing", "%s if target is missing %s %s");
        add(PSTFloatFunctions.HEALTH_LEVEL, "condition.player.full", "%s while at full health");
        add(PSTFloatFunctions.HEALTH_LEVEL, "condition.enemy.full", "%s if target is at full health");
        add(PSTFloatFunctions.HEALTH_LEVEL, "condition.player.not_full", "%s while injured");
        add(PSTFloatFunctions.HEALTH_LEVEL, "condition.enemy.not_full", "%s if target is injured");
        add(PSTFloatFunctions.HEALTH_LEVEL, "requirement", "Have %s %s");
        add(PSTLivingEntityPredicates.NUMERIC_VALUE, "equal.health_level", "exactly %s");

        add(PSTFloatFunctions.LEARNED_SKILLS_AMOUNT, "point", "skill");
        add(PSTFloatFunctions.LEARNED_SKILLS_AMOUNT, "point.plural", "skills");
        add(PSTFloatFunctions.LEARNED_SKILLS_AMOUNT, "multiplier.player", "%s per learned skill");
        add(PSTFloatFunctions.LEARNED_SKILLS_AMOUNT, "multiplier.player.plural", "%s per %s learned skills");
        add(PSTFloatFunctions.LEARNED_SKILLS_AMOUNT, "multiplier.enemy", "%s per skill learned by target");
        add(PSTFloatFunctions.LEARNED_SKILLS_AMOUNT, "multiplier.enemy.plural", "%s per %s skills learned by target");
        add(PSTFloatFunctions.LEARNED_SKILLS_AMOUNT, "condition.player", "%s if you have learned %s %s");
        add(PSTFloatFunctions.LEARNED_SKILLS_AMOUNT, "condition.enemy", "%s if target has learned %s %s");
        add(PSTFloatFunctions.LEARNED_SKILLS_AMOUNT, "requirement", "Learn %s %s");
        add(PSTLivingEntityPredicates.NUMERIC_VALUE, "equal.learned_skills_amount", "exactly %s");
        add("value_provider.skilltree.mana_level.point", "mana point");
        add("value_provider.skilltree.mana_level.point.plural", "mana points");
        add("value_provider.skilltree.mana_level.percentage", "mana");
        add("value_provider.skilltree.mana_level.percentage.plural", "mana");
        add("value_provider.skilltree.mana_level.multiplier.player", "%s per current %s");
        add("value_provider.skilltree.mana_level.multiplier.player.plural", "%s per %s current %s");
        add("value_provider.skilltree.mana_level.multiplier.player.missing", "%s per missing %s");
        add("value_provider.skilltree.mana_level.multiplier.player.plural.missing", "%s per %s missing %s");
        add("value_provider.skilltree.mana_level.multiplier.enemy", "%s per target's current %s");
        add("value_provider.skilltree.mana_level.multiplier.enemy.plural", "%s per %s target's current %s");
        add("value_provider.skilltree.mana_level.multiplier.enemy.missing", "%s per target's missing %s");
        add("value_provider.skilltree.mana_level.multiplier.enemy.plural.missing", "%s per %s target's missing %s");
        add("value_provider.skilltree.mana_level.condition.player", "%s if you have %s %s");
        add("value_provider.skilltree.mana_level.condition.enemy", "%s if target has %s %s");
        add("value_provider.skilltree.mana_level.condition.player.missing", "%s if you are missing %s %s");
        add("value_provider.skilltree.mana_level.condition.enemy.missing", "%s if target is missing %s %s");
        add("value_provider.skilltree.mana_level.condition.player.full", "%s while at full mana");
        add("value_provider.skilltree.mana_level.condition.enemy.full", "%s if target is at full mana");
        add("value_provider.skilltree.mana_level.condition.player.not_full", "%s while missing mana");
        add("value_provider.skilltree.mana_level.condition.enemy.not_full", "%s if target is missing mana");
        add("value_provider.skilltree.mana_level.requirement", "Have %s %s");
        add("value_provider.skilltree.mana_level.requirement.missing", "Be missing %s %s");
        add("value_provider.skilltree.mana_level.requirement.full", "Have full mana");
        add("value_provider.skilltree.mana_level.requirement.not_full", "Be missing mana");
        add(PSTLivingEntityPredicates.NUMERIC_VALUE, "equal.mana_level", "exactly %s");
        // skill requirements
        add(PSTSkillRequirements.ADVANCEMENT, "Get %s advancement");
        add(PSTSkillRequirements.LEARNED_SKILL, "Learn %s skill");
        add(PSTSkillRequirements.STAT_VALUE, "killed", "You killed %s %s");
        add(PSTSkillRequirements.STAT_VALUE, "killed_by", "You were killed by %s %s times");
        // items
        add("skilltree.poisoned_weapon", "Poisoned:");
        add("skilltree.poisoned_weapon.uses_left", "Poisoned [%s uses left]:");
        add("skilltree.poisoned_weapon.effect", " • %s (%s)");
        add("skilltree.poisoned_weapon.effect_instant", " • %s");
        add("item.minecraft.potion.mixture", "Mixture");
        add("item.minecraft.splash_potion.mixture", "Splash Mixture");
        add("item.minecraft.lingering_potion.mixture", "Lingering Mixture");
        add("item.cant_use.info", "You can not use this");
        add(PSTItems.WISDOM_SCROLL.get(), "Wisdom Scroll");
        add(PSTItems.AMNESIA_SCROLL.get(), "Amnesia Scroll");
        add(PSTItems.WORKBENCH.get(), "Advanced Workbench");
        addTooltip(PSTItems.WISDOM_SCROLL.get(), "Grants one passive skill point");
        addTooltip(PSTItems.AMNESIA_SCROLL.get(), "Resets your passive skill tree");
        addWarning(PSTItems.AMNESIA_SCROLL.get(), "%d%% of your skill points will be lost");
        // effects
        add(PSTMobEffects.LIQUID_FIRE.get(), "Liquid Fire");
        // potions
        add(PSTPotions.LIQUID_FIRE_1.get(), "Liquid Fire");
        add(PSTPotions.LIQUID_FIRE_2.get(), "Liquid Fire");
        // system messages
        add("skilltree.message.reset", "Skill Tree has changed. Your skill points have been restored.");
        add("skilltree.message.reset_command", "Your skill tree has been reset.");
        add("skilltree.message.point_command", "Skill point gained.");
        add("skilltree.message.grant_skill_command", "You were granted the %s skill.");
        // screen info
        add("widget.skill_points_left", "Points left: %s");
        add("widget.skill_button.not_learned", "Skill not learned");
        add("widget.buy_skill_button", "Buy Skill Point");
        add("widget.skill_button.multiple_bonuses", "%s and %s");
        add("widget.confirm_button", "Confirm");
        add("widget.cancel_button", "Cancel");
        add("widget.show_stats", "Show Stats");
        add("key.categories.skilltree", "Passive Skill Tree");
        add("key.display_skill_tree", "Open Skill Tree");
        add("skill.limitation", "Limited to: %s");
        add("skill.requirements", "Requirements:");
        // tabs
        add("itemGroup.skilltree", "Passive Skill Tree");
        // recipes
        add(PSTRecipeSerializers.WORKBENCH_ITEM_BONUS.get(), "%s [%s]");
        add(PSTRecipeSerializers.WORKBENCH_POTION_MIXING.get(), "Potion mixing");
        add(PSTRecipeSerializers.WORKBENCH_POTION_MIXING.get(), "custom_skill_description", "You can mix potions using advanced workbench");
        add(PSTRecipeSerializers.WORKBENCH_WEAPON_POISONING.get(), "Weapon poisoning");
        add(PSTRecipeSerializers.WORKBENCH_WEAPON_POISONING.get(), "custom_skill_description", "You can apply poisons to weapons using advanced workbench");
    }

    protected void add(Potion potion, String name) {
        ResourceLocation potionId = BuiltInRegistries.POTION.getKey(potion);
        String effectPath = potionId.getPath();
        add(Items.POTION.getDescriptionId() + ".effect." + effectPath, "Potion of " + name);
        add(Items.SPLASH_POTION.getDescriptionId() + ".effect." + effectPath, "Splash Potion of " + name);
        add(Items.LINGERING_POTION.getDescriptionId() + ".effect." + effectPath, "Lingering Potion of " + name);
    }
}
