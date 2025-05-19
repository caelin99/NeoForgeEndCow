package com.caelin.endercattle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class EndCow extends Cow {

    public EndCow(EntityType<? extends Cow> type, Level level) {
        super(type, level);
        this.setCustomNameVisible(false);
    }
}
