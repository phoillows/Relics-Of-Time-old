package net.phoi.rot.registry;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.level.item.*;
import static net.phoi.rot.registry.BlockRegistry.*;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, RelicsOfTime.MODID);

    public static final RegistryObject<Item> AMBER = ITEM.register("amber", () -> new GenericItem());

    public static final RegistryObject<Item> AMBER_ORE_ITEM = ITEM.register("amber_ore", () -> new GenericBlockItem(AMBER_ORE));
    public static final RegistryObject<Item> AMBER_GLASS_ITEM = ITEM.register("amber_glass", () -> new GenericBlockItem(AMBER_GLASS));
    public static final RegistryObject<Item> DNA_ANALYZER_ITEM = ITEM.register("dna_analyzer", () -> new GenericBlockItem(DNA_ANALYZER));
    public static final RegistryObject<Item> DNA_CENTRIFUGE_ITEM = ITEM.register("dna_centrifuge", () -> new GenericBlockItem(DNA_CENTRIFUGE));
    public static final RegistryObject<Item> AMMONITE_FOSSIL_PATH_ITEM = ITEM.register("ammonite_fossil_path", () -> new GenericBlockItem(AMMONITE_FOSSIL_PATH));

    public static final RegistryObject<Item> MESOZOIC_FOSSIL = ITEM.register("mesozoic_fossil", () -> new GenericItem());
    public static final RegistryObject<Item> PALEOZOIC_FOSSIL = ITEM.register("paleozoic_fossil", () -> new GenericItem());
    public static final RegistryObject<Item> PLANT_FOSSIL = ITEM.register("plant_fossil", () -> new GenericItem());

    public static final RegistryObject<Item> FURCACAUDA_BUCKET = ITEM.register("furcacauda_bucket", () -> new MobBucketItem(EntityRegistry.FURCACAUDA, () -> Fluids.WATER, () -> SoundEvents.BUCKET_FILL_FISH, new Item.Properties().stacksTo(1).tab(RelicsOfTime.ROT_TAB)));

    public static final RegistryObject<Item> ARCHAEOSIGLILLARIA_ITEM = ITEM.register("archaeosigillaria", () -> new GenericBlockItem(ARCHAEOSIGLILLARIA));
    public static final RegistryObject<Item> DILLHOFFIA_ITEM = ITEM.register("dillhoffia", () -> new GenericBlockItem(DILLHOFFIA));
    public static final RegistryObject<Item> FLORISSANTIA_ITEM = ITEM.register("florissantia", () -> new GenericBlockItem(FLORISSANTIA));
    public static final RegistryObject<Item> FOOZIA_ITEM = ITEM.register("foozia", () -> new GenericBlockItem(FOOZIA));
    public static final RegistryObject<Item> HORSETAIL_ITEM = ITEM.register("horsetail", () -> new GenericBlockItem(HORSETAIL));
    public static final RegistryObject<Item> SARRACENIA_ITEM = ITEM.register("sarracenia", () -> new GenericBlockItem(SARRACENIA));
    public static final RegistryObject<Item> VACCINIUM_ITEM = ITEM.register("vaccinium", () -> new GenericBlockItem(VACCINIUM));

    public static final RegistryObject<Item> ARCHAEOPTERIS_SAPLING_ITEM = ITEM.register("archaeopteris_sapling", () -> new GenericBlockItem(ARCHAEOPTERIS_SAPLING));
    public static final RegistryObject<Item> ARCHAEOPTERIS_PLANKS_ITEM = ITEM.register("archaeopteris_planks", () -> new GenericBlockItem(ARCHAEOPTERIS_PLANKS));
    public static final RegistryObject<Item> ARCHAEOPTERIS_LOG_ITEM = ITEM.register("archaeopteris_log", () -> new GenericBlockItem(ARCHAEOPTERIS_LOG));
    public static final RegistryObject<Item> STRIPPED_ARCHAEOPTERIS_LOG_ITEM = ITEM.register("stripped_archaeopteris_log", () -> new GenericBlockItem(STRIPPED_ARCHAEOPTERIS_LOG));
    public static final RegistryObject<Item> ARCHAEOPTERIS_LEAVES_ITEM = ITEM.register("archaeopteris_leaves", () -> new GenericBlockItem(ARCHAEOPTERIS_LEAVES));
    public static final RegistryObject<Item> ARCHAEOPTERIS_DOOR_ITEM = ITEM.register("archaeopteris_door", () -> new GenericBlockItem(ARCHAEOPTERIS_DOOR));
    public static final RegistryObject<Item> ARCHAEOPTERIS_TRAPDOOR_ITEM = ITEM.register("archaeopteris_trapdoor", () -> new GenericBlockItem(ARCHAEOPTERIS_TRAPDOOR));
    public static final RegistryObject<Item> ARCHAEOPTERIS_SIGN = ITEM.register("archaeopteris_sign", () -> new SignItem(new Item.Properties().stacksTo(16).tab(RelicsOfTime.ROT_TAB), BlockRegistry.ARCHAEOPTERIS_SIGN.get(), ARCHAEOPTERIS_WALL_SIGN.get()));

    public static final RegistryObject<Item> ARCHAEOSIGLILLARIA_DNA = ITEM.register("archaeosigillaria_dna", () -> new DnaBottleItem("Archaeosigillaria"));
    public static final RegistryObject<Item> DILLHOFFIA_DNA = ITEM.register("dillhoffia_dna", () -> new DnaBottleItem("Dillhoffia"));
    public static final RegistryObject<Item> FLORISSANTIA_DNA = ITEM.register("florissantia_dna", () -> new DnaBottleItem("Florissantia"));
    public static final RegistryObject<Item> FOOZIA_DNA = ITEM.register("foozia_dna", () -> new DnaBottleItem("Foozia"));
    public static final RegistryObject<Item> HORSETAIL_DNA = ITEM.register("horsetail_dna", () -> new DnaBottleItem("Horsetail"));
    public static final RegistryObject<Item> SARRACENIA_DNA = ITEM.register("sarracenia_dna", () -> new DnaBottleItem("Sarracenia"));
    public static final RegistryObject<Item> VACCINIUM_DNA = ITEM.register("vaccinium_dna", () -> new DnaBottleItem("Vaccinium"));

    public static final RegistryObject<Item> CONCAVENATOR_DNA = ITEM.register("concavenator_dna", () -> new DnaBottleItem("Concavenator"));
    public static final RegistryObject<Item> FURCACAUDA_DNA = ITEM.register("furcacauda_dna", () -> new DnaBottleItem("Furcacauda"));
    public static final RegistryObject<Item> PLATYHYSTRIX_DNA = ITEM.register("platyhystrix_dna", () -> new DnaBottleItem("Platyhystrix"));

    public static final RegistryObject<Item> CONCAVENATOR_EGG_ITEM = ITEM.register("concavenator_egg", () -> new GenericBlockItem(CONCAVENATOR_EGG));

    public static final RegistryObject<Item> CONCAVENATOR_SPAWN_EGG = ITEM.register("concavenator_spawn_egg", () -> new ForgeSpawnEggItem(EntityRegistry.CONCAVENATOR, 10116416, 13478556, new Item.Properties().tab(RelicsOfTime.ROT_TAB)));
    public static final RegistryObject<Item> FURCACAUDA_SPAWN_EGG = ITEM.register("furcacauda_spawn_egg", () -> new ForgeSpawnEggItem(EntityRegistry.FURCACAUDA, 14178304, 1326687, new Item.Properties().tab(RelicsOfTime.ROT_TAB)));
    public static final RegistryObject<Item> PLATYHYSTRIX_SPAWN_EGG = ITEM.register("platyhystrix_spawn_egg", () -> new ForgeSpawnEggItem(EntityRegistry.PLATYHYSTRIX, 7504960, 15046697, new Item.Properties().tab(RelicsOfTime.ROT_TAB)));
}
