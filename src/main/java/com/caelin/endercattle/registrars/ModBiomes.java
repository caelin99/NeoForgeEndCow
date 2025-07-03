package com.caelin.endercattle.registrars;

import com.caelin.endercattle.EnderCattle;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class ModBiomes {

    public static final ResourceKey<Biome> END_FIELDS =  ResourceKey.create(
            Registries.BIOME, ResourceLocation.fromNamespaceAndPath(EnderCattle.MODID, "end_fields"));
}
