package net.phoi.rot.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.util.Helper;

public class SoundRegistry {
    public static final SoundEvent CONCAVENATOR_AMBIENT = new SoundEvent(Helper.createPath("entity.concavenator.ambient"));
    public static final SoundEvent CONCAVENATOR_HURT = new SoundEvent(Helper.createPath("entity.concavenator.hurt"));
    public static final SoundEvent CONCAVENATOR_DEATH = new SoundEvent(Helper.createPath("entity.concavenator.death"));
    public static final SoundEvent CONCAVENATOR_CALL = new SoundEvent(Helper.createPath("entity.concavenator.call"));
}
