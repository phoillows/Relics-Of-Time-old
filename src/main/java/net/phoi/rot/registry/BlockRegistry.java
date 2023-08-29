package net.phoi.rot.registry;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.level.block.*;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCK = DeferredRegister.create(ForgeRegistries.BLOCKS, RelicsOfTime.MODID);

    public static final RegistryObject<Block> DNA_ANALYZER = BLOCK.register("dna_analyzer", () -> new DnaAnaylzerBlock(BlockBehaviour.Properties.of(Material.METAL).strength(3.0F, 9.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));

    // Plants
    public static final RegistryObject<Block> FOOZIA = BLOCK.register("foozia", () -> new DoublePlantBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> DILLHOFFIA = BLOCK.register("dillhoffia", () -> new TallGrassBlock(BlockBehaviour.Properties.copy(FOOZIA.get())));
    public static final RegistryObject<Block> ARCHAEOSIGLILLARIA = BLOCK.register("archaeosigillaria", () -> new TallGrassBlock(BlockBehaviour.Properties.copy(FOOZIA.get())));
    public static final RegistryObject<Block> VACCINIUM = BLOCK.register("vaccinium", () -> new TallGrassBlock(BlockBehaviour.Properties.copy(FOOZIA.get())));
    public static final RegistryObject<Block> SARRACENIA = BLOCK.register("sarracenia", () -> new DoublePlantBlock(BlockBehaviour.Properties.copy(FOOZIA.get())));
    public static final RegistryObject<Block> HORSETAIL = BLOCK.register("horsetail", () -> new TallGrassBlock(BlockBehaviour.Properties.copy(FOOZIA.get())));
}
