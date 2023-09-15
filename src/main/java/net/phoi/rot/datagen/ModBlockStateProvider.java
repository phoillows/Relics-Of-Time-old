package net.phoi.rot.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.registry.BlockRegistry;
import static net.phoi.rot.registry.BlockRegistry.*;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, RelicsOfTime.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        basicBlock(AMBER_ORE);
        basicBlock(AMMONITE_FOSSIL_PATH);
    }

    private void basicBlock(RegistryObject<Block> block) {
        simpleBlock(block.get());
    }
}
