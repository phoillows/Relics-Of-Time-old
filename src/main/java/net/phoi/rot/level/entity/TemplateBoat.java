package net.phoi.rot.level.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.phoi.rot.registry.BlockRegistry;
import net.phoi.rot.registry.EntityRegistry;
import net.phoi.rot.registry.ItemRegistry;

public class TemplateBoat extends Boat {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(Boat.class, EntityDataSerializers.INT);

    public TemplateBoat(EntityType<? extends Boat> entityType, Level level) {
        super(entityType, level);
    }

    public TemplateBoat(Level level, double x, double y, double z) {
        this(EntityRegistry.MOD_BOAT.get(), level);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    public Item getDropItem() {
        switch (getVariant()) {
            case ARCHAEOPTERIS -> {
                return ItemRegistry.ARCHAEOPTERIS_BOAT.get();
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
        nbt.putString("type", this.getVariant().getSerializedName());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag nbt) {
        if (nbt.contains("type")) {
            this.setVariant(TemplateBoat.Type.byName(nbt.getString("type")));
        }
    }

    public static enum Type implements StringRepresentable {
        ARCHAEOPTERIS(BlockRegistry.ARCHAEOPTERIS_PLANKS.get(), "archaeopteris");

        private final Block plank;
        private final String name;

        private Type(Block plank, String name) {
            this.plank = plank;
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }

        public String getName() {
            return this.name;
        }

        public Block getPlanks() {
            return this.plank;
        }

        @Override
        public String toString() {
            return this.name;
        }

        public static Type byId(int id) {
            Type[] boatEntityType = values();
            if (id < 0 || id >= boatEntityType.length) {
                id = 0;
            }
            return boatEntityType[id];
        }

        public static Type byName(String name) {
            Type[] boatEntityType = values();
            for (int i = 0; i < boatEntityType.length; ++i) {
                if (boatEntityType[i].getName().equals(name)) {
                    return boatEntityType[i];
                }
            }
            return boatEntityType[0];
        }
    }
}
