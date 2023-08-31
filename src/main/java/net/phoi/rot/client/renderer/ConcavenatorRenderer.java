package net.phoi.rot.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.phoi.rot.client.model.ConcavenatorModel;
import net.phoi.rot.client.renderer.layers.ConcavenatorLayer;
import net.phoi.rot.level.entity.Concavenator;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ConcavenatorRenderer extends GeoEntityRenderer<Concavenator> {
    public ConcavenatorRenderer(EntityRendererProvider.Context context) {
        super(context, new ConcavenatorModel());
        this.shadowRadius = 0.75F;
        this.addLayer(new ConcavenatorLayer(this));
    }
}
