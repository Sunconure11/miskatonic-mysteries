package com.miskatonicmysteries.common.entity.cultist;

import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityHasturCultist extends AbstractCultist {
    public EntityHasturCultist(World worldIn) {
        super(worldIn);
    }

    @Override
    public Blessing getAssociatedBlessing() {
        return Blessing.HASTUR;
    }

    @Nullable
    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        EntityHasturCultist cultist = new EntityHasturCultist(this.world);
        cultist.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(cultist)), (IEntityLivingData)null);
        return cultist;
    }
}
