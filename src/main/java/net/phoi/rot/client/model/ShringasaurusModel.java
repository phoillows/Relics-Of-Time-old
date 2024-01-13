package net.phoi.rot.client.model;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.phoi.rot.level.entity.Shringasaurus;
import net.phoi.rot.util.Helper;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class ShringasaurusModel extends AnimatedGeoModel<Shringasaurus> {
    @Override
    public ResourceLocation getModelResource(Shringasaurus entity) {
        return Helper.createPath("geo/shringasaurus.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Shringasaurus entity) {
        return Helper.createPath("textures/entity/shringasaurus.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Shringasaurus entity) {
        return Helper.createPath("animations/shringasaurus.animation.json");
    }

    @Override
    public void setCustomAnimations(Shringasaurus animatable, int instanceId, AnimationEvent animationEvent) {
        super.setCustomAnimations(animatable, instanceId, animationEvent);
        IBone head = this.getAnimationProcessor().getBone("bone");
        EntityModelData modelData = (EntityModelData)animationEvent.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null && !animatable.isSleeping()) {
            head.setRotationX(modelData.headPitch * Mth.DEG_TO_RAD);
            head.setRotationY(modelData.netHeadYaw * Mth.DEG_TO_RAD);
        }
    }
}
