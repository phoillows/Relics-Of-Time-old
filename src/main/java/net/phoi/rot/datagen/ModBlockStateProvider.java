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

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, RelicsOfTime.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        genericBlock(AMBER_ORE);
        genericBlock(AMMONITE_FOSSIL_PATH);
        genericBlock(ARCHAEOPTERIS_PLANKS);
        genericBlock(ARCHAEOPTERIS_WOOD, "block/archaeopteris_log");
        genericBlock(STRIPPED_ARCHAEOPTERIS_WOOD, "block/stripped_archaeopteris_log");
        translucentBlock(AMBER_GLASS);
        crossBlock(SMALL_HORSETAIL);
        crossBlock(ARCHAEOPTERIS_SAPLING);
        cutoutBlock(ARCHAEOPTERIS_LEAVES);
        logBlock((RotatedPillarBlock)ARCHAEOPTERIS_LOG.get());
        logBlock((RotatedPillarBlock)STRIPPED_ARCHAEOPTERIS_LOG.get());
        doorBlockWithRenderType((DoorBlock)ARCHAEOPTERIS_DOOR.get(), new ResourceLocation(RelicsOfTime.MODID, "block/archaeopteris_door_bottom"), new ResourceLocation(RelicsOfTime.MODID, "block/archaeopteris_door_top"), "cutout");
        trapdoorBlockWithRenderType((TrapDoorBlock)ARCHAEOPTERIS_TRAPDOOR.get(), new ResourceLocation(RelicsOfTime.MODID, "block/archaeopteris_trapdoor"), true, "cutout");
        signBlock(ARCHAEOPTERIS_SIGN, ARCHAEOPTERIS_WALL_SIGN, new ResourceLocation(RelicsOfTime.MODID, "block/archaeopteris_planks"));
    }

    private void genericBlock(RegistryObject<Block> block) {
        simpleBlock(block.get(), cubeAll(block.get()));
    }

    private void genericBlock(RegistryObject<Block> block, String texture) {
        simpleBlock(block.get(), new ConfiguredModel(models().cubeAll(block.getId().getPath(), new ResourceLocation(RelicsOfTime.MODID, texture))));
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
