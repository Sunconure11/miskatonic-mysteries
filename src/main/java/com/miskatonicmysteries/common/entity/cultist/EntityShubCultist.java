package com.miskatonicmysteries.common.entity.cultist;

import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import net.minecraft.world.World;

public class EntityShubCultist extends AbstractCultist {
    public EntityShubCultist(World worldIn) {
        super(worldIn);
    }

    @Override
    public Blessing getAssociatedBlessing() {
        return Blessing.SHUB;
    }
}
