package com.caelin.endercattle.worldgen.biome;

import com.caelin.endercattle.registrars.ModBiomes;
import com.caelin.endercattle.registrars.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class ModSurfaceRules {

    private static final SurfaceRules.RuleSource GRASS = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource END_DIRT = makeStateRule(ModBlocks.END_DIRT.get()); // For End Grass (change to correct block if needed)
    private static final SurfaceRules.RuleSource END_GRASS = makeStateRule(ModBlocks.END_GRASS_BLOCK.get()); // For End Grass (change to correct block if needed)

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }

    public static SurfaceRules.RuleSource end() {
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(ModBiomes.END_FIELDS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                        SurfaceRules.sequence(END_GRASS)
                                ),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR,
                                        SurfaceRules.sequence(END_DIRT)
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.noiseCondition(Noises.SURFACE, 0.4D, 1.0D),
                                        SurfaceRules.state(ModBlocks.END_GRASS_BLOCK.get().defaultBlockState())
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.noiseCondition(Noises.SURFACE, 1.0D, 1.5D),
                                        SurfaceRules.state(ModBlocks.END_GRASS_BLOCK.get().defaultBlockState())
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.noiseCondition(Noises.SURFACE, 1.5D, 2.0D),
                                        SurfaceRules.state(ModBlocks.END_GRASS_BLOCK.get().defaultBlockState())
                                )
                        )
                )
        );
//                SurfaceRules.ifTrue(
//                        SurfaceRules.noiseCondition(Noises.SURFACE, 1.0D, 1.5D),
//                        SurfaceRules.state(ModBlocks.END_GRASS_BLOCK.get().defaultBlockState())
//                )
//                SurfaceRules.ifTrue(
//                        SurfaceRules.noiseCondition(Noises.SURFACE, 1.5D, 2.0D),
//                        SurfaceRules.state(ModBlocks.END_GRASS_BLOCK.get().defaultBlockState())
//                )
    }
}
