package com.miskatonicmysteries.common.item.tool;

import com.google.common.collect.ImmutableList;
import com.miskatonicmysteries.client.render.RenderManipulatorHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemTerraform extends Item {
    private static final List<String> validBlocks = ImmutableList.of(
            "stone",
            "dirt",
            "grass",
            "sand",
            "gravel",
            "hardenedClay",
            "snowLayer",
            "mycelium",
            "podzol",
            "sandstone",
            "blockDiorite",
            "stoneDiorite",
            "blockGranite",
            "stoneGranite",
            "blockAndesite",
            "stoneAndesite",

            // Mod support
            "marble",
            "blockMarble",
            "limestone",
            "blockLimestone"
    );

    @Nonnull
    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        return EnumAction.BOW;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 72000;
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityLivingBase living, int count) {
        if (living.world.isRemote) {
            RenderManipulatorHandler.mobMob.put(living.getEntityId(), new EntityZombie(living.world));
            System.out.println("YAY!");
        }
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase entityIn, EnumHand hand) {
            if (playerIn.world.isRemote) {
                RenderManipulatorHandler.mobMob.put(entityIn.getEntityId(), new EntityPig(playerIn.world));
                System.out.println("YAY!");
            }
        return super.itemInteractionForEntity(stack, playerIn, entityIn, hand);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
        player.setActiveHand(hand);
        return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }
}
