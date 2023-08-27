package net.phoi.rot.client.model;

import net.minecraft.resources.ResourceLocation;
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.level.entity.Furcacauda;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FurcacaudaModel extends AnimatedGeoModel<Furcacauda> {

    @Override
    public ResourceLocation getModelResource(Furcacauda entity) {
        return new ResourceLocation(RelicsOfTime.MODID, "geo/furcacauda.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Furcacauda entity) {
        return new ResourceLocation(RelicsOfTime.MODID, "textures/entity/furcacauda.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Furcacauda entity) {
        return new ResourceLocation(RelicsOfTime.MODID, "animations/furcacauda.animation.json");
    }
}
