package com.miskatonicmysteries.common.entity;

import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public abstract class AbstractOldOne extends EntityLiving {
    public AbstractOldOne(World worldIn) {
        super(worldIn);
    }
    //add other common stuff, like the way the biome is distorted, there etc.
    public abstract Blessing getAssociatedBlessing();
}
