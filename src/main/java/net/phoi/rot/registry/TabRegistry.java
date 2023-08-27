package net.phoi.rot.registry;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class TabRegistry {
    public static final CreativeModeTab RELICS_OF_TIME = new CreativeModeTab("relics_of_time") {

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemRegistry.FURCACAUDA_BUCKET.get());
        }
    };
}
