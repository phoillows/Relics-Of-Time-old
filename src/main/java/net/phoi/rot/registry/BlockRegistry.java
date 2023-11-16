package net.phoi.rot.registry;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.level.block.*;
import net.phoi.rot.level.feature.tree.ArchaeopterisTreeGrower;
import net.phoi.rot.util.ModWoodTypes;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCK = DeferredRegister.create(ForgeRegistries.BLOCKS, RelicsOfTime.MODID);

    public static final RegistryObject<Block> DNA_ANALYZER = BLOCK.register("dna_analyzer", () -> new DnaAnaylzerBlock(BlockBehaviour.Properties.of(Material.METAL).strength(3.0F, 9.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DNA_CENTRIFUGE = BLOCK.register("dna_centrifuge", () -> new DnaCentrifugeBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2F, 9.0F).sound(SoundType.GLASS).requiresCorrectToolForDrops().noOcclusion()));
    public static final RegistryObject<Block> AMBER_ORE = BLOCK.register("amber_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).strength(1.5F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops(), UniformInt.of(1, 3)));
    public static final RegistryObject<Block> AMBER_GLASS = BLOCK.register("amber_glass", () -> new GlassBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).color(MaterialColor.COLOR_ORANGE)));
    public static final RegistryObject<Block> FOSSIL_ORE = BLOCK.register("fossil_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops(), UniformInt.of(1, 3)));
    public static final RegistryObject<Block> AMMONITE_FOSSIL_PATH = BLOCK.register("ammonite_fossil_path", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(1.5F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> STRIPPED_ARCHAEOPTERIS_LOG = BLOCK.register("stripped_archaeopteris_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> STRIPPED_ARCHAEOPTERIS_WOOD = BLOCK.register("stripped_archaeopteris_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(STRIPPED_ARCHAEOPTERIS_LOG.get())));
    public static final RegistryObject<Block> ARCHAEOPTERIS_PLANKS = BLOCK.register("archaeopteris_planks", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ARCHAEOPTERIS_SLAB = BLOCK.register("archaeopteris_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(ARCHAEOPTERIS_PLANKS.get())));
    public static final RegistryObject<Block> ARCHAEOPTERIS_STAIRS = BLOCK.register("archaeopteris_stairs", () -> new StairBlock(ARCHAEOPTERIS_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(ARCHAEOPTERIS_PLANKS.get())));
    public static final RegistryObject<Block> ARCHAEOPTERIS_FENCE = BLOCK.register("archaeopteris_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(ARCHAEOPTERIS_PLANKS.get())));
    public static final RegistryObject<Block> ARCHAEOPTERIS_FENCE_GATE = BLOCK.register("archaeopteris_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(ARCHAEOPTERIS_PLANKS.get())));
    public static final RegistryObject<Block> ARCHAEOPTERIS_BUTTON = BLOCK.register("archaeopteris_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ARCHAEOPTERIS_PRESSURE_PLATE = BLOCK.register("archaeopteris_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, ARCHAEOPTERIS_PLANKS.get().defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ARCHAEOPTERIS_LOG = BLOCK.register("archaeopteris_log", () -> new StrippableBlock(STRIPPED_ARCHAEOPTERIS_LOG.get(), BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ARCHAEOPTERIS_WOOD = BLOCK.register("archaeopteris_wood", () -> new StrippableBlock(STRIPPED_ARCHAEOPTERIS_WOOD.get(), BlockBehaviour.Properties.copy(ARCHAEOPTERIS_LOG.get())));
    public static final RegistryObject<Block> ARCHAEOPTERIS_LEAVES = BLOCK.register("archaeopteris_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).sound(SoundType.GRASS).randomTicks().noOcclusion()));
    public static final RegistryObject<Block> ARCHAEOPTERIS_DOOR = BLOCK.register("archaeopteris_door", () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(2.0F).sound(SoundType.LADDER).noOcclusion()));
    public static final RegistryObject<Block> ARCHAEOPTERIS_TRAPDOOR = BLOCK.register("archaeopteris_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> ARCHAEOPTERIS_SAPLING = BLOCK.register("archaeopteris_sapling", () -> new SaplingBlock(new ArchaeopterisTreeGrower(), BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> ARCHAEOPTERIS_SIGN = BLOCK.register("archaeopteris_sign", () -> new TemplateStandingSignBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(1.0F).sound(SoundType.WOOD).noCollission(), ModWoodTypes.ARCHAEOPTERIS));
    public static final RegistryObject<Block> ARCHAEOPTERIS_WALL_SIGN = BLOCK.register("archaeopteris_wall_sign", () -> new TemplateWallSignBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(1.0F).sound(SoundType.WOOD).noCollission(), ModWoodTypes.ARCHAEOPTERIS));

    public static final RegistryObject<Block> SMALL_HORSETAIL = BLOCK.register("small_horsetail", () -> new TallGrassBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> LARGE_HORSETAIL = BLOCK.register("large_horsetail", () -> new DoublePlantBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> NEOCALAMITES = BLOCK.register("neocalamites", () -> new NeocalamitesBlock(BlockBehaviour.Properties.of(Material.BAMBOO).strength(1.0F).sound(SoundType.BAMBOO).randomTicks()));

    public static final RegistryObject<Block> CONCAVENATOR_EGG = BLOCK.register("concavenator_egg", () -> new DinosaurEggBlock(EntityRegistry.CONCAVENATOR, BlockBehaviour.Properties.of(Material.EGG, MaterialColor.COLOR_ORANGE).strength(0.5F).sound(SoundType.METAL).randomTicks().noOcclusion()));
    public static final RegistryObject<Block> PROTOCERATOPS_EGG = BLOCK.register("protoceratops_egg", () -> new DinosaurEggBlock(EntityRegistry.PROTOCERATOPS, BlockBehaviour.Properties.of(Material.EGG, MaterialColor.COLOR_ORANGE).strength(0.5F).sound(SoundType.METAL).randomTicks().noOcclusion()));
}