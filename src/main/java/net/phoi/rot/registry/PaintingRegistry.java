package net.phoi.rot.registry;

import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.phoi.rot.RelicsOfTime;

public class PaintingRegistry {
    public static final DeferredRegister<PaintingVariant> PAINTING = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, RelicsOfTime.MODID);

    public static final RegistryObject<PaintingVariant> SUNSET_OF_THE_PERMIAN = PAINTING.register("sunset_in_the_permian", () -> new PaintingVariant(16, 32));
}
