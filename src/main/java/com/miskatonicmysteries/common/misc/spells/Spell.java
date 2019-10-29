package com.miskatonicmysteries.common.misc.spells;

import com.miskatonicmysteries.common.capability.spells.SpellKnowledge;
import com.miskatonicmysteries.util.InventoryUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Spell {
    public static final Map<String, Spell> SPELLS = new HashMap<>();
    public final ResourceLocation name;
    public final ResourceLocation iconLocation;
    public final List<Ingredient> reagents;
    protected int castTime = 60;
    protected int cooldownTime = 200;

    public Spell(ResourceLocation name, ResourceLocation iconLocation, Ingredient... reagents) {
        this.name = name;
        this.reagents = Arrays.asList(reagents);
        this.iconLocation = iconLocation;
        SPELLS.put(name.toString(), this);
    }

    public Spell(ResourceLocation name, Ingredient... reagents) {
        this(name, new ResourceLocation(name.getResourceDomain(), "textures/misc/spell/" + name.getResourcePath() + ".png"), reagents);
    }

    public boolean check(EntityPlayer caster){
        if (reagents.isEmpty()) return true;
        CopyOnWriteArrayList<Ingredient> toCheck = new CopyOnWriteArrayList<>(reagents);
        InventoryUtil.getInventoryList(caster).forEach(s -> toCheck.forEach(i -> {
            if (i.apply(s)){
                toCheck.remove(i);
            }
        }));
        return toCheck.isEmpty();
    }

    public void startCasting(EntityPlayer caster, BlockPos pos, EnumFacing facing){
        if (!caster.world.isRemote)
            caster.world.playSound(null, caster.getPosition(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.PLAYERS, 1, 1.5F);

        SpellKnowledge.Util.startCastingProgress(castTime, caster);
    }

    public void whileCasting(EntityPlayer caster){
        //maybe a continuous sound effect and maybe particles?
    }

    public void cast(EntityPlayer caster){
        if (!caster.world.isRemote)
            caster.world.playSound(null, caster.getPosition(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.PLAYERS, 1, 0.7F);
    }

    public abstract void price(EntityPlayer caster); //There is always a price to be paid...

    public void onCheckFalse(EntityPlayer caster){
        //sound effect
    }

    public void onCancelled(EntityPlayer caster){
        SpellKnowledge.Util.setCooldownFor(this, getCooldownTime(), caster, false);
    }
    //sound effect?


    public int getCastTime() {
        return castTime;
    }

    public int getCooldownTime() {
        return cooldownTime;
    }
}
