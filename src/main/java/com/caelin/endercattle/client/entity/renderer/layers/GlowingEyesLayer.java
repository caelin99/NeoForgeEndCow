package com.caelin.endercattle.client.entity.renderer.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class GlowingEyesLayer<T extends LivingEntityRenderState, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private final ResourceLocation eyesTexture;

    public GlowingEyesLayer(RenderLayerParent<T, M> parent, ResourceLocation eyesTexture) {
        super(parent);
        this.eyesTexture = eyesTexture;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, T entity, float yRot, float xRot) {
        M model = this.getParentModel();
        model.setupAnim(entity);

        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.eyes(this.eyesTexture));
        // Render the entire model
        model.renderToBuffer(
                poseStack,
                vertexConsumer,
                15728640, // full brightness
                OverlayTexture.NO_OVERLAY
        );
    }
}
