package net.phoi.rot.util;

import net.minecraft.resources.ResourceLocation;
import net.phoi.rot.RelicsOfTime;

public class Helper {
    public static ResourceLocation createPath(String path) {
        return new ResourceLocation(RelicsOfTime.MODID, path);
    }
}
