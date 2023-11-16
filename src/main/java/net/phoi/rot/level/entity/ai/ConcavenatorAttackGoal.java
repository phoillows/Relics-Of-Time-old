package net.phoi.rot.level.entity.ai;

import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.phoi.rot.level.entity.Concavenator;

public class ConcavenatorAttackGoal extends MeleeAttackGoal {
    private final Concavenator mob;

    public ConcavenatorAttackGoal(Concavenator mob) {
        super(mob, 1.8D, true);
        this.mob = mob;
    }

    @Override
    public boolean canUse() {
        return !this.mob.isBaby() && !this.mob.isStunned() && super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        return !this.mob.isStunned() && super.canContinueToUse();
    }

    @Override
    public void start() {
        super.start();
        if (this.mob.isLeader()) {
            this.mob.call();
        }
    }

    @Override
    public void stop() {
        super.stop();
        mob.setCalling(false);
    }
}
