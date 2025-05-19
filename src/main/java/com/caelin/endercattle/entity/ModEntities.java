package com.caelin.endercattle.entity;

import com.caelin.endercattle.EnderCattle;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, EnderCattle.MODID);

    public static final ResourceKey<EntityType<?>> END_COW_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath("endercattle", "end_cow"));

    public static final DeferredHolder<EntityType<?>, EntityType<EndCow>> END_COW =
            ENTITY_TYPES.register("end_cow", () ->
                    EntityType.Builder.of(EndCow::new, MobCategory.CREATURE)
                            .sized(0.9f, 1.4f)
                            .build(END_COW_KEY));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);

    }
}
