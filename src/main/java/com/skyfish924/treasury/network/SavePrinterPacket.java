package com.skyfish924.treasury.network;

import com.skyfish924.treasury.CreateTreasury;
import com.skyfish924.treasury.content.printer.MoneyPrinterBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record SavePrinterPacket(
        BlockPos pos,
        String first,
        String second,
        int denomination
) implements CustomPacketPayload {

    public static final Type<SavePrinterPacket> TYPE =
            new Type<>(
                    ResourceLocation.fromNamespaceAndPath(
                            CreateTreasury.MOD_ID,
                            "save_printer"
                    )
            );

    public static final StreamCodec<RegistryFriendlyByteBuf, SavePrinterPacket> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC,
                    SavePrinterPacket::pos,
                    net.minecraft.network.codec.ByteBufCodecs.STRING_UTF8,
                    SavePrinterPacket::first,
                    net.minecraft.network.codec.ByteBufCodecs.STRING_UTF8,
                    SavePrinterPacket::second,
                    net.minecraft.network.codec.ByteBufCodecs.INT,
                    SavePrinterPacket::denomination,
                    SavePrinterPacket::new
            );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(
            SavePrinterPacket packet,
            IPayloadContext context
    ) {
        context.enqueueWork(() -> {

            CreateTreasury.LOGGER.info("Received printer save packet");

            ServerPlayer player = (ServerPlayer) context.player();

            BlockEntity blockEntity =
                    player.level().getBlockEntity(packet.pos());

            if (blockEntity instanceof MoneyPrinterBlockEntity printer) {
                printer.setPrinterData(
                        packet.first(),
                        packet.second(),
                        packet.denomination()
                );
            }
        });
    }
}