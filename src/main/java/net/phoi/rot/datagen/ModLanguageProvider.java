package net.phoi.rot.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.registry.BlockRegistry;
import net.phoi.rot.registry.EntityRegistry;
import net.phoi.rot.registry.ItemRegistry;
import net.phoi.rot.registry.MobEffectRegistry;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(DataGenerator gen) {
        super(gen, RelicsOfTime.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // Blocks
        addBlock(BlockRegistry.DNA_ANALYZER, "DNA Analyzer");
        addBlock(BlockRegistry.DNA_CENTRIFUGE, "DNA Centrifuge");
        addBlock(BlockRegistry.AMBER_ORE, "Amber Ore");
        addBlock(BlockRegistry.AMBER_GLASS, "Amber Glass");
        addBlock(BlockRegistry.FOSSIL_ORE, "Fossil Ore");
        addBlock(BlockRegistry.AMMONITE_FOSSIL_PATH, "Ammonite Fossil Path");
        addBlock(BlockRegistry.STRIPPED_ARCHAEOPTERIS_LOG, "Stripped Archaeopteris Log");
        addBlock(BlockRegistry.STRIPPED_ARCHAEOPTERIS_WOOD, "Stripped Archaeopteris Wood");
        addBlock(BlockRegistry.ARCHAEOPTERIS_PLANKS, "Archaeopteris Planks");
        addBlock(BlockRegistry.ARCHAEOPTERIS_SLAB, "Archaeopteris Slab");
        addBlock(BlockRegistry.ARCHAEOPTERIS_STAIRS, "Archaeopteris Stairs");
        addBlock(BlockRegistry.ARCHAEOPTERIS_FENCE, "Archaeopteris Fence");
        addBlock(BlockRegistry.ARCHAEOPTERIS_FENCE_GATE, "Archaeopteris Fence Gate");
        addBlock(BlockRegistry.ARCHAEOPTERIS_BUTTON, "Archaeopteris Button");
        addBlock(BlockRegistry.ARCHAEOPTERIS_PRESSURE_PLATE, "Archaeopteris Pressure Plate");
        addBlock(BlockRegistry.ARCHAEOPTERIS_LOG, "Archaeopteris Log");
        addBlock(BlockRegistry.ARCHAEOPTERIS_WOOD, "Archaeopteris Wood");
        addBlock(BlockRegistry.ARCHAEOPTERIS_LEAVES, "Archaeopteris Leaves");
        addBlock(BlockRegistry.ARCHAEOPTERIS_DOOR, "Archaeopteris Door");
        addBlock(BlockRegistry.ARCHAEOPTERIS_TRAPDOOR, "Archaeopteris Trapdoor");
        addBlock(BlockRegistry.ARCHAEOPTERIS_SAPLING, "Archaeopteris Sapling");
        addBlock(BlockRegistry.ARCHAEOPTERIS_SIGN, "Archaeopteris Sign");
        addBlock(BlockRegistry.SMALL_HORSETAIL, "Small Horsetail");
        addBlock(BlockRegistry.LARGE_HORSETAIL, "Large Horsetail");
        addBlock(BlockRegistry.NEOCALAMITES, "Neocalamites");
        addBlock(BlockRegistry.CONCAVENATOR_EGG, "Concavenator Egg");
        addBlock(BlockRegistry.PROTOCERATOPS_EGG, "Protoceratops Egg");
        addBlock(BlockRegistry.DUNKLEOSTEUS_SKULL, "Dunkleosteus Skull");
        addBlock(BlockRegistry.CYCAD, "Cycad");
        addBlock(BlockRegistry.SPHENOPTERIS, "Sphenopteris");
        addBlock(BlockRegistry.COOKSONIA, "Cooksonia");
        addBlock(BlockRegistry.SAGENOPTERIS, "Sagenopteris");
        addBlock(BlockRegistry.PLATYHYSTRIX_EGGS, "Platyhystrix Eggs");

        // Items
        add("item.rot.dna_bottle", "DNA Bottle");
        addItem(ItemRegistry.AMBER, "Amber");
        addItem(ItemRegistry.MESOZOIC_FOSSIL, "Mesozoic Fossil");
        addItem(ItemRegistry.PALEOZOIC_FOSSIL, "Paleozoic Fossil");
        addItem(ItemRegistry.PLANT_FOSSIL, "Plant Fossil");
        addItem(ItemRegistry.FURCACAUDA_BUCKET, "Bucket of Furcacauda");
        addItem(ItemRegistry.ARCHAEOPTERIS_CLUB, "Archaeopteris Club");
        addItem(ItemRegistry.ARCHAEOPTERIS_BOAT, "Archaeopteris Boat");
        addItem(ItemRegistry.ARCHAEOPTERIS_CHEST_BOAT, "Archaeopteris Boat with Chest");
        addItem(ItemRegistry.BABY_PLATYHYSTRIX_BUCKET, "Bucket of Baby Platyhystrix");
        addItem(ItemRegistry.CONCAVENATOR_SPAWN_EGG, "Concavenator Spawn Egg");
        addItem(ItemRegistry.FURCACAUDA_SPAWN_EGG, "Furcacauda Spawn Egg");
        addItem(ItemRegistry.PLATYHYSTRIX_SPAWN_EGG, "Platyhystrix Spawn Egg");
        addItem(ItemRegistry.POSTOSUCHUS_SPAWN_EGG, "Postosuchus Spawn Egg");
        addItem(ItemRegistry.PROTOCERATOPS_SPAWN_EGG, "Protoceratops Spawn Egg");
        addItem(ItemRegistry.DUNKLEOSTEUS_SPAWN_EGG, "Dunkleosteus Spawn Egg");
        addItem(ItemRegistry.SHRINGASAURUS_SPAWN_EGG, "Shringasaurus Spawn Egg");

        // Effects
        addEffect(MobEffectRegistry.INTOXICATED, "Intoxicated");

        // Entities
        addEntityType(EntityRegistry.FURCACAUDA, "Furcacauda");
        addEntityType(EntityRegistry.CONCAVENATOR, "Concavenator");
        addEntityType(EntityRegistry.PLATYHYSTRIX, "Platyhystrix");
        addEntityType(EntityRegistry.PROTOCERATOPS, "Protoceratops");
        addEntityType(EntityRegistry.POSTOSUCHUS, "Postosuchus");
        addEntityType(EntityRegistry.DUNKLEOSTEUS, "Dunkleosteus");
        addEntityType(EntityRegistry.SHRINGASAURUS, "Shringasaurus");
        addEntityType(EntityRegistry.MOD_BOAT, "Boat");
        addEntityType(EntityRegistry.MOD_CHEST_BOAT, "Boat with Chest");

        // GUI
        // ...

        // Creative tabs
        add("itemGroup.default", "Relics of Time");
        add("itemGroup.spawn_eggs", "Relics of Time Spawns");

        // Subtitles
        add("subtitles.entity.concavenator.ambient", "Concavenator roars");
        add("subtitles.entity.concavenator.hurt", "Concavenator hurts");
        add("subtitles.entity.concavenator.death", "Concavenator dies");
        add("subtitles.entity.concavenator.call", "Concavenator calls");
        add("subtitles.entity.protoceratops.ambient", "Protoceratops screeches");
        add("subtitles.entity.protoceratops.hurt", "Protoceratops hurts");
        add("subtitles.entity.protoceratops.death", "Protoceratops dies");
        add("subtitles.entity.platyhystrix.ambient", "Platyhystrix crickets?");
        add("subtitles.entity.platyhystrix.hurt", "Platyhystrix hurts");
    }
}
