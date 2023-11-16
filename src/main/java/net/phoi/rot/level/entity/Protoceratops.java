package net.phoi.rot.level.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.phoi.rot.level.entity.ai.DinosaurLookAroundGoal;
import net.phoi.rot.level.entity.ai.DinosaurLookAtPlayerGoal;
import net.phoi.rot.level.entity.ai.DinosaurSleepGoal;
import net.phoi.rot.level.entity.ai.ProtoceratopsLayDownGoal;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class Protoceratops extends Dinosaur implements IAnimatable {
    private static final EntityDataAccessor<Boolean> DATA_LAYING = SynchedEntityData.defineId(Protoceratops.class, EntityDataSerializers.BOOLEAN);
    private final AnimationFactory cache = GeckoLibUtil.createFactory(this);

    public Protoceratops(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.3D));
        this.goalSelector.addGoal(2, new DinosaurSleepGoal(this));
        this.goalSelector.addGoal(2, new ProtoceratopsLayDownGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.1D));
        this.goalSelector.addGoal(4, new DinosaurLookAtPlayerGoal(this));
        this.goalSelector.addGoal(4, new DinosaurLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Dinosaur.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.15F);
    }

    public boolean isLaying() {
        return this.entityData.get(DATA_LAYING);
    }

    public void setLaying(boolean laying) {
        this.entityData.set(DATA_LAYING, laying);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_LAYING, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("Laying", this.isLaying());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.setLaying(nbt.getBoolean("Laying"));
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (this.isLaying()) {
            this.navigation.stop();
            this.setDeltaMovement(Vec3.ZERO);
        }
        super.travel(travelVector);
    }

    @Override
    public boolean hurt(DamageSource damageSource, float amount) {
        this.setLaying(false);
        return super.hurt(damageSource, amount);
    }


    // Geo animations
    protected static final AnimationBuilder IDLE = new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP);
    protected static final AnimationBuilder WALK = new AnimationBuilder().addAnimation("walk", ILoopType.EDefaultLoopTypes.LOOP);
    protected static final AnimationBuilder SLEEP = new AnimationBuilder().addAnimation("sleep", ILoopType.EDefaultLoopTypes.LOOP);
    protected static final AnimationBuilder LAY = new AnimationBuilder().addAnimation("lay", ILoopType.EDefaultLoopTypes.LOOP);

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "Controller", 5, (event) -> {
            if (this.isSleeping()) {
                event.getController().setAnimation(SLEEP);
            } else if (this.isLaying()) {
                event.getController().setAnimation(LAY);
            } else if (event.isMoving()) {
                event.getController().setAnimation(WALK);
            } else {
                event.getController().setAnimation(IDLE);
            }
            return PlayState.CONTINUE;
        }));
    }

    @Override
    public AnimationFactory getFactory() {
        return cache;
    }
}
