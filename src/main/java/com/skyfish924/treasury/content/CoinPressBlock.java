package com.skyfish924.treasury.content;

import com.simibubi.create.content.kinetics.base.KineticBlock;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class CoinPressBlock extends KineticBlock {
    public CoinPressBlock(Properties props) {
        super(props);
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return Direction.Axis.X;
    }
}
