package com.skyfish924.treasury.content.printer;

import com.skyfish924.treasury.AllMenus;
import com.skyfish924.treasury.AllTags;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

public class MoneyPrinterMenu extends AbstractContainerMenu {

    private final MoneyPrinterBlockEntity blockEntity;
    private final ContainerData fluidData;

    public MoneyPrinterBlockEntity getBlockEntity() {
        return blockEntity;
    }

    public MoneyPrinterMenu(int id, Inventory inventory, RegistryFriendlyByteBuf buf) {
        this(
                id,
                inventory,
                (MoneyPrinterBlockEntity) inventory.player.level().getBlockEntity(buf.readBlockPos())
        );
    }

    public MoneyPrinterMenu(int id, Inventory inventory, MoneyPrinterBlockEntity blockEntity) {
        super(AllMenus.MONEY_PRINTER_MENU.get(), id);
        this.blockEntity = blockEntity;

        this.fluidData = new ContainerData() {
            @Override
            public int get(int index) {
                if (index == 0) return blockEntity.getFluidTank().getFluidAmount();
                return 0;
            }

            @Override
            public void set(int index, int value) {
                if (index == 0) {
                    blockEntity.getFluidTank().setFluid(
                            new FluidStack(com.skyfish924.treasury.AllFluids.INK.get(), value)
                    );
                }
            }

            @Override
            public int getCount() {
                return 1;
            }
        };
        this.addDataSlots(this.fluidData);

        this.addSlot(new FilteredSlot(blockEntity.getInventory(), 0, 56, 7, stack -> stack.is(AllTags.TREATED_PAPERS)));
        this.addSlot(new FilteredSlot(blockEntity.getInventory(), 1, 203, -8, stack -> stack.is(Items.DIAMOND)));
        this.addSlot(new FilteredSlot(blockEntity.getInventory(), 2, 203, -26, stack -> stack.is(Items.DIAMOND)));
        this.addSlot(new OutputSlot(blockEntity.getInventory(), 3, 156, 7));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(inventory, col + row * 9 + 9, 56 + col * 18, 128 + row * 18));
            }
        }

        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(inventory, col, 56 + col * 18, 186));
        }
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return true;
    }

    public int getSyncedFluidAmount() {
        return this.fluidData.get(0);
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        return ItemStack.EMPTY;
    }
}
