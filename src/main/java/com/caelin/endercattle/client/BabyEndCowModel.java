package com.caelin.endercattle.client;

import net.minecraft.client.model.BabyModelTransform;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

import java.util.Set;

class BabyEndCowModel extends QuadrupedModel<LivingEntityRenderState> {
    // Apply transformations for baby
    public static final MeshTransformer BABY_TRANSFORMER = new BabyModelTransform(
            true, // scale head
            5.0F, // y-offset for head
            2.0F, // z-offset for head
            2.0F, // head scale factor
            2.0F, // body scale factor
            24.0F, // y-offset for body
            Set.of("head") // Specify which parts to transform (e.g., "head")
    );

    public BabyEndCowModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        // Create the mesh definition for the baby cow model
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        // Add head and body parts with transformation
        partdefinition.addOrReplaceChild(
                "head",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-3.0F, -3.0F, -5.0F, 6.0F, 6.0F, 5.0F)
                        .texOffs(22, 0)
                        .addBox("right_horn", -4.0F, -4.0F, -3.0F, 1.0F, 2.0F, 1.0F)
                        .texOffs(22, 0)
                        .addBox("left_horn", 3.0F, -4.0F, -3.0F, 1.0F, 2.0F, 1.0F),
                PartPose.offset(0.0F, 4.0F, -8.0F)
        );
        partdefinition.addOrReplaceChild(
                "body",
                CubeListBuilder.create()
                        .texOffs(18, 4)
                        .addBox(-4.0F, -8.0F, -5.0F, 8.0F, 14.0F, 8.0F),
                PartPose.offsetAndRotation(0.0F, 6.0F, 2.0F, (float) Math.PI / 2, 0.0F, 0.0F)
        );

        // Add legs
        CubeListBuilder legBuilder = CubeListBuilder.create().texOffs(0, 16).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 8.0F, 3.0F);
        partdefinition.addOrReplaceChild("right_hind_leg", legBuilder, PartPose.offset(-3.0F, 12.0F, 6.0F));
        partdefinition.addOrReplaceChild("left_hind_leg", legBuilder, PartPose.offset(3.0F, 12.0F, 6.0F));
        partdefinition.addOrReplaceChild("right_front_leg", legBuilder, PartPose.offset(-3.0F, 12.0F, -5.0F));
        partdefinition.addOrReplaceChild("left_front_leg", legBuilder, PartPose.offset(3.0F, 12.0F, -5.0F));

        // Apply baby transformation to the mesh definition
        meshdefinition = BABY_TRANSFORMER.apply(meshdefinition);

        return LayerDefinition.create(meshdefinition, 64, 32);
    }
}
