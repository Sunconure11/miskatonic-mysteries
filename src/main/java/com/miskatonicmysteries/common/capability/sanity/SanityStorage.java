package com.miskatonicmysteries.common.capability.sanity;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;

import java.util.Map;

public class SanityStorage implements Capability.IStorage<ISanity> {
    @Override
    public NBTBase writeNBT(Capability<ISanity> capability, ISanity instance, EnumFacing side) {
        final NBTTagCompound compound = new NBTTagCompound();
        compound.setInteger("sanity", instance.getSanity());
        NBTTagList list = new NBTTagList();
        compound.setTag("expansions", list);
        instance.getExpansionMap().entrySet().stream().forEach(entry -> this.addNewCouple(entry, list));
        return compound;
    }

    @Override
    public void readNBT(Capability<ISanity> capability, ISanity instance, EnumFacing side, NBTBase nbt) {
        final NBTTagCompound compound = (NBTTagCompound) nbt;
        instance.setSanity(compound.getInteger("sanity"));
        instance.getExpansionMap().clear();
        compound.getTagList("expansions", Constants.NBT.TAG_COMPOUND).forEach(s -> this.loadCouple(instance, s));
    }

    private void addNewCouple(Map.Entry<String, Integer> entry, NBTTagList list) {
        NBTTagCompound couple = new NBTTagCompound();
        couple.setString("id", entry.getKey());
        couple.setInteger("increase", entry.getValue());
        list.appendTag(couple);
    }

    private void loadCouple(ISanity sanity, NBTBase s) {
        NBTTagCompound tag = (NBTTagCompound) s;
        sanity.getExpansionMap().put(tag.getString("id"), tag.getInteger("increase"));
    }
}
