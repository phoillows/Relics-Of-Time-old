package net.phoi.rot.datagen.tag;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.registry.BlockRegistry;
import org.jetbrains.annotations.Nullable;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, RelicsOfTime.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                BlockRegistry.DNA_ANALYZER.get(),
                BlockRegistry.DNA_CENTRIFUGE.get(),
                BlockRegistry.AMBER_ORE.get(),
                BlockRegistry.AMMONITE_FOSSIL_PATH.get(),
                BlockRegistry.FOSSIL_ORE.get(),
                BlockRegistry.DUNKLEOSTEUS_SKULL.get()
        );
        tag(BlockTags.NEEDS_STONE_TOOL).add(
                BlockRegistry.DNA_ANALYZER.get(),
                BlockRegistry.DNA_CENTRIFUGE.get(),
                BlockRegistry.AMBER_ORE.get()
        );
        tag(BlockTags.FENCE_GATES).add(
                BlockRegistry.ARCHAEOPTERIS_FENCE_GATE.get()
        );
        tag(BlockTags.LEAVES).add(
                BlockRegistry.ARCHAEOPTERIS_LEAVES.get()
        );
        tag(BlockTags.LOGS_THAT_BURN).add(
                BlockRegistry.ARCHAEOPTERIS_LOG.get(),
                BlockRegistry.ARCHAEOPTERIS_WOOD.get(),
                BlockRegistry.STRIPPED_ARCHAEOPTERIS_LOG.get(),
                BlockRegistry.STRIPPED_ARCHAEOPTERIS_WOOD.get()
        );
        tag(BlockTags.PLANKS).add(
                BlockRegistry.ARCHAEOPTERIS_PLANKS.get()
        );
        tag(BlockTags.SAPLINGS).add(
                BlockRegistry.ARCHAEOPTERIS_SAPLING.get()
        );
        tag(BlockTags.SMALL_FLOWERS).add(
                BlockRegistry.SMALL_HORSETAIL.get(),
                BlockRegistry.COOKSONIA.get()
        );
        tag(BlockTags.STANDING_SIGNS).add(
                BlockRegistry.ARCHAEOPTERIS_SIGN.get()
        );
        tag(BlockTags.TALL_FLOWERS).add(
                BlockRegistry.LARGE_HORSETAIL.get(),
                BlockRegistry.SPHENOPTERIS.get(),
                BlockRegistry.SAGENOPTERIS.get()
        );
        tag(BlockTags.WALL_SIGNS).add(
                BlockRegistry.ARCHAEOPTERIS_WALL_SIGN.get()
        );
        tag(BlockTags.WOODEN_BUTTONS).add(
                BlockRegistry.ARCHAEOPTERIS_BUTTON.get()
        );
        tag(BlockTags.WOODEN_DOORS).add(
                BlockRegistry.ARCHAEOPTERIS_DOOR.get()
        );
        tag(BlockTags.FENCES).add(
                BlockRegistry.ARCHAEOPTERIS_FENCE.get()
        );
        tag(BlockTags.WOODEN_PRESSURE_PLATES).add(
                BlockRegistry.ARCHAEOPTERIS_PRESSURE_PLATE.get()
        );
        tag(BlockTags.WOODEN_SLABS).add(
                BlockRegistry.ARCHAEOPTERIS_SLAB.get()
        );
        tag(BlockTags.WOODEN_STAIRS).add(
                BlockRegistry.ARCHAEOPTERIS_STAIRS.get()
        );
        tag(BlockTags.WOODEN_TRAPDOORS).add(
                BlockRegistry.ARCHAEOPTERIS_TRAPDOOR.get()
        );
    }
}
