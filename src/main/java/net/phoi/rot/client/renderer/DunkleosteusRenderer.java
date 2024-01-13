package net.phoi.rot.client.renderer;

import net.phoi.rot.client.model.DunkleosteusModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;
import net.phoi.rot.level.entity.Dunkleosteus;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DunkleosteusRenderer extends GeoEntityRenderer<Dunkleosteus> {
    public DunkleosteusRenderer(EntityRendererProvider.Context context) {
        super(context, new DunkleosteusModel());
        this.shadowRadius = 1.0F;
    }

    @Override
    public void render(Dunkleosteus animatable, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if (animatable.isBreaching()) {
            poseStack.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(partialTick, animatable.yRotO, animatable.getYRot()) - 20.0F));
            poseStack.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(partialTick, animatable.xRotO, animatable.getXRot()) + 20.0F));
        }
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
