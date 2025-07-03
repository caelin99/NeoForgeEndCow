package com.caelin.endercattle.client.entity;

import com.caelin.endercattle.registrars.ModEntities;
import com.caelin.endercattle.registrars.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

public class EndChicken extends Chicken {

    public EndChicken(EntityType<? extends Chicken> type, Level level) {

        super(type, level);
    }

    @Nullable
    @Override
    public Chicken getBreedOffspring(ServerLevel level, AgeableMob partner) {
        EndChicken baby = ModEntities.END_CHICKEN.get().create(level, EntitySpawnReason.BREEDING);

        if (baby != null) {

            baby.setPos(this.getX(), this.getY(), this.getZ());
            baby.setAge(-24000); // -24000 = fully baby (positive means adult)

            baby.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.35);


        }
        return baby;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.END_CHICKEN_HURT.value();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.END_CHICKEN_AMBIENT.value();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.END_CHICKEN_DEATH.value();
    }

    public static boolean checkMobSpawnRules(EntityType<? extends Mob> type, LevelAccessor world, EntitySpawnReason reason, BlockPos pos, RandomSource random) {
        return world.getBlockState(pos.below()).is(Blocks.END_STONE);
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor p_21686_, EntitySpawnReason p_361803_) {
        return true;
    }
}
