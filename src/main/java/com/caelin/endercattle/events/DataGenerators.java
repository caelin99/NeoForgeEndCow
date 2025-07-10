package com.caelin.endercattle.events;

import com.caelin.endercattle.EnderCattle;
//import com.caelin.endercattle.client.EndCowBiomeModifier;
import com.caelin.endercattle.client.model.ModModelProvider;
import com.caelin.endercattle.worldgen.biome.EndFieldsBiomeProvider;
import com.caelin.endercattle.worldgen.biome_modifiers.EndSpawnModifiers;
import com.caelin.endercattle.worldgen.loot.ModEntityLootSubProvider;
import com.caelin.endercattle.worldgen.util.ModBlockTagsProvider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;
import java.util.Set;


@EventBusSubscriber(modid = EnderCattle.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {



    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();


        event.createDatapackRegistryObjects(
                new RegistrySetBuilder()
                        .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, EndSpawnModifiers::bootstrap)
                        .add(Registries.BIOME, EndFieldsBiomeProvider::bootstrap),
                        Set.of(EnderCattle.MODID)
                );


        event.createProvider((output, lookupProvider) -> new LootTableProvider(
                output,
                Set.of(),
                List.of(new LootTableProvider.SubProviderEntry(
                        ModEntityLootSubProvider::new,
                        LootContextParamSets.EMPTY)
                ),
                lookupProvider)
        );

        event.createProvider(ModBlockTagsProvider::new);
        event.createProvider(ModModelProvider::new);
    }
}
