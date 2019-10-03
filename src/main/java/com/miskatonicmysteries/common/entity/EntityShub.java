package com.miskatonicmysteries.common.entity;

import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import net.minecraft.world.World;

public class EntityShub extends AbstractOldOne {
    public EntityShub(World worldIn) {
        super(worldIn);
        setSize(3, 5.5F);
    }

    @Override
    public Blessing getAssociatedBlessing() {
        return Blessing.SHUB;
    }
}
