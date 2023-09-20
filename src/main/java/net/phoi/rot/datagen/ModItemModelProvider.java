package net.phoi.rot.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.level.item.GenericItem;
import net.phoi.rot.registry.ItemRegistry;
import static net.phoi.rot.registry.ItemRegistry.*;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, RelicsOfTime.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (RegistryObject<Item> item : ItemRegistry.ITEM.getEntries()) {
            if (item.get() instanceof GenericItem) {
                genericItem(item);
            }
        }
        genericItem(FURCACAUDA_BUCKET);
        spawnEgg(FURCACAUDA_SPAWN_EGG);
        spawnEgg(CONCAVENATOR_SPAWN_EGG);
        spawnEgg(PLATYHYSTRIX_SPAWN_EGG);
        blockItem(AMBER_ORE_ITEM);
        blockItem(AMBER_GLASS_ITEM);
        blockItem(AMMONITE_FOSSIL_PATH_ITEM);
        blockItem(ARCHAEOPTERIS_PLANKS_ITEM);
        blockItem(ARCHAEOPTERIS_LOG_ITEM);
        blockItem(ARCHAEOPTERIS_LEAVES_ITEM);
        blockItem(STRIPPED_ARCHAEOPTERIS_LOG_ITEM);
        blockItem(DILLHOFFIA_ITEM, "block/dillhoffia");
        blockItem(ARCHAEOSIGLILLARIA_ITEM, "block/archaeosigillaria");
        blockItem(VACCINIUM_ITEM, "block/vaccinium");
        blockItem(HORSETAIL_ITEM, "block/horsetail");
        blockItem(FLORISSANTIA_ITEM, "block/florissantia");
        blockItem(ARCHAEOPTERIS_DOOR_ITEM, "item/archaeopteris_door");
        blockItem(ARCHAEOPTERIS_SIGN, "item/archaeopteris_sign");
        blockItem(ARCHAEOPTERIS_SAPLING_ITEM, "block/archaeopteris_sapling");
        trapdoorBlockItem(ARCHAEOPTERIS_TRAPDOOR_ITEM);
    }

    private ItemModelBuilder genericItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), new ResourceLocation("item/generated"))
                .texture("layer0", new ResourceLocation(RelicsOfTime.MODID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder spawnEgg(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), new ResourceLocation("item/template_spawn_egg"));
    }

    private ItemModelBuilder blockItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), new ResourceLocation(RelicsOfTime.MODID, "block/" + item.getId().getPath()));
    }

    private ItemModelBuilder blockItem(RegistryObject<Item> item, String texture) {
        return withExistingParent(item.getId().getPath(), new ResourceLocation("item/generated"))
                .texture("layer0", new ResourceLocation(RelicsOfTime.MODID, texture));
    }

    private ItemModelBuilder trapdoorBlockItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), new ResourceLocation(RelicsOfTime.MODID, "block/" + item.getId().getPath() + "_bottom"));
    }
}
