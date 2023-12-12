package net.phoi.rot.level.entity.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.phoi.rot.level.entity.Concavenator;

public class ConcavenatorAttackGoal extends MeleeAttackGoal {
    private final Concavenator mob;

    public ConcavenatorAttackGoal(Concavenator mob) {
        super(mob, mob.isBaby() ? 1.6D : 1.9D, true);
        this.mob = mob;
    }

    @Override
    public boolean canUse() {
        if (!this.mob.isStunned()) {
            LivingEntity target = this.mob.getTarget();
            if (target != null) {
                double concavX = this.mob.getBoundingBox().getXsize();
                double concavY = this.mob.getBoundingBox().getYsize();
                double targetX = target.getBoundingBox().getXsize();
                double targetY = target.getBoundingBox().getYsize();
                return targetX < concavX && targetY < concavY && super.canUse();
            }
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        if (!this.mob.isStunned()) {
            LivingEntity target = this.mob.getTarget();
            if (target != null) {
                double concavX = this.mob.getBoundingBox().getXsize();
                double concavY = this.mob.getBoundingBox().getYsize();
                double targetX = target.getBoundingBox().getXsize();
                double targetY = target.getBoundingBox().getYsize();
                return targetX < concavX && targetY < concavY && super.canContinueToUse();
            }
        }
        return false;
    }

    @Override
    public void start() {
        super.start();
        if (this.mob.isLeader()) {
            this.mob.call();
        }
        this.mob.setRunning(true);
    }

    @Override
    public void stop() {
        super.stop();
        this.mob.setCalling(false);
        this.mob.setRunning(false);
    }
}
