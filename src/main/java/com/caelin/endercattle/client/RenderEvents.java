package com.caelin.endercattle.client;

import com.caelin.endercattle.entity.EndCow;
import net.minecraft.world.entity.Entity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderNameTagEvent;
import net.neoforged.neoforge.common.util.TriState;
import net.neoforged.neoforge.event.entity.living.BabyEntitySpawnEvent;

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

    @SubscribeEvent
    public static void onBabySpawn(BabyEntitySpawnEvent event) {
//        if (event.getChild() instanceof EndCow calf) {
//            if(calf.isBaby())
//                calf.setAge(-24000); // Make sure it's a baby
//                calf.refreshDimensions(); // Force update of size
//                System.out.println("Forcing dimension refresh for baby EndCow");
//        }
    }


}
