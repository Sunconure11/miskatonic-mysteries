package com.miskatonicmysteries.common.world.biome;

import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.entity.goo.EntityShub;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AreaShub extends GreatOldOneArea<EntityShub> {
    public AreaShub() {
        super("shub", Blessing.SHUB);
    }

    @Override
    public void onAdded(EntityShub goo, World world, BlockPos pos) {

    }

    @Override
    public void update(EntityShub goo, World world, BlockPos pos) {

    }

    @Override
    public void onRemoved(World world, BlockPos pos) {

    }

    @Override
    public Class<EntityShub> getGOOClass() {
        return EntityShub.class;
    }
}
