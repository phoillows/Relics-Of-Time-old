package net.phoi.rot.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.phoi.rot.client.model.PlatyhystrixModel;
import net.phoi.rot.level.entity.Platyhystrix;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class PlatyhystrixRenderer extends GeoEntityRenderer<Platyhystrix> {
    public PlatyhystrixRenderer(EntityRendererProvider.Context context) {
        super(context, new PlatyhystrixModel());
        this.shadowRadius = 0.45F;
    }

    @Override
    protected float getSwingMotionAnimThreshold() {
        return 0.001F;
    }
}
