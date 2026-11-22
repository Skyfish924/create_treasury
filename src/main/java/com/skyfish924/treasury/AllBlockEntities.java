package com.skyfish924.treasury;

import com.skyfish924.treasury.content.printer.MoneyPrinterBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AllBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(
                    Registries.BLOCK_ENTITY_TYPE,
                    CreateTreasury.MOD_ID
            );

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MoneyPrinterBlockEntity>> MONEY_PRINTER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("money_printer",
                    () -> BlockEntityType.Builder.of(MoneyPrinterBlockEntity::new, AllBlocks.MONEY_PRINTER.get()).build(null));

}
