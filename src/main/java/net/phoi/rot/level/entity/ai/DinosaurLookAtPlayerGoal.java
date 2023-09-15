package net.phoi.rot.level.entity.ai;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.player.Player;

public class DinosaurLookAtPlayerGoal extends LookAtPlayerGoal {
    private Mob mob;

    public DinosaurLookAtPlayerGoal(Mob mob, Class<? extends Player> player, float range) {
        super(mob, player, range);
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
