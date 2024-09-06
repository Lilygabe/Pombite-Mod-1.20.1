package net.lilygabe.pombitemod.datagen;

import net.lilygabe.pombitemod.PombiteMod;
import net.lilygabe.pombitemod.block.ModBlocks;
import net.lilygabe.pombitemod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> SMELTS_TO_POMBITELITE = List.of(
            ModItems.RAW_POMBITELITE.get(), ModBlocks.POMBITELITE_ORE.get(), ModBlocks.DEEPSLATE_POMBITELITE_ORE.get()
    );

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        oreSmelting(consumer, SMELTS_TO_POMBITELITE, RecipeCategory.MISC, ModItems.POMBITELITE_DUST.get(), 0.25f, 200,"pombitelite");
        oreBlasting(consumer, SMELTS_TO_POMBITELITE, RecipeCategory.MISC, ModItems.POMBITELITE_DUST.get(), 0.25f, 100,"pombitelite");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.POMBITELITE_DUST_BLOCK.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.POMBITELITE_DUST.get())
                .unlockedBy(getHasName(ModItems.POMBITELITE_DUST.get()), has(ModItems.POMBITELITE_DUST.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.POMBITELITE_DUST.get(),9)
                .requires(ModBlocks.POMBITELITE_DUST_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.POMBITELITE_DUST_BLOCK.get()), has(ModBlocks.POMBITELITE_DUST_BLOCK.get()))
                .save(consumer);
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        Iterator var9 = pIngredients.iterator();

        while(var9.hasNext()) {
            ItemLike itemlike = (ItemLike)var9.next();
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike)).save(pFinishedRecipeConsumer, PombiteMod.MOD_ID+":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
