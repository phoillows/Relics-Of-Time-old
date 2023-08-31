package net.phoi.rot.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.phoi.rot.RelicsOfTime;

public class SoundRegistry {
    public static final SoundEvent CONCAVENATOR_AMBIENT = new SoundEvent(new ResourceLocation(RelicsOfTime.MODID, "entity.concavenator.ambient"));
    public static final SoundEvent CONCAVENATOR_HURT = new SoundEvent(new ResourceLocation(RelicsOfTime.MODID, "entity.concavenator.hurt"));
    public static final SoundEvent CONCAVENATOR_DEATH = new SoundEvent(new ResourceLocation(RelicsOfTime.MODID, "entity.concavenator.death"));
    public static final SoundEvent CONCAVENATOR_CALL = new SoundEvent(new ResourceLocation(RelicsOfTime.MODID, "entity.concavenator.call"));
}
