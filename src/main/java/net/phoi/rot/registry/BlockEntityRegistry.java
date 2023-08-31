package net.phoi.rot.registry;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.level.block.entity.*;

public class BlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, RelicsOfTime.MODID);

    public static final RegistryObject<BlockEntityType<DnaAnaylzerBlockEntity>> DNA_ANAYLZER = BLOCK_ENTITY.register("dna_anaylzer", () -> BlockEntityType.Builder.of(DnaAnaylzerBlockEntity::new, BlockRegistry.DNA_ANALYZER.get()).build(null));
}
