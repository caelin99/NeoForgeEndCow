package com.caelin.endercattle.entity.renderer;

import com.caelin.endercattle.EnderCattle;
import com.caelin.endercattle.entity.EndChicken;
import com.caelin.endercattle.entity.GlowingEyesLayer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class EndChickenRenderer extends MobRenderer<EndChicken, ChickenRenderState, ChickenModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("endercattle", "textures/entity/end_chicken.png");

    public EndChickenRenderer(EntityRendererProvider.Context context) {
        super(context, new ChickenModel(context.bakeLayer(ModelLayers.CHICKEN)), 0.3f);
        this.addLayer(new GlowingEyesLayer<>(this, ResourceLocation.fromNamespaceAndPath(EnderCattle.MODID, "textures/entity/end_chicken_eyes.png")));
    }

    public @NotNull ChickenRenderState createRenderState() {
        return new ChickenRenderState();
    }

    @Override
    public ResourceLocation getTextureLocation(ChickenRenderState state) {
        return TEXTURE;
    }

    @Override
    protected void scale(ChickenRenderState state, PoseStack poseStack) {
        poseStack.scale(1.0F, 1.0F, 1.0F);
        if (state.isBaby) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
        }
    }
}
