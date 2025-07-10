package com.caelin.endercattle.worldgen.util;

import com.caelin.endercattle.EnderCattle;
import com.caelin.endercattle.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {

        super(output, lookup, EnderCattle.MODID);
    }

    public void addTags(HolderLookup.Provider provider) {
        tag(ModTags.ENDERCATTLE_SPAWNABLE_ON)
                .add(Blocks.GRASS_BLOCK)
                .add(Blocks.END_STONE)
                .addOptionalTag(BlockTags.DIRT);

    }
}
