package com.caelin.endercattle.registrars;

import com.caelin.endercattle.EnderCattle;
import com.caelin.endercattle.item.EndCowSpawnEggItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import javax.swing.*;

public class ModItems {

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(EnderCattle.MODID);

    public static final DeferredItem<BlockItem> END_DIRT_ITEM =
            ITEMS.registerSimpleBlockItem("end_dirt_item", ModBlocks.END_DIRT);

    public static final DeferredItem<BlockItem> END_GRASS_BLOCK_ITEM =
            ITEMS.registerSimpleBlockItem("end_grass_block_item", ModBlocks.END_GRASS_BLOCK);

}
