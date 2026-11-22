package com.skyfish924.treasury;

import com.simibubi.create.content.kinetics.base.KineticBlock;
import com.skyfish924.treasury.content.CoinPressBlock;
import com.skyfish924.treasury.content.printer.MoneyPrinterBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class AllBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(CreateTreasury.MOD_ID);

    public static final DeferredBlock<KineticBlock> COIN_PRESS = registerBlock("coin_press",
           () -> new CoinPressBlock(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));

    public static final DeferredBlock<KineticBlock> MONEY_PRINTER = registerBlock("money_printer",
            () -> new MoneyPrinterBlock(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));

    public static final DeferredBlock<LiquidBlock> INK =
            BLOCKS.register("ink",
                    () -> new LiquidBlock(
                            AllFluids.INK.get(),
                            BlockBehaviour.Properties.of()
                                    .noCollission()
                                    .replaceable()
                    ));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        AllItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
