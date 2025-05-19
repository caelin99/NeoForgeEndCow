package com.caelin.endercattle.client;

import com.caelin.endercattle.EnderCattle;
import com.caelin.endercattle.entity.EndCow;
import com.caelin.endercattle.entity.ModEntities;
import com.caelin.endercattle.entity.client.EndCowRenderer;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RenderNameTagEvent;
import net.neoforged.neoforge.common.util.TriState;
import org.slf4j.Logger;

//@EventBusSubscriber(modid = EnderCattle.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ClientModEvents {

    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        LOGGER.info("HELLO FROM CLIENT SETUP");
        LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.END_COW.get(), EndCowRenderer::new);
    }

    @SubscribeEvent
    public static void onRenderNameTag(RenderNameTagEvent.CanRender event) {
        Entity entity = event.getEntity();
        if (entity instanceof EndCow) {
            event.setCanRender(TriState.FALSE); // Suppress name tag rendering
        }
    }
}