package com.miskatonicmysteries.common.item.consumable;

import com.miskatonicmysteries.common.capability.sanity.Sanity;
import com.miskatonicmysteries.registry.ModPotions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemBlotter extends Item {

    public ItemBlotter() {
        super();
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if (target instanceof EntityVillager && target.isEntityAlive()) {
            target.addPotionEffect(new PotionEffect(ModPotions.mania, 3600, 0));
            stack.shrink(1);
            return true;
        }
        return super.itemInteractionForEntity(stack, playerIn, target, hand);
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        playerIn.setActiveHand(handIn);
        return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.EAT;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 80;
    }


    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        entityLiving.addPotionEffect(new PotionEffect(ModPotions.mania, 1200, 0));
        if (entityLiving instanceof EntityPlayer && worldIn.rand.nextFloat() < 0.3F) {
            Sanity.Util.setSanity(Sanity.Util.getSanity((EntityPlayer) entityLiving) - 5, (EntityPlayer) entityLiving);
        }
        stack.shrink(1);
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}
