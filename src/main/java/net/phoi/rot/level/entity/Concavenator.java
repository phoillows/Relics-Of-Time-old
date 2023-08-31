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
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.level.entity.ai.ConcavenatorAttackGoal;
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

public class Concavenator extends TamableAnimal implements Saddleable, IAnimatable {
    public static final EntityDataAccessor<Boolean> DATA_SADDLE = SynchedEntityData.defineId(Concavenator.class, EntityDataSerializers.BOOLEAN);
    private final AnimationFactory cache = GeckoLibUtil.createFactory(this);
    public boolean isLeader = false;

    public Concavenator(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new ConcavenatorAttackGoal(this));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1.1D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0F)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.3F)
                .add(Attributes.MOVEMENT_SPEED, 0.2F)
                .add(Attributes.ATTACK_DAMAGE, 5.0F);
    }

    public boolean getSaddleData() {
        return this.entityData.get(DATA_SADDLE);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_SADDLE, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("saddled", this.getSaddleData());
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        if (nbt.contains("saddled")) {
            this.entityData.set(DATA_SADDLE, nbt.getBoolean("saddled"));
        }
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
    public void aiStep() {
        super.aiStep();
        this.updateSwingTime();
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData groupData, @Nullable CompoundTag nbt) {
        boolean foundLeader = false;
        for (Concavenator concav : level.getEntitiesOfClass(Concavenator.class, this.getBoundingBox().inflate(24))) {
            if (concav.isLeader) {
                foundLeader = true;
                this.isLeader = false;
                break;
            } else {
                foundLeader = false;
                this.isLeader = true;
            }
        }

        this.isLeader = !foundLeader;
        if (this.isLeader) {
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

    @Override
    public double getPassengersRidingOffset() {
        return 1.35D;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
        return null;
    }

    @Override
    public boolean isSaddleable() {
        return this.isAlive();
    }

    @Override
    public void equipSaddle(@Nullable SoundSource source) {
        this.entityData.set(DATA_SADDLE, true);
        if (source != null) {
            this.level.playSound((Player)null, this.getX(), this.getY(), this.getZ(), SoundEvents.HORSE_SADDLE, source, 1.0F, 1.0F);
        }
    }

    @Override
    public boolean isSaddled() {
        return this.entityData.get(DATA_SADDLE);
    }


    // Geo animations
    protected static final AnimationBuilder IDLE = new AnimationBuilder().addAnimation("idle", EDefaultLoopTypes.LOOP);
    protected static final AnimationBuilder WALK = new AnimationBuilder().addAnimation("walk", EDefaultLoopTypes.LOOP);
    protected static final AnimationBuilder SLEEP = new AnimationBuilder().addAnimation("sleep", EDefaultLoopTypes.LOOP);
    protected static final AnimationBuilder CALL = new AnimationBuilder().addAnimation("call", EDefaultLoopTypes.PLAY_ONCE);
    protected static final AnimationBuilder BITE = new AnimationBuilder().addAnimation("bite", EDefaultLoopTypes.PLAY_ONCE);
    protected static final AnimationBuilder RUN = new AnimationBuilder().addAnimation("run", EDefaultLoopTypes.LOOP);

    private <E extends IAnimatable> PlayState walkPredicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(this.isSprinting() ? RUN : WALK);
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(IDLE);
        return PlayState.CONTINUE;
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
        data.addAnimationController(new AnimationController<>(this, "walk", 2, this::walkPredicate));
        data.addAnimationController(new AnimationController<>(this, "attack", 2, this::attackPredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return cache;
    }
}
