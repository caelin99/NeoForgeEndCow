package com.caelin.endercattle.client;

import com.caelin.endercattle.EnderCattle;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RenderNameTagEvent;
import net.neoforged.neoforge.common.NeoForge;

@OnlyIn(Dist.CLIENT)
public class EnderCattleClient {
    public static void init(IEventBus modEventBus) {
        // ✅ Register mod bus events like renderers
        modEventBus.addListener(ClientModEvents::onRegisterRenderers);
        // ✅ Register client-global events like name tag rendering
        NeoForge.EVENT_BUS.addListener(ClientModEvents::onRenderNameTag);
    }
}