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
    @Override
    public ResourceLocation getModelResource(Protoceratops entity) {
        return createPath("geo/protoceratops.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Protoceratops entity) {
        return entity.isSleeping() ? createPath("textures/entity/protoceratops/protoceratops_sleeping.png") : createPath("textures/entity/protoceratops/protoceratops.png");
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
