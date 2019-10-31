package com.miskatonicmysteries.common.misc;

import net.minecraft.entity.player.EntityPlayer;

public interface IInducesInsanity {
    int getInsanityPenalty();

    float getChanceForInsanity(EntityPlayer player);
}
