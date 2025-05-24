package com.caelin.endercattle.client;

import com.caelin.endercattle.EnderCattle;
import net.minecraft.world.entity.animal.Cow;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = EnderCattle.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventHandlers {
    @SubscribeEvent
    public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
        event.put(ModEntities.END_COW.get(),
                Cow.createAttributes().build());
    }
}
