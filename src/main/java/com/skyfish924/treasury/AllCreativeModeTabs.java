package com.skyfish924.treasury;

import com.simibubi.create.Create;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AllCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateTreasury.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB = CREATIVE_MODE_TAB.register("main_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(AllItems.ONE_DOLLAR_BILL.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(Create.ID, "palettes"))
                    .title(Component.translatable("creativetab.treasury.main"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(AllBlocks.MONEY_PRINTER.get());
                        output.accept(AllItems.ONE_DOLLAR_BILL.get());
                        output.accept(AllItems.TWO_DOLLAR_BILL.get());
                        output.accept(AllItems.FIVE_DOLLAR_BILL.get());
                        output.accept(AllItems.TEN_DOLLAR_BILL.get());
                        output.accept(AllItems.TWENTY_DOLLAR_BILL.get());
                        output.accept(AllItems.FIFTY_DOLLAR_BILL.get());
                        output.accept(AllItems.ONE_HUNDRED_DOLLAR_BILL.get());
                        output.accept(AllItems.INK_BUCKET.get());
                        output.accept(AllItems.TREATED_PAPER.get());
                        output.accept(AllItems.RED_TREATED_PAPER.get());
                        output.accept(AllItems.ORANGE_TREATED_PAPER.get());
                        output.accept(AllItems.YELLOW_TREATED_PAPER.get());
                        output.accept(AllItems.LIME_TREATED_PAPER.get());
                        output.accept(AllItems.GREEN_TREATED_PAPER.get());
                        output.accept(AllItems.CYAN_TREATED_PAPER.get());
                        output.accept(AllItems.LIGHT_BLUE_TREATED_PAPER.get());
                        output.accept(AllItems.BLUE_TREATED_PAPER.get());
                        output.accept(AllItems.PURPLE_TREATED_PAPER.get());
                        output.accept(AllItems.MAGENTA_TREATED_PAPER.get());
                        output.accept(AllItems.PINK_TREATED_PAPER.get());
                        output.accept(AllItems.WHITE_TREATED_PAPER.get());
                        output.accept(AllItems.LIGHT_GRAY_TREATED_PAPER.get());
                        output.accept(AllItems.GRAY_TREATED_PAPER.get());
                        output.accept(AllItems.BLACK_TREATED_PAPER.get());
                        output.accept(AllItems.BROWN_TREATED_PAPER.get());
                    }).build());

    public static void Register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}