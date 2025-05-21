package com.caelin.endercattle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.monster.Zombie;
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
        super.setCustomName(name);
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

    private void spawnBabyFromSpawnEgg() {
        EndCow baby = (EndCow) ModEntities.END_COW.get().create(this.level, EntitySpawnReason.BREEDING);

        if (baby != null) {
            // Set the baby at the same position as the parent
            baby.setPos(this.getX(), this.getY(), this.getZ());

            // Set the baby to be a baby (negative age)
            baby.setAge(-24000); // -24000 = fully baby (positive means adult)

            // Optionally: Set the baby's properties, e.g., health, name, attributes
            baby.setCustomName(Component.translatable("entity.endercattle.end_cow_baby"));
            baby.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20.0);
            baby.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.25);

            // Add the baby entity to the world
            this.level.addFreshEntity(baby);
        }
    }
}