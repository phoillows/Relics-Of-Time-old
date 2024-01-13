package net.phoi.rot.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.phoi.rot.level.entity.Platyhystrix;
import net.phoi.rot.registry.EntityRegistry;

public class PlatyhystrixEggBlock extends Block {
    private static final VoxelShape SHAPE = box(0, 0, 0, 16, 1.5, 16);

    public PlatyhystrixEggBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        for (int j = 1; j <= pRandom.nextInt(2, 6); ++j) {
            pLevel.destroyBlock(pPos, false);
            pLevel.playSound(null, pPos, SoundEvents.FROGSPAWN_HATCH, SoundSource.BLOCKS, 1.0F, 1.0F);
            Platyhystrix platy = EntityRegistry.PLATYHYSTRIX.get().create(pLevel);
            platy.moveTo(pPos.getX() + 0.5F, pPos.getY(), pPos.getZ() + 0.5F);
            platy.setBaby(true);
            pLevel.addFreshEntity(platy);
        }
        super.tick(pState, pLevel, pPos, pRandom);
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        RandomSource random = RandomSource.create();
        pLevel.scheduleTick(pPos, this, random.nextInt(400, 900));
        super.onPlace(pState, pLevel, pPos, pOldState, pIsMoving);
    }
}
