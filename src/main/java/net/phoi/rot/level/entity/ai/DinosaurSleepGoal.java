package net.phoi.rot.level.entity.ai;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Saddleable;
import net.minecraft.world.entity.ai.goal.Goal;
import net.phoi.rot.level.entity.Dinosaur;

public class DinosaurSleepGoal extends Goal {
    private Dinosaur dinosaur;
    private int sleepTimer = 0;

    public DinosaurSleepGoal(Dinosaur dinosaur) {
        super();
        this.dinosaur = dinosaur;
    }

    @Override
    public boolean canUse() {
        if (this.dinosaur.level.isNight() && !this.dinosaur.isInWater() && !this.dinosaur.isInLava() && this.dinosaur.getTarget() == null) {
            if (this.dinosaur instanceof Saddleable saddled && saddled.isSaddled()) {
                return RandomSource.create().nextInt(2000) == 1;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean canContinueToUse() {
        if (this.dinosaur.level.isDay() && !this.dinosaur.isInWater() && !this.dinosaur.isInLava()) {
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
        if (this.dinosaur.level.isDay()) {
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
