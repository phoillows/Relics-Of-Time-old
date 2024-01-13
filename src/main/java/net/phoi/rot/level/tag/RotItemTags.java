package net.phoi.rot.level.tag;

import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.phoi.rot.util.Helper;

public class RotItemTags {
    public static final TagKey<Item> DINO_EGGS = create("dino_eggs");

    private static TagKey<Item> create(String name) {
        return TagKey.create(Registry.ITEM_REGISTRY, Helper.createPath(name));
    }
}
