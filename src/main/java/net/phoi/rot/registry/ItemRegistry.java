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
import static net.phoi.rot.registry.EntityRegistry.*;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, RelicsOfTime.MODID);
    public static final CreativeModeTab ROT_TAB = new CreativeModeTab("relics_of_time") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(AMBER.get());
        }
    };


    public static final RegistryObject<Item> AMBER = ITEM.register("amber", () -> new Item(new Item.Properties().tab(ROT_TAB)));

    public static final RegistryObject<Item> AMBER_GLASS_ITEM = ITEM.register("amber_glass", () -> new BlockItem(AMBER_GLASS.get(), new Item.Properties().tab(ROT_TAB)));
    public static final RegistryObject<Item> AMBER_ORE_ITEM = ITEM.register("amber_ore", () -> new BlockItem(AMBER_ORE.get(), new Item.Properties().tab(ROT_TAB)));
    public static final RegistryObject<Item> DNA_ANALYZER_ITEM = ITEM.register("dna_analyzer", () -> new BlockItem(DNA_ANALYZER.get(), new Item.Properties().tab(ROT_TAB)));
    public static final RegistryObject<Item> DNA_CENTRIFUGE_ITEM = ITEM.register("dna_centrifuge", () -> new BlockItem(DNA_CENTRIFUGE.get(), new Item.Properties().tab(ROT_TAB)));
    public static final RegistryObject<Item> AMMONITE_FOSSIL_PATH_ITEM = ITEM.register("ammonite_fossil_path", () -> new BlockItem(AMMONITE_FOSSIL_PATH.get(), new Item.Properties().tab(ROT_TAB)));

    public static final RegistryObject<Item> MESOZOIC_FOSSIL = ITEM.register("mesozoic_fossil", () -> new Item(new Item.Properties().tab(ROT_TAB)));
    public static final RegistryObject<Item> PALEOZOIC_FOSSIL = ITEM.register("paleozoic_fossil", () -> new Item(new Item.Properties().tab(ROT_TAB)));
    public static final RegistryObject<Item> PLANT_FOSSIL = ITEM.register("plant_fossil", () -> new Item(new Item.Properties().tab(ROT_TAB)));

    public static final RegistryObject<Item> FOOZIA_ITEM = ITEM.register("foozia", () -> new BlockItem(FOOZIA.get(), new Item.Properties().tab(ROT_TAB)));
    public static final RegistryObject<Item> DILLHOFFIA_ITEM = ITEM.register("dillhoffia", () -> new BlockItem(DILLHOFFIA.get(), new Item.Properties().tab(ROT_TAB)));
    public static final RegistryObject<Item> ARCHAEOSIGLILLARIA_ITEM = ITEM.register("archaeosigillaria", () -> new BlockItem(ARCHAEOSIGLILLARIA.get(), new Item.Properties().tab(ROT_TAB)));
    public static final RegistryObject<Item> VACCINIUM_ITEM = ITEM.register("vaccinium", () -> new BlockItem(VACCINIUM.get(), new Item.Properties().tab(ROT_TAB)));
    public static final RegistryObject<Item> SARRACENIA_ITEM = ITEM.register("sarracenia", () -> new BlockItem(SARRACENIA.get(), new Item.Properties().tab(ROT_TAB)));
    public static final RegistryObject<Item> HORSETAIL_ITEM = ITEM.register("horsetail", () -> new BlockItem(HORSETAIL.get(), new Item.Properties().tab(ROT_TAB)));
    public static final RegistryObject<Item> FLORISSANTIA_ITEM = ITEM.register("florissantia", () -> new BlockItem(FLORISSANTIA.get(), new Item.Properties().tab(ROT_TAB)));

    public static final RegistryObject<Item> ARCHAEOSIGLILLARIA_DNA = ITEM.register("archaeosigillaria_dna", () -> new DnaBottleItem("Archaeosigillaria", new Item.Properties().tab(ROT_TAB)));
    public static final RegistryObject<Item> DILLHOFFIA_DNA = ITEM.register("dillhoffia_dna", () -> new DnaBottleItem("Dillhoffia", new Item.Properties().tab(ROT_TAB)));
    public static final RegistryObject<Item> FOOZIA_DNA = ITEM.register("foozia_dna", () -> new DnaBottleItem("Foozia", new Item.Properties().tab(ROT_TAB)));
    public static final RegistryObject<Item> HORSETAIL_DNA = ITEM.register("horsetail_dna", () -> new DnaBottleItem("Horsetail", new Item.Properties().tab(ROT_TAB)));
    public static final RegistryObject<Item> SARRACENIA_DNA = ITEM.register("sarracenia_dna", () -> new DnaBottleItem("Sarracenia", new Item.Properties().tab(ROT_TAB)));
    public static final RegistryObject<Item> VACCINIUM_DNA = ITEM.register("vaccinium_dna", () -> new DnaBottleItem("Vaccinium", new Item.Properties().tab(ROT_TAB)));
    public static final RegistryObject<Item> FLORISSANTIA_DNA = ITEM.register("florissantia_dna", () -> new DnaBottleItem("Florissantia", new Item.Properties().tab(ROT_TAB)));

    public static final RegistryObject<Item> CONCAVENATOR_DNA = ITEM.register("concavenator_dna", () -> new DnaBottleItem("Concavenator", new Item.Properties().tab(ROT_TAB)));
    public static final RegistryObject<Item> FURCACAUDA_DNA = ITEM.register("furcacauda_dna", () -> new DnaBottleItem("Furcacauda", new Item.Properties().tab(ROT_TAB)));

    public static final RegistryObject<Item> FURCACAUDA_BUCKET = ITEM.register("furcacauda_bucket", () -> new MobBucketItem(FURCACAUDA, () -> Fluids.WATER, () -> SoundEvents.BUCKET_FILL_FISH, new Item.Properties().stacksTo(1).tab(ROT_TAB)));

    public static final RegistryObject<Item> FURCACAUDA_SPAWN_EGG = ITEM.register("furcacauda_spawn_egg", () -> new ForgeSpawnEggItem(FURCACAUDA, 14178304, 1326687, new Item.Properties().tab(ROT_TAB)));
    public static final RegistryObject<Item> CONCAVENATOR_SPAWN_EGG = ITEM.register("concavenator_spawn_egg", () -> new ForgeSpawnEggItem(CONCAVENATOR, 10116416, 13478556, new Item.Properties().tab(ROT_TAB)));
    public static final RegistryObject<Item> CONCAVENATOR_EGG_ITEM = ITEM.register("concavenator_egg", () -> new BlockItem(CONCAVENATOR_EGG.get(), new Item.Properties().tab(ROT_TAB)));
}
