package net.phoi.rot.level.item;

import net.minecraft.world.item.Item;
import net.phoi.rot.RelicsOfTime;

public class GenericItem extends Item {
    public GenericItem() {
        super(new Item.Properties().tab(RelicsOfTime.RELICS_OF_TIME));
    }

    public GenericItem(Properties properties) {
        super(properties.tab(RelicsOfTime.RELICS_OF_TIME));
    }
}
