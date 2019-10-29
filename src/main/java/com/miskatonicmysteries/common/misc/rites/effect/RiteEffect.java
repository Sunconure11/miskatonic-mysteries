package com.miskatonicmysteries.common.misc.rites.effect;

import com.miskatonicmysteries.common.block.tile.TileEntityOctagram;
import com.miskatonicmysteries.registry.ModRegistries;
import net.minecraft.util.ResourceLocation;

public abstract class RiteEffect {
    protected ResourceLocation name;
    protected float baseChance;
    protected float instabilityLevel;
    protected EnumTrigger trigger;

    public RiteEffect(ResourceLocation name, float baseChance, float level, EnumTrigger trigger) {
        this.name = name;
        this.baseChance = baseChance;
        this.instabilityLevel = level;
        this.trigger = trigger;
        this.name = name;
        ModRegistries.RITE_EFFECTS.put(name, this);
    }


    public abstract boolean test(TileEntityOctagram octagram, EnumTrigger trigger);

    public abstract void execute(TileEntityOctagram octagram, EnumTrigger trigger);


    public float getBaseChance(TileEntityOctagram octagram) {
        return baseChance;
    }

    public float getInstabilityLevel(TileEntityOctagram octagram) {
        return instabilityLevel;
    }

    public EnumTrigger getTrigger(TileEntityOctagram octagram) {
        return trigger;
    }

    public ResourceLocation getName() {
        return name;
    }

    public enum EnumTrigger{
        GOO_CHECK,
        POWER_CHECK,
        RITE_EXECUTED //must be called actively, i.e when an item is crafted
    }

}