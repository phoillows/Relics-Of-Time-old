package net.phoi.rot.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.phoi.rot.client.model.ProtoceratopsModel;
import net.phoi.rot.level.entity.Protoceratops;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ProtoceratopsRenderer extends GeoEntityRenderer<Protoceratops> {
    public ProtoceratopsRenderer(EntityRendererProvider.Context context) {
        super(context, new ProtoceratopsModel());
        this.shadowRadius = 0.45F;
    }

    @Override
    public void render(Protoceratops animatable, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if (animatable.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
        }
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
