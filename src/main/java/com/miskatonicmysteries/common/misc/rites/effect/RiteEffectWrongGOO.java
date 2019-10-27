package com.miskatonicmysteries.common.misc.rites.effect;

import com.miskatonicmysteries.common.block.tile.TileEntityOctagram;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import net.minecraft.util.ResourceLocation;

public abstract class RiteEffectWrongGOO extends RiteEffect {
    protected Blessing gooAdressed;

    public RiteEffectWrongGOO(ResourceLocation name, float baseChance, float level, Blessing gooAdressed) {
        super(name, baseChance, level, EnumTrigger.GOO_CHECK);
        this.gooAdressed = gooAdressed;
    }

    @Override
    public boolean test(TileEntityOctagram octagram, EnumTrigger trigger) {
        return trigger == EnumTrigger.GOO_CHECK && octagram.getAssociatedBlessing() == gooAdressed && (octagram.getCurrentRite().octagram != gooAdressed && octagram.getCurrentRite().octagram != Blessing.NONE);
    }
}