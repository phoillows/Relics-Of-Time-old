package net.phoi.rot.level.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.goal.Goal;
import net.phoi.rot.level.entity.Platyhystrix;
import net.phoi.rot.util.ModTags;

public class PlatyhystrixDrowseGoal extends Goal {
    private final Platyhystrix mob;
    private int drownTimer = 0;

    public PlatyhystrixDrowseGoal(Platyhystrix mob) {
        this.mob = mob;
    }

    @Override
    public boolean canUse() {
        return !this.mob.isInWater() && this.mob.level.getBiome(new BlockPos(this.mob.getX(), this.mob.getY(), this.mob.getZ())).is(ModTags.ALLOWS_PLATY_TO_DROWSE);
    }

    @Override
    public boolean canContinueToUse() {
        return !this.mob.isInWater() && this.mob.level.getBiome(new BlockPos(this.mob.getX(), this.mob.getY(), this.mob.getZ())).is(ModTags.ALLOWS_PLATY_TO_DROWSE);
    }

    @Override
    public void tick() {
        super.tick();
        this.drownTimer++;
        if (drownTimer > 100) {
            this.mob.setDrousy(true);
        }
        if (drownTimer > 200) {
            this.mob.hurt(DamageSource.DROWN, 1.0F);
        }
    }

    @Override
    public void stop() {
        super.stop();
        this.mob.setDrousy(false);
        this.drownTimer = 0;
    }
}
