package com.caelin.endercattle.client;

import com.caelin.endercattle.EnderCattle;
import com.caelin.endercattle.entity.EndCow;
import net.minecraft.client.telemetry.events.WorldLoadEvent;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

@EventBusSubscriber (modid = EnderCattle.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ForgeEventHandler {

    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void onEntityJoin(EntityJoinLevelEvent event) {
        LOGGER.info("LOADED FORGE BUS HANDLER");
        if (event.getEntity() instanceof EndCow) {
            LOGGER.info("EndCow joined world at {}", event.getEntity().blockPosition());
        }
        if (event.getEntity() instanceof EnderMan) {
            LOGGER.info("ENDERMANGAYBOI joined world at {}", event.getEntity().blockPosition());
        }
        if (event.getEntity() instanceof Zombie) {
            LOGGER.info("ZombieGAYBoiII joined world at {}", event.getEntity().blockPosition());
        }
        if (event.getEntity() instanceof Pig) {
            LOGGER.info("PIGGIESLUT joined world at {}", event.getEntity().blockPosition());
        }

    }


}
