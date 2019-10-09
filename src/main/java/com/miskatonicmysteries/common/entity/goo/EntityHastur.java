package com.miskatonicmysteries.common.entity.goo;

import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import net.minecraft.world.World;

public class EntityHastur extends AbstractOldOne {
    public EntityHastur(World worldIn) {
        super(worldIn);
        setSize(2.5F, 9);
    }

    @Override
    public Blessing getAssociatedBlessing() {
        return Blessing.HASTUR;
    }
}
