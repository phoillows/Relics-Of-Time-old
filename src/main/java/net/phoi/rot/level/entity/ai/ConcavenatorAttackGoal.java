package net.phoi.rot.level.entity.ai;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.player.Player;
import net.phoi.rot.level.entity.Concavenator;
import net.phoi.rot.registry.SoundRegistry;

public class ConcavenatorAttackGoal extends MeleeAttackGoal {
    private final Concavenator mob;

    public ConcavenatorAttackGoal(Concavenator mob) {
        super(mob, 1.8D, true);
        this.mob = mob;
    }

    @Override
    public boolean canUse() {
        return !this.mob.isBaby() && super.canUse();
    }

    @Override
    public void start() {
        super.start();
        if (this.mob.getEntityData().get(Concavenator.DATA_LEADER)) {
            this.mob.level.playSound((Player)null, this.mob.getX(), this.mob.getY(), this.mob.getZ(), SoundRegistry.CONCAVENATOR_CALL, SoundSource.HOSTILE, 2.0F, 1.0F);
            this.mob.setCalling(true);
            for (Concavenator concavenator : this.mob.level.getEntitiesOfClass(Concavenator.class, this.mob.getBoundingBox().inflate(16))) {
                concavenator.setSleeping(false);
            }
        }
        if (!this.mob.isCalling()) {
            this.mob.setSprinting(true);
        }
    }

    @Override
    public void stop() {
        super.stop();
        mob.setSprinting(false);
    }
}
