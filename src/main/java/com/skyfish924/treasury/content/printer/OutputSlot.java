package com.skyfish924.treasury.content.printer;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class OutputSlot extends SlotItemHandler {

    public OutputSlot(
            ItemStackHandler inventory,
            int index,
            int x,
            int y
    ) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return false;
    }
}