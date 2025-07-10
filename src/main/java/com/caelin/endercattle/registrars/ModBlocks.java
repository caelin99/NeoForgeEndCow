package com.caelin.endercattle.registrars;

import com.caelin.endercattle.EnderCattle;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(EnderCattle.MODID);

    public static final DeferredBlock<Block> END_DIRT = BLOCKS.register(
            "end_dirt", registryName ->
                    new Block(BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, registryName))
                            .sound(SoundType.GRAVEL)
                            .strength(0.5F, 0.5F)
                    )
    );

    public static final DeferredBlock<GrassBlock> END_GRASS_BLOCK = BLOCKS.register(
            "end_grass_block", registryName ->
                    new GrassBlock(BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, registryName))
                            .randomTicks()
                    )
    );

}
