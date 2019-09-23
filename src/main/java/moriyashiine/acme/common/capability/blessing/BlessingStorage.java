package moriyashiine.acme.common.capability.blessing;

import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class BlessingStorage implements Capability.IStorage<IBlessingCapability> {
    @Override
    public NBTBase writeNBT(Capability<IBlessingCapability> capability, IBlessingCapability instance, EnumFacing side) {
        final NBTTagCompound compound = new NBTTagCompound();
        compound.setString("blessing", instance.getBlessing().getName());
        return compound;
    }

    @Override
    public void readNBT(Capability<IBlessingCapability> capability, IBlessingCapability instance, EnumFacing side, NBTBase nbt) {
        final NBTTagCompound compound = (NBTTagCompound) nbt;
        instance.setBlessing(Blessing.getBlessing(compound.getString("blessing")));
    }
}
