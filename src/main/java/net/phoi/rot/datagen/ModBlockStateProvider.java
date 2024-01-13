package net.phoi.rot.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.registry.BlockRegistry;
import net.phoi.rot.util.Helper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, RelicsOfTime.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        genericBlock(BlockRegistry.AMBER_ORE);
        genericBlock(BlockRegistry.FOSSIL_ORE);
        genericBlock(BlockRegistry.AMMONITE_FOSSIL_PATH);
        genericBlock(BlockRegistry.ARCHAEOPTERIS_PLANKS);
        genericBlock(BlockRegistry.ARCHAEOPTERIS_WOOD, "block/archaeopteris_log");
        genericBlock(BlockRegistry.STRIPPED_ARCHAEOPTERIS_WOOD, "block/stripped_archaeopteris_log");

        translucentBlock(BlockRegistry.AMBER_GLASS);

        crossBlock(BlockRegistry.SMALL_HORSETAIL);
        crossBlock(BlockRegistry.ARCHAEOPTERIS_SAPLING);
        crossBlock(BlockRegistry.COOKSONIA);

        cutoutBlock(BlockRegistry.ARCHAEOPTERIS_LEAVES);

        logBlock((RotatedPillarBlock)BlockRegistry.ARCHAEOPTERIS_LOG.get());
        logBlock((RotatedPillarBlock)BlockRegistry.STRIPPED_ARCHAEOPTERIS_LOG.get());

        doorBlockWithRenderType((DoorBlock)BlockRegistry.ARCHAEOPTERIS_DOOR.get(), Helper.createPath("block/archaeopteris_door_bottom"), Helper.createPath("block/archaeopteris_door_top"), "cutout");

        trapdoorBlockWithRenderType((TrapDoorBlock)BlockRegistry.ARCHAEOPTERIS_TRAPDOOR.get(), Helper.createPath("block/archaeopteris_trapdoor"), true, "cutout");

        signBlock(BlockRegistry.ARCHAEOPTERIS_SIGN, BlockRegistry.ARCHAEOPTERIS_WALL_SIGN, Helper.createPath("block/archaeopteris_planks"));

        slabBlock((SlabBlock)BlockRegistry.ARCHAEOPTERIS_SLAB.get(), Helper.createPath("block/archaeopteris_planks"), Helper.createPath("block/archaeopteris_planks"));

        stairsBlock((StairBlock)BlockRegistry.ARCHAEOPTERIS_STAIRS.get(), "archaeopteris", Helper.createPath("block/archaeopteris_planks"));

        fenceBlock((FenceBlock)BlockRegistry.ARCHAEOPTERIS_FENCE.get(), Helper.createPath("block/archaeopteris_planks"));

        fenceGateBlock((FenceGateBlock)BlockRegistry.ARCHAEOPTERIS_FENCE_GATE.get(), Helper.createPath("block/archaeopteris_planks"));

        buttonBlock((ButtonBlock)BlockRegistry.ARCHAEOPTERIS_BUTTON.get(), Helper.createPath("block/archaeopteris_planks"));

        pressurePlateBlock((PressurePlateBlock)BlockRegistry.ARCHAEOPTERIS_PRESSURE_PLATE.get(), Helper.createPath("block/archaeopteris_planks"));
    }

    private void genericBlock(RegistryObject<Block> block) {
        simpleBlock(block.get(), cubeAll(block.get()));
    }

    private void genericBlock(RegistryObject<Block> block, String texture) {
        simpleBlock(block.get(), new ConfiguredModel(models().cubeAll(block.getId().getPath(), Helper.createPath(texture))));
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
