package net.phoi.rot.level.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.phoi.rot.registry.EntityRegistry;
import net.phoi.rot.registry.ItemRegistry;

public class TemplateChestBoat extends ChestBoat {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(Boat.class, EntityDataSerializers.INT);

    public TemplateChestBoat(EntityType<? extends Boat> entityType, Level level) {
        super(entityType, level);
    }

    public TemplateChestBoat(Level level, double x, double y, double z) {
        this(EntityRegistry.CHEST_BOAT.get(), level);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    public Item getDropItem() {
        switch (this.getVariant()) {
            case ARCHAEOPTERIS -> {
                return ItemRegistry.ARCHAEOPTERIS_CHEST_BOAT.get();
            }
        }
        return super.getDropItem();
    }

    public TemplateBoat.Type getVariant() {
        return TemplateBoat.Type.byId(this.entityData.get(DATA_ID_TYPE));
    }

    public void setVariant(TemplateBoat.Type variant) {
        this.entityData.set(DATA_ID_TYPE, variant.ordinal());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE, TemplateBoat.Type.ARCHAEOPTERIS.ordinal());
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putString("type", this.getVariant().getSerializedName());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        if (nbt.contains("type")) {
            this.setVariant(TemplateBoat.Type.byName(nbt.getString("type")));
        }
    }
}
