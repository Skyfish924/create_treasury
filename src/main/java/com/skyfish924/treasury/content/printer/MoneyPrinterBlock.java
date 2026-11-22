package com.skyfish924.treasury.content.printer;

import com.simibubi.create.content.kinetics.base.KineticBlock;
import com.simibubi.create.foundation.block.IBE;
import com.skyfish924.treasury.AllBlockEntities;
import com.skyfish924.treasury.CreateTreasury;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class MoneyPrinterBlock extends KineticBlock implements IBE<MoneyPrinterBlockEntity> {

    public MoneyPrinterBlock(Properties props) {
        super(props);
        registerDefaultState(
                defaultBlockState()
                        .setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction facing = context.getHorizontalDirection().getOpposite();

        if (context.getPlayer() != null && context.getPlayer().isShiftKeyDown()) {
            facing = facing.getOpposite();
        }

        return defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, facing);
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return Direction.Axis.Y;
    }

    @Override
    public Class<MoneyPrinterBlockEntity> getBlockEntityClass() {
        return MoneyPrinterBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends MoneyPrinterBlockEntity> getBlockEntityType() {
        return AllBlockEntities.MONEY_PRINTER_BLOCK_ENTITY.get();
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(
            @NotNull BlockState state,
            Level level,
            @NotNull BlockPos pos,
            @NotNull Player player,
            @NotNull BlockHitResult hit
    ) {
        if (!level.isClientSide) {
            BlockEntity blockEntity = level.getBlockEntity(pos);

            CreateTreasury.LOGGER.info("Clicked block entity: {}", blockEntity);

            if (blockEntity instanceof MoneyPrinterBlockEntity printer) {
                player.openMenu(
                        printer,
                        buf -> buf.writeBlockPos(pos)
                );

                CreateTreasury.LOGGER.info("Opened money printer menu");
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean hasShaftTowards(net.minecraft.world.level.LevelReader world, net.minecraft.core.BlockPos pos, BlockState state, net.minecraft.core.Direction face) {
        return face == net.minecraft.core.Direction.DOWN;
    }
}
