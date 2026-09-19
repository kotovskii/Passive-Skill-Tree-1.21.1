package daripher.skilltree.recipe.workbench;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.MapCodec;
import daripher.skilltree.init.PSTRecipeSerializers;
import daripher.skilltree.inventory.menu.WorkbenchContainer;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.core.registries.BuiltInRegistries;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class WorkbenchPotionMixingRecipe extends AbstractWorkbenchRecipe {
    public static final String IS_MIXTURE_TAG_NAME = "isMixture";
    private static Ingredient cachedAllPotionsIngredient = null;
    private static final Map<PotionItem, Ingredient> cachedPotionItemIngredients = new ConcurrentHashMap<>();

    public WorkbenchPotionMixingRecipe(ResourceLocation id, boolean requiresPassiveSkill) {
        super(id, requiresPassiveSkill);
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull WorkbenchContainer container, @NotNull HolderLookup.Provider registries) {
        return getResult(container);
    }

    @Override
    public boolean isValidBaseItem(ItemStack itemStack) {
        return isValidPotion(itemStack);
    }

    @Override
    public boolean isValidIngredient(ItemStack itemStack) {
        return isValidPotion(itemStack);
    }

    private boolean isValidPotion(ItemStack itemStack) {
        return itemStack.getItem() instanceof PotionItem && canMixPotion(itemStack);
    }

    @Override
    public Pair<Ingredient, Integer> getBaseIngredient() {
        return Pair.of(getAllPotionsIngredient(), 1);
    }

    private void setIsMixtureTag(ItemStack itemStack) {
        CustomData.update(DataComponents.CUSTOM_DATA, itemStack, itemTag -> itemTag.putBoolean(IS_MIXTURE_TAG_NAME, true));
    }

    private boolean canMixPotion(ItemStack itemStack) {
        return !itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean(IS_MIXTURE_TAG_NAME);
    }

    private Ingredient getPotionItemIngredient(PotionItem baseItem) {
        return cachedPotionItemIngredients.computeIfAbsent(
                baseItem, item -> {
                    Collection<Potion> availablePotions = BuiltInRegistries.POTION.stream().toList();
                    ItemStack[] suitablePotionStacks = availablePotions.stream()
                            .filter(potion -> !potion.getEffects().isEmpty())
                            .map(potion -> getPotionStack(item, potion))
                            .toArray(ItemStack[]::new);
                    return Ingredient.of(suitablePotionStacks);
                }
        );
    }

    private Ingredient getAllPotionsIngredient() {
        if (cachedAllPotionsIngredient == null) {
            Collection<Potion> availablePotions = BuiltInRegistries.POTION.stream().toList();
            List<Potion> potionsWithEffects = availablePotions.stream().filter(potion -> !potion.getEffects().isEmpty()).toList();
            List<PotionItem> potionItems = BuiltInRegistries.ITEM.stream().toList()
                    .stream()
                    .filter(PotionItem.class::isInstance)
                    .map(PotionItem.class::cast)
                    .toList();
            List<ItemStack> suitablePotionStacks = new ArrayList<>();
            for (Potion potion : potionsWithEffects) {
                for (PotionItem potionItem : potionItems) {
                    suitablePotionStacks.add(getPotionStack(potionItem, potion));
                }
            }
            cachedAllPotionsIngredient = Ingredient.of(suitablePotionStacks.toArray(new ItemStack[0]));
        }
        return cachedAllPotionsIngredient;
    }

    private static @NotNull ItemStack getPotionStack(PotionItem baseItem, Potion potion) {
        ItemStack itemStack = new ItemStack(baseItem);
        itemStack.set(DataComponents.POTION_CONTENTS, new PotionContents(BuiltInRegistries.POTION.wrapAsHolder(potion)));
        return itemStack;
    }

    @Override
    public Map<Ingredient, Integer> getAdditionalIngredients(ItemStack baseItem) {
        if (baseItem.isEmpty()) {
            return Collections.singletonMap(getAllPotionsIngredient(), 1);
        }
        Item item = baseItem.getItem();
        if (!(item instanceof PotionItem potionItem)) {
            return Collections.singletonMap(getAllPotionsIngredient(), 1);
        }
        return Collections.singletonMap(getPotionItemIngredient(potionItem), 1);
    }

    @Override
    public Component getShortDescription() {
        return Component.translatable(getDescriptionId());
    }

    @Override
    public @NotNull ItemStack getResult(WorkbenchContainer workbenchContainer) {
        ItemStack potionStack1 = workbenchContainer.getBaseItem();
        ItemStack potionStack2 = workbenchContainer.getItem(1);
        ItemStack resultItemStack = new ItemStack(potionStack1.getItem());
        setMixtureEffects(potionStack1, potionStack2, resultItemStack);
        setMixtureColor(potionStack1, potionStack2, resultItemStack);
        setMixtureName(potionStack1, resultItemStack);
        setIsMixtureTag(resultItemStack);
        return resultItemStack;
    }

    private void setMixtureEffects(ItemStack potionStack1, ItemStack potionStack2, ItemStack resultItemStack) {
        List<MobEffectInstance> mobEffectInstances = new ArrayList<>();
        potionStack1.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY).forEachEffect(mobEffectInstances::add);
        potionStack2.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY).forEachEffect(mobEffectInstances::add);
        resultItemStack.set(DataComponents.POTION_CONTENTS, new PotionContents(Optional.empty(), Optional.empty(), mobEffectInstances));
    }

    private void setMixtureColor(ItemStack potionStack1, ItemStack potionStack2, ItemStack resultItemStack) {
        int color1 = potionStack1.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY).getColor();
        int color2 = potionStack2.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY).getColor();
        int potionColor = mixHexColors(color1, color2);
        PotionContents potionContents = resultItemStack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
        resultItemStack.set(DataComponents.POTION_CONTENTS, new PotionContents(potionContents.potion(), Optional.of(potionColor), potionContents.customEffects()));
    }

    private void setMixtureName(ItemStack potionStack1, ItemStack resultItemStack) {
        String descriptionId = potionStack1.getItem().getDescriptionId() + ".mixture";
        MutableComponent itemStackName = Component.translatable(descriptionId);
        resultItemStack.set(DataComponents.CUSTOM_NAME, itemStackName);
    }

    private int mixHexColors(int color1, int color2) {
        return ((color1 ^ color2) & 0xFEFEFE) >> 1 + (color1 & color2);
    }

    @Override
    public int requiredBaseItemAmount() {
        return 1;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return PSTRecipeSerializers.WORKBENCH_POTION_MIXING.get();
    }

    public static class Serializer implements RecipeSerializer<WorkbenchPotionMixingRecipe> {
        private static final ResourceLocation UNKNOWN_ID = ResourceLocation.fromNamespaceAndPath("skilltree", "unknown_workbench_potion_mixing");
        private final MapCodec<WorkbenchPotionMixingRecipe> codec = MapCodec.assumeMapUnsafe(Codec.PASSTHROUGH.xmap(
                dynamic -> fromJson(UNKNOWN_ID, dynamic.convert(JsonOps.INSTANCE).getValue().getAsJsonObject()),
                recipe -> new Dynamic<>(JsonOps.INSTANCE, new JsonObject())));
        private final StreamCodec<RegistryFriendlyByteBuf, WorkbenchPotionMixingRecipe> streamCodec = StreamCodec.of(
                this::toNetwork,
                buf -> fromNetwork(UNKNOWN_ID, buf));

        @Override
        public MapCodec<WorkbenchPotionMixingRecipe> codec() {
            return codec;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, WorkbenchPotionMixingRecipe> streamCodec() {
            return streamCodec;
        }

        public @NotNull WorkbenchPotionMixingRecipe fromJson(@NotNull ResourceLocation id, @NotNull JsonObject jsonObject) {
            boolean requiresPassiveSkill = jsonObject.get("requires_passive_skill").getAsBoolean();
            return new WorkbenchPotionMixingRecipe(id, requiresPassiveSkill);
        }

        public @Nullable WorkbenchPotionMixingRecipe fromNetwork(@NotNull ResourceLocation id, @NotNull FriendlyByteBuf buf) {
            boolean requiresPassiveSkill = buf.readBoolean();
            return new WorkbenchPotionMixingRecipe(id, requiresPassiveSkill);
        }

        public void toNetwork(@NotNull FriendlyByteBuf buf, @NotNull WorkbenchPotionMixingRecipe recipe) {
            buf.writeBoolean(recipe.hasPassiveSkillRequirement());
        }
    }
}
