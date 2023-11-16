package net.phoi.rot.registry;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.level.entity.*;

public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, RelicsOfTime.MODID);

    public static final RegistryObject<EntityType<Furcacauda>> FURCACAUDA = register("furcacauda", EntityType.Builder.of(Furcacauda::new, MobCategory.WATER_CREATURE).sized(0.3F, 0.3F));
    public static final RegistryObject<EntityType<Concavenator>> CONCAVENATOR = register("concavenator", EntityType.Builder.of(Concavenator::new, MobCategory.CREATURE).sized(1.4F, 2.1F));
    public static final RegistryObject<EntityType<Platyhystrix>> PLATYHYSTRIX = register("platyhystrix", EntityType.Builder.of(Platyhystrix::new, MobCategory.WATER_CREATURE).sized(0.8F, 0.55F));
    public static final RegistryObject<EntityType<Protoceratops>> PROTOCERATOPS = register("protoceratops", EntityType.Builder.of(Protoceratops::new, MobCategory.CREATURE).sized(0.9F, 0.9F));
    public static final RegistryObject<EntityType<Postosuchus>> POSTOSUCHUS = register("postosuchus", EntityType.Builder.of(Postosuchus::new, MobCategory.CREATURE).sized(1.3F, 1.35F));

    public static final RegistryObject<EntityType<TemplateBoat>> BOAT = register("boat", EntityType.Builder.<TemplateBoat>of(TemplateBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F));
    public static final RegistryObject<EntityType<TemplateChestBoat>> CHEST_BOAT = register("chest_boat", EntityType.Builder.<TemplateChestBoat>of(TemplateChestBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F));


    /** Helper methods **/
    private static <E extends Entity> RegistryObject<EntityType<E>> register(String name, EntityType.Builder<E> builder) {
        return ENTITY.register(name, () -> builder.build(name));
    }
}
