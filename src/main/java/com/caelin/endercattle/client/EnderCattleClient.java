package com.caelin.endercattle.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;

@OnlyIn(Dist.CLIENT)
public class EnderCattleClient {
    public static void init(IEventBus eventBus) {
        // ✅ Register mod bus events like renderers
        eventBus.addListener(ClientModEvents::onRegisterRenderers);
//        eventBus.addListener(ClientModEvents::addLayers);
        // ✅ Register client-global events like name tag rendering
        NeoForge.EVENT_BUS.addListener(RenderEvents::onRenderNameTag);
    }
}