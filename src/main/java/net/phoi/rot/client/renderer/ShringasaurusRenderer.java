package net.phoi.rot.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.phoi.rot.client.model.ShringasaurusModel;
import net.phoi.rot.level.entity.Shringasaurus;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ShringasaurusRenderer extends GeoEntityRenderer<Shringasaurus> {
    public ShringasaurusRenderer(EntityRendererProvider.Context context) {
        super(context, new ShringasaurusModel());
        this.shadowRadius = 0.6F;
    }
}
