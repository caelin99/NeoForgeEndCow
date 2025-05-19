package com.caelin.testmod.client;

import com.caelin.testmod.TestMod;
import com.caelin.testmod.entity.ModEntities;
import com.caelin.testmod.entity.client.MyEndCowRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import org.apache.logging.log4j.core.config.plugins.util.ResolverUtil;

@EventBusSubscriber(modid = TestMod.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ClientModEvents {
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.END_COW.get(),
                MyEndCowRenderer::new);
    }
}
