package com.skyfish924.treasury;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AllItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreateTreasury.MOD_ID);

    public static final DeferredItem<Item> TREATED_PAPER = ITEMS.register("treated_paper",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RED_TREATED_PAPER = ITEMS.register("red_treated_paper",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ORANGE_TREATED_PAPER = ITEMS.register("orange_treated_paper",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> YELLOW_TREATED_PAPER = ITEMS.register("yellow_treated_paper",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LIME_TREATED_PAPER = ITEMS.register("lime_treated_paper",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GREEN_TREATED_PAPER = ITEMS.register("green_treated_paper",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CYAN_TREATED_PAPER = ITEMS.register("cyan_treated_paper",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LIGHT_BLUE_TREATED_PAPER = ITEMS.register("light_blue_treated_paper",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BLUE_TREATED_PAPER = ITEMS.register("blue_treated_paper",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PURPLE_TREATED_PAPER = ITEMS.register("purple_treated_paper",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> MAGENTA_TREATED_PAPER = ITEMS.register("magenta_treated_paper",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PINK_TREATED_PAPER = ITEMS.register("pink_treated_paper",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> WHITE_TREATED_PAPER = ITEMS.register("white_treated_paper",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LIGHT_GRAY_TREATED_PAPER = ITEMS.register("light_gray_treated_paper",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GRAY_TREATED_PAPER = ITEMS.register("gray_treated_paper",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BLACK_TREATED_PAPER = ITEMS.register("black_treated_paper",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BROWN_TREATED_PAPER = ITEMS.register("brown_treated_paper",
            () -> new Item(new Item.Properties()));



    public static final DeferredItem<Item> ONE_DOLLAR_BILL = ITEMS.register("1_dollar_bill",
                    () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> TWO_DOLLAR_BILL = ITEMS.register("2_dollar_bill",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> FIVE_DOLLAR_BILL = ITEMS.register("5_dollar_bill",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> TEN_DOLLAR_BILL = ITEMS.register("10_dollar_bill",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> TWENTY_DOLLAR_BILL = ITEMS.register("20_dollar_bill",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> FIFTY_DOLLAR_BILL = ITEMS.register("50_dollar_bill",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ONE_HUNDRED_DOLLAR_BILL = ITEMS.register("100_dollar_bill",
            () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> INK_BUCKET = ITEMS.register("ink_bucket",
            () -> new net.minecraft.world.item.BucketItem(
                    AllFluids.INK.get(),
                    new Item.Properties()
                            .craftRemainder(net.minecraft.world.item.Items.BUCKET)
                            .stacksTo(1)
            ));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}