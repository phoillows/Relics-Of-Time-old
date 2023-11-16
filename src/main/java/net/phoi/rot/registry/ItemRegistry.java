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
import static net.phoi.rot.registry.BlockRegistry.*;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, RelicsOfTime.MODID);

    public static final RegistryObject<Item> AMBER = ITEM.register("amber", () -> new GenericItem());
    public static final RegistryObject<Item> AMBER_ORE_ITEM = blockItem(AMBER_ORE);
    public static final RegistryObject<Item> AMBER_GLASS_ITEM = blockItem(AMBER_GLASS);
    public static final RegistryObject<Item> DNA_ANALYZER_ITEM = blockItem(DNA_ANALYZER);
    public static final RegistryObject<Item> DNA_CENTRIFUGE_ITEM = blockItem(DNA_CENTRIFUGE);
    public static final RegistryObject<Item> FOSSIL_ORE_ITEM = blockItem(FOSSIL_ORE);
    public static final RegistryObject<Item> AMMONITE_FOSSIL_PATH_ITEM = blockItem(AMMONITE_FOSSIL_PATH);

    public static final RegistryObject<Item> MESOZOIC_FOSSIL = ITEM.register("mesozoic_fossil", () -> new GenericItem());
    public static final RegistryObject<Item> PALEOZOIC_FOSSIL = ITEM.register("paleozoic_fossil", () -> new GenericItem());
    public static final RegistryObject<Item> PLANT_FOSSIL = ITEM.register("plant_fossil", () -> new GenericItem());

    public static final RegistryObject<Item> FURCACAUDA_BUCKET = ITEM.register("furcacauda_bucket", () -> new MobBucketItem(EntityRegistry.FURCACAUDA, () -> Fluids.WATER, () -> SoundEvents.BUCKET_FILL_FISH, new Item.Properties().stacksTo(1).tab(RelicsOfTime.RELICS_OF_TIME)));
    public static final RegistryObject<Item> ARCHAEOPTERIS_CLUB = ITEM.register("archaeopteris_club", () -> new ArchaeopterisClubItem(Tiers.WOOD, 4, -3.1F, new Item.Properties().stacksTo(1).tab(RelicsOfTime.RELICS_OF_TIME)));

    public static final RegistryObject<Item> ARCHAEOPTERIS_PLANKS_ITEM = blockItem(ARCHAEOPTERIS_PLANKS);
    public static final RegistryObject<Item> ARCHAEOPTERIS_SAPLING_ITEM = blockItem(ARCHAEOPTERIS_SAPLING);
    public static final RegistryObject<Item> ARCHAEOPTERIS_LOG_ITEM = blockItem(ARCHAEOPTERIS_LOG);
    public static final RegistryObject<Item> STRIPPED_ARCHAEOPTERIS_LOG_ITEM = blockItem(STRIPPED_ARCHAEOPTERIS_LOG);
    public static final RegistryObject<Item> STRIPPED_ARCHAEOPTERIS_WOOD_ITEM = blockItem(STRIPPED_ARCHAEOPTERIS_WOOD);
    public static final RegistryObject<Item> ARCHAEOPTERIS_WOOD_ITEM = blockItem(ARCHAEOPTERIS_WOOD);
    public static final RegistryObject<Item> ARCHAEOPTERIS_LEAVES_ITEM = blockItem(ARCHAEOPTERIS_LEAVES);
    public static final RegistryObject<Item> ARCHAEOPTERIS_SLAB_ITEM = blockItem(ARCHAEOPTERIS_SLAB);
    public static final RegistryObject<Item> ARCHAEOPTERIS_FENCE_ITEM = blockItem(ARCHAEOPTERIS_FENCE);
    public static final RegistryObject<Item> ARCHAEOPTERIS_STAIRS_ITEM = blockItem(ARCHAEOPTERIS_STAIRS);
    public static final RegistryObject<Item> ARCHAEOPTERIS_BUTTON_ITEM = blockItem(ARCHAEOPTERIS_BUTTON);
    public static final RegistryObject<Item> ARCHAEOPTERIS_PRESSURE_PLATE_ITEM = blockItem(ARCHAEOPTERIS_PRESSURE_PLATE);
    public static final RegistryObject<Item> ARCHAEOPTERIS_DOOR_ITEM = blockItem(ARCHAEOPTERIS_DOOR);
    public static final RegistryObject<Item> ARCHAEOPTERIS_TRAPDOOR_ITEM = blockItem(ARCHAEOPTERIS_TRAPDOOR);
    public static final RegistryObject<Item> ARCHAEOPTERIS_FENCE_GATE_ITEM = blockItem(ARCHAEOPTERIS_FENCE_GATE);
    public static final RegistryObject<Item> ARCHAEOPTERIS_BOAT = ITEM.register("archaeopteris_boat", () -> new ModBoatItem(false, TemplateBoat.Type.ARCHAEOPTERIS));
    public static final RegistryObject<Item> ARCHAEOPTERIS_CHEST_BOAT = ITEM.register("archaeopteris_chest_boat", () -> new ModBoatItem(true, TemplateBoat.Type.ARCHAEOPTERIS));
    public static final RegistryObject<Item> ARCHAEOPTERIS_SIGN = ITEM.register("archaeopteris_sign", () -> new SignItem(new Item.Properties().stacksTo(16).tab(RelicsOfTime.RELICS_OF_TIME), BlockRegistry.ARCHAEOPTERIS_SIGN.get(), ARCHAEOPTERIS_WALL_SIGN.get()));

    public static final RegistryObject<Item> SMALL_HORSETAIL_ITEM = blockItem(SMALL_HORSETAIL);
    public static final RegistryObject<Item> LARGE_HORSETAIL_ITEM = blockItem(LARGE_HORSETAIL);
    public static final RegistryObject<Item> NEOCALAMITES_ITEM = blockItem(NEOCALAMITES);

    public static final RegistryObject<Item> HORSETAIL_DNA = ITEM.register("horsetail_dna", () -> new DnaBottleItem("Horsetail"));
    public static final RegistryObject<Item> NEOCALAMITES_DNA = ITEM.register("neocalamites_dna", () -> new DnaBottleItem("Neocalamites"));

    public static final RegistryObject<Item> CONCAVENATOR_DNA = ITEM.register("concavenator_dna", () -> new DnaBottleItem("Concavenator"));
    public static final RegistryObject<Item> FURCACAUDA_DNA = ITEM.register("furcacauda_dna", () -> new DnaBottleItem("Furcacauda"));
    public static final RegistryObject<Item> PLATYHYSTRIX_DNA = ITEM.register("platyhystrix_dna", () -> new DnaBottleItem("Platyhystrix"));
    public static final RegistryObject<Item> POSTOSUCHUS_DNA = ITEM.register("postosuchus_dna", () -> new DnaBottleItem("Postosuchus"));
    public static final RegistryObject<Item> PROTOCERATOPS_DNA = ITEM.register("protoceratops_dna", () -> new DnaBottleItem("Protoceratops"));

    public static final RegistryObject<Item> CONCAVENATOR_EGG_ITEM = blockItem(CONCAVENATOR_EGG);
    public static final RegistryObject<Item> PROTOCERATOPS_EGG_ITEM = blockItem(PROTOCERATOPS_EGG);

    public static final RegistryObject<Item> CONCAVENATOR_SPAWN_EGG = ITEM.register("concavenator_spawn_egg", () -> new ForgeSpawnEggItem(EntityRegistry.CONCAVENATOR, 10116416, 13478556, new Item.Properties().tab(RelicsOfTime.RELICS_OF_TIME)));
    public static final RegistryObject<Item> FURCACAUDA_SPAWN_EGG = ITEM.register("furcacauda_spawn_egg", () -> new ForgeSpawnEggItem(EntityRegistry.FURCACAUDA, 14178304, 1326687, new Item.Properties().tab(RelicsOfTime.RELICS_OF_TIME)));
    public static final RegistryObject<Item> PLATYHYSTRIX_SPAWN_EGG = ITEM.register("platyhystrix_spawn_egg", () -> new ForgeSpawnEggItem(EntityRegistry.PLATYHYSTRIX, 7504960, 15046697, new Item.Properties().tab(RelicsOfTime.RELICS_OF_TIME)));
    public static final RegistryObject<Item> POSTOSUCHUS_SPAWN_EGG = ITEM.register("postosuchus_spawn_egg", () -> new ForgeSpawnEggItem(EntityRegistry.POSTOSUCHUS, 11443259, 7352096, new Item.Properties().tab(RelicsOfTime.RELICS_OF_TIME)));
    public static final RegistryObject<Item> PROTOCERATOPS_SPAWN_EGG = ITEM.register("protoceratops_spawn_egg", () -> new ForgeSpawnEggItem(EntityRegistry.PROTOCERATOPS, 9555526, 5929016, new Item.Properties().tab(RelicsOfTime.RELICS_OF_TIME)));


    /** Helper methods **/
    private static RegistryObject<Item> blockItem(RegistryObject<Block> block) {
        return ITEM.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(RelicsOfTime.RELICS_OF_TIME)));
    }
}
