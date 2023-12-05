package net.phoi.rot.level.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class Dinosaur extends TamableAnimal {
    protected static final EntityDataAccessor<Boolean> DATA_SLEEPING = SynchedEntityData.defineId(Dinosaur.class, EntityDataSerializers.BOOLEAN);
    protected static final EntityDataAccessor<Boolean> DATA_RUNNNING = SynchedEntityData.defineId(Dinosaur.class, EntityDataSerializers.BOOLEAN);

    public Dinosaur(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }

    public boolean isSleeping() {
        return this.entityData.get(DATA_SLEEPING);
    }

    public void setSleeping(boolean sleeping) {
        this.entityData.set(DATA_SLEEPING, sleeping);
    }

    public boolean isRunning() {
        return this.entityData.get(DATA_RUNNNING);
    }

    public void setRunning(boolean running) {
        this.entityData.set(DATA_RUNNNING, running);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_SLEEPING, false);
        this.entityData.define(DATA_RUNNNING, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("Sleeping", this.isSleeping());
        nbt.putBoolean("Running", this.isRunning());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.setSleeping(nbt.getBoolean("Sleeping"));
        this.setRunning(nbt.getBoolean("Running"));
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (this.isSleeping()) {
            this.navigation.stop();
            this.setDeltaMovement(Vec3.ZERO);
        }
        super.travel(travelVector);
    }

    @Override
    public boolean hurt(DamageSource damageSource, float amount) {
        this.setSleeping(false);
        return super.hurt(damageSource, amount);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
        return null;
    }
}
