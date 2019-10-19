package com.miskatonicmysteries.common.misc.rites;

import com.miskatonicmysteries.common.block.tile.TileEntityOctagram;
import com.miskatonicmysteries.registry.ModRites;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public abstract class OctagramRite {
    public ResourceLocation name;
    public NonNullList<Ingredient> ingredients;
    public int focusPower;
    public EnumType type;
    public int ticksNeeded;

    public OctagramRite(ResourceLocation name, int focusPower, int ticksNeeded, EnumType type, Ingredient... reagents) {
        this.name = name;
        this.focusPower = focusPower;
        this.ticksNeeded = ticksNeeded;
        this.ingredients = NonNullList.create();
        ingredients.addAll(Arrays.asList(reagents));
        ModRites.RITES.put(name, this);
    }

    public abstract boolean test(TileEntityOctagram octagram);

    public abstract void doRitual(TileEntityOctagram octagram, @Nullable EntityPlayer caster); //more parameters if needed

    public abstract void effect(TileEntityOctagram octagram, @Nullable EntityPlayer caster); //There is always a price to be paid...

    //(effect methods will only exist in extended classes? not sure...)

    public enum EnumType {
        FOCUSED, //cast the rite, then have the effect
        PRIMED //cast the rite, then have it take effect after a condition is met
    }
}
