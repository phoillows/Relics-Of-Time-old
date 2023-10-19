package net.phoi.rot.level.entity.ai;

import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.player.Player;
import net.phoi.rot.level.entity.Dinosaur;

public class DinosaurLookAtPlayerGoal extends LookAtPlayerGoal {
    private Dinosaur mob;

    public DinosaurLookAtPlayerGoal(Dinosaur mob) {
        super(mob, Player.class, 6.0F);
        this.mob =  mob;
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
