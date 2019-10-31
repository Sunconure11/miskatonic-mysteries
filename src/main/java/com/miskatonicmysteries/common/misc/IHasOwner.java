package com.miskatonicmysteries.common.misc;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

import java.util.UUID;

public interface IHasOwner {
    UUID getOwnerUUID();

    EntityPlayer getOwner();
}
