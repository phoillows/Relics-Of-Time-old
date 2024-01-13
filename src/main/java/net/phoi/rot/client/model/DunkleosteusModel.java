package net.phoi.rot.client.model;

import net.minecraft.resources.ResourceLocation;
import net.phoi.rot.level.entity.Dunkleosteus;
import net.phoi.rot.util.Helper;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DunkleosteusModel extends AnimatedGeoModel<Dunkleosteus> {
    @Override
    public ResourceLocation getModelResource(Dunkleosteus entity) {
        return Helper.createPath("geo/dunkleosteus.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Dunkleosteus entity) {
        return Helper.createPath("textures/entity/dunkleosteus/dunkleosteus.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Dunkleosteus entity) {
        return Helper.createPath("animations/dunkleosteus.animation.json");
    }
}
