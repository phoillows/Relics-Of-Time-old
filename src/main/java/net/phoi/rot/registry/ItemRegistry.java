package net.phoi.rot.registry;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.phoi.rot.RelicsOfTime;

import static net.phoi.rot.registry.EntityRegistry.*;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, RelicsOfTime.MODID);

    public static final RegistryObject<Item> FURCACAUDA_BUCKET = ITEM.register("furcacauda_bucket", () -> new MobBucketItem(FURCACAUDA, () -> Fluids.WATER, () -> SoundEvents.BUCKET_FILL_FISH, new Item.Properties().stacksTo(1).tab(TabRegistry.RELICS_OF_TIME)));
    public static final RegistryObject<Item> FURCACAUDA_SPAWN_EGG = ITEM.register("furcacauda_spawn_egg", () -> new ForgeSpawnEggItem(FURCACAUDA, 14178304, 1326687, new Item.Properties().tab(TabRegistry.RELICS_OF_TIME)));
}
