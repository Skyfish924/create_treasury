package com.skyfish924.treasury.datagen;

import com.skyfish924.treasury.AllBlocks;
import com.skyfish924.treasury.AllItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllBlocks.MONEY_PRINTER.get())
                .pattern("BPB")
                .pattern("STS")
                .pattern("BIB")
                .define('B', com.simibubi.create.AllItems.BRASS_SHEET.get())
                .define('I', com.simibubi.create.AllBlocks.INDUSTRIAL_IRON_BLOCK.get())
                .define('S', com.simibubi.create.AllBlocks.SHAFT.get())
                .define('P', com.simibubi.create.AllBlocks.FLUID_PIPE.get())
                .define('T', com.simibubi.create.AllBlocks.FLUID_TANK.get())
                .unlockedBy("has_brass_sheet", has(com.simibubi.create.AllItems.BRASS_SHEET))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.TREATED_PAPER.get(), 1)
                .requires(Items.PAPER, 1)
                .requires(Items.SLIME_BALL, 1)
                .unlockedBy("has_paper", has(Items.PAPER))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.WHITE_TREATED_PAPER.get(), 1)
                .requires(AllItems.TREATED_PAPER, 1)
                .requires(Items.WHITE_DYE, 1)
                .unlockedBy("has_treated_paper", has(AllItems.TREATED_PAPER))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.ORANGE_TREATED_PAPER.get(), 1)
                .requires(AllItems.TREATED_PAPER, 1)
                .requires(Items.ORANGE_DYE, 1)
                .unlockedBy("has_treated_paper", has(AllItems.TREATED_PAPER))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.MAGENTA_TREATED_PAPER.get(), 1)
                .requires(AllItems.TREATED_PAPER, 1)
                .requires(Items.MAGENTA_DYE, 1)
                .unlockedBy("has_treated_paper", has(AllItems.TREATED_PAPER))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.LIGHT_BLUE_TREATED_PAPER.get(), 1)
                .requires(AllItems.TREATED_PAPER, 1)
                .requires(Items.LIGHT_BLUE_DYE, 1)
                .unlockedBy("has_treated_paper", has(AllItems.TREATED_PAPER))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.YELLOW_TREATED_PAPER.get(), 1)
                .requires(AllItems.TREATED_PAPER, 1)
                .requires(Items.YELLOW_DYE, 1)
                .unlockedBy("has_treated_paper", has(AllItems.TREATED_PAPER))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.LIME_TREATED_PAPER.get(), 1)
                .requires(AllItems.TREATED_PAPER, 1)
                .requires(Items.LIME_DYE, 1)
                .unlockedBy("has_treated_paper", has(AllItems.TREATED_PAPER))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.PINK_TREATED_PAPER.get(), 1)
                .requires(AllItems.TREATED_PAPER, 1)
                .requires(Items.PINK_DYE, 1)
                .unlockedBy("has_treated_paper", has(AllItems.TREATED_PAPER))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.GRAY_TREATED_PAPER.get(), 1)
                .requires(AllItems.TREATED_PAPER, 1)
                .requires(Items.GRAY_DYE, 1)
                .unlockedBy("has_treated_paper", has(AllItems.TREATED_PAPER))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.LIGHT_GRAY_TREATED_PAPER.get(), 1)
                .requires(AllItems.TREATED_PAPER, 1)
                .requires(Items.LIGHT_GRAY_DYE, 1)
                .unlockedBy("has_treated_paper", has(AllItems.TREATED_PAPER))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.CYAN_TREATED_PAPER.get(), 1)
                .requires(AllItems.TREATED_PAPER, 1)
                .requires(Items.CYAN_DYE, 1)
                .unlockedBy("has_treated_paper", has(AllItems.TREATED_PAPER))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.PURPLE_TREATED_PAPER.get(), 1)
                .requires(AllItems.TREATED_PAPER, 1)
                .requires(Items.PURPLE_DYE, 1)
                .unlockedBy("has_treated_paper", has(AllItems.TREATED_PAPER))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.BLUE_TREATED_PAPER.get(), 1)
                .requires(AllItems.TREATED_PAPER, 1)
                .requires(Items.BLUE_DYE, 1)
                .unlockedBy("has_treated_paper", has(AllItems.TREATED_PAPER))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.BROWN_TREATED_PAPER.get(), 1)
                .requires(AllItems.TREATED_PAPER, 1)
                .requires(Items.BROWN_DYE, 1)
                .unlockedBy("has_treated_paper", has(AllItems.TREATED_PAPER))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.GREEN_TREATED_PAPER.get(), 1)
                .requires(AllItems.TREATED_PAPER, 1)
                .requires(Items.GREEN_DYE, 1)
                .unlockedBy("has_treated_paper", has(AllItems.TREATED_PAPER))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.RED_TREATED_PAPER.get(), 1)
                .requires(AllItems.TREATED_PAPER, 1)
                .requires(Items.RED_DYE, 1)
                .unlockedBy("has_treated_paper", has(AllItems.TREATED_PAPER))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.BLACK_TREATED_PAPER.get(), 1)
                .requires(AllItems.TREATED_PAPER, 1)
                .requires(Items.BLACK_DYE, 1)
                .unlockedBy("has_treated_paper", has(AllItems.TREATED_PAPER))
                .save(recipeOutput);
    }
}
