package net.phoi.rot.client.model;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.phoi.rot.level.entity.Postosuchus;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import static net.phoi.rot.util.Helper.createPath;

public class PostosuchusModel extends AnimatedGeoModel<Postosuchus> {
    @Override
    public ResourceLocation getModelResource(Postosuchus entity) {
        return createPath("geo/postosuchus.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Postosuchus entity) {
        return entity.isSleeping() ? createPath("textures/entity/postosuchus/postosuchus_sleeping.png") : createPath("textures/entity/postosuchus/postosuchus.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Postosuchus entity) {
        return createPath("animations/postosuchus.animation.json");
    }

    @Override
    public void setCustomAnimations(Postosuchus animatable, int instanceId, AnimationEvent animationEvent) {
        super.setCustomAnimations(animatable, instanceId, animationEvent);
        IBone head = this.getAnimationProcessor().getBone("bone");
        EntityModelData modelData = (EntityModelData)animationEvent.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null && !animatable.isSleeping()) {
            head.setRotationX(modelData.headPitch * Mth.DEG_TO_RAD);
            head.setRotationY(modelData.netHeadYaw * Mth.DEG_TO_RAD);
        }
    }
}
