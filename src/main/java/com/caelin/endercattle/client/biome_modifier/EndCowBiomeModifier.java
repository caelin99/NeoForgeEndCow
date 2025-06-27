package com.caelin.endercattle.client.biome_modifier;

import com.caelin.endercattle.client.ModEntities;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;

import java.util.List;
import java.util.Set;

public record EndCowBiomeModifier(Set<ResourceKey<Biome>> targetBiomes) implements BiomeModifier {

    public static final MapCodec<EndCowBiomeModifier> CODEC = ResourceKey.codec(Registries.BIOME).listOf()
            .xmap(Set::copyOf, List::copyOf)
            .xmap(EndCowBiomeModifier::new, EndCowBiomeModifier::targetBiomes).fieldOf("targetBiomes");

    @Override
    public void modify(Holder<Biome> biomeHolder, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        System.out.println("Checking biome: " + biomeHolder.unwrapKey().orElse(null));
        System.out.println("MobSpawnSettings before: " + builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE));

        if (phase == Phase.ADD && targetBiomes.contains(biomeHolder.unwrapKey().orElse(null))) {
            // Log the mob category and the spawner data
            System.out.println("Adding spawn for: " + ModEntities.END_COW.get());
            MobSpawnSettings.SpawnerData spawnData = new MobSpawnSettings.SpawnerData(ModEntities.END_COW.get(), 50, 1, 4);
            System.out.println("SpawnData: " + spawnData);

            builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, spawnData);
            System.out.println("Added End Cow spawn to the biome.");
        }

        System.out.println("MobSpawnSettings after: " + builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE));
    }

    @Override
    public MapCodec<? extends BiomeModifier> codec() {
        return CODEC;
    }


}
