package net.phoi.rot.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.phoi.rot.registry.ItemRegistry;

public class ConcavenatorEggBlock extends Block {
    private static final IntegerProperty EGGS = IntegerProperty.create("eggs", 1, 3);
    private static final VoxelShape ONE_EGG_SHAPE = box(3, 0, 3, 12, 8, 12);
    private static final VoxelShape MULTIPLE_EGG_SHAPE = box(1, 0, 1, 15, 8, 15);

    public ConcavenatorEggBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(EGGS, 1));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult blockHitResult) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.is(ItemRegistry.CONCAVENATOR_EGG_ITEM.get()) && state.getValue(EGGS) < 3) {
            level.setBlock(pos, state.setValue(EGGS, state.getValue(EGGS) + 1), 2);
            level.playSound(player, pos, SoundEvents.METAL_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
            player.swing(hand);
            if (!player.isCreative()) {
                stack.shrink(1);
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return super.use(state, level, pos, player, hand, blockHitResult);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext collisionContext) {
        return state.getValue(EGGS) > 1 ? MULTIPLE_EGG_SHAPE : ONE_EGG_SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(EGGS);
    }
}
