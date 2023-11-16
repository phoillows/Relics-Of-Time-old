package net.phoi.rot.client.model;

import net.minecraft.resources.ResourceLocation;
import net.phoi.rot.level.entity.Furcacauda;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import static net.phoi.rot.util.Helper.createPath;

public class FurcacaudaModel extends AnimatedGeoModel<Furcacauda> {
    @Override
    public ResourceLocation getModelResource(Furcacauda entity) {
        return createPath("geo/furcacauda.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Furcacauda entity) {
        return createPath("textures/entity/furcacauda.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Furcacauda entity) {
        return createPath("animations/furcacauda.animation.json");
    }
}
