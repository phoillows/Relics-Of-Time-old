package net.phoi.rot.level.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.JumpGoal;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.phoi.rot.level.entity.Dunkleosteus;

public class DunkleosteusJumpGoal extends JumpGoal {
    private static final int[] STEPS_TO_CHECK = new int[]{0, 1, 4, 5, 6, 7};
    private final Dunkleosteus dunk;
    private final int interval;
    private boolean breached;

    /** Copied directly from the DolphinJumpGoal class **/
    public DunkleosteusJumpGoal(Dunkleosteus dunk, int pInterval) {
        this.dunk = dunk;
        this.interval = reducedTickDelay(pInterval);
    }

    public boolean canUse() {
        if (this.dunk.getRandom().nextInt(this.interval) != 0) {
            return false;
        } else {
            Direction direction = this.dunk.getMotionDirection();
            int i = direction.getStepX();
            int j = direction.getStepZ();
            BlockPos blockpos = this.dunk.blockPosition();

            for(int k : STEPS_TO_CHECK) {
                if (!this.waterIsClear(blockpos, i, j, k) || !this.surfaceIsClear(blockpos, i, j, k)) {
                    return false;
                }
            }

            return true;
        }
    }

    private boolean waterIsClear(BlockPos pPos, int pDx, int pDz, int pScale) {
        BlockPos blockpos = pPos.offset(pDx * pScale, 0, pDz * pScale);
        return this.dunk.level.getFluidState(blockpos).is(FluidTags.WATER) && !this.dunk.level.getBlockState(blockpos).getMaterial().blocksMotion();
    }

    private boolean surfaceIsClear(BlockPos pPos, int pDx, int pDz, int pScale) {
        return this.dunk.level.getBlockState(pPos.offset(pDx * pScale, 1, pDz * pScale)).isAir() && this.dunk.level.getBlockState(pPos.offset(pDx * pScale, 2, pDz * pScale)).isAir();
    }

    public boolean canContinueToUse() {
        double d0 = this.dunk.getDeltaMovement().y;
        return (!(d0 * d0 < (double)0.03F) || this.dunk.getXRot() == 0.0F || !(Math.abs(this.dunk.getXRot()) < 10.0F) || !this.dunk.isInWater()) && !this.dunk.isOnGround();
    }

    public boolean isInterruptable() {
        return false;
    }

    public void start() {
        Direction direction = this.dunk.getMotionDirection();
        this.dunk.setDeltaMovement(this.dunk.getDeltaMovement().add((double)direction.getStepX() * 0.6D, 0.7D, (double)direction.getStepZ() * 0.6D));
        this.dunk.getNavigation().stop();
        this.dunk.setBreaching(true);
    }

    public void stop() {
        this.dunk.setXRot(0.0F);
        this.dunk.setBreaching(false);
    }

    public void tick() {
        boolean flag = this.breached;
        if (!flag) {
            FluidState fluidstate = this.dunk.level.getFluidState(this.dunk.blockPosition());
            this.breached = fluidstate.is(FluidTags.WATER);
        }

        if (this.breached && !flag) {
            this.dunk.playSound(SoundEvents.DOLPHIN_JUMP, 1.0F, 1.0F);
        }

        Vec3 vec3 = this.dunk.getDeltaMovement();
        if (vec3.y * vec3.y < (double)0.03F && this.dunk.getXRot() != 0.0F) {
            this.dunk.setXRot(Mth.rotlerp(this.dunk.getXRot(), 0.0F, 0.2F));
        } else if (vec3.length() > (double)1.0E-5F) {
            double d0 = vec3.horizontalDistance();
            double d1 = Math.atan2(-vec3.y, d0) * (double)(180F / (float)Math.PI);
            this.dunk.setXRot((float)d1);
        }

    }
}
