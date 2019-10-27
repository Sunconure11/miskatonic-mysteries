package com.miskatonicmysteries.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;
import java.util.List;

public class InventoryUtil {

    public static boolean areItemStackListsEqual(List<Ingredient> ings, ItemStackHandler handler) {
        List<ItemStack> checklist = new ArrayList<>();
        for (int i = 0; i < handler.getSlots(); i++) {
            ItemStack stack = handler.getStackInSlot(i);
            if (!stack.isEmpty()) checklist.add(stack);
        }
        if (ings.size() != checklist.size()) return false;
        for (Ingredient ing : ings) {
            boolean found = false;
            for (ItemStack stack : checklist) {
                if (ing.apply(stack)) {
                    found = true;
                    checklist.remove(stack);
                    break;
                }
            }
            if (!found) return false;
        }
        return true;
    }

    public static void insertCurrentItemStack(EntityPlayer player, ItemStackHandler inventory, int slot, int count){
        ItemStack stack = player.inventory.decrStackSize(player.inventory.currentItem, count);
        player.inventory.addItemStackToInventory(inventory.insertItem(slot, stack, false));
    }

    public static void insertCurrentItemStack(EntityPlayer player, ItemStackHandler inventory, int slot){
        insertCurrentItemStack(player, inventory, slot, 1);
    }

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

    public static int getArmorPieces(EntityLivingBase living, ItemArmor.ArmorMaterial mat) {
        int fin = 0;
        for (ItemStack stack : living.getArmorInventoryList()) if (stack.getItem() instanceof ItemArmor && ((ItemArmor) stack.getItem()).getArmorMaterial() == mat) fin++;
        return fin;
    }

    public static void giveAndConsumeItem(EntityPlayer player, EnumHand hand, ItemStack stack) {
        if (!player.isCreative()) player.getHeldItem(hand).shrink(1);
        if (player.getHeldItem(hand).isEmpty()) player.setHeldItem(hand, stack);
        else giveItem(player, stack);
    }

    public static void giveItem(EntityPlayer player, ItemStack stack) {
        if (!player.inventory.addItemStackToInventory(stack)) player.dropItem(stack, false);
    }

    public static NonNullList<ItemStack> getInventoryList(EntityPlayer player){
        NonNullList<ItemStack> list = NonNullList.create();
        list.addAll(player.inventory.mainInventory);
        list.addAll(player.inventory.offHandInventory);
        list.addAll(player.inventory.armorInventory);
        return list;
    }

    public static NonNullList<ItemStack> getInventoryList(ItemStackHandler inventory){
        NonNullList<ItemStack> list = NonNullList.create();
        for (int i = 0; i < inventory.getSlots(); i++){
            list.add(inventory.getStackInSlot(i));
        }
        return list;
    }
}
