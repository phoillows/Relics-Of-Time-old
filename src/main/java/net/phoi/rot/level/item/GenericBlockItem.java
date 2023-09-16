package net.phoi.rot.level.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import net.phoi.rot.RelicsOfTime;

public class GenericBlockItem extends BlockItem {
    public GenericBlockItem(RegistryObject<Block> block) {
        super(block.get(), new Item.Properties().tab(RelicsOfTime.ROT_TAB));
    }
}
