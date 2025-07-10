package com.caelin.endercattle.client.model;

import com.caelin.endercattle.EnderCattle;
import com.caelin.endercattle.item.EndChickenSpawnEggItem;
import com.caelin.endercattle.item.EndCowSpawnEggItem;
import com.caelin.endercattle.registrars.ModBlocks;
import net.minecraft.client.color.item.GrassColorSource;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.Variant;
import net.minecraft.client.data.models.blockstates.VariantProperties;
import net.minecraft.client.data.models.model.*;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrassBlock;

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
                .put(TextureSlot.BOTTOM, dirtLoc)
                .copyForced(TextureSlot.BOTTOM, TextureSlot.PARTICLE)
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(ModBlocks.END_GRASS_BLOCK.get(), "_top"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(ModBlocks.END_GRASS_BLOCK.get(), "_side"));

        ResourceLocation model = ModelTemplates.CUBE_BOTTOM_TOP.create(endGrassBlock, textureMapping, blockModels.modelOutput);
        Variant variant = Variant.variant()
                .with(VariantProperties.MODEL, model);

        blockModels.createTrivialBlock(endDirtBlock, TexturedModel.CUBE);

        itemModels.generateSpawnEgg(END_COW_SPAWN_EGG.get(), 4263815, 8195975);
        itemModels.generateSpawnEgg(END_CHICKEN_SPAWN_EGG.get(), 12102850, 2363955);
    }

}
