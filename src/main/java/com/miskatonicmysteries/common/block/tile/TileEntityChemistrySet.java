package com.miskatonicmysteries.common.block.tile;

import com.miskatonicmysteries.common.block.BlockChemistrySet;
import com.miskatonicmysteries.common.misc.recipes.ChemistryRecipe;
import com.miskatonicmysteries.common.network.PacketHandler;
import com.miskatonicmysteries.registry.ModRegistries;
import com.miskatonicmysteries.util.InventoryUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
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

        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            return tickCount <= 0;
        }

        @Nonnull
        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            return canWork() ? ItemStack.EMPTY : super.extractItem(slot, amount, simulate);
        }

        @Nonnull
        @Override
        public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
            return canWork() ? stack : super.insertItem(slot, stack, simulate);
        }
    };

    public final FluidTank tank = new FluidTank(Fluid.BUCKET_VOLUME){
        @Override
        public boolean canFillFluidType(FluidStack fluid) {
            return fluid.getFluid() == FluidRegistry.WATER && super.canFillFluidType(fluid);
        }

        @Override
        public boolean canDrain() {
            return tickCount <= 0;
        }
    };

    public int tickCount;
    public int draws;
    public ChemistryRecipe currentRecipe;
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("inventory", inventory.serializeNBT());
        compound.setTag("tank", tank.writeToNBT(new NBTTagCompound()));
        compound.setInteger("tickCount", tickCount);
        compound.setInteger("draws", draws);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        tank.readFromNBT(compound.getCompoundTag("tank"));
        tickCount = compound.getInteger("tickCount");
        draws = compound.getInteger("draws");
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
        if (canWork()){
            tickCount++;
            if(world.isRemote) doParticles();
            if (isDone()){
                for (int i = 0; i < inventory.getSlots(); i++) {
                    inventory.setStackInSlot(i, ItemStack.EMPTY);
                }
                if (draws >= currentRecipe.result.getCount()){
                    clearStuff();
                }
            }
        }else{
            tickCount = 0;
        }
        PacketHandler.updateTE(this);

    }

    public void collect(EntityPlayer user, EnumHand hand){
        if (getCurrentRecipe().containerItem.test(user.getHeldItem(hand))){
            user.getHeldItem(hand).shrink(1);
            ItemStack stack = getCurrentRecipe().result.copy();
            stack.setCount(1);
            ItemHandlerHelper.giveItemToPlayer(user, stack);
            draws++;
        }
    }

    public boolean canWork(){
        return getCurrentRecipe() != null && tank.getFluid().getFluid() == FluidRegistry.WATER && tank.getFluidAmount() >= 1000 && isLit();
    }

    public boolean isDone(){
        return tickCount >= 100;
    }

    public ChemistryRecipe getCurrentRecipe(){
        if (currentRecipe == null){
            currentRecipe = ModRegistries.Util.getChemistryRecipe(this);
        }
        return currentRecipe;
    }


    private void clearStuff(){
        for (int i = 0; i < inventory.getSlots(); i++){
            inventory.setStackInSlot(i, ItemStack.EMPTY);
        }
        tank.setFluid(new FluidStack(tank.getFluid(), 0));
        tickCount = 0;
        world.setBlockState(pos, world.getBlockState(pos).withProperty(BlockChemistrySet.LIT, false));
        currentRecipe = null;
        draws = 0;
    }

    public boolean isLit(){
        return world.getBlockState(pos).getValue(BlockChemistrySet.LIT);
    }

    @SideOnly(Side.CLIENT)
    public void doParticles(){
        //particles will go here
    }
}
