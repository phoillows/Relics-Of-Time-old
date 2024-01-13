package net.phoi.rot.client.model;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.phoi.rot.level.entity.Platyhystrix;
import net.phoi.rot.util.Helper;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class PlatyhystrixModel extends AnimatedGeoModel<Platyhystrix> {
    private static final ResourceLocation DEFAULT = Helper.createPath("textures/entity/concavenator/concavenator.png");
    private static final ResourceLocation SLEEPING = Helper.createPath("textures/entity/concavenator/concavenator_sleeping.png");
    private static final ResourceLocation BABY = Helper.createPath("textures/entity/concavenator/hatchling_concavenator.png");
    private static final ResourceLocation BABY_SLEEPING = Helper.createPath("textures/entity/concavenator/hatchling_concavenator_sleeping.png");

    @Override
    public ResourceLocation getModelResource(Platyhystrix entity) {
        return entity.isBaby() ? Helper.createPath("geo/baby_platyhystrix.geo.json") : Helper.createPath("geo/platyhystrix.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Platyhystrix entity) {
        if (entity.isSleeping()) {
            return Helper.createPath("textures/entity/platyhystrix/platyhystrix_sleeping.png");
        } else if (entity.isDrousy()) {
            return Helper.createPath("textures/entity/platyhystrix/platyhystrix_drousy.png");
        }
        return entity.isBaby() ? Helper.createPath("textures/entity/platyhystrix/baby_platyhystrix.png" ): Helper.createPath("textures/entity/platyhystrix/platyhystrix.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Platyhystrix entity) {
        return entity.isBaby() ? Helper.createPath("animations/baby_platyhystrix.animation.json") : Helper.createPath("animations/platyhystrix.animation.json");
    }

    @Override
    public void setCustomAnimations(Platyhystrix animatable, int instanceId, AnimationEvent animationEvent) {
        super.setCustomAnimations(animatable, instanceId, animationEvent);
        IBone head = this.getAnimationProcessor().getBone("Head");
        EntityModelData modelData = (EntityModelData)animationEvent.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null && !animatable.isSleeping()) {
            head.setRotationX(modelData.headPitch * Mth.DEG_TO_RAD);
            head.setRotationY(modelData.netHeadYaw * Mth.DEG_TO_RAD);
        }
    }
}
