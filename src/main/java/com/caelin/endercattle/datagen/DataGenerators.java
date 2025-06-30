package com.caelin.endercattle.datagen;

import com.caelin.endercattle.EnderCattle;
//import com.caelin.endercattle.client.EndCowBiomeModifier;
import com.caelin.endercattle.client.ModEntities;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;
import java.util.Set;


@EventBusSubscriber(modid = EnderCattle.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    public static final ResourceKey<BiomeModifier> ADD_SPAWN_ENDERCATTLE = ResourceKey.create(
            NeoForgeRegistries.Keys.BIOME_MODIFIERS,
            ResourceLocation.fromNamespaceAndPath(EnderCattle.MODID, "add_spawn_endercattle")
    );

    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        var lookupProvider = event.getLookupProvider();

        event.createDatapackRegistryObjects(
                new RegistrySetBuilder().add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, bootstrap -> {
                            HolderGetter<Biome> biomes = bootstrap.lookup(Registries.BIOME);

                            bootstrap.register(ADD_SPAWN_ENDERCATTLE,
                                    new BiomeModifiers.AddSpawnsBiomeModifier(
                                            HolderSet.direct(
                                                    biomes.getOrThrow(Biomes.END_BARRENS),
                                                    biomes.getOrThrow(Biomes.END_HIGHLANDS),
                                                    biomes.getOrThrow(Biomes.END_MIDLANDS),
                                                    biomes.getOrThrow(Biomes.THE_END),
                                                    biomes.getOrThrow(Biomes.SMALL_END_ISLANDS)),
                                            List.of(
                                                    new MobSpawnSettings.SpawnerData(ModEntities.END_COW.get(), 20, 1, 4),
                                                    new MobSpawnSettings.SpawnerData(ModEntities.END_CHICKEN.get(), 20, 2, 5)
                                            )
                                    )
                            );
                        }),
                        Set.of(EnderCattle.MODID)
                );

    }
}
