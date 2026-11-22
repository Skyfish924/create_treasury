package com.skyfish924.treasury.datagen;

import com.skyfish924.treasury.AllItems;
import com.skyfish924.treasury.CreateTreasury;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CreateTreasury.MOD_ID, existingFileHelper);
    }

    private void item(Item item, String modelName, String texturePath) {
        singleTexture(
                modelName,
                mcLoc("item/generated"),
                "layer0",
                modLoc("item/" + texturePath)
        );
    }

    @Override
    protected void registerModels() {
        item(AllItems.TREATED_PAPER.get(), 
                "treated_paper",
                "treated_papers/treated_paper");
        item(AllItems.RED_TREATED_PAPER.get(),
                "red_treated_paper",
                "treated_papers/red_treated_paper");
        item(AllItems.ORANGE_TREATED_PAPER.get(),
                "orange_treated_paper",
                "treated_papers/orange_treated_paper");
        item(AllItems.YELLOW_TREATED_PAPER.get(),
                "yellow_treated_paper",
                "treated_papers/yellow_treated_paper");
        item(AllItems.LIME_TREATED_PAPER.get(),
                "lime_treated_paper",
                "treated_papers/lime_treated_paper");
        item(AllItems.GREEN_TREATED_PAPER.get(),
                "green_treated_paper",
                "treated_papers/green_treated_paper");
        item(AllItems.CYAN_TREATED_PAPER.get(),
                "cyan_treated_paper",
                "treated_papers/cyan_treated_paper");
        item(AllItems.LIGHT_BLUE_TREATED_PAPER.get(),
                "light_blue_treated_paper",
                "treated_papers/light_blue_treated_paper");
        item(AllItems.BLUE_TREATED_PAPER.get(),
                "blue_treated_paper",
                "treated_papers/blue_treated_paper");
        item(AllItems.PURPLE_TREATED_PAPER.get(),
                "purple_treated_paper",
                "treated_papers/purple_treated_paper");
        item(AllItems.MAGENTA_TREATED_PAPER.get(),
                "magenta_treated_paper",
                "treated_papers/magenta_treated_paper");
        item(AllItems.PINK_TREATED_PAPER.get(),
                "pink_treated_paper",
                "treated_papers/pink_treated_paper");
        item(AllItems.WHITE_TREATED_PAPER.get(),
                "white_treated_paper",
                "treated_papers/white_treated_paper");
        item(AllItems.LIGHT_GRAY_TREATED_PAPER.get(),
                "light_gray_treated_paper",
                "treated_papers/light_gray_treated_paper");
        item(AllItems.GRAY_TREATED_PAPER.get(),
                "gray_treated_paper",
                "treated_papers/gray_treated_paper");
        item(AllItems.BLACK_TREATED_PAPER.get(),
                "black_treated_paper",
                "treated_papers/black_treated_paper");
        item(AllItems.BROWN_TREATED_PAPER.get(),
                "brown_treated_paper",
                "treated_papers/brown_treated_paper");
    }
}
