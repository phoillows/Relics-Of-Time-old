package net.phoi.rot.client.model;

import net.minecraft.resources.ResourceLocation;
import net.phoi.rot.level.entity.Dunkleosteus;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import static net.phoi.rot.util.Helper.createPath;

public class DunkleosteusModel extends AnimatedGeoModel<Dunkleosteus> {
    @Override
    public ResourceLocation getModelResource(Dunkleosteus entity) {
        return createPath("geo/dunkleosteus.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Dunkleosteus entity) {
        return entity.isAggressive() ? createPath("textures/entity/dunkleosteus/dunkleosteus_angry.png") : createPath("textures/entity/dunkleosteus/dunkleosteus.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Dunkleosteus entity) {
        return createPath("animations/dunkleosteus.animation.json");
    }
}
