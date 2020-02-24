package com.miskatonicmysteries.common.block.tile;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.item.misc.ItemMMBook;
import com.miskatonicmysteries.common.misc.IHasAssociatedBlessing;
import com.miskatonicmysteries.common.network.PacketHandler;
import com.miskatonicmysteries.registry.ModObjects;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.HashMap;
import java.util.Map;

public class TileEntityStatue extends TileEntityMod implements IHasAssociatedBlessing {
    protected Blessing blessing = Blessing.NONE;
    public String statue = "";
    public boolean item = false;

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setString("blessing", blessing.getName());
        compound.setString("statue", statue);
        compound.setBoolean("isItem", item);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        blessing = Blessing.getBlessing(compound.getString("blessing"));
        statue = compound.getString("statue");
        item = compound.getBoolean("isItem");
        super.readFromNBT(compound);
    }

    public void setBlessing(Blessing blessing) {
        this.blessing = blessing;
    }

    @Override
    public Blessing getAssociatedBlessing() {
        return blessing;
    }
}
