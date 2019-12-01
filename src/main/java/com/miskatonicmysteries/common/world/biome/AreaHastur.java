package com.miskatonicmysteries.common.world.biome;

import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.entity.goo.EntityHastur;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AreaHastur extends GreatOldOneArea<EntityHastur> {
    public AreaHastur() {
        super("hastur", Blessing.HASTUR);
    }

    @Override
    public void onAdded(EntityHastur goo, World world, BlockPos pos) {

    }

    @Override
    public void update(EntityHastur goo, World world, BlockPos pos) {

    }

    @Override
    public void onRemoved(World world, BlockPos pos) {

    }

    @Override
    public Class<EntityHastur> getGOOClass() {
        return EntityHastur.class;
    }
}
