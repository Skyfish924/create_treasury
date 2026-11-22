package com.skyfish924.treasury.datagen;

import com.skyfish924.treasury.AllBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(AllBlocks.MONEY_PRINTER.get());
        dropSelf(AllBlocks.COIN_PRESS.get());
        dropSelf(AllBlocks.INK.get());
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return AllBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
