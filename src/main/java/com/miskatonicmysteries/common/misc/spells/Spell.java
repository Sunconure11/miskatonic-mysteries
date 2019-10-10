package com.miskatonicmysteries.common.misc.spells;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.Arrays;
import java.util.List;

public abstract class Spell {
    public ResourceLocation name;
    public ResourceLocation iconLocation;
    public List<ItemStack> reagents;

    public Spell(ResourceLocation name,  ResourceLocation iconLocation, ItemStack... reagents) {
        this.name = name;
        this.reagents = Arrays.asList(reagents);
        this.iconLocation = iconLocation;
    }

    public abstract void cast(EntityPlayer caster); //more parameters if needed

    public abstract void price(EntityPlayer caster); //There is always a price to be paid...

    //(effect methods will only exist in extended classes? not sure...)
}
