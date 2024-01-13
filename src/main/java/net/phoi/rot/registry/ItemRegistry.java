package net.phoi.rot.registry;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.level.entity.TemplateBoat;
import net.phoi.rot.level.item.*;
import net.phoi.rot.util.RotCreativeTabs;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, RelicsOfTime.MODID);

    public static final RegistryObject<Item> AMBER = ITEM.register("amber", () -> new GenericItem());
    public static final RegistryObject<Item> AMBER_ORE = blockItem(BlockRegistry.AMBER_ORE);
    public static final RegistryObject<Item> AMBER_GLASS = blockItem(BlockRegistry.AMBER_GLASS);
    public static final RegistryObject<Item> DNA_ANALYZER = blockItem(BlockRegistry.DNA_ANALYZER);
    public static final RegistryObject<Item> DNA_CENTRIFUGE = blockItem(BlockRegistry.DNA_CENTRIFUGE);
    public static final RegistryObject<Item> FOSSIL_ORE = blockItem(BlockRegistry.FOSSIL_ORE);
    public static final RegistryObject<Item> AMMONITE_FOSSIL_PATH = blockItem(BlockRegistry.AMMONITE_FOSSIL_PATH);

    public static final RegistryObject<Item> MESOZOIC_FOSSIL = ITEM.register("mesozoic_fossil", () -> new GenericItem());
    public static final RegistryObject<Item> PALEOZOIC_FOSSIL = ITEM.register("paleozoic_fossil", () -> new GenericItem());
    public static final RegistryObject<Item> PLANT_FOSSIL = ITEM.register("plant_fossil", () -> new GenericItem());

    public static final RegistryObject<Item> ARCHAEOPTERIS_CLUB = ITEM.register("archaeopteris_club", () -> new ArchaeopterisClubItem(Tiers.WOOD, 4, -3.1F, new Item.Properties().stacksTo(1).tab(RotCreativeTabs.DEFAULT)));

    public static final RegistryObject<Item> ARCHAEOPTERIS_PLANKS = blockItem(BlockRegistry.ARCHAEOPTERIS_PLANKS);
    public static final RegistryObject<Item> ARCHAEOPTERIS_SAPLING = blockItem(BlockRegistry.ARCHAEOPTERIS_SAPLING);
    public static final RegistryObject<Item> ARCHAEOPTERIS_LOG = blockItem(BlockRegistry.ARCHAEOPTERIS_LOG);
    public static final RegistryObject<Item> STRIPPED_ARCHAEOPTERIS_LOG = blockItem(BlockRegistry.STRIPPED_ARCHAEOPTERIS_LOG);
    public static final RegistryObject<Item> STRIPPED_ARCHAEOPTERIS_WOOD = blockItem(BlockRegistry.STRIPPED_ARCHAEOPTERIS_WOOD);
    public static final RegistryObject<Item> ARCHAEOPTERIS_WOOD = blockItem(BlockRegistry.ARCHAEOPTERIS_WOOD);
    public static final RegistryObject<Item> ARCHAEOPTERIS_LEAVES = blockItem(BlockRegistry.ARCHAEOPTERIS_LEAVES);
    public static final RegistryObject<Item> ARCHAEOPTERIS_SLAB = blockItem(BlockRegistry.ARCHAEOPTERIS_SLAB);
    public static final RegistryObject<Item> ARCHAEOPTERIS_FENCE = blockItem(BlockRegistry.ARCHAEOPTERIS_FENCE);
    public static final RegistryObject<Item> ARCHAEOPTERIS_STAIRS = blockItem(BlockRegistry.ARCHAEOPTERIS_STAIRS);
    public static final RegistryObject<Item> ARCHAEOPTERIS_BUTTON = blockItem(BlockRegistry.ARCHAEOPTERIS_BUTTON);
    public static final RegistryObject<Item> ARCHAEOPTERIS_PRESSURE_PLATE = blockItem(BlockRegistry.ARCHAEOPTERIS_PRESSURE_PLATE);
    public static final RegistryObject<Item> ARCHAEOPTERIS_DOOR = blockItem(BlockRegistry.ARCHAEOPTERIS_DOOR);
    public static final RegistryObject<Item> ARCHAEOPTERIS_TRAPDOOR = blockItem(BlockRegistry.ARCHAEOPTERIS_TRAPDOOR);
    public static final RegistryObject<Item> ARCHAEOPTERIS_FENCE_GATE = blockItem(BlockRegistry.ARCHAEOPTERIS_FENCE_GATE);
    public static final RegistryObject<Item> ARCHAEOPTERIS_BOAT = ITEM.register("archaeopteris_boat", () -> new ModBoatItem(false, TemplateBoat.Type.ARCHAEOPTERIS));
    public static final RegistryObject<Item> ARCHAEOPTERIS_CHEST_BOAT = ITEM.register("archaeopteris_chest_boat", () -> new ModBoatItem(true, TemplateBoat.Type.ARCHAEOPTERIS));
    public static final RegistryObject<Item> ARCHAEOPTERIS_SIGN = ITEM.register("archaeopteris_sign", () -> new SignItem(new Item.Properties().stacksTo(16).tab(RotCreativeTabs.DEFAULT), BlockRegistry.ARCHAEOPTERIS_SIGN.get(), BlockRegistry.ARCHAEOPTERIS_WALL_SIGN.get()));

    public static final RegistryObject<Item> SMALL_HORSETAIL = blockItem(BlockRegistry.SMALL_HORSETAIL);
    public static final RegistryObject<Item> LARGE_HORSETAIL = blockItem(BlockRegistry.LARGE_HORSETAIL);
    public static final RegistryObject<Item> COOKSONIA = blockItem(BlockRegistry.COOKSONIA);
    public static final RegistryObject<Item> SPHENOPTERIS = blockItem(BlockRegistry.SPHENOPTERIS);
    public static final RegistryObject<Item> SAGENOPTERIS = blockItem(BlockRegistry.SAGENOPTERIS);
    public static final RegistryObject<Item> NEOCALAMITES = blockItem(BlockRegistry.NEOCALAMITES);
    public static final RegistryObject<Item> CYCAD = blockItem(BlockRegistry.CYCAD);

    public static final RegistryObject<Item> HORSETAIL_DNA = ITEM.register("horsetail_dna", () -> new DnaBottleItem("Horsetail"));
    public static final RegistryObject<Item> NEOCALAMITES_DNA = ITEM.register("neocalamites_dna", () -> new DnaBottleItem("Neocalamites"));

    public static final RegistryObject<Item> CONCAVENATOR_DNA = ITEM.register("concavenator_dna", () -> new DnaBottleItem("Concavenator"));
    public static final RegistryObject<Item> FURCACAUDA_DNA = ITEM.register("furcacauda_dna", () -> new DnaBottleItem("Furcacauda"));
    public static final RegistryObject<Item> PLATYHYSTRIX_DNA = ITEM.register("platyhystrix_dna", () -> new DnaBottleItem("Platyhystrix"));
    public static final RegistryObject<Item> POSTOSUCHUS_DNA = ITEM.register("postosuchus_dna", () -> new DnaBottleItem("Postosuchus"));
    public static final RegistryObject<Item> PROTOCERATOPS_DNA = ITEM.register("protoceratops_dna", () -> new DnaBottleItem("Protoceratops"));

    public static final RegistryObject<Item> CONCAVENATOR_EGG = blockItem(BlockRegistry.CONCAVENATOR_EGG, true);
    public static final RegistryObject<Item> PROTOCERATOPS_EGG = blockItem(BlockRegistry.PROTOCERATOPS_EGG, true);
    public static final RegistryObject<Item> PLATYHYSTRIX_EGGS = blockItem(BlockRegistry.PLATYHYSTRIX_EGGS, true);
    public static final RegistryObject<Item> FURCACAUDA_BUCKET = ITEM.register("furcacauda_bucket", () -> new MobBucketItem(EntityRegistry.FURCACAUDA, () -> Fluids.WATER, () -> SoundEvents.BUCKET_FILL_FISH, new Item.Properties().stacksTo(1).tab(RotCreativeTabs.SPAWN_EGGS)));
    public static final RegistryObject<Item> BABY_PLATYHYSTRIX_BUCKET = ITEM.register("baby_platyhystrix_bucket", () -> new BabyBucketItem(EntityRegistry.PLATYHYSTRIX, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_AXOLOTL, new Item.Properties().stacksTo(1).tab(RotCreativeTabs.SPAWN_EGGS)));
    public static final RegistryObject<Item> DUNKLEOSTEUS_SKULL = blockItem(BlockRegistry.DUNKLEOSTEUS_SKULL, true);

    public static final RegistryObject<Item> CONCAVENATOR_SPAWN_EGG = ITEM.register("concavenator_spawn_egg", () -> new ForgeSpawnEggItem(EntityRegistry.CONCAVENATOR, 10116416, 13478556, new Item.Properties().tab(RotCreativeTabs.SPAWN_EGGS)));
    public static final RegistryObject<Item> DUNKLEOSTEUS_SPAWN_EGG = ITEM.register("dunkleosteus_spawn_egg", () -> new ForgeSpawnEggItem(EntityRegistry.DUNKLEOSTEUS, 8094617, 3812675, new Item.Properties().tab(RotCreativeTabs.SPAWN_EGGS)));
    public static final RegistryObject<Item> FURCACAUDA_SPAWN_EGG = ITEM.register("furcacauda_spawn_egg", () -> new ForgeSpawnEggItem(EntityRegistry.FURCACAUDA, 14178304, 1326687, new Item.Properties().tab(RotCreativeTabs.SPAWN_EGGS)));
    public static final RegistryObject<Item> PLATYHYSTRIX_SPAWN_EGG = ITEM.register("platyhystrix_spawn_egg", () -> new ForgeSpawnEggItem(EntityRegistry.PLATYHYSTRIX, 7504960, 15046697, new Item.Properties().tab(RotCreativeTabs.SPAWN_EGGS)));
    public static final RegistryObject<Item> POSTOSUCHUS_SPAWN_EGG = ITEM.register("postosuchus_spawn_egg", () -> new ForgeSpawnEggItem(EntityRegistry.POSTOSUCHUS, 11443259, 7352096, new Item.Properties().tab(RotCreativeTabs.SPAWN_EGGS)));
    public static final RegistryObject<Item> PROTOCERATOPS_SPAWN_EGG = ITEM.register("protoceratops_spawn_egg", () -> new ForgeSpawnEggItem(EntityRegistry.PROTOCERATOPS, 6373677, 14076739, new Item.Properties().tab(RotCreativeTabs.SPAWN_EGGS)));
    public static final RegistryObject<Item> SHRINGASAURUS_SPAWN_EGG = ITEM.register("shringasaurus_spawn_egg", () -> new ForgeSpawnEggItem(EntityRegistry.SHRINGASAURUS, 8015674, 10566926, new Item.Properties().tab(RotCreativeTabs.SPAWN_EGGS)));

    private static RegistryObject<Item> blockItem(RegistryObject<Block> block) {
        return blockItem(block, false);
    }

    private static RegistryObject<Item> blockItem(RegistryObject<Block> block, boolean inSpawnEggTab) {
        return ITEM.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(inSpawnEggTab ? RotCreativeTabs.SPAWN_EGGS : RotCreativeTabs.DEFAULT)));
    }
}
