package com.miskatonicmysteries.common.misc.rites;

import com.miskatonicmysteries.common.block.tile.TileEntityOctagram;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public abstract class OctagramRite {
    public ResourceLocation name;
    public List<Ingredient> ingredients;
    public int focusPower;

    public OctagramRite(ResourceLocation name, int focusPower, Ingredient... reagents) {
        this.name = name;
        this.focusPower = focusPower;
        this.ingredients = Arrays.asList(reagents);
    }

    public abstract boolean test(TileEntityOctagram octagram);

    public abstract void castRitual(TileEntityOctagram octagram, @Nullable EntityPlayer caster); //more parameters if needed

    public abstract void onRitualProcessing(TileEntityOctagram octagram, @Nullable EntityPlayer caster); //There is always a price to be paid...

    //(effect methods will only exist in extended classes? not sure...)
}
