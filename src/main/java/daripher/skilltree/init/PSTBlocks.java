package daripher.skilltree.init;

import daripher.skilltree.SkillTreeMod;
import daripher.skilltree.block.WorkbenchBlock;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;

public class PSTBlocks {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK, SkillTreeMod.MOD_ID);

    // crafting stations
    public static final DeferredHolder<Block, ? extends Block> WORKBENCH = REGISTRY.register("workbench", WorkbenchBlock::new);
}
