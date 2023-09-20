package net.phoi.rot.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.phoi.rot.registry.BlockEntityRegistry;

public class TemplateSignBlockEntity extends SignBlockEntity {
    public TemplateSignBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return BlockEntityRegistry.SIGN_BLOCK_ENTITY.get();
    }
}
