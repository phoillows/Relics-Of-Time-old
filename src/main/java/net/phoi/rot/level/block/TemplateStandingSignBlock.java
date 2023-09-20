package net.phoi.rot.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.phoi.rot.level.block.entity.TemplateSignBlockEntity;

public class TemplateStandingSignBlock extends StandingSignBlock {
    public TemplateStandingSignBlock(Properties properties, WoodType woodType) {
        super(properties, woodType);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TemplateSignBlockEntity(pos, state);
    }
}
