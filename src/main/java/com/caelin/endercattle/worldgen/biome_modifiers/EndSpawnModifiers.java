package com.caelin.endercattle.worldgen.biome_modifiers;

import com.caelin.endercattle.EnderCattle;
import com.caelin.endercattle.registrars.ModBiomes;
import com.caelin.endercattle.registrars.ModEntities;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;

public class EndSpawnModifiers {

    public static final ResourceKey<BiomeModifier> ADD_SPAWN_ENDERCATTLE = ResourceKey.create(
            NeoForgeRegistries.Keys.BIOME_MODIFIERS,
            ResourceLocation.fromNamespaceAndPath(EnderCattle.MODID, "add_spawn_endercattle")
    );

    public static void bootstrap(BootstrapContext<BiomeModifier> bootstrapContext) {
        HolderGetter<Biome> biomes = bootstrapContext.lookup(Registries.BIOME);

        bootstrapContext.register(ADD_SPAWN_ENDERCATTLE,
                new BiomeModifiers.AddSpawnsBiomeModifier(
                        HolderSet.direct(
                                biomes.getOrThrow(Biomes.END_BARRENS),
                                biomes.getOrThrow(Biomes.END_HIGHLANDS),
                                biomes.getOrThrow(Biomes.END_MIDLANDS),
                                biomes.getOrThrow(Biomes.THE_END),
                                biomes.getOrThrow(Biomes.SMALL_END_ISLANDS),
                                // Custom End Biome
                                biomes.getOrThrow(ModBiomes.END_FIELDS)),
                        List.of(
                                new MobSpawnSettings.SpawnerData(ModEntities.END_COW.get(), 20, 1, 4),
                                new MobSpawnSettings.SpawnerData(ModEntities.END_CHICKEN.get(), 20, 2, 5)
                        )
                )
        );
    }
}
