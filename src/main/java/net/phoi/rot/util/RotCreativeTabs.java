package net.phoi.rot.util;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.phoi.rot.registry.ItemRegistry;

public class RotCreativeTabs {
    public static final CreativeModeTab DEFAULT = new CreativeModeTab("default") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemRegistry.MESOZOIC_FOSSIL.get());
        }
    };

    public static final CreativeModeTab SPAWN_EGGS = new CreativeModeTab("spawn_eggs") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemRegistry.FURCACAUDA_SPAWN_EGG.get());
        }
    };
}
