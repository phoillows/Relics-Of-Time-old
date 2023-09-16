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
import net.phoi.rot.registry.ItemRegistry;
import static net.phoi.rot.registry.ItemRegistry.*;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, RelicsOfTime.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        genericItem(AMBER);
        genericItem(PLANT_FOSSIL);
        genericItem(MESOZOIC_FOSSIL);
        genericItem(PALEOZOIC_FOSSIL);
        genericItem(FURCACAUDA_BUCKET);
        spawnEgg(FURCACAUDA_SPAWN_EGG);
        spawnEgg(CONCAVENATOR_SPAWN_EGG);

        blockItem(AMBER_ORE_ITEM);
        blockItem(AMBER_GLASS_ITEM);
        blockItem(AMMONITE_FOSSIL_PATH_ITEM);
        texturedBlockItem(DILLHOFFIA_ITEM);
        texturedBlockItem(ARCHAEOSIGLILLARIA_ITEM);
        texturedBlockItem(VACCINIUM_ITEM);
        texturedBlockItem(HORSETAIL_ITEM);
        texturedBlockItem(FLORISSANTIA_ITEM);

        for (RegistryObject<Item> item : ItemRegistry.ITEM.getEntries()) {
            if (item.get() instanceof DnaBottleItem) {
                genericItem(item);
            }
        }
    }

    private ItemModelBuilder genericItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), new ResourceLocation("item/generated"))
                .texture("layer0", new ResourceLocation(RelicsOfTime.MODID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder blockItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), new ResourceLocation(RelicsOfTime.MODID, "block/" + item.getId().getPath()));
    }

    private ItemModelBuilder texturedBlockItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), new ResourceLocation("item/generated"))
                .texture("layer0", new ResourceLocation(RelicsOfTime.MODID, "block/" + item.getId().getPath()));
    }

    private ItemModelBuilder spawnEgg(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), new ResourceLocation("item/template_spawn_egg"));
    }
}
