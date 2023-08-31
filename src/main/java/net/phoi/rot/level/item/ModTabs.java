package net.phoi.rot.level.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.phoi.rot.registry.ItemRegistry;

public class ModTabs {
    public static final CreativeModeTab RELICS_OF_TIME = new CreativeModeTab("relics_of_time") {

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemRegistry.FURCACAUDA_BUCKET.get());
        }
    };
}
