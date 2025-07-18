package com.caelin.endercattle.blocks;

import com.caelin.endercattle.registrars.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class EndGrassBlock extends GrassBlock {

    public EndGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {

        BlockPos abovePos = pos.above();
        BlockState aboveState = level.getBlockState(abovePos);

        // This mimics the vanilla behavior: if too dark or block above blocks light
        boolean isDark = level.getMaxLocalRawBrightness(pos.above()) < 9;
        boolean blocksLight = !aboveState.isAir() && aboveState.getLightBlock() >= 1;

        // Die if too dark or covered
        if (blocksLight && isDark) {
            level.setBlockAndUpdate(pos, ModBlocks.END_DIRT.get().defaultBlockState());
            return;
        }

        // Spread logic (based on vanilla)
        if (level.getMaxLocalRawBrightness(pos.above()) >= 9) {
            for (int i = 0; i < 4; ++i) {
                BlockPos targetPos = pos.offset(
                        random.nextInt(3) - 1,
                        random.nextInt(5) - 3,
                        random.nextInt(3) - 1
                );
                BlockState targetState = level.getBlockState(targetPos);
                BlockState blockAboveTarget = level.getBlockState(targetPos.above());

                if (targetState.is(ModBlocks.END_DIRT.get()) &&
                        level.getMaxLocalRawBrightness(targetPos.above()) >= 4 &&
                        blockAboveTarget.getLightBlock() <= 2) {
                    level.setBlockAndUpdate(targetPos, this.defaultBlockState());
                }
            }
        }
    }
}
