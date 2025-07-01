package com.caelin.endercattle.client.renderer.layers;

import com.caelin.endercattle.EnderCattle;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class GlowingEyesCowLayer extends RenderLayer<LivingEntityRenderState, CowModel> {

    private static final ResourceLocation EYE_TEXTURE = ResourceLocation.fromNamespaceAndPath(EnderCattle.MODID, "textures/entity/end_cow_eyes.png");

    //    private static final ResourceLocation EYE_TEXTURE = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/enderman/enderman_eyes.png");
    public GlowingEyesCowLayer(RenderLayerParent<LivingEntityRenderState, CowModel> parent, EntityModelSet modelSet) {
        super(parent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, LivingEntityRenderState state, float yRot, float xRot) {
        CowModel model = this.getParentModel();

        // Animate the model using the render state directly
        model.setupAnim(state);


        ModelPart head = model.head;
        // Render ONLY the head (which contains the eyes) using the glowing texture
        head.render(
                poseStack,
                bufferSource.getBuffer(RenderType.eyes(EYE_TEXTURE)),
                15728640, // full brightness
                OverlayTexture.NO_OVERLAY
        );
    }
}







//        ResourceLocation baseTexture = ResourceLocation.fromNamespaceAndPath("endercattle", "textures/entity/end_cow_v2.png");
//        this.getParentModel().renderToBuffer(poseStack, bufferSource.getBuffer(RenderType.entityCutoutNoCull(baseTexture)),
//                packedLight, 0);
//        RenderType renderType = RenderType.eyes(EYE_TEXTURE);
//        // Render the glowing eyes with max brightness
//        this.getParentModel().renderToBuffer(
//                poseStack,
//                bufferSource.getBuffer(renderType),
//                15728640, // maximum brightness
//                OverlayTexture.NO_OVERLAY);
//    }
