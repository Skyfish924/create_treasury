package com.skyfish924.treasury.content.printer;

import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

public class MoneyPrinterRenderer
        extends KineticBlockEntityRenderer<MoneyPrinterBlockEntity> {

    public MoneyPrinterRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }
}