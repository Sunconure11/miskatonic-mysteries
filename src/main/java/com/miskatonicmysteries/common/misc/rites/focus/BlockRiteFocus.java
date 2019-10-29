package com.miskatonicmysteries.common.misc.rites.focus;

import net.minecraft.block.Block;

public class BlockRiteFocus extends RiteFocus {
    public BlockRiteFocus(float instabilityRate, int conduitAmount, int sameTypeMax, Class<? extends Block> block) {
        super(instabilityRate, conduitAmount, sameTypeMax, EnumType.PLACED);
        this.selector = (s -> s instanceof Block && block.isInstance(s));
    }

    public static BlockRiteFocus create(float instabilityRate, int conduitAmount, int sameTypeMax, Class<? extends Block> block){
        BlockRiteFocus r = new BlockRiteFocus(instabilityRate, conduitAmount, sameTypeMax, block);
        addFocus(r);
        return r;
    }
}
