package com.caelin.endercattle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class EndCow extends Cow {

    /**
     * Constructor to create an EndCow.
     * Sets the custom name visibility to false by default.
     *
     * @param type The entity type for this cow.
     * @param level The world level where the entity exists.
     */
    public EndCow(EntityType<? extends Cow> type, Level level) {
        super(type, level);
        this.setCustomNameVisible(false); // Default visibility is false
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
}