package com.miskatonicmysteries.common.item.consumable;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.sanity.Sanity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemLaudanum extends Item {
    public ItemLaudanum() {
        super();
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 80; //nice
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        playerIn.setActiveHand(handIn);
        return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer){
            Sanity.Util.setSanity(Sanity.Util.getSanity((EntityPlayer) entityLiving) + 2 + entityLiving.getRNG().nextInt(4), (EntityPlayer) entityLiving);

        }
        entityLiving.attackEntityFrom(MiskatonicMysteries.SLEEP, 5 + worldIn.rand.nextInt(5));
        stack.shrink(1);
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}
