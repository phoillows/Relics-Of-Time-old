package net.phoi.rot.level.entity.ai;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.phoi.rot.level.entity.Sleepable;

public class DinosaurSleepGoal extends Goal {
    private Sleepable dino;
    private Level level;
    private final int sleepAmount;
    private int sleepTimer = 0;

    public DinosaurSleepGoal(Sleepable dino, int sleepAmount, Level level) {
        super();
        this.dino = dino;
        this.sleepAmount = sleepAmount;
        this.level = level;
    }

    @Override
    public boolean canUse() {
        if (this.level.isDay()) {
            return RandomSource.create().nextInt(600) == 1;
        } else {
            return RandomSource.create().nextInt(400) == 1;
        }
    }

    @Override
    public boolean canContinueToUse() {
        if (this.sleepTimer >= this.sleepAmount) {
            stop();
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void tick() {
        super.tick();
        this.sleepTimer++;
        if (this.sleepTimer >= this.sleepAmount) {
            stop();
        }
    }

    @Override
    public void start() {
        this.sleepTimer = 0;
        this.dino.setSleeping(true);
    }

    @Override
    public void stop() {
        this.sleepTimer = 0;
        this.dino.setSleeping(false);
    }
}
