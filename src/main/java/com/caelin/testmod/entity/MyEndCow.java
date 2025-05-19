package com.caelin.testmod.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.Level;

public class MyEndCow extends Cow {

    public MyEndCow(EntityType<? extends Cow> type, Level level) {
        super(type, level);
    }
}
