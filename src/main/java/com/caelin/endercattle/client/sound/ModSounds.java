package com.caelin.endercattle.client.sound;

import com.caelin.endercattle.EnderCattle;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, EnderCattle.MODID);

    // Resource Locations for Soundevents
    // End Cow
    public static final ResourceLocation END_COW_HURT_LOCATION =
            ResourceLocation.fromNamespaceAndPath(EnderCattle.MODID, "end_cow.hurt");
    public static final ResourceLocation END_COW_AMBIENT_LOCATION =
            ResourceLocation.fromNamespaceAndPath(EnderCattle.MODID, "end_cow.ambient");
    public static final ResourceLocation END_COW_DEATH_LOCATION =
            ResourceLocation.fromNamespaceAndPath(EnderCattle.MODID, "end_cow.death");

    // End Chicken
    public static final ResourceLocation END_CHICKEN_HURT_LOCATION =
            ResourceLocation.fromNamespaceAndPath(EnderCattle.MODID, "end_chicken.hurt");
    public static final ResourceLocation END_CHICKEN_AMBIENT_LOCATION =
            ResourceLocation.fromNamespaceAndPath(EnderCattle.MODID, "end_chicken.ambient");
    public static final ResourceLocation END_CHICKEN_DEATH_LOCATION =
            ResourceLocation.fromNamespaceAndPath(EnderCattle.MODID, "end_chicken.death");


    // DeferredHolders SoundEvents
    // End Cow
    public static final DeferredHolder<SoundEvent, SoundEvent> END_COW_HURT =
            SOUND_EVENTS.register("end_cow.hurt",
                    () -> SoundEvent.createVariableRangeEvent(END_COW_HURT_LOCATION));
    public static final DeferredHolder<SoundEvent, SoundEvent> END_COW_AMBIENT =
            SOUND_EVENTS.register("end_cow.ambient",
                    () -> SoundEvent.createVariableRangeEvent(END_COW_AMBIENT_LOCATION));
    public static final DeferredHolder<SoundEvent, SoundEvent> END_COW_DEATH =
            SOUND_EVENTS.register("end_cow.death",
                    () -> SoundEvent.createVariableRangeEvent(END_COW_DEATH_LOCATION));

    // End Chicken
    public static final DeferredHolder<SoundEvent, SoundEvent> END_CHICKEN_HURT =
            SOUND_EVENTS.register("end_chicken.hurt",
                    () -> SoundEvent.createVariableRangeEvent(END_CHICKEN_HURT_LOCATION));
    public static final DeferredHolder<SoundEvent, SoundEvent> END_CHICKEN_AMBIENT =
            SOUND_EVENTS.register("end_chicken.ambient",
                    () -> SoundEvent.createVariableRangeEvent(END_CHICKEN_AMBIENT_LOCATION));
    public static final DeferredHolder<SoundEvent, SoundEvent> END_CHICKEN_DEATH =
            SOUND_EVENTS.register("end_chicken.death",
                    () -> SoundEvent.createVariableRangeEvent(END_CHICKEN_DEATH_LOCATION));
}