package net.phoi.rot.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.level.block.entity.DnaAnaylzerBlockEntity;
import org.jetbrains.annotations.Nullable;

public class DnaAnaylzerBlock extends BaseFacingBlock implements EntityBlock {
    public DnaAnaylzerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof DnaAnaylzerBlockEntity) {
                NetworkHooks.openScreen((ServerPlayer)player, (DnaAnaylzerBlockEntity)blockEntity, pos);
            } else {
                RelicsOfTime.LOGGER.error("Incorrect block entity");
            }
        }
        return super.use(state, level, pos, player, hand, hit);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DnaAnaylzerBlockEntity(pos, state);
    }
}
