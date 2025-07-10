package com.caelin.endercattle.events;

import com.caelin.endercattle.EnderCattle;
import com.caelin.endercattle.registrars.ModBlocks;
import com.caelin.endercattle.registrars.ModEntities;
import com.caelin.endercattle.client.entity.EndChicken;
import com.caelin.endercattle.client.entity.EndCow;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@EventBusSubscriber(modid = EnderCattle.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventHandlers {

    @SubscribeEvent
    public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
        event.put(ModEntities.END_COW.get(),
                Cow.createAttributes()
                        .add(Attributes.MAX_HEALTH, 25.0D)
                        .build());
        event.put(ModEntities.END_CHICKEN.get(),
                Chicken.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(ModEntities.END_COW.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EndCow::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(ModEntities.END_CHICKEN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EndChicken::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

    @SubscribeEvent
    public static void registerBlockColorHandlers(RegisterColorHandlersEvent.Block event) {
        event.register((state, level, pos, tintIndex) -> {
            if (level != null && pos != null) {
                // tintIndex 0 = top, tintIndex 1 = side_overlay
                return tintIndex == 0 || tintIndex == 1
                        ? BiomeColors.getAverageGrassColor(level, pos)
                        : 0xFFFFFFFF; // No tinting for other parts
            }
            return 0xFFFFFFFF; // Default color fallback
        }, ModBlocks.END_GRASS_BLOCK.get());
    }

    @SubscribeEvent // on the mod event bus only on the physical client
    public static void registerColorResolvers(RegisterColorHandlersEvent.ColorResolvers event) {
        // Parameters are the current biome, the block's X position, and the block's Z position.
        event.register((biome, x, z) -> {
            // Replace with your own calculation. See the BiomeColors class for vanilla references.
            // Colors are in ARGB format.
            return 0xFFFFFFFF;
        });
    }

}
