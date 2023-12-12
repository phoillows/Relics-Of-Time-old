package net.phoi.rot.client.model;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.phoi.rot.level.entity.Protoceratops;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import static net.phoi.rot.util.Helper.createPath;

public class ProtoceratopsModel extends AnimatedGeoModel<Protoceratops> {
    private static final ResourceLocation DEFAULT = createPath("textures/entity/protoceratops/protoceratops.png");
    private static final ResourceLocation SLEEPING = createPath("textures/entity/protoceratops/sleeping_protoceratops.png");
    private static final ResourceLocation BABY = createPath("textures/entity/protoceratops/baby_protoceratops.png");
    private static final ResourceLocation BABY_SLEEPING = createPath("textures/entity/protoceratops/baby_sleeping_protoceratops.png");

    @Override
    public ResourceLocation getModelResource(Protoceratops entity) {
        return entity.isBaby() ? createPath("geo/baby_protoceratops.geo.json") :  createPath("geo/protoceratops.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Protoceratops entity) {
        if (entity.isSleeping()) {
            return entity.isBaby() ? BABY_SLEEPING : SLEEPING;
        }
        return entity.isBaby() ? BABY : DEFAULT;
    }

    @Override
    public ResourceLocation getAnimationResource(Protoceratops entity) {
        return createPath("animations/protoceratops.animation.json");
    }

    @Override
    public void setCustomAnimations(Protoceratops animatable, int instanceId, AnimationEvent animationEvent) {
        super.setCustomAnimations(animatable, instanceId, animationEvent);
        IBone head = this.getAnimationProcessor().getBone("Head");
        EntityModelData modelData = (EntityModelData)animationEvent.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null && !animatable.isSleeping()) {
            head.setRotationX(modelData.headPitch * Mth.DEG_TO_RAD);
            head.setRotationY(modelData.netHeadYaw * Mth.DEG_TO_RAD);
        }
    }
}
