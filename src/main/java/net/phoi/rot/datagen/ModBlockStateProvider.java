package net.phoi.rot.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.phoi.rot.RelicsOfTime;
import static net.phoi.rot.registry.BlockRegistry.*;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, RelicsOfTime.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        genericBlock(AMBER_ORE);
        genericBlock(AMMONITE_FOSSIL_PATH);
        translucentBlock(AMBER_GLASS);

        crossBlock(DILLHOFFIA);
        crossBlock(ARCHAEOSIGLILLARIA);
        crossBlock(VACCINIUM);
        crossBlock(HORSETAIL);
        crossBlock(FLORISSANTIA);
    }

    private void genericBlock(RegistryObject<Block> block) {
        simpleBlock(block.get(), cubeAll(block.get()));
    }

    private void translucentBlock(RegistryObject<Block> block) {
        simpleBlock(block.get(), new ConfiguredModel(models().cubeAll(block.getId().getPath(), blockTexture(block.get())).renderType("translucent")));
    }

    private void crossBlock(RegistryObject<Block> block) {
        simpleBlock(block.get(), new ConfiguredModel(models().cross(block.getId().getPath(), blockTexture(block.get())).renderType("cutout")));
    }
}
