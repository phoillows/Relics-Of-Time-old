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

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCK = DeferredRegister.create(ForgeRegistries.BLOCKS, RelicsOfTime.MODID);

    public static final RegistryObject<Block> DNA_ANALYZER = BLOCK.register("dna_analyzer", () -> new DnaAnaylzerBlock(BlockBehaviour.Properties.of(Material.METAL).strength(3.0F, 9.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DNA_CENTRIFUGE = BLOCK.register("dna_centrifuge", () -> new DnaCentrifugeBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2F, 9.0F).sound(SoundType.GLASS).requiresCorrectToolForDrops().noOcclusion()));
    public static final RegistryObject<Block> AMBER_ORE = BLOCK.register("amber_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).strength(1.5F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops(), UniformInt.of(1, 3)));
    public static final RegistryObject<Block> AMBER_GLASS = BLOCK.register("amber_glass", () -> new GlassBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).color(MaterialColor.COLOR_ORANGE)));

    public static final RegistryObject<Block> FOOZIA = BLOCK.register("foozia", () -> new DoublePlantBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> DILLHOFFIA = BLOCK.register("dillhoffia", () -> new TallGrassBlock(BlockBehaviour.Properties.copy(FOOZIA.get())));
    public static final RegistryObject<Block> ARCHAEOSIGLILLARIA = BLOCK.register("archaeosigillaria", () -> new TallGrassBlock(BlockBehaviour.Properties.copy(FOOZIA.get())));
    public static final RegistryObject<Block> VACCINIUM = BLOCK.register("vaccinium", () -> new TallGrassBlock(BlockBehaviour.Properties.copy(FOOZIA.get())));
    public static final RegistryObject<Block> SARRACENIA = BLOCK.register("sarracenia", () -> new DoublePlantBlock(BlockBehaviour.Properties.copy(FOOZIA.get())));
    public static final RegistryObject<Block> HORSETAIL = BLOCK.register("horsetail", () -> new TallGrassBlock(BlockBehaviour.Properties.copy(FOOZIA.get())));
}
