package com.caelin.endercattle.worldgen.loot;

import com.caelin.endercattle.registrars.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class ModBlockLootSubProvider extends BlockLootSubProvider {

    public ModBlockLootSubProvider(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        // The contents of our DeferredRegister.
        return ModBlocks.BLOCKS.getEntries()
                .stream()
                // Cast to Block here, otherwise it will be a ? extends Block and Java will complain.
                .map(e -> (Block) e.value())
                .toList();
    }

    @Override
    protected void generate() {
        // END_GRASS_BLOCK drops END_DIRT
        dropOther(ModBlocks.END_GRASS_BLOCK.get(), ModBlocks.END_DIRT.get());

        // Normal drop for END_DIRT
        dropSelf(ModBlocks.END_DIRT.get());
    }
}
