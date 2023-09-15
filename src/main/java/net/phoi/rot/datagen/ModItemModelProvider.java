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
        simpleItem(AMBER);
        simpleItem(PLANT_FOSSIL);
        simpleItem(MESOZOIC_FOSSIL);
        simpleItem(PALEOZOIC_FOSSIL);
        simpleItem(FURCACAUDA_BUCKET);

        spawnEggItem(FURCACAUDA_SPAWN_EGG);
        spawnEggItem(CONCAVENATOR_SPAWN_EGG);

        blockItem(AMBER_ORE_ITEM);
        blockItem(AMMONITE_FOSSIL_PATH_ITEM);
        for (RegistryObject<Item> item : ItemRegistry.ITEM.getEntries()) {
            if (item.get() instanceof DnaBottleItem) {
                simpleItem(item);
            }
        }
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), new ResourceLocation("item/generated"))
                .texture("layer0", new ResourceLocation(RelicsOfTime.MODID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder blockItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), new ResourceLocation(RelicsOfTime.MODID, "block/" + item.getId().getPath()));
    }

    private ItemModelBuilder spawnEggItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), new ResourceLocation("item/template_spawn_egg"));
    }
}
