package com.miskatonicmysteries.common.handler.effects;

import com.miskatonicmysteries.common.capability.sanity.ISanity;
import com.miskatonicmysteries.registry.ModInsanityEffects;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public abstract class InsanityEffect {
    protected ResourceLocation name;
    protected float baseChance;
    protected int level;
    protected EnumTrigger trigger;

    public InsanityEffect(ResourceLocation name, float baseChance, int level, EnumTrigger trigger) {
        this.name = name;
        this.baseChance = baseChance;
        this.level = level;
        this.trigger = trigger;
        this.name = name;
        ModInsanityEffects.INSANITY_EFFECTS.put(name, this);
    }

    public InsanityEffect(ResourceLocation name, float baseChance, int level) {
        this(name, baseChance, level, EnumTrigger.TICK);
    }


    public abstract boolean handle(World world, EntityPlayer player, ISanity sanity);

    public float getChance(World world, EntityPlayer player, ISanity sanity) {
        return baseChance;
    }

    public int getLevel(World world, EntityPlayer player, ISanity sanity) {
        return level;
    }

    public EnumTrigger getTrigger(World world, EntityPlayer player, ISanity sanity) {
        return trigger;
    }

    public boolean standardAvailability(World world, EntityPlayer player, ISanity sanity){
        return sanity.getSanity() < level && baseChance > world.rand.nextFloat();
    }

    public ResourceLocation getName() {
        return name;
    }

    public enum EnumTrigger{
        TICK,
        HIT,
        SPECIAL //must be called actively, i.e when an item is crafted
    }

}