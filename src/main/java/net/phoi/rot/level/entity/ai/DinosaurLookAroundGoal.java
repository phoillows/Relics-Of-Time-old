package net.phoi.rot.level.entity.ai;

import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.phoi.rot.level.entity.Dinosaur;

public class DinosaurLookAroundGoal extends RandomLookAroundGoal {
    private Dinosaur mob;

    public DinosaurLookAroundGoal(Dinosaur mob) {
        super(mob);
        this.mob = mob;
    }

    @Override
    public boolean canUse() {
        return super.canUse() && !mob.isSleeping();
    }

    @Override
    public boolean canContinueToUse() {
        if (mob.isSleeping()) {
            return false;
        } else {
            return super.canContinueToUse();
        }
    }
}
