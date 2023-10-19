package net.phoi.rot.level.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.phoi.rot.level.entity.Dinosaur;

public class DinosaurGoToWaterGoal extends MoveToBlockGoal {
    private final Dinosaur mob;

    public DinosaurGoToWaterGoal(Dinosaur pMob, double pSpeedModifier, int pSearchRange) {
        super(pMob, pSpeedModifier, pSearchRange);
        this.mob = pMob;
    }

    @Override
    public boolean canUse() {
        return !this.mob.isInWater() && super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        return !this.mob.isInWater() && super.canContinueToUse();
    }

    @Override
    protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
        return pLevel.getBlockState(pPos).is(Blocks.WATER);
    }
}
