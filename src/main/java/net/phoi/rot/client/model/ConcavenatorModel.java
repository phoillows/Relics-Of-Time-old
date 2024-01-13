package net.phoi.rot.client.model;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.phoi.rot.level.entity.Concavenator;
import net.phoi.rot.util.Helper;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class ConcavenatorModel extends AnimatedGeoModel<Concavenator> {
    private static final ResourceLocation DEFAULT = Helper.createPath("textures/entity/concavenator/concavenator.png");
    private static final ResourceLocation SLEEPING = Helper.createPath("textures/entity/concavenator/concavenator_sleeping.png");
    private static final ResourceLocation BABY = Helper.createPath("textures/entity/concavenator/hatchling_concavenator.png");
    private static final ResourceLocation BABY_SLEEPING = Helper.createPath("textures/entity/concavenator/hatchling_concavenator_sleeping.png");

    @Override
    public ResourceLocation getModelResource(Concavenator entity) {
        return entity.isBaby() ? Helper.createPath("geo/hatchling_concavenator.geo.json") : Helper.createPath("geo/concavenator.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Concavenator entity) {
        if (entity.isSleeping()) {
            return entity.isBaby() ? BABY_SLEEPING : SLEEPING;
        }
        return entity.isBaby() ? BABY : DEFAULT;
    }

    @Override
    public ResourceLocation getAnimationResource(Concavenator entity) {
        return entity.isBaby() ? Helper.createPath("animations/hatchling_concavenator.animation.json") : Helper.createPath("animations/concavenator.animation.json");
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
