package com.skyfish924.treasury;

import com.skyfish924.treasury.content.printer.MoneyPrinterMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AllMenus {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(
                    Registries.MENU,
                    CreateTreasury.MOD_ID
            );

    public static final DeferredHolder<MenuType<?>, MenuType<MoneyPrinterMenu>> MONEY_PRINTER_MENU =
            MENUS.register(
                    "money_printer",
                    () -> IMenuTypeExtension.create(
                            MoneyPrinterMenu::new
                    )
            );
}