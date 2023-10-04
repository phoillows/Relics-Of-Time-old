package net.phoi.rot.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class NeocalamitesBlock extends Block {
    private static final BooleanProperty TIP = BooleanProperty.create("tip");
    private static final VoxelShape STEM_SHAPE = box(5, 0, 5, 11, 16, 11);
    private static final VoxelShape TIP_SHAPE = box(5, 0, 5, 11, 11, 11);

    public NeocalamitesBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(TIP, true));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        return state.getValue(TIP) ? TIP_SHAPE : STEM_SHAPE;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return !level.getBlockState(pos.below()).is(Blocks.AIR);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState facingState, LevelAccessor level, BlockPos pos, BlockPos facingPos) {
        if (!state.canSurvive(level, pos)) {
            return Blocks.AIR.defaultBlockState();
        }
        if (level.getBlockState(pos.above()).is(Blocks.AIR)) {
            level.setBlock(pos, this.defaultBlockState(), 2);
        } else {
            level.setBlock(pos, state.setValue(TIP, false), 2);
        }
        return super.updateShape(state, direction, facingState, level, pos, facingPos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TIP);
    }
}
