package net.phoi.rot.level.entity.ai;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.goal.Goal;
import net.phoi.rot.level.entity.Protoceratops;

public class ProtoceratopsLayDownGoal extends Goal {
    private Protoceratops mob;

    public ProtoceratopsLayDownGoal(Protoceratops mob) {
        super();
        this.mob = mob;
    }

    @Override
    public boolean canUse() {
        if (!this.mob.isSleeping() && !this.mob.level.isNight() && !this.mob.isInWater() && !this.mob.isInLava() && this.mob.getTarget() == null) {
            return RandomSource.create().nextInt(400) == 1;
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        return !this.mob.isSleeping() && !this.mob.level.isNight() && !this.mob.isInWater() && !this.mob.isInLava() && this.mob.getTarget() == null;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.mob.level.isNight()) {
            stop();
        }
    }

    @Override
    public void start() {
        this.mob.setLaying(true);
    }

    @Override
    public void stop() {
        this.mob.setLaying(false);
    }
}
