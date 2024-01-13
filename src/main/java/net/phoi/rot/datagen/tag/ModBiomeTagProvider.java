package net.phoi.rot.datagen.tag;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.level.tag.RotBiomeTags;
import org.jetbrains.annotations.Nullable;

public class ModBiomeTagProvider extends BiomeTagsProvider {
    public ModBiomeTagProvider(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, RelicsOfTime.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(RotBiomeTags.ALLOWS_PLATY_TO_DROWSE).add(Biomes.DESERT).addTags(BiomeTags.IS_BADLANDS);
    }
}
