package net.phoi.rot.level.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.level.entity.ai.ConcavenatorAttackGoal;
import net.phoi.rot.level.entity.ai.DinosaurLookAroundGoal;
import net.phoi.rot.level.entity.ai.DinosaurLookAtPlayerGoal;
import net.phoi.rot.level.entity.ai.DinosaurSleepGoal;
import net.phoi.rot.registry.SoundRegistry;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType.EDefaultLoopTypes;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class Concavenator extends Dinosaur implements Saddleable, PlayerRideable, IAnimatable {
    public static final EntityDataAccessor<Boolean> IS_LEADER = SynchedEntityData.defineId(Concavenator.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> SADDLED = SynchedEntityData.defineId(Concavenator.class, EntityDataSerializers.BOOLEAN);
    private final AnimationFactory cache = GeckoLibUtil.createFactory(this);

    public Concavenator(EntityType<? extends Dinosaur> entityType, Level level) {
        super(entityType, level);
        this.maxUpStep = 1.0F;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new ConcavenatorAttackGoal(this));
        this.goalSelector.addGoal(2, new DinosaurSleepGoal(this, 600) {
            @Override
            public boolean canUse() {
                return !isSaddled() && super.canUse();
            }
        });
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1.1D));
        this.goalSelector.addGoal(4, new DinosaurLookAtPlayerGoal(this));
        this.goalSelector.addGoal(5, new DinosaurLookAroundGoal(this));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0F)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.3F)
                .add(Attributes.MOVEMENT_SPEED, 0.2F)
                .add(Attributes.ATTACK_DAMAGE, 5.0F);
    }

    public boolean isLeader() {
        return this.entityData.get(IS_LEADER);
    }

    public void setLeader(boolean leader) {
        this.entityData.set(IS_LEADER, leader);
    }

    public void setSaddled(boolean saddled) {
        this.entityData.set(SADDLED, saddled);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_LEADER, false);
        this.entityData.define(SADDLED, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("leader", this.isLeader());
        nbt.putBoolean("saddled", this.isSaddled());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.setLeader(nbt.getBoolean("leader"));
        this.setSaddled(nbt.getBoolean("saddled"));
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (this.isSaddled()) {
            // Purely for testing purposes, will be changed
            player.startRiding(this);
            this.tame(player);
        }
        return super.mobInteract(player, hand);
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (this.isVehicle() && getControllingPassenger() instanceof Player) {
            LivingEntity entity = this.getControllingPassenger();
            this.setYRot(entity.getYRot());
            this.yRotO = this.getYRot();
            this.setXRot(entity.getXRot() * 0.5F);
            this.setRot(this.getYRot(), this.getXRot());
            this.yBodyRot = this.getYRot();
            this.yHeadRot = this.yBodyRot;
            float f = entity.xxa * 0.5F;
            float f1 = entity.zza;

            if (this.isControlledByLocalInstance()) {
                if (this.getControllingPassenger() instanceof Player player && player.isSprinting()) {
                    this.setSprinting(true);
                    this.setSpeed(0.25F);
                } else {
                    this.setSprinting(false);
                    this.setSpeed(0.08F);
                }
                super.travel(new Vec3(f, travelVector.y, f1));
            }
        } else if (this.isSleeping()) {
            this.navigation.stop();
            this.setDeltaMovement(Vec3.ZERO);
        } else {
            super.travel(travelVector);
        }
    }

    @Override
    public void positionRider(Entity passenger) {
        super.positionRider(passenger);
        if (this.hasPassenger(passenger)) {
            if (this.isSprinting()) {
                Vec3 vec3 = (new Vec3((double) 0.2D, 1.0D, 0.0D)).yRot(-this.getYRot() * ((float) Math.PI / 180F) - ((float) Math.PI / 2F));
                passenger.setPos(this.getX() + vec3.x, this.getY() + vec3.y, this.getZ() + vec3.z);
            } else {
                Vec3 vec3 = (new Vec3((double) 0.1D, 1.05D, 0.0D)).yRot(-this.getYRot() * ((float) Math.PI / 180F) - ((float) Math.PI / 2F));
                passenger.setPos(this.getX() + vec3.x, this.getY() + vec3.y, this.getZ() + vec3.z);
            }
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.updateSwingTime();
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData groupData, @Nullable CompoundTag nbt) {
        boolean foundLeader = false;
        for (Concavenator concav : level.getEntitiesOfClass(Concavenator.class, this.getBoundingBox().inflate(24))) {
            if (concav.entityData.get(IS_LEADER).equals(true)) {
                foundLeader = true;
                this.entityData.set(IS_LEADER, false);
                break;
            } else {
                foundLeader = false;
                this.entityData.set(IS_LEADER, true);
            }
        }

        this.entityData.set(IS_LEADER, !foundLeader);
        if (this.entityData.get(IS_LEADER).equals(true)) {
            RelicsOfTime.LOGGER.info("Current concavenator has been chosen as leader");
        }
        return super.finalizeSpawn(level, difficulty, spawnType, groupData, nbt);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundRegistry.CONCAVENATOR_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundRegistry.CONCAVENATOR_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundRegistry.CONCAVENATOR_DEATH;
    }

    @Override
    protected void dropEquipment() {
        if (this.isSaddled()) {
            this.spawnAtLocation(Items.SADDLE);
        }
        super.dropEquipment();
    }

    @Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        return ((LivingEntity)this.getFirstPassenger());
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
        return null;
    }

    @Override
    public boolean isSaddleable() {
        return this.isAlive() && !this.isSleeping();
    }

    @Override
    public void equipSaddle(@Nullable SoundSource source) {
        this.entityData.set(SADDLED, true);
        if (source != null) {
            this.level.playSound((Player)null, this.getX(), this.getY(), this.getZ(), SoundEvents.HORSE_SADDLE, source, 1.0F, 1.0F);
        }
    }

    @Override
    public boolean isSaddled() {
        return this.entityData.get(SADDLED);
    }


    // Geo animations
    protected static final AnimationBuilder IDLE = new AnimationBuilder().addAnimation("idle", EDefaultLoopTypes.LOOP);
    protected static final AnimationBuilder WALK = new AnimationBuilder().addAnimation("walk", EDefaultLoopTypes.LOOP);
    protected static final AnimationBuilder SLEEP = new AnimationBuilder().addAnimation("sleep", EDefaultLoopTypes.LOOP);
    protected static final AnimationBuilder CALL = new AnimationBuilder().addAnimation("call", EDefaultLoopTypes.PLAY_ONCE);
    protected static final AnimationBuilder BITE = new AnimationBuilder().addAnimation("bite", EDefaultLoopTypes.PLAY_ONCE);
    protected static final AnimationBuilder RUN = new AnimationBuilder().addAnimation("run", EDefaultLoopTypes.LOOP);
    protected static final AnimationBuilder STUNNED = new AnimationBuilder().addAnimation("stunned", EDefaultLoopTypes.LOOP);

    private <E extends IAnimatable> PlayState basicPredicate(AnimationEvent<E> event) {
        if (this.isSleeping()) {
            event.getController().setAnimation(SLEEP);
            return PlayState.CONTINUE;
        } else {
            if (event.isMoving()) {
                event.getController().setAnimation(this.isSprinting() ? RUN : WALK);
                return PlayState.CONTINUE;

            } else {
                event.getController().setAnimation(IDLE);
                return PlayState.CONTINUE;
            }
        }
    }

    private PlayState attackPredicate(AnimationEvent event) {
        if (this.swinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(BITE);
            this.swinging = false;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "basic", 2, this::basicPredicate));
        data.addAnimationController(new AnimationController<>(this, "attack", 2, this::attackPredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return cache;
    }
}
