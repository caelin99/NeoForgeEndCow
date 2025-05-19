package com.caelin.testmod.entity;

import com.caelin.testmod.TestMod;
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
            DeferredRegister.create(Registries.ENTITY_TYPE, TestMod.MODID);

    public static final ResourceKey<EntityType<?>> MY_END_COW_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath("testmod", "my_end_cow"));

    public static final DeferredHolder<EntityType<?>, EntityType<MyEndCow>> MY_END_COW =
            ENTITY_TYPES.register("my_end_cow", () ->
                    EntityType.Builder.of(MyEndCow::new, MobCategory.CREATURE)
                            .sized(0.9f, 1.4f)
                            .build(MY_END_COW_KEY));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);

    }
}
