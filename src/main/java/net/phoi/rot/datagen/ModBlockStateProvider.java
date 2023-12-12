package net.phoi.rot.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.phoi.rot.RelicsOfTime;
import static net.phoi.rot.registry.BlockRegistry.*;
import static net.phoi.rot.util.Helper.createPath;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, RelicsOfTime.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        genericBlock(AMBER_ORE);
        genericBlock(FOSSIL_ORE);
        genericBlock(AMMONITE_FOSSIL_PATH);
        genericBlock(ARCHAEOPTERIS_PLANKS);
        genericBlock(ARCHAEOPTERIS_WOOD, "block/archaeopteris_log");
        genericBlock(STRIPPED_ARCHAEOPTERIS_WOOD, "block/stripped_archaeopteris_log");
        translucentBlock(AMBER_GLASS);
        crossBlock(SMALL_HORSETAIL);
        crossBlock(ARCHAEOPTERIS_SAPLING);
        crossBlock(COOKSONIA);
        cutoutBlock(ARCHAEOPTERIS_LEAVES);
        logBlock((RotatedPillarBlock)ARCHAEOPTERIS_LOG.get());
        logBlock((RotatedPillarBlock)STRIPPED_ARCHAEOPTERIS_LOG.get());
        doorBlockWithRenderType((DoorBlock)ARCHAEOPTERIS_DOOR.get(), createPath("block/archaeopteris_door_bottom"), createPath("block/archaeopteris_door_top"), "cutout");
        trapdoorBlockWithRenderType((TrapDoorBlock)ARCHAEOPTERIS_TRAPDOOR.get(), createPath("block/archaeopteris_trapdoor"), true, "cutout");
        signBlock(ARCHAEOPTERIS_SIGN, ARCHAEOPTERIS_WALL_SIGN, createPath("block/archaeopteris_planks"));
        slabBlock((SlabBlock)ARCHAEOPTERIS_SLAB.get(), createPath("block/archaeopteris_planks"), createPath("block/archaeopteris_planks"));
        stairsBlock((StairBlock)ARCHAEOPTERIS_STAIRS.get(), "archaeopteris", createPath("block/archaeopteris_planks"));
        fenceBlock((FenceBlock)ARCHAEOPTERIS_FENCE.get(), createPath("block/archaeopteris_planks"));
        fenceGateBlock((FenceGateBlock)ARCHAEOPTERIS_FENCE_GATE.get(), createPath("block/archaeopteris_planks"));
        buttonBlock((ButtonBlock)ARCHAEOPTERIS_BUTTON.get(), createPath("block/archaeopteris_planks"));
        pressurePlateBlock((PressurePlateBlock)ARCHAEOPTERIS_PRESSURE_PLATE.get(), createPath("block/archaeopteris_planks"));
    }

    private void genericBlock(RegistryObject<Block> block) {
        simpleBlock(block.get(), cubeAll(block.get()));
    }

    private void genericBlock(RegistryObject<Block> block, String texture) {
        simpleBlock(block.get(), new ConfiguredModel(models().cubeAll(block.getId().getPath(), createPath(texture))));
    }

    private void translucentBlock(RegistryObject<Block> block) {
        simpleBlock(block.get(), new ConfiguredModel(models().cubeAll(block.getId().getPath(), blockTexture(block.get())).renderType("translucent")));
    }

    private void crossBlock(RegistryObject<Block> block) {
        simpleBlock(block.get(), new ConfiguredModel(models().cross(block.getId().getPath(), blockTexture(block.get())).renderType("cutout")));
    }

    private void cutoutBlock(RegistryObject<Block> block) {
        simpleBlock(block.get(), new ConfiguredModel(models().cubeAll(block.getId().getPath(), blockTexture(block.get())).renderType("cutout")));
    }

    private void signBlock(RegistryObject<Block> standing, RegistryObject<Block> wall, ResourceLocation texture) {
        signBlock((StandingSignBlock)standing.get(), (WallSignBlock)wall.get(), texture);
    }
}
