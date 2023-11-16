package net.phoi.rot.level.tag;

import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.phoi.rot.util.Helper;

public class ModBiomeTags {
    public static final TagKey<Biome> ALLOWS_PLATY_TO_DROWSE = createTag("allows_platy_to_drowse");

    private static TagKey<Biome> createTag(String name) {
        return TagKey.create(Registry.BIOME_REGISTRY, Helper.createPath(name));
    }
}
