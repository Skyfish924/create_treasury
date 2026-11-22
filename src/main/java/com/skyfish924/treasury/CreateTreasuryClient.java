package com.skyfish924.treasury;

import com.skyfish924.treasury.content.printer.MoneyPrinterRenderer;
import com.skyfish924.treasury.content.printer.MoneyPrinterScreen;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(
        modid = CreateTreasury.MOD_ID,
        value = Dist.CLIENT,
        bus = EventBusSubscriber.Bus.MOD
)
public class CreateTreasuryClient {

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        BlockEntityRenderers.register(
                AllBlockEntities.MONEY_PRINTER_BLOCK_ENTITY.get(),
                MoneyPrinterRenderer::new
        );
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(
                AllMenus.MONEY_PRINTER_MENU.get(),
                MoneyPrinterScreen::new
        );
    }
}
