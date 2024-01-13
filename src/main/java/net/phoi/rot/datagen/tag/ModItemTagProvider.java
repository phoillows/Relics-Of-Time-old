package net.phoi.rot.datagen.tag;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.level.tag.RotItemTags;
import net.phoi.rot.registry.ItemRegistry;
import org.jetbrains.annotations.Nullable;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(DataGenerator pGenerator, BlockTagsProvider pBlockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, pBlockTagsProvider, RelicsOfTime.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(RotItemTags.DINO_EGGS).add(
                ItemRegistry.CONCAVENATOR_EGG.get(),
                ItemRegistry.PROTOCERATOPS_EGG.get());
        tag(ItemTags.PLANKS).add(
                ItemRegistry.ARCHAEOPTERIS_PLANKS.get()
        );
    }
}
