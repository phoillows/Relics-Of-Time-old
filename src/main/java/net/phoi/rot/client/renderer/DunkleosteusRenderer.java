package net.phoi.rot.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.phoi.rot.client.model.DunkleosteusModel;
import net.phoi.rot.level.entity.Dunkleosteus;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DunkleosteusRenderer extends GeoEntityRenderer<Dunkleosteus> {
    public DunkleosteusRenderer(EntityRendererProvider.Context context) {
        super(context, new DunkleosteusModel());
        this.shadowRadius = 1.0F;
    }
}
