package com.skyfish924.treasury.content.printer;

import com.skyfish924.treasury.*;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class MoneyPrinterBlockEntity extends KineticBlockEntity implements MenuProvider {

    private final FluidTank fluidTank = new FluidTank(4000,
            fluidStack -> fluidStack.getFluid() == AllFluids.INK.get()
    ) {
        @Override
        protected void onContentsChanged() {
            notifyUpdate();
        }
    };

    private final ItemStackHandler inventory = new ItemStackHandler(4) {

        @Override
        protected void onContentsChanged(int slot) {
            notifyUpdate();
        }

        @Override
        public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
            if (slot == 0) {
                if (!stack.is(AllTags.TREATED_PAPERS)) {
                    return stack;
                }
            } else if (slot == 1 || slot == 2) {
                if (!stack.is(net.minecraft.world.item.Items.DIAMOND)) {
                    return stack;
                }
            } else if (slot == 3) {
                return stack;
            }

            return super.insertItem(slot, stack, simulate);
        }
    };

    private String first = "";
    private String second = "";
    private int denomination = 1;
    private int printProgress = 0;

    public MoneyPrinterBlockEntity(BlockPos pos, BlockState state) {
        super(AllBlockEntities.MONEY_PRINTER_BLOCK_ENTITY.get(), pos, state);
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }

    public FluidTank getFluidTank() {
        return fluidTank;
    }

    public void setPrinterData(String first, String second, int denomination) {
        this.first = first;
        this.second = second;
        this.denomination = denomination;

        System.out.println("Printer saved: " + first + ", " + second + ", " + denomination);
        notifyUpdate();
    }

    public String getFirst() { return first; }
    public String getSecond() { return second; }
    public int getDenomination() { return denomination; }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.literal("Money Printer");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, @NotNull Inventory inventory, @NotNull Player player) {
        System.out.println("Opening printer: " + first + " " + second + " " + denomination);
        return new MoneyPrinterMenu(id, inventory, this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public void handleUpdateTag(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        loadWithComponents(tag, registries);
    }

    @Override
    protected void write(CompoundTag tag, HolderLookup.Provider registries, boolean clientPacket) {
        super.write(tag, registries, clientPacket);

        tag.put("Inventory", inventory.serializeNBT(registries));
        fluidTank.writeToNBT(registries, tag);

        tag.putString("First", first);
        tag.putString("Second", second);
        tag.putInt("Denomination", denomination);
        tag.putInt("PrintProgress", printProgress);
    }

    @Override
    protected void read(CompoundTag tag, HolderLookup.Provider registries, boolean clientPacket) {
        super.read(tag, registries, clientPacket);

        if (tag.contains("Inventory")) {
            inventory.deserializeNBT(registries, tag.getCompound("Inventory"));
        }
        fluidTank.readFromNBT(registries, tag);

        first = tag.getString("First");
        second = tag.getString("Second");
        denomination = tag.getInt("Denomination");
        printProgress = tag.getInt("PrintProgress");
    }

    @Override
    public void tick() {
        super.tick();

        if (level == null || level.isClientSide)
            return;

        if (Math.abs(getSpeed()) < 128)
            return;

        printMoney();
    }


    private void printMoney() {
        ItemStack paperInput = inventory.getStackInSlot(0);
        ItemStack plateInputA = inventory.getStackInSlot(1);
        ItemStack plateInputB = inventory.getStackInSlot(2);
        ItemStack outputSlot = inventory.getStackInSlot(3);

        int requiredDiamonds = this.denomination == 0 ? 1 : this.denomination;
        ItemStack targetBill = getBillForDenomination(requiredDiamonds);
        if (targetBill.isEmpty()) return;

        int totalDiamondsAvailable = 0;
        if (!plateInputA.isEmpty() && plateInputA.is(net.minecraft.world.item.Items.DIAMOND)) totalDiamondsAvailable += plateInputA.getCount();
        if (!plateInputB.isEmpty() && plateInputB.is(net.minecraft.world.item.Items.DIAMOND)) totalDiamondsAvailable += plateInputB.getCount();

        boolean hasPaper = !paperInput.isEmpty() && paperInput.is(AllTags.TREATED_PAPERS);
        boolean hasPlate = totalDiamondsAvailable >= requiredDiamonds;
        boolean hasInk = fluidTank.getFluidAmount() >= 50;

        boolean canFitOutput = outputSlot.isEmpty() ||
                (outputSlot.is(targetBill.getItem()) && (outputSlot.getCount() + 5) <= outputSlot.getMaxStackSize());

        if (hasPaper && hasPlate && hasInk && canFitOutput) {
            printProgress++;

            int requiredWork = Math.max(10, 200 - (int)(Math.abs(getSpeed()) / 1.35));

            if (printProgress >= requiredWork) {
                int modelDataIndex = getColorModelData(paperInput.getItem());

                paperInput.shrink(1);
                fluidTank.drain(50, net.neoforged.neoforge.fluids.capability.IFluidHandler.FluidAction.EXECUTE);

                int remainingToDeduct = requiredDiamonds;
                if (!plateInputA.isEmpty() && plateInputA.is(net.minecraft.world.item.Items.DIAMOND)) {
                    int countA = plateInputA.getCount();
                    if (countA >= remainingToDeduct) {
                        plateInputA.shrink(remainingToDeduct);
                        remainingToDeduct = 0;
                    } else {
                        plateInputA.setCount(0);
                        remainingToDeduct -= countA;
                    }
                }
                if (remainingToDeduct > 0 && !plateInputB.isEmpty() && plateInputB.is(net.minecraft.world.item.Items.DIAMOND)) {
                    plateInputB.shrink(remainingToDeduct);
                }

                ItemStack printedBatch = targetBill.copy();
                printedBatch.setCount(5);

                String finalNameString;
                if (this.first == null || this.first.trim().isEmpty()) {
                    finalNameString = requiredDiamonds + " Dollar Bill";
                } else if (this.second == null || this.second.trim().isEmpty()) {
                    finalNameString = requiredDiamonds + " " + this.first;
                } else {
                    finalNameString = requiredDiamonds + " " + this.first + " " + this.second;
                }

                printedBatch.set(
                        DataComponents.ITEM_NAME,
                        net.minecraft.network.chat.Component.literal(finalNameString)
                );

                if (modelDataIndex > 0) {
                    printedBatch.set(
                            net.minecraft.core.component.DataComponents.CUSTOM_MODEL_DATA,
                            new net.minecraft.world.item.component.CustomModelData(modelDataIndex)
                    );
                }

                if (outputSlot.isEmpty()) {
                    inventory.setStackInSlot(3, printedBatch);
                } else {
                    outputSlot.grow(5);
                }

                assert level != null;
                level.playSound(
                        null,
                        worldPosition,
                        AllSounds.PRINTER.get(),
                        SoundSource.BLOCKS,
                        1.0F,
                        1.0F
                );

                printProgress = 0;
                notifyUpdate();
            }
        } else {
            printProgress = 0;
        }
    }

    private int getColorModelData(net.minecraft.world.item.Item paperItem) {
        if (paperItem == AllItems.RED_TREATED_PAPER.get()) return 1;
        if (paperItem == AllItems.ORANGE_TREATED_PAPER.get()) return 2;
        if (paperItem == AllItems.YELLOW_TREATED_PAPER.get()) return 3;
        if (paperItem == AllItems.LIME_TREATED_PAPER.get()) return 4;
        if (paperItem == AllItems.GREEN_TREATED_PAPER.get()) return 5;
        if (paperItem == AllItems.CYAN_TREATED_PAPER.get()) return 6;
        if (paperItem == AllItems.LIGHT_BLUE_TREATED_PAPER.get()) return 7;
        if (paperItem == AllItems.BLUE_TREATED_PAPER.get()) return 8;
        if (paperItem == AllItems.PURPLE_TREATED_PAPER.get()) return 9;
        if (paperItem == AllItems.MAGENTA_TREATED_PAPER.get()) return 10;
        if (paperItem == AllItems.PINK_TREATED_PAPER.get()) return 11;
        if (paperItem == AllItems.WHITE_TREATED_PAPER.get()) return 12;
        if (paperItem == AllItems.LIGHT_GRAY_TREATED_PAPER.get()) return 13;
        if (paperItem == AllItems.GRAY_TREATED_PAPER.get()) return 14;
        if (paperItem == AllItems.BLACK_TREATED_PAPER.get()) return 15;
        if (paperItem == AllItems.BROWN_TREATED_PAPER.get()) return 16;
        return 0;
    }


    private ItemStack getBillForDenomination(int value) {
        return switch (value) {
            case 1 -> new ItemStack(AllItems.ONE_DOLLAR_BILL.get());
            case 2 -> new ItemStack(AllItems.TWO_DOLLAR_BILL.get());
            case 5 -> new ItemStack(AllItems.FIVE_DOLLAR_BILL.get());
            case 10 -> new ItemStack(AllItems.TEN_DOLLAR_BILL.get());
            case 20 -> new ItemStack(AllItems.TWENTY_DOLLAR_BILL.get());
            case 50 -> new ItemStack(AllItems.FIFTY_DOLLAR_BILL.get());
            case 100 -> new ItemStack(AllItems.ONE_HUNDRED_DOLLAR_BILL.get());
            default -> ItemStack.EMPTY;
        };
    }

    @Override
    public float calculateStressApplied() {
        return 4.0f;
    }


    @Override
    public boolean addToGoggleTooltip(java.util.List<net.minecraft.network.chat.Component> tooltip, boolean isPlayerSneaking) {
        float currentSpeed = Math.abs(getSpeed());
        int stressImpact = (int) (calculateStressApplied() * currentSpeed);

        net.minecraft.network.chat.MutableComponent line = net.minecraft.network.chat.Component.literal("    Kinetic Stats:");
        tooltip.add(line);
        net.minecraft.network.chat.MutableComponent line2 = net.minecraft.network.chat.Component.literal("    Kinetic Stress Impact:").withStyle(ChatFormatting.GRAY);
        tooltip.add(line2);
        net.minecraft.network.chat.MutableComponent line3 = net.minecraft.network.chat.Component.literal(String.format("     %,d", (long) stressImpact) + "su ")
                .withStyle(ChatFormatting.AQUA)
                .append(net.minecraft.network.chat.Component.literal("at current speed")
                        .withStyle(ChatFormatting.DARK_GRAY));
        tooltip.add(line3);


        return true;
    }
}