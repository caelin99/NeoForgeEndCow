package com.caelin.endercattle.models.item;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.ARGB;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public record EndGrassTint(int defaultColor) implements ItemTintSource {

    public static final MapCodec<EndGrassTint> MAP_CODEC = ExtraCodecs.RGB_COLOR_CODEC.fieldOf("default")
            .xmap(EndGrassTint::new, EndGrassTint::defaultColor);

    public EndGrassTint(int defaultColor) {
        this.defaultColor = ARGB.opaque(defaultColor);
    }

    @Override
    public int calculate(@NotNull ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity) {
        // You can sample biome tint or use a static one
        return 0x9966CC; // your End grass purple tint
    }

    @Override
    public MapCodec<? extends ItemTintSource> type() {
        return MAP_CODEC;
    }
}

