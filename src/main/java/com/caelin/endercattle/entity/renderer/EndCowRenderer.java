package com.caelin.endercattle.entity.renderer;

import com.caelin.endercattle.entity.EndCow;
import com.caelin.endercattle.entity.GlowingEyesCowLayer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;


// The generic type in the superclass should be set to what entity you want to render.
// If you wanted to enable rendering for any entity, you'd use Entity, like we do here.
// You'd also use an EntityRenderState that fits your use case. More on this below.
public class EndCowRenderer extends MobRenderer<EndCow, LivingEntityRenderState, CowModel> {
    // In our constructor, we just forward to super.
    public EndCowRenderer(EntityRendererProvider.Context context) {
        super(context, new CowModel(context.bakeLayer(ModelLayers.COW)), 0.5f);
        this.addLayer(new GlowingEyesCowLayer(this, context.getModelSet()));
    }

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("endercattle", "textures/entity/end_cow_v2.png");

    public @NotNull LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    public void extractRenderState(EndCow entity, LivingEntityRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
    }

    @Override
    public void render(LivingEntityRenderState state, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(state, poseStack, bufferSource,packedLight);
    }

    @Override
    protected void scale(LivingEntityRenderState cow, PoseStack poseStack) {
        poseStack.scale(1.0F, 1.0F, 1.0F);
        if(cow.isBaby) {
            poseStack.scale(0.5F, 0.5F, 0.5f);
        }
        super.scale(cow, poseStack);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull LivingEntityRenderState state) {
        return TEXTURE;
    }
}