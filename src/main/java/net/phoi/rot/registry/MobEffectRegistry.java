package net.phoi.rot.registry;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.level.effect.*;

public class MobEffectRegistry {
    public static final DeferredRegister<MobEffect> MOB_EFFECT = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, RelicsOfTime.MODID);

    public static final RegistryObject<MobEffect> INTOXICATED = MOB_EFFECT.register("intoxicated", () -> new IntoxicatedEffect());
}
