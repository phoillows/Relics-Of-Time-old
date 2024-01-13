package net.phoi.rot.client.model;

import net.minecraft.resources.ResourceLocation;
import net.phoi.rot.level.entity.Furcacauda;
import net.phoi.rot.util.Helper;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FurcacaudaModel extends AnimatedGeoModel<Furcacauda> {
    @Override
    public ResourceLocation getModelResource(Furcacauda entity) {
        return Helper.createPath("geo/furcacauda.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Furcacauda entity) {
        return Helper.createPath("textures/entity/furcacauda.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Furcacauda entity) {
        return Helper.createPath("animations/furcacauda.animation.json");
    }
}
