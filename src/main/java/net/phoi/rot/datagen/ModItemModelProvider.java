package net.phoi.rot.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.registry.ItemRegistry;
import net.phoi.rot.util.Helper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, RelicsOfTime.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        genericItem(ItemRegistry.AMBER);
        genericItem(ItemRegistry.MESOZOIC_FOSSIL);
        genericItem(ItemRegistry.PALEOZOIC_FOSSIL);
        genericItem(ItemRegistry.PLANT_FOSSIL);
        genericItem(ItemRegistry.FURCACAUDA_BUCKET);
        genericItem(ItemRegistry.BABY_PLATYHYSTRIX_BUCKET);
        genericItem(ItemRegistry.ARCHAEOPTERIS_BOAT);
        genericItem(ItemRegistry.ARCHAEOPTERIS_CHEST_BOAT);
        genericItem(ItemRegistry.ARCHAEOPTERIS_CLUB, true);
        genericItem(ItemRegistry.HORSETAIL_DNA, "dna/horsetail");
        genericItem(ItemRegistry.NEOCALAMITES_DNA, "dna/neocalamites");
        genericItem(ItemRegistry.CONCAVENATOR_DNA, "dna/concavenator");
        genericItem(ItemRegistry.FURCACAUDA_DNA, "dna/furcacauda");
        genericItem(ItemRegistry.PLATYHYSTRIX_DNA, "dna/platyhystrix");
        genericItem(ItemRegistry.POSTOSUCHUS_DNA, "dna/postosuchus");
        genericItem(ItemRegistry.PROTOCERATOPS_DNA, "dna/protoceratops");

        spawnEgg(ItemRegistry.FURCACAUDA_SPAWN_EGG);
        spawnEgg(ItemRegistry.CONCAVENATOR_SPAWN_EGG);
        spawnEgg(ItemRegistry.PLATYHYSTRIX_SPAWN_EGG);
        spawnEgg(ItemRegistry.PROTOCERATOPS_SPAWN_EGG);
        spawnEgg(ItemRegistry.POSTOSUCHUS_SPAWN_EGG);
        spawnEgg(ItemRegistry.DUNKLEOSTEUS_SPAWN_EGG);

        blockItem(ItemRegistry.AMBER_ORE);
        blockItem(ItemRegistry.AMBER_GLASS);
        blockItem(ItemRegistry.DNA_ANALYZER);
        blockItem(ItemRegistry.DNA_CENTRIFUGE);
        blockItem(ItemRegistry.AMMONITE_FOSSIL_PATH);
        blockItem(ItemRegistry.ARCHAEOPTERIS_PLANKS);
        blockItem(ItemRegistry.ARCHAEOPTERIS_LOG);
        blockItem(ItemRegistry.ARCHAEOPTERIS_LEAVES);
        blockItem(ItemRegistry.STRIPPED_ARCHAEOPTERIS_LOG);
        blockItem(ItemRegistry.ARCHAEOPTERIS_WOOD);
        blockItem(ItemRegistry.STRIPPED_ARCHAEOPTERIS_WOOD);
        blockItem(ItemRegistry.ARCHAEOPTERIS_SLAB);
        blockItem(ItemRegistry.ARCHAEOPTERIS_STAIRS);
        blockItem(ItemRegistry.ARCHAEOPTERIS_FENCE_GATE);
        blockItem(ItemRegistry.ARCHAEOPTERIS_PRESSURE_PLATE);
        blockItem(ItemRegistry.FOSSIL_ORE);
        blockItem(ItemRegistry.DUNKLEOSTEUS_SKULL);

        blockItemWithTexture(ItemRegistry.SMALL_HORSETAIL, "block/large_horsetail_top");
        blockItemWithTexture(ItemRegistry.LARGE_HORSETAIL, "block/small_horsetail");
        blockItemWithTexture(ItemRegistry.ARCHAEOPTERIS_DOOR, "item/archaeopteris_door");
        blockItemWithTexture(ItemRegistry.ARCHAEOPTERIS_SIGN, "item/archaeopteris_sign");
        blockItemWithTexture(ItemRegistry.ARCHAEOPTERIS_SAPLING, "block/archaeopteris_sapling");
        blockItemWithTexture(ItemRegistry.CONCAVENATOR_EGG, "item/concavenator_egg");
        blockItemWithTexture(ItemRegistry.PROTOCERATOPS_EGG, "item/protoceratops_egg");
        blockItemWithTexture(ItemRegistry.COOKSONIA, "block/cooksonia");
        blockItemWithTexture(ItemRegistry.SAGENOPTERIS, "block/sagenopteris_top");
        blockItemWithTexture(ItemRegistry.SPHENOPTERIS, "block/sphenopteris_top");
        blockItemWithTexture(ItemRegistry.PLATYHYSTRIX_EGGS, "block/platyhystrix_eggs");

        trapdoorBlockItem(ItemRegistry.ARCHAEOPTERIS_TRAPDOOR);

        inventoryBlockItem(ItemRegistry.ARCHAEOPTERIS_FENCE);
        inventoryBlockItem(ItemRegistry.ARCHAEOPTERIS_BUTTON);
    }

    private ItemModelBuilder genericItem(RegistryObject<Item> item) {
        return genericItem(item, false);
    }

    private ItemModelBuilder genericItem(RegistryObject<Item> item, boolean handheld) {
        return withExistingParent(item.getId().getPath(), new ResourceLocation(handheld ? "item/handheld" : "item/generated")).texture("layer0", Helper.createPath("item/" + item.getId().getPath()));
    }

    private ItemModelBuilder genericItem(RegistryObject<Item> item, String texture) {
        return withExistingParent(item.getId().getPath(), new ResourceLocation("item/generated")).texture("layer0", Helper.createPath("item/" + texture));
    }

    private ItemModelBuilder spawnEgg(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), new ResourceLocation("item/template_spawn_egg"));
    }

    private ItemModelBuilder blockItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), Helper.createPath("block/" + item.getId().getPath()));
    }

    private ItemModelBuilder blockItemWithTexture(RegistryObject<Item> item, String texture) {
        return withExistingParent(item.getId().getPath(), new ResourceLocation("item/generated")).texture("layer0", Helper.createPath(texture));
    }

    private ItemModelBuilder trapdoorBlockItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), Helper.createPath("block/" + item.getId().getPath() + "_bottom"));
    }

    private ItemModelBuilder inventoryBlockItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), Helper.createPath("block/" + item.getId().getPath() + "_inventory"));
    }
}
