package net.phoi.rot.level.item;

import net.minecraft.world.item.Item;
import net.phoi.rot.util.RotCreativeTabs;

public class GenericItem extends Item {
    public GenericItem() {
        super(new Item.Properties().tab(RotCreativeTabs.DEFAULT));
    }

    public GenericItem(Properties properties) {
        super(properties.tab(RotCreativeTabs.DEFAULT));
    }
}
