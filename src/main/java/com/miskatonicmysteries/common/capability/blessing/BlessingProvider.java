package com.miskatonicmysteries.common.capability.blessing;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class BlessingProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(IBlessingCapability.class)
    public static final Capability<IBlessingCapability> BLESSING = null;
    private IBlessingCapability instance = BLESSING.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == BLESSING;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == BLESSING ? BLESSING.<T>cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return BLESSING.getStorage().writeNBT(BLESSING, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        BLESSING.getStorage().readNBT(BLESSING, this.instance, null, nbt);
    }
}
