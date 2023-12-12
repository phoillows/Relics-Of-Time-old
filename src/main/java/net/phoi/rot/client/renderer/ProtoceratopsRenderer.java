package net.phoi.rot.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.phoi.rot.client.model.ProtoceratopsModel;
import net.phoi.rot.level.entity.Protoceratops;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ProtoceratopsRenderer extends GeoEntityRenderer<Protoceratops> {
    public ProtoceratopsRenderer(EntityRendererProvider.Context context) {
        super(context, new ProtoceratopsModel());
        this.shadowRadius = 0.5F;
    }
}
