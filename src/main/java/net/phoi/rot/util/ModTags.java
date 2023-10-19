package net.phoi.rot.util;

import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class ModTags {
    public static final TagKey<Biome> ALLOWS_PLATY_TO_DROWSE = createBiomeTag("allows_platy_to_drowse");

    private static TagKey<Biome> createBiomeTag(String name) {
        return TagKey.create(Registry.BIOME_REGISTRY, Helper.createPath(name));
    }
}
