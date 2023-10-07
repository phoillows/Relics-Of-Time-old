package net.phoi.rot.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.level.entity.*;

public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, RelicsOfTime.MODID);

    public static final RegistryObject<EntityType<Furcacauda>> FURCACAUDA = ENTITY.register("furcacauda", () -> EntityType.Builder.of(Furcacauda::new, MobCategory.WATER_CREATURE).sized(0.3F, 0.3F).build(RelicsOfTime.MODID + "furcacauda"));
    public static final RegistryObject<EntityType<Concavenator>> CONCAVENATOR = ENTITY.register("concavenator", () -> EntityType.Builder.of(Concavenator::new, MobCategory.CREATURE).sized(1.4F, 2.1F).build(RelicsOfTime.MODID + "concavenator"));
    public static final RegistryObject<EntityType<Platyhystrix>> PLATYHYSTRIX = ENTITY.register("platyhystrix", () -> EntityType.Builder.of(Platyhystrix::new, MobCategory.WATER_CREATURE).sized(0.8F, 0.55F).build(RelicsOfTime.MODID + "platyhystrix"));

    public static final RegistryObject<EntityType<TemplateBoat>> BOAT = ENTITY.register("boat", () -> EntityType.Builder.<TemplateBoat>of(TemplateBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).build(RelicsOfTime.MODID + "boat"));
    public static final RegistryObject<EntityType<TemplateChestBoat>> CHEST_BOAT = ENTITY.register("chest_boat", () -> EntityType.Builder.<TemplateChestBoat>of(TemplateChestBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).build(RelicsOfTime.MODID + "chest_boat"));
}
