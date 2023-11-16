package net.phoi.rot.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.level.item.DnaBottleItem;
import net.phoi.rot.level.item.GenericItem;
import net.phoi.rot.registry.ItemRegistry;
import static net.phoi.rot.registry.ItemRegistry.*;
import static net.phoi.rot.util.Helper.createPath;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, RelicsOfTime.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        generateItemModels();
        genericItem(FURCACAUDA_BUCKET);
        genericItem(ARCHAEOPTERIS_BOAT);
        spawnEgg(FURCACAUDA_SPAWN_EGG);
        spawnEgg(CONCAVENATOR_SPAWN_EGG);
        spawnEgg(PLATYHYSTRIX_SPAWN_EGG);
        spawnEgg(PROTOCERATOPS_SPAWN_EGG);
        spawnEgg(POSTOSUCHUS_SPAWN_EGG);
        blockItem(AMBER_ORE_ITEM);
        blockItem(AMBER_GLASS_ITEM);
        blockItem(DNA_ANALYZER_ITEM);
        blockItem(DNA_CENTRIFUGE_ITEM);
        blockItem(AMMONITE_FOSSIL_PATH_ITEM);
        blockItem(ARCHAEOPTERIS_PLANKS_ITEM);
        blockItem(ARCHAEOPTERIS_LOG_ITEM);
        blockItem(ARCHAEOPTERIS_LEAVES_ITEM);
        blockItem(STRIPPED_ARCHAEOPTERIS_LOG_ITEM);
        blockItem(ARCHAEOPTERIS_WOOD_ITEM);
        blockItem(STRIPPED_ARCHAEOPTERIS_WOOD_ITEM);
        blockItem(ARCHAEOPTERIS_SLAB_ITEM);
        blockItem(ARCHAEOPTERIS_STAIRS_ITEM);
        blockItem(ARCHAEOPTERIS_FENCE_GATE_ITEM);
        blockItem(ARCHAEOPTERIS_PRESSURE_PLATE_ITEM);
        blockItem(FOSSIL_ORE_ITEM);
        blockItem(SMALL_HORSETAIL_ITEM, "block/large_horsetail_top");
        blockItem(LARGE_HORSETAIL_ITEM, "block/small_horsetail");
        blockItem(ARCHAEOPTERIS_DOOR_ITEM, "item/archaeopteris_door");
        blockItem(ARCHAEOPTERIS_SIGN, "item/archaeopteris_sign");
        blockItem(ARCHAEOPTERIS_SAPLING_ITEM, "block/archaeopteris_sapling");
        blockItem(CONCAVENATOR_EGG_ITEM, "item/concavenator_egg");
        blockItem(PROTOCERATOPS_EGG_ITEM, "item/protoceratops_egg");
        trapdoorBlockItem(ARCHAEOPTERIS_TRAPDOOR_ITEM);
        inventoryBlockItem(ARCHAEOPTERIS_FENCE_ITEM);
        inventoryBlockItem(ARCHAEOPTERIS_BUTTON_ITEM);
    }

    private ItemModelBuilder genericItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), new ResourceLocation("item/generated")).texture("layer0", createPath("item/" + item.getId().getPath()));
    }

    private ItemModelBuilder genericItem(RegistryObject<Item> item, String texture) {
        return withExistingParent(item.getId().getPath(), new ResourceLocation("item/generated")).texture("layer0", createPath("item/" + texture));
    }

    private ItemModelBuilder handHeldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), new ResourceLocation("item/handheld")).texture("layer0", createPath("item/" + item.getId().getPath()));
    }

    private ItemModelBuilder spawnEgg(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), new ResourceLocation("item/template_spawn_egg"));
    }

    private ItemModelBuilder blockItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), createPath("block/" + item.getId().getPath()));
    }

    private ItemModelBuilder blockItem(RegistryObject<Item> item, String texture) {
        return withExistingParent(item.getId().getPath(), new ResourceLocation("item/generated")).texture("layer0", createPath(texture));
    }

    private ItemModelBuilder trapdoorBlockItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), createPath("block/" + item.getId().getPath() + "_bottom"));
    }

    private ItemModelBuilder inventoryBlockItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), createPath("block/" + item.getId().getPath() + "_inventory"));
    }

    private void generateItemModels() {
        for (RegistryObject<Item> item : ItemRegistry.ITEM.getEntries()) {
            if (item.get() instanceof GenericItem) {
                if (item.get() instanceof DnaBottleItem) {
                    genericItem(item, "dna/" + item.getId().getPath());
                } else {
                    genericItem(item);
                }
            }
        }
    }
}
