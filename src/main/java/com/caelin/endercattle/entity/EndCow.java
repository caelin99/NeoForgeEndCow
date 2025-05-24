package com.caelin.endercattle.entity;

import com.caelin.endercattle.client.ModEntities;
import com.caelin.endercattle.client.ModSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class EndCow extends Cow {

    private static final EntityDimensions BABY_DIMENSIONS = EntityType.COW.getDimensions().scale(0.5F).withEyeHeight(0.665F);

    @Override
    public EntityDimensions getDefaultDimensions(Pose p_316185_) {
        // If the entity is a baby, scale its dimensions down
        return this.isBaby() ? BABY_DIMENSIONS : super.getDefaultDimensions(p_316185_);
    }


    /**
     * Constructor to create an EndCow.
     * Sets the custom name visibility to false by default.
     *
     * @param type The entity type for this cow.
     * @param level The world level where the entity exists.
     */
    public EndCow(EntityType<? extends Cow> type, Level level) {
        super(type, level);
    }

    /**
     * Optionally sets a custom name for the cow.
     *
     * @param name The custom name to set for the cow (can be null).
     */
    @Override
    public void setCustomName(@Nullable Component name) {
        super.setCustomName(Component.translatable("entity.endercattle.end_cow"));
        this.setCustomNameVisible(name != null); // Only show name if not null
    }

    @Nullable
    @Override
    public Cow getBreedOffspring(ServerLevel level, AgeableMob partner) {
        EndCow baby = ModEntities.END_COW.get().create(level, EntitySpawnReason.BREEDING);

        if(baby != null) {

            baby.setPos(this.getX(), this.getY(), this.getZ());
            baby.setAge(-24000); // -24000 = fully baby (positive means adult)


            baby.setCustomName(Component.translatable("entity.endercattle.end_cow_baby"));
            baby.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(1);


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

}