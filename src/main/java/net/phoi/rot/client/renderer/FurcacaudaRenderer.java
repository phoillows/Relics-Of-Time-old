package net.phoi.rot.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.phoi.rot.client.model.FurcacaudaModel;
import net.phoi.rot.level.entity.Furcacauda;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class FurcacaudaRenderer extends GeoEntityRenderer<Furcacauda> {
    public FurcacaudaRenderer(EntityRendererProvider.Context context) {
        super(context, new FurcacaudaModel());
        this.shadowRadius = 0.2F;
    }
}
