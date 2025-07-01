package com.caelin.endercattle.common;

import com.caelin.endercattle.EnderCattle;
import com.caelin.endercattle.common.entity.EndChicken;
import com.caelin.endercattle.common.entity.EndCow;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {

    // Deferred Register to hold EntityTypes
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, EnderCattle.MODID);

    // ResourceKey for the "end_cow" entity type
    public static final ResourceKey<EntityType<?>> END_COW_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EnderCattle.MODID, "end_cow"));

    // DeferredHolder for the EndCow entity type
    public static final DeferredHolder<EntityType<?>, EntityType<EndCow>> END_COW =
            ENTITY_TYPES.register("end_cow", () ->
                    EntityType.Builder.of(EndCow::new, MobCategory.CREATURE)
                            .canSpawnFarFromPlayer()
                            .sized(0.9f, 1.4f) // Adjust these sizes as needed
                            .build(END_COW_KEY));

    // ResourceKey for the "end_chicken" entity type
    public static final ResourceKey<EntityType<?>> END_CHICKEN_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EnderCattle.MODID, "end_chicken"));

    // DeferredHolder for the EndChicken entity type
    public static final DeferredHolder<EntityType<?>, EntityType<EndChicken>> END_CHICKEN =
            ENTITY_TYPES.register("end_chicken", () ->
                    EntityType.Builder.of(EndChicken::new, MobCategory.CREATURE)
                            .sized(0.9f, 1.4f) // Adjust these sizes as needed
                            .build(END_CHICKEN_KEY));



    // Registering the entities to the event bus
//    public static void register(IEventBus eventBus) {
//        ENTITY_TYPES.register(eventBus);
//    }
}
