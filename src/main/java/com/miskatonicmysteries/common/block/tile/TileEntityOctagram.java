package com.miskatonicmysteries.common.block.tile;

import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.network.PacketHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.Iterator;

public class TileEntityOctagram extends TileEntityMod implements ITickable {

    public ItemStackHandler inventory = new ItemStackHandler(8) {
        @Override
        public int getSlotLimit(int slot) {
            return 1;
        }

        @Override
        protected void onContentsChanged(int slot) {
            PacketHandler.updateTE(world, pos);
            super.onContentsChanged(slot);
        }
    };

    public BlockPos closestAltarPos = null;

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("inventory", inventory.serializeNBT());
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        super.readFromNBT(compound);
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return super.getRenderBoundingBox().grow(2, 2, 2);
    }


    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T) inventory : super.getCapability(capability, facing);
    }

    @Override
    public void update() {
        if (!altarUsable()){
            findNearestAltar();
        }
        //flipAltarPages();

        //stuff
    }

    public void flipAltarPages(){
        if (altarUsable()){
           getAltar().flipSpeed += 0.1;
            // getAltar().flipSpeed = Math.max(getAltar().flipSpeed + 0.1F, 2);
        }
    }

    public void findNearestAltar(){
        Iterator<BlockPos.MutableBlockPos> checkPoses = BlockPos.getAllInBoxMutable(pos.add(-4, -2, -4), pos.add(4, 2, 4)).iterator();
        while (checkPoses.hasNext()){
            BlockPos checkPos = checkPoses.next();
            if (world.getTileEntity(checkPos) instanceof TileEntityAltar){
                closestAltarPos = checkPos;
                return;
            }
        }
    }

    public boolean altarUsable(){
        return closestAltarPos != null && world.getTileEntity(closestAltarPos) instanceof TileEntityAltar;
    }

    public TileEntityAltar getAltar(){
        if (altarUsable()) {
            return (TileEntityAltar) world.getTileEntity(closestAltarPos);
        }
        return null;
    }

    public Blessing getAltarBlessing(){
        if (closestAltarPos != null && world.getTileEntity(closestAltarPos) instanceof TileEntityAltar){
            return ((TileEntityAltar) world.getTileEntity(closestAltarPos)).getAssociatedBlessing();
        }
        return Blessing.NONE; //or null if it's relevant that a book is present?
    }
}
