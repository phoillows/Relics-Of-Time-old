package net.phoi.rot.client.model;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.level.entity.Concavenator;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import static net.phoi.rot.util.Helper.createPath;

public class ConcavenatorModel extends AnimatedGeoModel<Concavenator> {

    @Override
    public ResourceLocation getModelResource(Concavenator entity) {
        return createPath("geo/concavenator.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Concavenator entity) {
        if (entity.isSleeping()) {
            return createPath("textures/entity/concavenator/concavenator_sleeping.png");
        }
        return createPath("textures/entity/concavenator/concavenator.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Concavenator entity) {
        return createPath("animations/concavenator.animation.json");
    }

    @Override
    public void setCustomAnimations(Concavenator animatable, int instanceId, AnimationEvent animationEvent) {
        super.setCustomAnimations(animatable, instanceId, animationEvent);
        IBone head = this.getAnimationProcessor().getBone("Head");
        EntityModelData modelData = (EntityModelData)animationEvent.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null && !animatable.isSleeping()) {
            head.setRotationX(modelData.headPitch * Mth.DEG_TO_RAD);
            head.setRotationY(modelData.netHeadYaw * Mth.DEG_TO_RAD);
        }
    }
}
