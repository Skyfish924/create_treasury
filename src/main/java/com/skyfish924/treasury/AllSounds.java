package com.skyfish924.treasury;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class AllSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, CreateTreasury.MOD_ID);

    public static final DeferredHolder<SoundEvent, SoundEvent> PRINTER =
            SOUNDS.register("printer",
                    () -> SoundEvent.createVariableRangeEvent(
                            CreateTreasury.id("printer")
                    ));
}