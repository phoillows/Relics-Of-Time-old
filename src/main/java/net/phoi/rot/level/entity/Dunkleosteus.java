package net.phoi.rot.level.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.phoi.rot.level.entity.ai.DunkleosteusJumpGoal;
import net.phoi.rot.util.WaterBreachingNavigation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class Dunkleosteus extends WaterAnimal implements IAnimatable {
    protected static final EntityDataAccessor<Boolean> DATA_BREACHING = SynchedEntityData.defineId(Dunkleosteus.class, EntityDataSerializers.BOOLEAN);
    private final AnimationFactory cache = GeckoLibUtil.createFactory(this);
    protected static final AnimationBuilder IDLE = new AnimationBuilder().loop("animation.dunkleosteus.idle");
    protected static final AnimationBuilder LAND = new AnimationBuilder().loop("animation.dunkleosteus.land");
    protected static final AnimationBuilder SWIM = new AnimationBuilder().loop("animation.dunkleosteus.swim");
    protected static final AnimationBuilder BITE = new AnimationBuilder().playOnce("animation.dunkleosteus.bite");

    public Dunkleosteus(EntityType<? extends WaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.BREACH, 0.0F);
        this.moveControl = new SmoothSwimmingMoveControl(this, 60, 20, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new BreathAirGoal(this));
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.3D, true) {
            @Override
            public boolean canUse() {
                return Dunkleosteus.this.isAggressive() && super.canUse();
            }
            @Override
            public boolean canContinueToUse() {
                return Dunkleosteus.this.isAggressive() && super.canContinueToUse();
            }
        });
        this.goalSelector.addGoal(2, new RandomSwimmingGoal(this, 1.0D, 10));
        this.goalSelector.addGoal(3, new DunkleosteusJumpGoal(this, 10));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
    }

    public static AttributeSupplier.Builder createAttributes() {
        return WaterAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 50.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.9D)
                .add(Attributes.ATTACK_DAMAGE, 9.0D);
    }

    public boolean isBreaching() {
        return this.entityData.get(DATA_BREACHING);
    }

    public void setBreaching(boolean breaching) {
        this.entityData.set(DATA_BREACHING, breaching);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_BREACHING, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("Breaching", this.isBreaching());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setBreaching(pCompound.getBoolean("Breaching"));
    }

    @Override
    protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack stack = pPlayer.getItemInHand(pHand);
        if (this.isAggressive() && stack.is(ItemTags.FISHES)) {
            this.setAggressive(false);
            this.setTarget(null);
            stack.shrink(1);
            this.playSound(SoundEvents.DOLPHIN_EAT);
            for(int i = 0; i < 7; ++i) {
                double d0 = this.random.nextGaussian() * 0.02D;
                double d1 = this.random.nextGaussian() * 0.02D;
                double d2 = this.random.nextGaussian() * 0.02D;
                this.level.addParticle(ParticleTypes.HEART, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
            }
        }
        return super.mobInteract(pPlayer, pHand);
    }

    public void tick() {
        super.tick();
        if (this.isNoAi()) {
            this.setAirSupply(this.getMaxAirSupply());
        }
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
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (pSource.getEntity() instanceof Player player && !player.isCreative()) {
            this.setAggressive(true);
        }
        return super.hurt(pSource, pAmount);
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        this.setAirSupply(this.getMaxAirSupply());
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    @Override
    protected PathNavigation createNavigation(Level pLevel) {
        return new WaterBreachingNavigation(this, pLevel);
    }

    @Override
    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pDimensions) {
        return pDimensions.height * 0.5F;
    }

    @Override
    public int getMaxHeadXRot() {
        return 1;
    }

    @Override
    public int getMaxHeadYRot() {
        return 1;
    }

    @Override
    protected void handleAirSupply(int pAirSupply) {
    }

    @Override
    public int getMaxAirSupply() {
        return 700;
    }

    @Override
    protected int increaseAirSupply(int pCurrentAir) {
        return this.getMaxAirSupply();
    }

    @Override
    public boolean canBreatheUnderwater() {
        return false;
    }

    private <E extends IAnimatable> PlayState basicPredicate(AnimationEvent<E> event) {
        if (this.isInWater()) {
            event.getController().setAnimation(event.isMoving() ? SWIM : IDLE);
        } else if (this.isOnGround()) {
            event.getController().setAnimation(LAND);
        }
        return PlayState.CONTINUE;
    }

    private <E extends IAnimatable> PlayState attackPredicate(AnimationEvent<E> event) {
        if (this.swinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(BITE);
            this.swinging = false;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "Basic Controller", 5, this::basicPredicate));
        data.addAnimationController(new AnimationController<>(this, "Attack Controller", 5, this::attackPredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return cache;
    }
}
