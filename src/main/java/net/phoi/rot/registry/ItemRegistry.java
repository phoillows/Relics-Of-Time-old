package net.phoi.rot.registry;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.level.item.DNABottleItem;
import net.phoi.rot.level.item.ModTabs;

import static net.phoi.rot.registry.BlockRegistry.*;
import static net.phoi.rot.registry.EntityRegistry.*;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, RelicsOfTime.MODID);

    public static final RegistryObject<Item> DNA_ANALYZER_ITEM = ITEM.register("dna_analyzer", () -> new BlockItem(DNA_ANALYZER.get(), new Item.Properties().tab(ModTabs.RELICS_OF_TIME)));
    public static final RegistryObject<Item> DNA_CENTRIFUGE_ITEM = ITEM.register("dna_centrifuge", () -> new BlockItem(DNA_CENTRIFUGE.get(), new Item.Properties().tab(ModTabs.RELICS_OF_TIME)));
    public static final RegistryObject<Item> AMBER_GLASS_ITEM = ITEM.register("amber_glass", () -> new BlockItem(AMBER_GLASS.get(), new Item.Properties().tab(ModTabs.RELICS_OF_TIME)));
    public static final RegistryObject<Item> AMBER_ORE_ITEM = ITEM.register("amber_ore", () -> new BlockItem(AMBER_ORE.get(), new Item.Properties().tab(ModTabs.RELICS_OF_TIME)));

    public static final RegistryObject<Item> AMBER = ITEM.register("amber", () -> new Item(new Item.Properties().tab(ModTabs.RELICS_OF_TIME)));

    public static final RegistryObject<Item> FOOZIA_ITEM = ITEM.register("foozia", () -> new BlockItem(FOOZIA.get(), new Item.Properties().tab(ModTabs.RELICS_OF_TIME)));
    public static final RegistryObject<Item> DILLHOFFIA_ITEM = ITEM.register("dillhoffia", () -> new BlockItem(DILLHOFFIA.get(), new Item.Properties().tab(ModTabs.RELICS_OF_TIME)));
    public static final RegistryObject<Item> ARCHAEOSIGLILLARIA_ITEM = ITEM.register("archaeosigillaria", () -> new BlockItem(ARCHAEOSIGLILLARIA.get(), new Item.Properties().tab(ModTabs.RELICS_OF_TIME)));
    public static final RegistryObject<Item> VACCINIUM_ITEM = ITEM.register("vaccinium", () -> new BlockItem(VACCINIUM.get(), new Item.Properties().tab(ModTabs.RELICS_OF_TIME)));
    public static final RegistryObject<Item> SARRACENIA_ITEM = ITEM.register("sarracenia", () -> new BlockItem(SARRACENIA.get(), new Item.Properties().tab(ModTabs.RELICS_OF_TIME)));
    public static final RegistryObject<Item> HORSETAIL_ITEM = ITEM.register("horsetail", () -> new BlockItem(HORSETAIL.get(), new Item.Properties().tab(ModTabs.RELICS_OF_TIME)));

    public static final RegistryObject<Item> ARCHAEOSIGLILLARIA_DNA = ITEM.register("archaeosigillaria_dna", () -> new DNABottleItem("Archaeosigillaria", new Item.Properties().tab(ModTabs.RELICS_OF_TIME)));
    public static final RegistryObject<Item> DILLHOFFIA_DNA = ITEM.register("dillhoffia_dna", () -> new DNABottleItem("Dillhoffia", new Item.Properties().tab(ModTabs.RELICS_OF_TIME)));
    public static final RegistryObject<Item> FOOZIA_DNA = ITEM.register("foozia_dna", () -> new DNABottleItem("Foozia", new Item.Properties().tab(ModTabs.RELICS_OF_TIME)));
    public static final RegistryObject<Item> HORSETAIL_DNA = ITEM.register("horsetail_dna", () -> new DNABottleItem("Horsetail", new Item.Properties().tab(ModTabs.RELICS_OF_TIME)));
    public static final RegistryObject<Item> SARRACENIA_DNA = ITEM.register("sarracenia_dna", () -> new DNABottleItem("Sarracenia", new Item.Properties().tab(ModTabs.RELICS_OF_TIME)));
    public static final RegistryObject<Item> VACCINIUM_DNA = ITEM.register("vaccinium_dna", () -> new DNABottleItem("Vaccinium", new Item.Properties().tab(ModTabs.RELICS_OF_TIME)));

    public static final RegistryObject<Item> CONCAVENATOR_DNA = ITEM.register("concavenator_dna", () -> new DNABottleItem("Concavenator", new Item.Properties().tab(ModTabs.RELICS_OF_TIME)));
    public static final RegistryObject<Item> FURCACAUDA_DNA = ITEM.register("furcacauda_dna", () -> new DNABottleItem("Furcacauda", new Item.Properties().tab(ModTabs.RELICS_OF_TIME)));

    public static final RegistryObject<Item> FURCACAUDA_BUCKET = ITEM.register("furcacauda_bucket", () -> new MobBucketItem(FURCACAUDA, () -> Fluids.WATER, () -> SoundEvents.BUCKET_FILL_FISH, new Item.Properties().stacksTo(1).tab(ModTabs.RELICS_OF_TIME)));

    public static final RegistryObject<Item> FURCACAUDA_SPAWN_EGG = ITEM.register("furcacauda_spawn_egg", () -> new ForgeSpawnEggItem(FURCACAUDA, 14178304, 1326687, new Item.Properties().tab(ModTabs.RELICS_OF_TIME)));
    public static final RegistryObject<Item> CONCAVENATOR_SPAWN_EGG = ITEM.register("concavenator_spawn_egg", () -> new ForgeSpawnEggItem(CONCAVENATOR, 10116416, 13478556, new Item.Properties().tab(ModTabs.RELICS_OF_TIME)));
}
