package com.miskatonicmysteries.common.misc.rites.effect;

import com.miskatonicmysteries.common.block.tile.TileEntityOctagram;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RiteEffectOverload extends RiteEffect {
    public RiteEffectOverload(ResourceLocation name, float level) {
        super(name, 1, level, EnumTrigger.POWER_CHECK);
    }

    @Override
    public void execute(TileEntityOctagram octagram, EnumTrigger trigger) {
        for (int i = 0; i < octagram.inventory.getSlots(); i++) {
            octagram.inventory.setStackInSlot(i, ItemStack.EMPTY);
        }
        if (octagram.getWorld().isRemote) {
            octagram.doOverloadEffects(octagram.getWorld().rand.nextInt(15) + 10);
        }
    }

    @Override
    public boolean test(TileEntityOctagram octagram, EnumTrigger trigger) {
        System.out.println(octagram.focusPower);
        return octagram.focusPower >= (octagram.getCurrentRite().focusPower + octagram.getCurrentRite().overflowTolerance);
    }
}