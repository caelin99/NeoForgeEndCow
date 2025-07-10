package com.caelin.endercattle.client.model;

import com.caelin.endercattle.EnderCattle;
import com.caelin.endercattle.item.EndChickenSpawnEggItem;
import com.caelin.endercattle.item.EndCowSpawnEggItem;
import com.caelin.endercattle.models.OverlayTextureSlot;
import com.caelin.endercattle.registrars.ModBlocks;
import net.minecraft.client.color.item.GrassColorSource;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.Variant;
import net.minecraft.client.data.models.blockstates.VariantProperties;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrassBlock;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplate;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplateBuilder;

import java.util.Map;

import static com.caelin.endercattle.EnderCattle.END_CHICKEN_SPAWN_EGG;
import static com.caelin.endercattle.EnderCattle.END_COW_SPAWN_EGG;

public class ModModelProvider extends ModelProvider {


    public ModModelProvider(PackOutput output) {
        super(output, EnderCattle.MODID);
    }

    public static final ExtendedModelTemplate END_GRASS_TINTED_TEMPLATE =
            ExtendedModelTemplateBuilder.builder()
                    .parent(ResourceLocation.fromNamespaceAndPath("minecraft", "block/block")) // or use your own base
                    .requiredTextureSlot(TextureSlot.SIDE)
                    .requiredTextureSlot(TextureSlot.TOP)
                    .requiredTextureSlot(TextureSlot.BOTTOM)
                    .requiredTextureSlot(OverlayTextureSlot.OVERLAY)
                    .element(element -> {
                        element.from(0, 0, 0).to(16, 16, 16);

                        // Bottom face - using end_dirt.png (colored)
                        element.face(Direction.DOWN, face -> face.texture(TextureSlot.BOTTOM).uvs(0, 0, 16, 16).cullface(Direction.DOWN));
                        element.face(Direction.UP, face -> face.texture(TextureSlot.TOP).uvs(0, 0, 16, 16).tintindex(0).cullface(Direction.UP));
                        // Side faces - using end_grass_block_side.png (colored with green and dirt)
                        element.face(Direction.NORTH, face -> face.texture(TextureSlot.SIDE).uvs(0, 0, 16, 16).cullface(Direction.NORTH));
                        element.face(Direction.SOUTH, face -> face.texture(TextureSlot.SIDE).uvs(0, 0, 16, 16).cullface(Direction.SOUTH));
                        element.face(Direction.WEST, face -> face.texture(TextureSlot.SIDE).uvs(0, 0, 16, 16).cullface(Direction.WEST));
                        element.face(Direction.EAST, face -> face.texture(TextureSlot.SIDE).uvs(0, 0, 16, 16).cullface(Direction.EAST));
                    })

                    // Overlay layer - tinted with tintIndex 0 (for top and side faces)
                    .element(element -> {
                        element.from(0, 0, 0).to(16, 16, 16);

                        // Side faces - greyscale sliver (to be tinted)
                        element.face(Direction.NORTH, face -> face.texture(OverlayTextureSlot.OVERLAY).uvs(0, 0, 16, 16).tintindex(0).cullface(Direction.NORTH));
                        element.face(Direction.SOUTH, face -> face.texture(OverlayTextureSlot.OVERLAY).uvs(0, 0, 16, 16).tintindex(0).cullface(Direction.SOUTH));
                        element.face(Direction.WEST, face -> face.texture(OverlayTextureSlot.OVERLAY).uvs(0, 0, 16, 16).tintindex(0).cullface(Direction.WEST));
                        element.face(Direction.EAST, face -> face.texture(OverlayTextureSlot.OVERLAY).uvs(0, 0, 16, 16).tintindex(0).cullface(Direction.EAST));
                    })
                    .renderType(ResourceLocation.fromNamespaceAndPath("minecraft", "cutout_mipped"))
                    .build();


    @Override
    public void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        Block endDirtBlock = ModBlocks.END_DIRT.get();
        GrassBlock endGrassBlock = ModBlocks.END_GRASS_BLOCK.get();
        ResourceLocation dirtLoc = TextureMapping.getBlockTexture(ModBlocks.END_DIRT.get());

        TextureMapping textureMapping = new TextureMapping()
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(ModBlocks.END_DIRT.get())) // end_dirt.png for the bottom
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(ModBlocks.END_GRASS_BLOCK.get(), "_top")) // end_grass_block_top.png for the top (greyscale)
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(ModBlocks.END_GRASS_BLOCK.get(), "_side")) // end_grass_block_side.png for the sides
                .put(OverlayTextureSlot.OVERLAY, TextureMapping.getBlockTexture(ModBlocks.END_GRASS_BLOCK.get(), "_side_overlay")); // end_grass_block_side_overlay.png for the side overlays (greyscale)

        ResourceLocation model = END_GRASS_TINTED_TEMPLATE.create(endGrassBlock, textureMapping, blockModels.modelOutput);

        Variant variant = Variant.variant()
                .with(VariantProperties.MODEL, model);

        blockModels.createTrivialBlock(endDirtBlock, TexturedModel.CUBE);

        blockModels.blockStateOutput.accept(
                net.minecraft.client.data.models.blockstates.MultiVariantGenerator.multiVariant(
                        endGrassBlock,
                        variant
                )
        );

        itemModels.generateSpawnEgg(END_COW_SPAWN_EGG.get(), 4263815, 8195975);
        itemModels.generateSpawnEgg(END_CHICKEN_SPAWN_EGG.get(), 12102850, 2363955);
    }


}
