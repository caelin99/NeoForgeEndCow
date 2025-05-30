package com.caelin.endercattle.entity;

import com.caelin.endercattle.client.ModEntities;
import com.caelin.endercattle.client.ModSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class EndChicken extends Chicken {

    public EndChicken(EntityType<? extends Chicken> type, Level level) {
        super(type, level);
    }

    @Override
    public void setCustomName(@Nullable Component name) {
        super.setCustomName(Component.translatable("entity.endercattle.end_chicken"));
        this.setCustomNameVisible(name != null); // Only show name if not null
    }

    @Nullable
    @Override
    public Chicken getBreedOffspring(ServerLevel level, AgeableMob partner) {
        EndChicken baby = ModEntities.END_CHICKEN.get().create(level, EntitySpawnReason.BREEDING);

        if (baby != null) {

            baby.setPos(this.getX(), this.getY(), this.getZ());
            baby.setAge(-24000); // -24000 = fully baby (positive means adult)


            baby.setCustomName(Component.translatable("entity.endercattle.end_chicken_baby"));
            baby.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(1);


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
}
