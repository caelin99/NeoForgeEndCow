package com.caelin.endercattle.models.block;

import com.caelin.endercattle.models.OverlayTextureSlot;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplate;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplateBuilder;

public class BlockModelTemplates {

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

}
