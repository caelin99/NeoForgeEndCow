package com.caelin.endercattle.client.renderer;

import com.caelin.endercattle.EnderCattle;
import com.caelin.endercattle.common.entity.EndChicken;
import com.caelin.endercattle.client.renderer.layers.GlowingEyesLayer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class EndChickenRenderer extends MobRenderer<EndChicken, ChickenRenderState, ChickenModel> {

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("endercattle", "textures/entity/end_chicken.png");
    private final ChickenModel adultModel;
    private final ChickenModel babyModel;

    public EndChickenRenderer(EntityRendererProvider.Context context) {
        super(context, new ChickenModel(context.bakeLayer(ModelLayers.CHICKEN)),0.3f);
        this.addLayer(new GlowingEyesLayer<>(this, ResourceLocation.fromNamespaceAndPath(EnderCattle.MODID, "textures/entity/end_chicken_eyes.png")));
        this.adultModel = new ChickenModel(context.bakeLayer(ModelLayers.CHICKEN));
        this.babyModel = new ChickenModel(context.bakeLayer(ModelLayers.CHICKEN_BABY));
    }

    public @NotNull ChickenRenderState createRenderState() {
        return new ChickenRenderState();
    }

    @Override
    public ResourceLocation getTextureLocation(ChickenRenderState state) {
        return TEXTURE;
    }

    @Override
    public void render(ChickenRenderState state, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if(state.isBaby) {
            this.model = babyModel;
        } else {
            this.model = adultModel;
        }
        super.render(state, poseStack, bufferSource, packedLight);
    }
}
