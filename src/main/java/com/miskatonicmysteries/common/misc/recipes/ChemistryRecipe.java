package com.miskatonicmysteries.common.misc.recipes;

import com.miskatonicmysteries.registry.ModRegistries;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import java.util.Arrays;
import java.util.List;

public class ChemistryRecipe{
    public ResourceLocation name;
    public List<Ingredient> ingredients;
    public ItemStack containerItem;
    public ItemStack result;

    public ChemistryRecipe(ResourceLocation name, ItemStack result, ItemStack containerItem, Ingredient... ingredients) {
        this.name = name;
        this.ingredients = Arrays.asList(ingredients);
        this.containerItem = containerItem;
        this.result = result;
        ModRegistries.CHEMISTRY_RECIPES.put(name, this);
    }

    public ChemistryRecipe(ResourceLocation name, ItemStack result, Ingredient... ingredients) {
        this(name, result, new ItemStack(Items.GLASS_BOTTLE), ingredients);
    }
}
