package com.miskatonicmysteries.common.misc.rites;

import com.miskatonicmysteries.common.block.tile.TileEntityOctagram;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
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
    public int overflowTolerance;
    public EnumType type;
    public int ticksNeeded;
    public Blessing octagram, unlockBook;

    /**
     *
     * @param name The Rite's name
     * @param focusPower The Focal Power needed for the rite to run
     * @param ticksNeeded The amount of ticks needed till the rite is complete
     * @param type The type of the rite
     * @param octagram The type of octagram needed to perform this rite
     * @param unlockBook The type of book needed to be present
     * @param reagents Required items
     */
    public OctagramRite(ResourceLocation name, int focusPower, int overflowTolerance, int ticksNeeded, EnumType type, Blessing octagram, Blessing unlockBook, Ingredient... reagents) {
        this.name = name;
        this.focusPower = focusPower;
        this.overflowTolerance = overflowTolerance;
        this.ticksNeeded = ticksNeeded;
        this.ingredients = NonNullList.create();
        this.type = type;
        this.octagram = octagram;
        this.unlockBook = unlockBook;
        ingredients.addAll(Arrays.asList(reagents));
        ModRites.RITES.put(name, this);
    }

    public abstract boolean test(TileEntityOctagram octagram);

    public abstract void doRitual(TileEntityOctagram octagram, @Nullable EntityPlayer caster); //more parameters if needed

    public abstract void effect(TileEntityOctagram octagram, @Nullable EntityPlayer caster); //There is always a price to be paid...

    public abstract boolean checkShouldTrigger(TileEntityOctagram octagram, @Nullable EntityPlayer closest);

    public enum EnumType {
        FOCUSED, //cast the rite, then have the effect
        PRIMED //cast the rite, then have it take effect after a condition is met
    }
}
