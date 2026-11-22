package com.skyfish924.treasury;

import com.skyfish924.treasury.content.EconomySecurityHandler;
import com.skyfish924.treasury.content.TeenyWeenyClass;
import com.skyfish924.treasury.network.SavePrinterPacket;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(CreateTreasury.MOD_ID)
public class CreateTreasury {
    public static final String MOD_ID = "create_treasury";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CreateTreasury(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::register);
        modEventBus.addListener(this::registerCapabilities);

        AllCreativeModeTabs.Register(modEventBus);
        AllDataComponents.DATA_COMPONENTS.register(modEventBus);
        AllItems.register(modEventBus);
        AllBlocks.register(modEventBus);
        AllBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        AllFluids.FLUIDS.register(modEventBus);
        AllFluids.FLUID_TYPES.register(modEventBus);
        AllMenus.MENUS.register(modEventBus);
        AllSounds.SOUNDS.register(modEventBus);
        NeoForge.EVENT_BUS.addListener(EconomySecurityHandler::onAnvilRenameCheck);
        NeoForge.EVENT_BUS.register(this);
        TRIGGERS.register(modEventBus);
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public void register(RegisterPayloadHandlersEvent event) {
        var registrar = event.registrar("1");

        registrar.playToServer(
                SavePrinterPacket.TYPE,
                SavePrinterPacket.STREAM_CODEC,
                SavePrinterPacket::handle
        );
    }

    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.FluidHandler.BLOCK,
                AllBlockEntities.MONEY_PRINTER_BLOCK_ENTITY.get(),
                (blockEntity, direction) -> blockEntity.getFluidTank()
        );

        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                AllBlockEntities.MONEY_PRINTER_BLOCK_ENTITY.get(),
                (blockEntity, direction) -> blockEntity.getInventory()
        );
    }

    public static final DeferredRegister<CriterionTrigger<?>> TRIGGERS =
            DeferredRegister.create(Registries.TRIGGER_TYPE, CreateTreasury.MOD_ID);

    public static final DeferredHolder<CriterionTrigger<?>, TeenyWeenyClass> FULL_INVENTORY =
            TRIGGERS.register("full_inventory", TeenyWeenyClass::new);


    private void commonSetup(FMLCommonSetupEvent event) {

    }

    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent.Post event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            FULL_INVENTORY.get().trigger(player);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}
