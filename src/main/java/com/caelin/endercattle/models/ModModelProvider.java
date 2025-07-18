package com.caelin.endercattle.models;

import com.caelin.endercattle.EnderCattle;
import com.caelin.endercattle.models.block.BlockModelTemplates;
import com.caelin.endercattle.models.item.EndGrassTint;
import com.caelin.endercattle.registrars.ModBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.Variant;
import net.minecraft.client.data.models.blockstates.VariantProperties;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.item.BlockModelWrapper;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrassBlock;

import java.util.List;

import static com.caelin.endercattle.EnderCattle.END_CHICKEN_SPAWN_EGG;
import static com.caelin.endercattle.EnderCattle.END_COW_SPAWN_EGG;

public class ModModelProvider extends ModelProvider {


    public ModModelProvider(PackOutput output) {
        super(output, EnderCattle.MODID);
    }

    @Override
    public void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        Block endDirtBlock = ModBlocks.END_DIRT.get();
        GrassBlock endGrassBlock = ModBlocks.END_GRASS_BLOCK.get();
        ResourceLocation dirtLoc = TextureMapping.getBlockTexture(ModBlocks.END_DIRT.get());

        TextureMapping textureMapping = new TextureMapping()
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(ModBlocks.END_DIRT.get())) // end_dirt.png for the bottom
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(ModBlocks.END_GRASS_BLOCK.get(), "_top")) // end_grass_block_top.png for the top (greyscale)
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(ModBlocks.END_GRASS_BLOCK.get(), "_side")) // end_grass_block_side.png for the sides
                .put(OverlayTextureSlot.OVERLAY, TextureMapping.getBlockTexture(ModBlocks.END_GRASS_BLOCK.get(), "_side_overlay")) // end_grass_block_side_overlay.png for the side overlays (greyscale)
                .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(ModBlocks.END_GRASS_BLOCK.get(), "_side"));
        ResourceLocation model = BlockModelTemplates.END_GRASS_TINTED_TEMPLATE.create(endGrassBlock, textureMapping, blockModels.modelOutput);

        Variant variant = Variant.variant()
                .with(VariantProperties.MODEL, model);

        blockModels.createTrivialBlock(endDirtBlock, TexturedModel.CUBE);

        blockModels.blockStateOutput.accept(
                net.minecraft.client.data.models.blockstates.MultiVariantGenerator.multiVariant(
                        endGrassBlock,
                        variant
                )
        );

        itemModels.itemModelOutput.accept(
                ModBlocks.END_GRASS_BLOCK.get().asItem(),
                new BlockModelWrapper.Unbaked(
                        // Reference the actual block model
                        ModelLocationUtils.getModelLocation(ModBlocks.END_GRASS_BLOCK.get()),
                        List.of(
                                new EndGrassTint(0x895199) // your tinting logic here
                        )
                )
        );


        itemModels.generateSpawnEgg(END_COW_SPAWN_EGG.get(), 4263815, 8195975);
        itemModels.generateSpawnEgg(END_CHICKEN_SPAWN_EGG.get(), 12102850, 2363955);
    }


}
