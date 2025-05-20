package com.caelin.endercattle.client;

import com.caelin.endercattle.entity.EndCow;
import com.caelin.endercattle.entity.client.EndCowRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RenderNameTagEvent;
import net.neoforged.neoforge.common.util.TriState;

@OnlyIn(Dist.CLIENT)
public class RenderEvents {

    @SubscribeEvent
    public static void onRenderNameTag(RenderNameTagEvent.CanRender event) {
        // Suppressing the name tag rendering for EndCow
        Entity entity = event.getEntity();
        if (entity instanceof EndCow) {
            event.setCanRender(TriState.FALSE); // Don't render name tag
        }
    }
}
