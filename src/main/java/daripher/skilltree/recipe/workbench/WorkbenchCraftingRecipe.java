package daripher.skilltree.recipe.workbench;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.MapCodec;
import daripher.skilltree.init.PSTRecipeSerializers;
import daripher.skilltree.inventory.menu.WorkbenchContainer;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class WorkbenchCraftingRecipe extends AbstractWorkbenchRecipe {
    private final @Nullable Pair<Ingredient, Integer> baseIngredient;
    private final Map<Ingredient, Integer> additionalIngredients;
    private final ItemStack result;

    public WorkbenchCraftingRecipe(ResourceLocation id, @Nullable Pair<Ingredient, Integer> baseIngredient, Map<Ingredient, Integer> additionalIngredients, boolean requiresPassiveSkill, ItemStack result) {
        super(id, requiresPassiveSkill);
        this.result = result;
        this.baseIngredient = baseIngredient;
        this.additionalIngredients = additionalIngredients;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull WorkbenchContainer container, @NotNull HolderLookup.Provider registries) {
        return getResult(container);
    }

    @Override
    public boolean isValidBaseItem(ItemStack itemStack) {
        if (baseIngredient == null) {
            return itemStack.isEmpty();
        }
        return baseIngredient.getLeft().test(itemStack) && itemStack.getCount() >= baseIngredient.getRight();
    }

    @Override
    public Map<Ingredient, Integer> getAdditionalIngredients(ItemStack baseIngredient) {
        return getAdditionalIngredients();
    }

    public Map<Ingredient, Integer> getAdditionalIngredients() {
        return additionalIngredients;
    }

    @Override
    public Component getShortDescription() {
        return result.getHoverName();
    }

    @Override
    public @NotNull ItemStack getResult(WorkbenchContainer workbenchContainer) {
        return result.copy();
    }

    @Override
    public int requiredBaseItemAmount() {
        return baseIngredient == null ? 0 : baseIngredient.getRight();
    }

    @Override
    public @Nullable Pair<Ingredient, Integer> getBaseIngredient() {
        return baseIngredient;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return PSTRecipeSerializers.WORKBENCH_CRAFTING.get();
    }

    public static class Serializer implements RecipeSerializer<WorkbenchCraftingRecipe> {
        private static final ResourceLocation UNKNOWN_ID = ResourceLocation.fromNamespaceAndPath("skilltree", "unknown_workbench_crafting");
        private final MapCodec<WorkbenchCraftingRecipe> codec = MapCodec.assumeMapUnsafe(Codec.PASSTHROUGH.xmap(
                dynamic -> fromJson(UNKNOWN_ID, dynamic.convert(JsonOps.INSTANCE).getValue().getAsJsonObject()),
                recipe -> new Dynamic<>(JsonOps.INSTANCE, new JsonObject())));
        private final StreamCodec<RegistryFriendlyByteBuf, WorkbenchCraftingRecipe> streamCodec = StreamCodec.of(
                this::toNetwork,
                buf -> fromNetwork(UNKNOWN_ID, buf));

        @Override
        public MapCodec<WorkbenchCraftingRecipe> codec() {
            return codec;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, WorkbenchCraftingRecipe> streamCodec() {
            return streamCodec;
        }

        public @NotNull WorkbenchCraftingRecipe fromJson(@NotNull ResourceLocation id, @NotNull JsonObject jsonObject) {
            boolean requiresPassiveSkill = jsonObject.get("requires_passive_skill").getAsBoolean();
            Map<Ingredient, Integer> additionalIngredients = new HashMap<>();
            JsonArray ingredientsJson = jsonObject.getAsJsonArray("additionalIngredients");
            for (JsonElement jsonElement : ingredientsJson) {
                JsonObject ingredientJson = jsonElement.getAsJsonObject();
                Ingredient ingredient = Ingredient.CODEC_NONEMPTY.parse(JsonOps.INSTANCE, ingredientJson.get("ingredient")).getOrThrow(JsonParseException::new);
                int requiredAmount = ingredientJson.get("required_amount").getAsInt();
                additionalIngredients.put(ingredient, requiredAmount);
            }
            Pair<Ingredient, Integer> baseIngredient = null;
            if (jsonObject.has("base_ingredient")) {
                JsonObject baseIngredientJson = jsonObject.get("base_ingredient").getAsJsonObject();
                Ingredient ingredient = Ingredient.CODEC_NONEMPTY.parse(JsonOps.INSTANCE, baseIngredientJson.get("ingredient")).getOrThrow(JsonParseException::new);
                int requiredAmount = baseIngredientJson.get("required_amount").getAsInt();
                baseIngredient = Pair.of(ingredient, requiredAmount);
            }
            JsonObject resultJson = jsonObject.getAsJsonObject("result");
            ItemStack result = ItemStack.CODEC.parse(JsonOps.INSTANCE, resultJson).getOrThrow(JsonParseException::new);
            return new WorkbenchCraftingRecipe(id, baseIngredient, additionalIngredients, requiresPassiveSkill, result);
        }

        public @Nullable WorkbenchCraftingRecipe fromNetwork(@NotNull ResourceLocation id, @NotNull FriendlyByteBuf buf) {
            boolean requiresPassiveSkill = buf.readBoolean();
            Map<Ingredient, Integer> additionalIngredients = new HashMap<>();
            int ingredientsCount = buf.readInt();
            for (int i = 0; i < ingredientsCount; i++) {
                additionalIngredients.put(Ingredient.CONTENTS_STREAM_CODEC.decode((RegistryFriendlyByteBuf) buf), buf.readInt());
            }
            Pair<Ingredient, Integer> baseIngredient = null;
            boolean hasBaseIngredient = buf.readBoolean();
            if (hasBaseIngredient) {
                baseIngredient = Pair.of(Ingredient.CONTENTS_STREAM_CODEC.decode((RegistryFriendlyByteBuf) buf), buf.readInt());
            }
            ItemStack result = ItemStack.STREAM_CODEC.decode((RegistryFriendlyByteBuf) buf);
            return new WorkbenchCraftingRecipe(id, baseIngredient, additionalIngredients, requiresPassiveSkill, result);
        }

        public void toNetwork(@NotNull FriendlyByteBuf buf, @NotNull WorkbenchCraftingRecipe recipe) {
            buf.writeBoolean(recipe.hasPassiveSkillRequirement());
            int ingredientsCount = recipe.getAdditionalIngredients().size();
            buf.writeInt(ingredientsCount);
            recipe.getAdditionalIngredients().forEach((ingredient, requiredAmount) -> {
                Ingredient.CONTENTS_STREAM_CODEC.encode((RegistryFriendlyByteBuf) buf, ingredient);
                buf.writeInt(requiredAmount);
            });
            buf.writeBoolean(recipe.baseIngredient != null);
            if (recipe.baseIngredient != null) {
                Ingredient.CONTENTS_STREAM_CODEC.encode((RegistryFriendlyByteBuf) buf, recipe.baseIngredient.getLeft());
                buf.writeInt(recipe.baseIngredient.getRight());
            }
            ItemStack.STREAM_CODEC.encode((RegistryFriendlyByteBuf) buf, recipe.result);
        }
    }
}
