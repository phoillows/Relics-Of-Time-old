package net.phoi.rot.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
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
            Platyhystrix platy = EntityRegistry.PLATYHYSTRIX.get().create(pLevel);
            platy.moveTo(pPos.getX() + 0.5F, pPos.getY(), pPos.getZ() + 0.5F);
            pLevel.addFreshEntity(platy);
        }
        super.tick(pState, pLevel, pPos, pRandom);
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        RandomSource random = RandomSource.create();
        pLevel.scheduleTick(pPos, this, random.nextInt(100, 200));
        super.onPlace(pState, pLevel, pPos, pOldState, pIsMoving);
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return mayPlace(pLevel, pPos.below());
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
        if (!pState.canSurvive(pLevel, pCurrentPos)) {
            return Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(pState, pDirection, pNeighborState, pLevel, pCurrentPos, pNeighborPos);
    }

    private static boolean mayPlace(BlockGetter blockGetter, BlockPos pos) {
        FluidState state = blockGetter.getFluidState(pos);
        FluidState state1 = blockGetter.getFluidState(pos.above());
        return state.getType() == Fluids.WATER && state1.getType() == Fluids.EMPTY;
    }
}
