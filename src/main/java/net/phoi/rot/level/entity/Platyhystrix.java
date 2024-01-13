package net.phoi.rot.level.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidType;
import net.phoi.rot.level.entity.ai.*;
import net.phoi.rot.registry.EntityRegistry;
import net.phoi.rot.registry.ItemRegistry;
import net.phoi.rot.registry.SoundRegistry;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType.EDefaultLoopTypes;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class Platyhystrix extends Dinosaur implements IAnimatable, Bucketable {
    protected static final EntityDataAccessor<Boolean> DROUSY = SynchedEntityData.defineId(Platyhystrix.class, EntityDataSerializers.BOOLEAN);
    protected static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(Platyhystrix.class, EntityDataSerializers.BOOLEAN);
    private final AnimationFactory cache = GeckoLibUtil.createFactory(this);
    protected static final AnimationBuilder IDLE = new AnimationBuilder().addAnimation("idle", EDefaultLoopTypes.LOOP);
    protected static final AnimationBuilder WALK = new AnimationBuilder().addAnimation("walk", EDefaultLoopTypes.LOOP);
    protected static final AnimationBuilder SLEEP = new AnimationBuilder().addAnimation("sleep", EDefaultLoopTypes.LOOP);
    protected static final AnimationBuilder BABY_IDLE = new AnimationBuilder().addAnimation("animation.baby_platyhystrix.idle", EDefaultLoopTypes.LOOP);
    protected static final AnimationBuilder BABY_WALK = new AnimationBuilder().addAnimation("animation.baby_platyhystrix.walk", EDefaultLoopTypes.LOOP);
    protected static final AnimationBuilder BABY_SWIM = new AnimationBuilder().addAnimation("animation.baby_platyhystrix.swim", EDefaultLoopTypes.LOOP);
    protected static final AnimationBuilder BABY_SWIM_IDLE = new AnimationBuilder().addAnimation("animation.baby_platyhystrix.wateridle", EDefaultLoopTypes.LOOP);

    public Platyhystrix(EntityType<? extends Dinosaur> entityType, Level level) {
        super(entityType, level);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.setPersistenceRequired();
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 15, 0.45F, 1.4F, true);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PlatyhystrixFloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.15D, true));
        this.goalSelector.addGoal(2, new DinosaurGoToWaterGoal(this, 1.1D, 16) {
            @Override
            public boolean canUse() {
                return isDrousy() && super.canUse();
            }
            @Override
            public boolean canContinueToUse() {
                return isDrousy() && super.canContinueToUse();
            }
        });
        this.goalSelector.addGoal(3, new PlatyhystrixSleepGoal(this));
        this.goalSelector.addGoal(3, new PlatyhystrixDrowseGoal(this));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.3D));
        this.goalSelector.addGoal(5, new PlatyhystrixSwimGoal(this, 1.5D, 10));
        this.goalSelector.addGoal(6, new DinosaurLookAtPlayerGoal(this));
        this.goalSelector.addGoal(6, new DinosaurLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.1F)
                .add(Attributes.ATTACK_DAMAGE, 2.0F);
    }

    public boolean isDrousy() {
        return this.entityData.get(DROUSY);
    }

    public void setDrousy(boolean drousy) {
        this.entityData.set(DROUSY, drousy);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DROUSY, false);
        this.entityData.define(FROM_BUCKET, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("Drousy", this.isDrousy());
        nbt.putBoolean("From Bucket", this.fromBucket());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.setDrousy(nbt.getBoolean("Drousy"));
        this.setFromBucket(nbt.getBoolean("From Bucket"));
    }

    @Override
    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        if (this.isBaby()) {
            return Bucketable.bucketMobPickup(pPlayer, pHand, this).orElse(super.mobInteract(pPlayer, pHand));
        }
        return super.mobInteract(pPlayer, pHand);
    }

    @Override
    public void travel(Vec3 pTravelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), pTravelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
        }
        super.travel(pTravelVector);
    }

    @Override
    protected PathNavigation createNavigation(Level pLevel) {
        return new AmphibiousPathNavigation(this, pLevel);
    }

    @Override
    public MobType getMobType() {
        return MobType.WATER;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public boolean isPushedByFluid(FluidType type) {
        return false;
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return dimensions.height * 0.7F;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundRegistry.PLATYHYSTRIX_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundRegistry.PLATYHYSTRIX_HURT;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
        return EntityRegistry.PLATYHYSTRIX.get().create(level);
    }

    @Override
    public boolean fromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }

    @Override
    public void setFromBucket(boolean pFromBucket) {
        this.entityData.set(FROM_BUCKET, pFromBucket);
    }

    @Override
    public void saveToBucketTag(ItemStack pStack) {
        Bucketable.saveDefaultDataToBucketTag(this, pStack);
        CompoundTag tag = pStack.getOrCreateTag();
        tag.putInt("Age", this.getAge());
    }

    @Override
    public void loadFromBucketTag(CompoundTag pTag) {
        Bucketable.loadDefaultDataFromBucketTag(this, pTag);
        if (pTag.contains("Age")) {
            this.setAge(pTag.getInt("Age"));
        }
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ItemRegistry.BABY_PLATYHYSTRIX_BUCKET.get());
    }

    @Override
    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_EMPTY_AXOLOTL;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "Controller", 5, (event) -> {
            if (this.isBaby()) {
                if (this.isInWater()) {
                    event.getController().setAnimation(event.isMoving() ? BABY_SWIM : BABY_SWIM_IDLE);
                } else {
                    event.getController().setAnimation(event.isMoving() ? BABY_WALK : BABY_IDLE);
                }
            } else {
                if (this.isSleeping()) {
                    event.getController().setAnimation(SLEEP);
                } else {
                    event.getController().setAnimation(event.isMoving() ? WALK : IDLE);
                }
            }
            return PlayState.CONTINUE;
        }));
    }

    @Override
    public AnimationFactory getFactory() {
        return cache;
    }

    public static class PlatyhystrixSwimGoal extends RandomSwimmingGoal {
        private final Platyhystrix platyhystrix;

        public PlatyhystrixSwimGoal(Platyhystrix platyhystrix, double speed, int interval) {
            super(platyhystrix, speed, interval);
            this.platyhystrix = platyhystrix;
        }

        @Override
        public boolean canUse() {
            return this.platyhystrix.isBaby() && super.canUse();
        }

        @Override
        public boolean canContinueToUse() {
            return this.platyhystrix.isBaby() && super.canContinueToUse();
        }
    }

    public static class PlatyhystrixSleepGoal extends DinosaurSleepGoal {
        private final Platyhystrix platyhystrix;

        public PlatyhystrixSleepGoal(Platyhystrix platyhystrix) {
            super(platyhystrix);
            this.platyhystrix = platyhystrix;
        }

        @Override
        public boolean canUse() {
            return !this.platyhystrix.isBaby() && super.canUse();
        }

        @Override
        public boolean canContinueToUse() {
            return !this.platyhystrix.isBaby() && super.canContinueToUse();
        }
    }

    public static class PlatyhystrixFloatGoal extends FloatGoal {
        private final Platyhystrix platyhystrix;

        public PlatyhystrixFloatGoal(Platyhystrix platyhystrix) {
            super(platyhystrix);
            this.platyhystrix = platyhystrix;
        }

        @Override
        public boolean canUse() {
            return !this.platyhystrix.isBaby() && super.canUse();
        }

        @Override
        public boolean canContinueToUse() {
            return !this.platyhystrix.isBaby() && super.canContinueToUse();
        }
    }
}
