package moriyashiine.acme.common.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class SanityStorage implements Capability.IStorage<ISanity> {
    @Override
    public NBTBase writeNBT(Capability<ISanity> capability, ISanity instance, EnumFacing side) {
        final NBTTagCompound compound = new NBTTagCompound();
        compound.setInteger("sanity", instance.getSanity());
        return compound;
    }

    @Override
    public void readNBT(Capability<ISanity> capability, ISanity instance, EnumFacing side, NBTBase nbt) {
        final NBTTagCompound compound = (NBTTagCompound) nbt;
        instance.setSanity(compound.getInteger("sanity"));

    }
}
