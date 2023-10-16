package net.phoi.rot.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
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
import net.minecraftforge.registries.RegistryObject;

public class DinosaurEggBlock extends Block {
    private static final IntegerProperty EGGS = IntegerProperty.create("eggs", 1, 3);
    private static final IntegerProperty CRACKED = IntegerProperty.create("cracked", 0, 2);
    private static final VoxelShape ONE_EGG_SHAPE = box(3, 0, 3, 12, 8, 12);
    private static final VoxelShape MULTIPLE_EGG_SHAPE = box(1, 0, 1, 15, 8, 15);
    private final RegistryObject<? extends EntityType<?>> entityType;

    public DinosaurEggBlock(RegistryObject<? extends EntityType<?>> entityType, Properties properties) {
        super(properties);
        this.entityType = entityType;
        this.registerDefaultState(this.getStateDefinition().any().setValue(EGGS, 1).setValue(CRACKED, 0));
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource randomSource) {
        for (int x = 0; x < state.getValue(EGGS); x++) {
            Entity baby = this.entityType.get().create(level);
            baby.moveTo(pos.getX() + (Math.random() * 0.9F), pos.getY(), pos.getZ() + (Math.random() * 0.9F));
            ((Animal)baby).setBaby(true);
            level.addFreshEntity(baby);
            level.destroyBlock(pos, false);
            level.playSound(null, pos, SoundEvents.TURTLE_EGG_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
        }
        super.randomTick(state, level, pos, randomSource);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult blockHitResult) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.is(this.asItem()) && state.getValue(EGGS) < 3) {
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
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float p_152430_) {
        this.decreaseEggs(level, state, pos);
        super.fallOn(level, state, pos, entity, p_152430_);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext collisionContext) {
        return state.getValue(EGGS) > 1 ? MULTIPLE_EGG_SHAPE : ONE_EGG_SHAPE;
    }

    public void decreaseEggs(Level level, BlockState state, BlockPos pos) {
        if (state.getValue(CRACKED) < 2) {
            level.setBlock(pos, state.setValue(CRACKED, state.getValue(CRACKED) + 1), 2);
            level.playSound(null, pos, SoundEvents.TURTLE_EGG_CRACK, SoundSource.BLOCKS, 1.0F, 1.0F);
        } else {
            if (state.getValue(EGGS) > 1) {
                level.destroyBlock(pos, false);
                level.setBlock(pos, state.setValue(EGGS, state.getValue(EGGS) - 1).setValue(CRACKED, state.getValue(CRACKED)), 2);
            } else {
                level.destroyBlock(pos, false);
            }
            level.playSound(null, pos, SoundEvents.TURTLE_EGG_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(EGGS, CRACKED);
    }
}
