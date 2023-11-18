package net.phoi.rot.level.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.level.entity.ai.ConcavenatorAttackGoal;
import net.phoi.rot.level.entity.ai.DinosaurLookAroundGoal;
import net.phoi.rot.level.entity.ai.DinosaurLookAtPlayerGoal;
import net.phoi.rot.level.entity.ai.DinosaurSleepGoal;
import net.phoi.rot.level.inventory.ConcavenatorMenu;
import net.phoi.rot.registry.SoundRegistry;
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

public class Concavenator extends Dinosaur implements IAnimatable, Saddleable, PlayerRideable {
    private int callingTimer = 40;
    public int stunnedTimer = 100;
    public int stunAmount = 0;
    protected SimpleContainer inventory;
    public static final EntityDataAccessor<Boolean> DATA_LEADER = SynchedEntityData.defineId(Concavenator.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> DATA_SADDLED = SynchedEntityData.defineId(Concavenator.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> DATA_CALLING = SynchedEntityData.defineId(Concavenator.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> DATA_STUNNED = SynchedEntityData.defineId(Concavenator.class, EntityDataSerializers.BOOLEAN);
    private final AnimationFactory cache = GeckoLibUtil.createFactory(this);
    protected static final AnimationBuilder IDLE = new AnimationBuilder().loop("idle");
    protected static final AnimationBuilder WALK = new AnimationBuilder().loop("walk");
    protected static final AnimationBuilder SLEEP = new AnimationBuilder().loop("sleep");
    protected static final AnimationBuilder CALL = new AnimationBuilder().playOnce("call");
    protected static final AnimationBuilder BITE = new AnimationBuilder().playOnce("bite");
    protected static final AnimationBuilder RUN = new AnimationBuilder().loop("run");
    protected static final AnimationBuilder STUNNED = new AnimationBuilder().loop("stunned");

    public Concavenator(EntityType<? extends Dinosaur> entityType, Level level) {
        super(entityType, level);
        this.maxUpStep = 1.0F;
        this.inventory = new SimpleContainer(16);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.2D) {
            @Override
            public boolean canUse() {
                return isBaby() && super.canUse();
            }
        });
        this.goalSelector.addGoal(1, new ConcavenatorAttackGoal(this));
        this.goalSelector.addGoal(2, new DinosaurSleepGoal(this) {
            @Override
            public boolean canUse() {
                return !isCalling() && !isStunned() && getPassengers().isEmpty() && super.canUse();
            }
        });
        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.2D));
        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new DinosaurLookAtPlayerGoal(this) {
            @Override
            public boolean canUse() {
                return !isStunned() && super.canUse();
            }

            @Override
            public boolean canContinueToUse() {
                return !isStunned() && super.canContinueToUse();
            }
        });
        this.goalSelector.addGoal(5, new DinosaurLookAroundGoal(this) {
            @Override
            public boolean canUse() {
                return !isStunned() && super.canUse();
            }
            @Override
            public boolean canContinueToUse() {
                return !isStunned() &&  super.canContinueToUse();
            }
        });
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true, (entity) -> !isBaby()));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.2F)
                .add(Attributes.ATTACK_DAMAGE, 5.0F)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.3F);
    }

    public boolean isLeader() {
        return this.entityData.get(DATA_LEADER);
    }

    public void setLeader(boolean leader) {
        this.entityData.set(DATA_LEADER, leader);
    }

    public boolean isCalling() {
        return this.entityData.get(DATA_CALLING);
    }

    public void setCalling(boolean calling) {
        this.entityData.set(DATA_CALLING, calling);
    }

    public boolean isStunned() {
        return this.entityData.get(DATA_STUNNED);
    }

    public void setStunned(boolean stunned) {
        this.entityData.set(DATA_STUNNED, stunned);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_LEADER, false);
        this.entityData.define(DATA_SADDLED, false);
        this.entityData.define(DATA_CALLING, false);
        this.entityData.define(DATA_STUNNED, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("Leader", this.isLeader());
        nbt.putBoolean("Saddled", this.isSaddled());
        nbt.putBoolean("Calling", this.isCalling());
        nbt.putBoolean("Stunned", this.isStunned());
        if (inventory != null) {
            ListTag nbtList = new ListTag();
            for (int i = 0; i < this.inventory.getContainerSize(); ++i) {
                ItemStack itemstack = this.inventory.getItem(i);
                if (!itemstack.isEmpty()) {
                    CompoundTag CompoundNBT = new CompoundTag();
                    CompoundNBT.putByte("Slot", (byte) i);
                    itemstack.save(CompoundNBT);
                    nbtList.add(CompoundNBT);
                }
            }
            nbt.put("Items", nbtList);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.setLeader(nbt.getBoolean("Leader"));
        this.setSaddled(nbt.getBoolean("Saddled"));
        this.setCalling(nbt.getBoolean("Calling"));
        this.setStunned(nbt.getBoolean("Stunned"));
        if (inventory != null) {
            ListTag nbtList = nbt.getList("Items", 10);
            for (int i = 0; i < nbtList.size(); ++i) {
                CompoundTag CompoundNBT = nbtList.getCompound(i);
                int j = CompoundNBT.getByte("Slot") & 255;
                this.inventory.setItem(j, ItemStack.of(CompoundNBT));
            }
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (this.isSaddled() && !this.isSleeping()) {
            if (player.isShiftKeyDown()) {
                this.openConcavInventory(player, this);
            } else {
                player.startRiding(this);
            }
        }
        return super.mobInteract(player, hand);
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (!this.isStunned() && this.isVehicle() && getControllingPassenger() instanceof Player) {
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
                    this.setRunning(true);
                    this.setSpeed(0.19F);
                } else {
                    this.setRunning(false);
                    this.setSpeed(0.07F);
                }
                super.travel(new Vec3(f, travelVector.y, f1));
            }
        } else if (this.isCalling() || this.isStunned()) {
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
    public void tick() {
        if (!this.level.isClientSide) {
            if (this.isCalling()) {
                callingTimer--;
                if (callingTimer <= 0) {
                    callingTimer = 40;
                    this.setCalling(false);
                }
            }
            if (this.isStunned()) {
                this.heal(1);
                stunnedTimer--;
                if (stunnedTimer <= 0) {
                    stunnedTimer = 100;
                    stunAmount++;
                    this.setStunned(false);
                }
            }
        }
        super.tick();
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData groupData, @Nullable CompoundTag nbt) {
        boolean foundLeader = false;
        for (Concavenator concav : level.getEntitiesOfClass(Concavenator.class, this.getBoundingBox().inflate(24))) {
            if (!concav.isBaby()) {
                if (concav.isLeader()) {
                    foundLeader = true;
                    this.entityData.set(DATA_LEADER, false);
                    break;
                } else {
                    foundLeader = false;
                    this.entityData.set(DATA_LEADER, true);
                }
            }
        }

        this.entityData.set(DATA_LEADER, !foundLeader);
        if (this.isLeader()) {
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
            for (int i = 0; i < inventory.getContainerSize(); i++) {
                this.spawnAtLocation(inventory.getItem(i));
            }
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
        return this.isAlive() && this.isTame() && !this.isBaby() && !this.isSleeping();
    }

    @Override
    public void equipSaddle(@Nullable SoundSource source) {
        this.inventory.setItem(0, new ItemStack(Items.SADDLE));
        this.setSaddled(true);
        this.setLeader(false);
        if (source != null) {
            this.level.playSound((Player)null, this.getX(), this.getY(), this.getZ(), SoundEvents.HORSE_SADDLE, source, 1.0F, 1.0F);
        }
    }

    @Override
    public boolean isSaddled() {
        return this.entityData.get(DATA_SADDLED);
    }

    public void setSaddled(boolean saddled) {
        this.entityData.set(DATA_SADDLED, saddled);
    }

    public void call() {
        this.setCalling(true);
        this.level.playSound((Player)null, this.getX(), this.getY(), this.getZ(), SoundRegistry.CONCAVENATOR_CALL, SoundSource.HOSTILE, 2.0F, 1.0F);
        for (Concavenator concavenator : this.level.getEntitiesOfClass(Concavenator.class, this.getBoundingBox().inflate(16))) {
            concavenator.setSleeping(false);
        }
    }

    private void openConcavInventory(Player player, Concavenator concav) {
        if (!this.level.isClientSide && !this.hasPassenger(player)) {
            NetworkHooks.openScreen((ServerPlayer) player, new MenuProvider() {
                @Override
                public Component getDisplayName() {
                    return Component.translatable("gui.rot.concavenator");
                }

                @Override
                public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
                    return new ConcavenatorMenu(pContainerId, inventory, pPlayerInventory, concav);
                }
            });
        }
    }

    private <E extends IAnimatable> PlayState basicPredicate(AnimationEvent<E> event) {
        if (this.isSleeping()) {
            event.getController().setAnimation(SLEEP);
            return PlayState.CONTINUE;

        } else if (this.isStunned()) {
            event.getController().setAnimation(STUNNED);
            return PlayState.CONTINUE;

        } else if (this.isCalling()) {
            event.getController().setAnimation(CALL);
            return PlayState.CONTINUE;

        } else if (event.isMoving()) {
            event.getController().setAnimation(this.isRunning() ? RUN : WALK);
            return PlayState.CONTINUE;
        } else {
            event.getController().setAnimation(IDLE);
            return PlayState.CONTINUE;
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
        data.addAnimationController(new AnimationController<>(this, "Basic Controller", 5, this::basicPredicate));
        data.addAnimationController(new AnimationController<>(this, "Attack Controller", 5, this::attackPredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return cache;
    }
}
