package com.miskatonicmysteries.util;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;

public class InventoryUtil {
    public static int getFreeSlot(IItemHandler handler){
        for (int i = 0; i < handler.getSlots(); i++){
            if (handler.getStackInSlot(i).isEmpty())
                return i;
        }
        return -1;
    }

    public static int getNextStackSlot(IItemHandler handler){
        for (int i = 0; i < handler.getSlots(); i++){
            System.out.println(i);
            if (!handler.getStackInSlot(i).isEmpty())
                return i;
        }
        return -1;
    }
    public static void dropAllItems(World world, IItemHandler handler, BlockPos pos){
        for (int i = 0; i < handler.getSlots(); i++){
            if (!world.isRemote)
                world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), handler.getStackInSlot(i)));
        }
    }

    public static void dropItem(World world, IItemHandler handler, BlockPos pos, int slot, int amount){
        EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), handler.extractItem(slot, amount, false));
        if (!world.isRemote)
            world.spawnEntity(item);
    }

    public static boolean isEmpty(IItemHandler handler){
        for (int i = 0; i < handler.getSlots(); i++){
            if (!handler.getStackInSlot(i).isEmpty())
                return false;
        }
        return true;
    }

    public static void transferInventory(IItemHandler from, IItemHandler to){
        for (int i = 0; i < to.getSlots(); i++){
            to.insertItem( i, from.extractItem(i, from.getStackInSlot(i).getCount(), false), false);
        }
    }
}
