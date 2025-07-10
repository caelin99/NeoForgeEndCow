package com.caelin.endercattle.worldgen.util;

import com.caelin.endercattle.EnderCattle;
import com.caelin.endercattle.worldgen.biome.placement.ModEndPlacements;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import javax.annotation.RegEx;

public class ModPlacementUtils {

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {

    }

    public static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(EnderCattle.MODID, name));
        }
}
