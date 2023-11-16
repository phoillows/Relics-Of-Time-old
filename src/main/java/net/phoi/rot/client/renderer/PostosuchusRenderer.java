package net.phoi.rot.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.phoi.rot.client.model.PostosuchusModel;
import net.phoi.rot.level.entity.Postosuchus;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class PostosuchusRenderer extends GeoEntityRenderer<Postosuchus> {
    public PostosuchusRenderer(EntityRendererProvider.Context context) {
        super(context, new PostosuchusModel());
        this.shadowRadius = 0.7F;
    }
}
