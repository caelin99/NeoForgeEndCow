package com.caelin.endercattle.entity;

import com.caelin.endercattle.client.ModEntities;
import com.caelin.endercattle.client.ModSounds;
import com.mojang.logging.LogUtils;
import net.minecraft.data.worldgen.DimensionTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.client.resources.sounds.EntityBoundSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.DimensionType;
import org.jetbrains.annotations.Nullable;

public class EndCow extends Cow {

    /**
     * Constructor to create an EndCow.
     * Sets the custom name visibility to false by default.
     *
     * @param type  The entity type for this cow.
     * @param level The world level where the entity exists.
     */
    public EndCow(EntityType<? extends Cow> type, Level level) {

        super(type, level);
    }


    @Nullable
    @Override
    public Cow getBreedOffspring(ServerLevel level, AgeableMob partner) {
        EndCow baby = ModEntities.END_COW.get().create(level, EntitySpawnReason.BREEDING);

        if (baby != null) {

            baby.setPos(this.getX(), this.getY(), this.getZ());
            baby.setAge(-24000); // -24000 = fully baby (positive means adult)

            baby.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.35);
        }
        return baby;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.END_COW_HURT.value();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.END_COW_AMBIENT.value();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.END_COW_DEATH.value();
    }

    public static boolean checkMobSpawnRules(EntityType<? extends Mob> type, LevelAccessor world, EntitySpawnReason reason, BlockPos pos, RandomSource random) {
        BlockPos below = pos.below();
        boolean validBlock = world.getBlockState(below).is(Blocks.DIRT) ||
                world.getBlockState(below).is(Blocks.END_STONE) ||
                world.getBlockState(below).is(Blocks.GRASS_BLOCK);

        if (!validBlock) {
            return false;
        }

        int blockLight = world.getBrightness(LightLayer.BLOCK, pos);
        return blockLight <= 7;

    }

    @Override
    public boolean checkSpawnRules(LevelAccessor p_21686_, EntitySpawnReason p_361803_) {
        return true;
    }

//    @Override
//    public float getWalkTargetValue(BlockPos p_21688_, LevelReader p_21689_) {
//        return 0.0F;
//    }

}