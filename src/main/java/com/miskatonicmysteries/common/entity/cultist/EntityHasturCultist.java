package com.miskatonicmysteries.common.entity.cultist;

import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import net.minecraft.world.World;

public class EntityHasturCultist extends AbstractCultist {
    public EntityHasturCultist(World worldIn) {
        super(worldIn);
    }

    @Override
    public Blessing getAssociatedBlessing() {
        return Blessing.HASTUR;
    }
}
