package com.skyfish924.treasury;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class AllFluids {

    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(Registries.FLUID, CreateTreasury.MOD_ID);

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(
                    NeoForgeRegistries.Keys.FLUID_TYPES,
                    CreateTreasury.MOD_ID
            );

    public static final DeferredHolder<FluidType, FluidType> INK_TYPE =
            FLUID_TYPES.register("ink_type",
                    () -> new FluidType(FluidType.Properties.create()
                            .density(1000)
                            .viscosity(8000)
                            .temperature(300)) {
                        @Override
                        public void initializeClient(java.util.function.@NotNull Consumer<IClientFluidTypeExtensions> consumer) {
                            consumer.accept(new IClientFluidTypeExtensions() {
                                @Override
                                public @NotNull ResourceLocation getStillTexture() {
                                    return CreateTreasury.id("block/ink_still");
                                }

                                @Override
                                public @NotNull ResourceLocation getFlowingTexture() {
                                    return CreateTreasury.id("block/ink_flow");
                                }
                            });
                        }
                    });

    public static final DeferredHolder<Fluid, FlowingFluid> INK =
            FLUIDS.register("ink",
                    () -> new BaseFlowingFluid.Source(createProperties()));

    public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_INK =
            FLUIDS.register("flowing_ink",
                    () -> new BaseFlowingFluid.Flowing(createProperties()));

    private static BaseFlowingFluid.Properties createProperties() {
        return new BaseFlowingFluid.Properties(
                INK_TYPE,
                INK,
                FLOWING_INK
        )
                .bucket(AllItems.INK_BUCKET)
                .tickRate(30)
                .levelDecreasePerBlock(2)
                .block(AllBlocks.INK);
    }
}
