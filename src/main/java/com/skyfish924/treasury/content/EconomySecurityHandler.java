package com.skyfish924.treasury.content;

import com.skyfish924.treasury.AllItems;
import com.skyfish924.treasury.CreateTreasury;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AnvilUpdateEvent;

@EventBusSubscriber(modid = CreateTreasury.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class EconomySecurityHandler {

    @SubscribeEvent
    public static void onAnvilRenameCheck(AnvilUpdateEvent event) {
        ItemStack leftItem = event.getLeft();

        if (leftItem.isEmpty()) {
            return;
        }

        boolean isBanknote = leftItem.is(AllItems.ONE_DOLLAR_BILL.get()) ||
                leftItem.is(AllItems.TWO_DOLLAR_BILL.get()) ||
                leftItem.is(AllItems.FIVE_DOLLAR_BILL.get()) ||
                leftItem.is(AllItems.TEN_DOLLAR_BILL.get()) ||
                leftItem.is(AllItems.TWENTY_DOLLAR_BILL.get()) ||
                leftItem.is(AllItems.FIFTY_DOLLAR_BILL.get()) ||
                leftItem.is(AllItems.ONE_HUNDRED_DOLLAR_BILL.get());

        if (isBanknote) {
            event.setCanceled(true);
        }
    }
}
