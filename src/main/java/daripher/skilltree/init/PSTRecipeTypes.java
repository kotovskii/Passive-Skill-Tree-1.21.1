package daripher.skilltree.init;

import daripher.skilltree.SkillTreeMod;
import daripher.skilltree.recipe.workbench.AbstractWorkbenchRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;

public class PSTRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, SkillTreeMod.MOD_ID);

    public static final DeferredHolder<RecipeType<?>, RecipeType<AbstractWorkbenchRecipe>> WORKBENCH = REGISTRY.register("workbench", () -> recipeType("workbench"));

    private static <T extends Recipe<?>> RecipeType<T> recipeType(final String identifier) {
        return new RecipeType<>() {
            public String toString() {
                return identifier;
            }
        };
    }
}
