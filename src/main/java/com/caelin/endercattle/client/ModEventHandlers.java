package com.caelin.endercattle.client;

import com.caelin.endercattle.EnderCattle;
import com.caelin.endercattle.entity.EndChicken;
import com.caelin.endercattle.entity.EndCow;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = EnderCattle.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventHandlers {

    @SubscribeEvent
    public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
        event.put(ModEntities.END_COW.get(),
                Cow.createAttributes().build());
        event.put(ModEntities.END_CHICKEN.get(),
                Chicken.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(ModEntities.END_COW.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EndCow::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(ModEntities.END_CHICKEN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EndChicken::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }


}
