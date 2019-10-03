package com.miskatonicmysteries.common.entity;

import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import net.minecraft.world.World;

public class EntityHastur extends AbstractOldOne {
    public EntityHastur(World worldIn) {
        super(worldIn);
        setSize(1.5F, 4.25F);
    }

    @Override
    public Blessing getAssociatedBlessing() {
        return Blessing.HASTUR;
    }
}
