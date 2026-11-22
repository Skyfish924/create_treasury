package com.skyfish924.treasury.content.printer;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class FilteredSlot extends SlotItemHandler {

    private final Predicate<ItemStack> validator;

    public FilteredSlot(
            ItemStackHandler inventory,
            int index,
            int x,
            int y,
            Predicate<ItemStack> validator
    ) {
        super(inventory, index, x, y);
        this.validator = validator;
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return validator.test(stack);
    }
}