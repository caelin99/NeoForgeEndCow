package com.caelin.endercattle;

import com.caelin.endercattle.client.*;
import com.caelin.endercattle.entity.renderer.EndChickenRenderer;
import com.caelin.endercattle.entity.renderer.EndCowRenderer;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.*;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

@Mod(EnderCattle.MODID)
public class EnderCattle {
    // Mod id ...
    public static final String MODID = "endercattle";

    // Standard Forge logger for logging messages to the console or log files
    private static final Logger LOGGER = LogUtils.getLogger();

    // Deferred registers for registering blocks, items, and creative tabs.
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Register SpawnEggs
    public static final DeferredItem<SpawnEggItem> END_COW_SPAWN_EGG = ITEMS.register("end_cow_spawn_egg", () ->
            new SpawnEggItem(ModEntities.END_COW.get(),
                    new Item.Properties().useItemDescriptionPrefix().setId(
                            ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MODID, "end_cow_spawn_egg")))));

    public static final DeferredItem<SpawnEggItem> END_CHICKEN_SPAWN_EGG = ITEMS.register("end_chicken_spawn_egg", () ->
            new SpawnEggItem(ModEntities.END_CHICKEN.get(),
                    new Item.Properties().useItemDescriptionPrefix().setId(
                            ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MODID, "end_chicken_spawn_egg")))));

    // Registers a custom creative mode tab for your mod's items
    // - Uses Ender Eye as an icon
    // - Adds custom spawn eggs to the tab display
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ENDERCATTLE_TAB = CREATIVE_MODE_TABS.register("endercattle_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.endercattle"))
                    .withTabsBefore(CreativeModeTabs.REDSTONE_BLOCKS)
                    .icon(() -> new ItemStack(Items.ENDER_EYE))
                    .displayItems((parameters, output) -> {
                        output.accept(END_COW_SPAWN_EGG.get());
                        output.accept(END_CHICKEN_SPAWN_EGG.get());
                    }).build());


    public EnderCattle(IEventBus modEventBus, ModContainer modContainer) {

        // Register lifecycle events
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);

        // Register registries
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);
        ModSounds.SOUND_EVENTS.register(modEventBus);

        // Register config
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        // Register this class to NeoForge event bus
        NeoForge.EVENT_BUS.register(this);

        // Register renderers lambda
        modEventBus.addListener(EntityRenderersEvent.RegisterRenderers.class, event -> {
            event.registerEntityRenderer(ModEntities.END_COW.value(), EndCowRenderer::new);
            event.registerEntityRenderer(ModEntities.END_CHICKEN.value(), EndChickenRenderer::new);
        });
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock) {
            LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT);
        }

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach(item -> LOGGER.info("ITEM >> {}", item));
    }

    // Add items to Creative tabs
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(END_COW_SPAWN_EGG);
            event.accept(END_CHICKEN_SPAWN_EGG);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

        LOGGER.info("HELLO from server starting");
    }
}
