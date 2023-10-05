package net.phoi.rot.level.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidType;
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.level.entity.ai.DinosaurLookAroundGoal;
import net.phoi.rot.level.entity.ai.DinosaurLookAtPlayerGoal;
import net.phoi.rot.level.entity.ai.DinosaurSleepGoal;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType.EDefaultLoopTypes;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class Platyhystrix extends Dinosaur implements IAnimatable {
    private static final EntityDataAccessor<Boolean> DROUSY = SynchedEntityData.defineId(Platyhystrix.class, EntityDataSerializers.BOOLEAN);
    private final AnimationFactory cache = GeckoLibUtil.createFactory(this);
    private int drownTimer = 0;

    public Platyhystrix(EntityType<? extends Dinosaur> entityType, Level level) {
        super(entityType, level);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 1.1F, 1.0F, false);
        this.maxUpStep = 1.0F;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.15D, true));
        this.goalSelector.addGoal(2, new DinosaurSleepGoal(this, 600) {
            @Override
            public boolean canUse() {
                return !isInWater() && super.canUse();
            }

            @Override
            public boolean canContinueToUse() {
                return !isInWater() && super.canContinueToUse();
            }
        });
        this.goalSelector.addGoal(3, new RandomSwimmingGoal(this, 1.0D, 5));
        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(6, new DinosaurLookAtPlayerGoal(this));
        this.goalSelector.addGoal(7, new DinosaurLookAroundGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this).setAlertOthers());
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
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("drousy", this.isDrousy());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.setDrousy(nbt.getBoolean("drousy"));
    }

    @Override
    public void tick() {
        if (!this.isInWater()) {
            if (!this.level.isClientSide) {
                drownTimer++;
                RelicsOfTime.LOGGER.info("Platyhystrix drown timer: " + drownTimer);
                if (drownTimer > 100) {
                    this.setDrousy(true);
                }
                if (drownTimer > 200) {
                    this.hurt(DamageSource.DROWN, 1.0F);
                }
            }
        } else {
            drownTimer = 0;
            this.setDrousy(false);
        }
        super.tick();
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), travelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));

        } else if (this.isSleeping()) {
            this.navigation.stop();
            this.setDeltaMovement(Vec3.ZERO);

        } else {
            super.travel(travelVector);
        }
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new AmphibiousPathNavigation(this, level);
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
    public MobType getMobType() {
        return MobType.WATER;
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return dimensions.height * 0.7F;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
        return null;
    }


    // Geo animations
    protected static final AnimationBuilder IDLE = new AnimationBuilder().addAnimation("idle", EDefaultLoopTypes.LOOP);
    protected static final AnimationBuilder WALK = new AnimationBuilder().addAnimation("walk", EDefaultLoopTypes.LOOP);
    protected static final AnimationBuilder SLEEP = new AnimationBuilder().addAnimation("sleep", EDefaultLoopTypes.LOOP);

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 2, (event) -> {
            if (this.isSleeping()) {
                event.getController().setAnimation(SLEEP);
            } else {
                if (event.isMoving()) {
                    event.getController().setAnimation(WALK);
                } else {
                    event.getController().setAnimation(IDLE);
                }
            }
            return PlayState.CONTINUE;
        }));
    }

    @Override
    public AnimationFactory getFactory() {
        return cache;
    }
}
