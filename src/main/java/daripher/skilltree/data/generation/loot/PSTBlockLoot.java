package daripher.skilltree.data.generation.loot;

import daripher.skilltree.init.PSTBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

public class PSTBlockLoot extends BlockLootSubProvider {
    protected PSTBlockLoot(HolderLookup.Provider provider) {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {
        dropSelf(PSTBlocks.WORKBENCH.get());
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return PSTBlocks.REGISTRY.getEntries().stream().map(DeferredHolder::get).map(Block.class::cast)::iterator;
    }
}
