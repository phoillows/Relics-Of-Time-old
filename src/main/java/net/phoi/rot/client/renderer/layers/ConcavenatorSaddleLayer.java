package net.phoi.rot.client.renderer.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.phoi.rot.level.entity.Concavenator;
import net.phoi.rot.util.Helper;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class ConcavenatorSaddleLayer extends GeoLayerRenderer<Concavenator> {
    private static final ResourceLocation MODEL = Helper.createPath("geo/concavenator.geo.json");
    private static final ResourceLocation SADDLE = Helper.createPath("textures/entity/concavenator/concavenator_saddle.png");

    public ConcavenatorSaddleLayer(IGeoRenderer<Concavenator> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn, Concavenator entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entity.isSaddled()) {
            RenderType type = RenderType.entityCutout(SADDLE);
            this.getRenderer().render(this.getEntityModel().getModel(MODEL), entity, partialTicks, type, poseStack, bufferIn, bufferIn.getBuffer(type), packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
