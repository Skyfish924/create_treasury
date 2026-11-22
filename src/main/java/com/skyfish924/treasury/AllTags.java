package com.skyfish924.treasury;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class AllTags {
    public static final TagKey<Item> TREATED_PAPERS = ItemTags.create(
            ResourceLocation.fromNamespaceAndPath("create_treasury", "treated_papers")
    );
}
