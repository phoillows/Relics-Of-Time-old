package net.phoi.rot.registry;

import net.minecraft.sounds.SoundEvent;
import net.phoi.rot.util.Helper;

public class SoundRegistry {
    public static final SoundEvent CONCAVENATOR_AMBIENT = new SoundEvent(Helper.createPath("entity.concavenator.ambient"));
    public static final SoundEvent CONCAVENATOR_HURT = new SoundEvent(Helper.createPath("entity.concavenator.hurt"));
    public static final SoundEvent CONCAVENATOR_DEATH = new SoundEvent(Helper.createPath("entity.concavenator.death"));
    public static final SoundEvent CONCAVENATOR_CALL = new SoundEvent(Helper.createPath("entity.concavenator.call"));
    public static final SoundEvent PROTOCERATOPS_AMBIENT = new SoundEvent(Helper.createPath("entity.protoceratops.ambient"));
    public static final SoundEvent PROTOCERATOPS_HURT = new SoundEvent(Helper.createPath("entity.protoceratops.hurt"));
    public static final SoundEvent PROTOCERATOPS_DEATH = new SoundEvent(Helper.createPath("entity.protoceratops.death"));
    public static final SoundEvent PLATYHYSTRIX_AMBIENT = new SoundEvent(Helper.createPath("entity.platyhystrix.ambient"));
    public static final SoundEvent PLATYHYSTRIX_HURT = new SoundEvent(Helper.createPath("entity.platyhystrix.hurt"));
}
