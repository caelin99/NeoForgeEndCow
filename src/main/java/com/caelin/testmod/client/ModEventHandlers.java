package com.caelin.testmod.client;

import com.caelin.testmod.TestMod;
import com.caelin.testmod.entity.ModEntities;
import net.minecraft.world.entity.animal.Cow;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = TestMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventHandlers {
    @SubscribeEvent
    public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
        event.put(ModEntities.MY_END_COW.get(),
                Cow.createAttributes().build());
    }
}
