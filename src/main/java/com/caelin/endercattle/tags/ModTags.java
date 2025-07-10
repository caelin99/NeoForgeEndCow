package com.caelin.endercattle.tags;

import com.caelin.endercattle.EnderCattle;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static final TagKey<Block> ENDERCATTLE_SPAWNABLE_ON = TagKey.create(
            Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(EnderCattle.MODID, "endercattle_spawnable_on")
    );
}
