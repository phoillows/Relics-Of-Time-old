package net.phoi.rot.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.level.entity.*;

public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, RelicsOfTime.MODID);

    public static final RegistryObject<EntityType<Furcacauda>> FURCACAUDA = ENTITY_TYPE.register("furcacauda", () -> EntityType.Builder.of(Furcacauda::new, MobCategory.WATER_CREATURE).sized(0.3F, 0.3F).build(RelicsOfTime.MODID + "furcacauda"));
}
