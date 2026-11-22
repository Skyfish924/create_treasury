package com.skyfish924.treasury.content.printer;

import com.simibubi.create.Create;
import com.skyfish924.treasury.CreateTreasury;
import com.skyfish924.treasury.network.SavePrinterPacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class MoneyPrinterScreen extends AbstractContainerScreen<MoneyPrinterMenu> {

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    CreateTreasury.MOD_ID,
                    "textures/gui/money_printer_screen.png"
            );

    private static final ResourceLocation INVENTORY_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    Create.ID,
                    "textures/gui/player_inventory.png"
            );

    private void renderTooltip(
            GuiGraphics guiGraphics,
            List<Component> lines,
            int mouseX,
            int mouseY
    ) {
        guiGraphics.renderTooltip(
                this.font,
                lines,
                Optional.empty(),
                mouseX,
                mouseY
        );
    }

    private static final int[] DENOMINATIONS = {
            1, 2, 5, 10, 20, 50, 100
    };

    private int denomination = 1;

    private boolean draggingSlider = false;

    int offset = 50;

    private double sliderValue = 0.0;

    private boolean hoveringCheckmark = false;

    public MoneyPrinterScreen(
            MoneyPrinterMenu menu,
            Inventory inventory,
            Component title
    ) {
        super(menu, inventory, title);

        this.imageWidth = 228;
        this.imageHeight = 154;
    }

    private boolean isHoveringSlider(double mouseX, double mouseY) {
        int sliderLeft = this.leftPos + 10;
        int sliderRight = this.leftPos + 187;
        int sliderTop = this.topPos + 134 - offset;
        int sliderBottom = this.topPos + 145 - offset;

        return mouseX >= sliderLeft
                && mouseX <= sliderRight
                && mouseY >= sliderTop
                && mouseY <= sliderBottom;
    }

    @Override
    protected void renderBg(
            GuiGraphics guiGraphics,
            float partialTick,
            int mouseX,
            int mouseY
    ) {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        guiGraphics.blit(
                TEXTURE,
                x,
                y - offset,
                0,
                0,
                this.imageWidth,
                this.imageHeight
        );

        guiGraphics.blit(
                INVENTORY_TEXTURE,
                x + 48,
                y + 160 - offset,
                0,
                0,
                176,
                120
        );

        int inkLevel = this.menu.getSyncedFluidAmount();
        int capacity = 4000;
        int maxTankGuiHeight = 94;
        int scaledHeight = (int) ((long) inkLevel * maxTankGuiHeight / capacity);

        if (scaledHeight > 0) {
            int renderX = x + 9;
            int width = 16;

            net.minecraft.client.renderer.texture.TextureAtlasSprite sprite =
                    net.minecraft.client.Minecraft.getInstance()
                            .getTextureAtlas(net.minecraft.client.renderer.texture.TextureAtlas.LOCATION_BLOCKS)
                            .apply(net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(
                                    com.skyfish924.treasury.CreateTreasury.MOD_ID,
                                    "block/ink_still"
                            ));

            int remainingHeight = scaledHeight;
            int currentY = y + 25 - offset + maxTankGuiHeight;

            while (remainingHeight > 0) {
                int chunkHeight = Math.min(remainingHeight, 16);
                currentY -= chunkHeight;

                guiGraphics.blit(
                        renderX,
                        currentY,
                        0,
                        width,
                        chunkHeight,
                        sprite
                );

                remainingHeight -= chunkHeight;
            }
        }

        int thumbX = x + 10 + (int)(sliderValue * 177 - 3);
        int thumbY = y + 133 - offset;
        boolean hovered = isHoveringSlider(mouseX, mouseY) || draggingSlider;

        guiGraphics.blit(TEXTURE, thumbX, thumbY, hovered ? 87 : 79, 163, 7, 14);

        hoveringCheckmark = mouseX >= this.leftPos + 203
                && mouseX <= this.leftPos + 220
                && mouseY >= this.topPos + 131 - offset
                && mouseY <= this.topPos + 148 - offset;

        guiGraphics.blit(TEXTURE, x + 203, y + 131 - offset, hoveringCheckmark ? 98 : 118, 161, 18, 18);
    }

    @Override
    public void render(
            @NotNull GuiGraphics guiGraphics,
            int mouseX,
            int mouseY,
            float partialTick
    ) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        if (isHoveringSlider(mouseX, mouseY)) {
            this.renderTooltip(
                    guiGraphics,
                    List.of(
                            Component.literal("Decides what denomination to be printed."),
                            Component.literal("Higher denominations require equivalent diamonds.")
                    ),
                    mouseX + 6,
                    mouseY + 24
            );
        }

        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(
            GuiGraphics guiGraphics,
            int mouseX,
            int mouseY
    ) {
        int titleX = (this.imageWidth - this.font.width(this.title)) / 2;

        guiGraphics.drawString(
                this.font,
                this.title,
                titleX,
                -25,
                0x404040,
                false
        );

        guiGraphics.drawString(
                this.font,
                this.playerInventoryTitle,
                56,
                116,
                0x404040,
                false
        );

        String text = "Denomination: " + denomination;

        guiGraphics.drawString(
                this.font,
                text,
                95 - this.font.width(text) / 2,
                136 - offset,
                0xFFFFFF,
                true
        );
    }

    private EditBox firstField;
    private EditBox secondField;

    @Override
    public boolean keyPressed(
            int keyCode,
            int scanCode,
            int modifiers
    ) {
        if (this.getFocused() instanceof EditBox editBox) {
            if (keyCode == 256) { // ESC
                return super.keyPressed(keyCode, scanCode, modifiers);
            }

            return editBox.keyPressed(keyCode, scanCode, modifiers);
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    protected void init() {
        super.init();

        firstField = new EditBox(
                this.font,
                this.leftPos + 34,
                this.topPos + 32,
                180,
                16,
                Component.literal("First")
        );

        firstField.setCanLoseFocus(true);
        firstField.setMaxLength(20);

        secondField = new EditBox(
                this.font,
                this.leftPos + 34,
                this.topPos + 52,
                180,
                16,
                Component.literal("Second")
        );

        secondField.setCanLoseFocus(true);
        secondField.setMaxLength(20);

        this.addRenderableWidget(firstField);
        this.addRenderableWidget(secondField);

        firstField.setValue(menu.getBlockEntity().getFirst());
        secondField.setValue(menu.getBlockEntity().getSecond());

        denomination = menu.getBlockEntity().getDenomination();

        for (int i = 0; i < DENOMINATIONS.length; i++) {
            if (DENOMINATIONS[i] == denomination) {
                sliderValue = i / (double)(DENOMINATIONS.length - 1);
                break;
            }
        }
    }

    private void updateSlider(double mouseX) {
        int sliderLeft = this.leftPos + 10;
        int sliderRight = this.leftPos + 187;

        sliderValue = (mouseX - sliderLeft)
                / (double)(sliderRight - sliderLeft);

        sliderValue = net.minecraft.util.Mth.clamp(
                sliderValue,
                0.0,
                1.0
        );

        int index = Math.round(
                (float)(sliderValue * (DENOMINATIONS.length - 1))
        );

        denomination = DENOMINATIONS[index];
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.firstField.mouseClicked(mouseX, mouseY, button)) {
            this.setFocused(this.firstField);
            return true;
        }
        if (this.secondField.mouseClicked(mouseX, mouseY, button)) {
            this.setFocused(this.secondField);
            return true;
        }

        if (isHoveringSlider(mouseX, mouseY) && button == 0) {
            this.draggingSlider = true;
            this.updateSlider(mouseX);
            return true;
        }

        if (hoveringCheckmark && button == 0) {
            net.neoforged.neoforge.network.PacketDistributor.sendToServer(new SavePrinterPacket(
                    this.menu.getBlockEntity().getBlockPos(),
                    firstField.getValue(),
                    secondField.getValue(),
                    this.denomination
            ));

            net.minecraft.client.Minecraft.getInstance().getSoundManager().play(
                    net.minecraft.client.resources.sounds.SimpleSoundInstance.forUI(
                            net.minecraft.sounds.SoundEvents.UI_BUTTON_CLICK, 1.0F
                    )
            );

            this.onClose();
            return true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0) {
            this.draggingSlider = false;
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (this.draggingSlider && button == 0) {
            this.updateSlider(mouseX);
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    public boolean isHovering(
            int x,
            int y,
            int width,
            int height,
            double mouseX,
            double mouseY
    ) {
        return super.isHovering(x, y, width, height, mouseX, mouseY);
    }
}