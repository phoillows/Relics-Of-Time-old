package net.phoi.rot.level.entity.ai;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.goal.Goal;
import net.phoi.rot.level.entity.Sleepable;

public class DinosaurSleepGoal extends Goal {
    private Sleepable dino;
    private final int sleepAmount;
    private int sleepTimer = 0;

    public DinosaurSleepGoal(Sleepable dino, int sleepAmount) {
        super();
        this.dino = dino;
        this.sleepAmount = sleepAmount;
    }

    @Override
    public boolean canUse() {
        return RandomSource.create().nextInt(200) == 1;
    }

    @Override
    public boolean canContinueToUse() {
        if (sleepTimer >= sleepAmount) {
            stop();
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void tick() {
        super.tick();
        sleepTimer++;
        if (sleepTimer >= sleepAmount) {
            stop();
        }
    }

    @Override
    public void start() {
        sleepTimer = 0;
        dino.setSleeping(true);
    }

    @Override
    public void stop() {
        sleepTimer = 0;
        dino.setSleeping(false);
    }
}
