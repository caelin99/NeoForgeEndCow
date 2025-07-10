package com.caelin.endercattle.worldgen.biome;

import com.caelin.endercattle.registrars.ModBiomes;
import com.caelin.endercattle.registrars.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Panda;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.neoforged.neoforge.common.world.BiomeGenerationSettingsBuilder;
import net.neoforged.neoforge.common.world.MobSpawnSettingsBuilder;

public class EndFieldsBiomeProvider {


    public static void bootstrap(BootstrapContext<Biome> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var worldCarvers = context.lookup(Registries.CONFIGURED_CARVER);
        context.register(ModBiomes.END_FIELDS, makeEndFields(placedFeatures, worldCarvers));
    }

    public static Biome makeEndFields(HolderGetter<PlacedFeature> placedFeature, HolderGetter<ConfiguredWorldCarver<?>> worldCarver) {
        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(placedFeature, worldCarver);

        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.END_CHICKEN.get(), 100, 3, 6));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.END_COW.get(), 100, 3, 5));

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(0.5F)
                .downfall(0.0F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .fogColor(0xA080FF)
                        .skyColor(0xA080FF)
                        .waterColor(0x3F76E4)
                        .waterFogColor(0x050533)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(generation.build())
                .build();
    }
}
