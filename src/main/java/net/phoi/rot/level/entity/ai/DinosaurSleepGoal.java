package net.phoi.rot.level.entity.ai;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.goal.Goal;
import net.phoi.rot.level.entity.Dinosaur;

public class DinosaurSleepGoal extends Goal {
    private Dinosaur dinosaur;
    private int sleepTimer = 0;
    private final int sleepAmount;

    public DinosaurSleepGoal(Dinosaur dinosaur, int sleepAmount) {
        super();
        this.dinosaur = dinosaur;
        this.sleepAmount = sleepAmount;
    }

    @Override
    public boolean canUse() {
        if (this.dinosaur.level.isDay()) {
            return RandomSource.create().nextInt(800) == 1;
        } else {
            return RandomSource.create().nextInt(500) == 1;
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
        this.dinosaur.setSleeping(true);
    }

    @Override
    public void stop() {
        this.sleepTimer = 0;
        this.dinosaur.setSleeping(false);
    }
}
