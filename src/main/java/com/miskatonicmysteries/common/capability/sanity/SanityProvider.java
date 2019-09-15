package com.miskatonicmysteries.common.capability.sanity;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class SanityProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(ISanity.class)
    public static final Capability<ISanity> SANITY = null;
    private ISanity instance = SANITY.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == SANITY;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == SANITY ? SANITY.<T>cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return SANITY.getStorage().writeNBT(SANITY, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        SANITY.getStorage().readNBT(SANITY, this.instance, null, nbt);
    }
}
