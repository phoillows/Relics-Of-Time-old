package net.phoi.rot.level.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidType;
import net.phoi.rot.level.entity.ai.*;
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
    private static final EntityDataAccessor<Boolean> DATA_DROUSY = SynchedEntityData.defineId(Platyhystrix.class, EntityDataSerializers.BOOLEAN);
    private final AnimationFactory cache = GeckoLibUtil.createFactory(this);
    protected static final AnimationBuilder IDLE = new AnimationBuilder().addAnimation("idle", EDefaultLoopTypes.LOOP);
    protected static final AnimationBuilder WALK = new AnimationBuilder().addAnimation("walk", EDefaultLoopTypes.LOOP);
    protected static final AnimationBuilder SLEEP = new AnimationBuilder().addAnimation("sleep", EDefaultLoopTypes.LOOP);

    public Platyhystrix(EntityType<? extends Dinosaur> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PlatyhystrixDrowseGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.15D, true));
        this.goalSelector.addGoal(2, new DinosaurSleepGoal(this));
        this.goalSelector.addGoal(3, new DinosaurGoToWaterGoal(this, 1.1D, 16) {
            @Override
            public boolean canUse() {
                return isDrousy() && super.canUse();
            }
            @Override
            public boolean canContinueToUse() {
                return isDrousy() && super.canContinueToUse();
            }
        });
        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new DinosaurLookAtPlayerGoal(this));
        this.goalSelector.addGoal(5, new DinosaurLookAroundGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this).setAlertOthers());
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.1F)
                .add(Attributes.ATTACK_DAMAGE, 2.0F);
    }

    public boolean isDrousy() {
        return this.entityData.get(DATA_DROUSY);
    }

    public void setDrousy(boolean drousy) {
        this.entityData.set(DATA_DROUSY, drousy);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_DROUSY, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("Drousy", this.isDrousy());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.setDrousy(nbt.getBoolean("Drousy"));
    }

    @Override
    public void tick() {
        if (this.isUnderWater()) {
            this.setDeltaMovement(this.getDeltaMovement().x, 0.08D, this.getDeltaMovement().z);
        }
        super.tick();
    }

    @Override
    public void travel(Vec3 pTravelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(0.05F, pTravelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
        }
        super.travel(pTravelVector);
    }

    @Override
    protected PathNavigation createNavigation(Level pLevel) {
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
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return dimensions.height * 0.7F;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
        return null;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "Controller", 5, (event) -> {
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
