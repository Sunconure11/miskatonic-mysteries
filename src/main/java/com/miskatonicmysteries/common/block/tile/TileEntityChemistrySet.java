package com.miskatonicmysteries.common.block.tile;

import com.miskatonicmysteries.common.block.BlockChemistrySet;
import com.miskatonicmysteries.common.misc.recipes.ChemistryRecipe;
import com.miskatonicmysteries.common.network.PacketHandler;
import com.miskatonicmysteries.registry.ModRegistries;
import com.miskatonicmysteries.util.InventoryUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TileEntityChemistrySet extends TileEntityMod implements ITickable {
    public ItemStackHandler inventory = new ItemStackHandler(5) {
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

    public final FluidTank tank = new FluidTank(Fluid.BUCKET_VOLUME){
        @Override
        public boolean canFillFluidType(FluidStack fluid) {
            return fluid.getFluid() == FluidRegistry.WATER && super.canFillFluidType(fluid);
        }

        @Override
        public boolean canDrainFluidType(@Nullable FluidStack fluid) {
            return super.canDrainFluidType(fluid);
        }
    };

    public int tickCount;

    public ChemistryRecipe currentRecipe;
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("inventory", inventory.serializeNBT());
        compound.setTag("tank", tank.writeToNBT(new NBTTagCompound()));
        compound.setInteger("tickCount", tickCount);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        tank.readFromNBT(compound.getCompoundTag("tank"));
        tickCount = compound.getInteger("tickCount");
        super.readFromNBT(compound);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T) inventory : capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY ? (T) tank : super.getCapability(capability, facing);
    }

    @Override
    public void update() {
        if (!world.isRemote && tank.getFluidAmount() >= 1000 && !isDone() && isLit() && getCurrentRecipe() != null){
            tickCount++;
            PacketHandler.updateTE(this);
            System.out.println(getCurrentRecipe()); //todo add particles later once assets are in
        }
    }

    public ChemistryRecipe getCurrentRecipe(){
        if (currentRecipe == null){
            currentRecipe = ModRegistries.Util.getChemistryRecipe(this);
        }
        return currentRecipe;
    }

    public boolean collectResults(EntityPlayer user, ItemStack stackUsed){
        if (getCurrentRecipe() != null){
            if (stackUsed.getItem() == currentRecipe.containerItem.getItem() && ((stackUsed.getTagCompound() == null && currentRecipe.containerItem.getTagCompound() == null) || (stackUsed.getTagCompound().equals(currentRecipe.containerItem.getTagCompound())))){
                stackUsed.shrink(1);
                user.addItemStackToInventory(currentRecipe.result);
                clearStuff();
                return true;
            }
        }
        return false;
    }

    public boolean isDone(){
        return tank.getFluidAmount() >= 1000 && tickCount >= 100 && getCurrentRecipe() != null;
    }

    private void clearStuff(){
        for (int i = 0; i < inventory.getSlots(); i++){
            inventory.setStackInSlot(i, ItemStack.EMPTY);
        }
        tank.setFluid(new FluidStack(tank.getFluid(), 0));
        tickCount = 0;
        world.setBlockState(pos, world.getBlockState(pos).withProperty(BlockChemistrySet.LIT, false));
        currentRecipe = null;
    }

    public boolean isLit(){
        return world.getBlockState(pos).getValue(BlockChemistrySet.LIT);
    }
}
