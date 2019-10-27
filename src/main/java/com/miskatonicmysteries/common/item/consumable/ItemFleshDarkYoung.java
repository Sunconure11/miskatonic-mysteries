package com.miskatonicmysteries.common.item.consumable;

import com.miskatonicmysteries.registry.ModPotions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemFleshDarkYoung extends ItemFood {

    public ItemFleshDarkYoung() {
        super(6, 6, true);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        entityLiving.addPotionEffect(new PotionEffect(ModPotions.mania, 600, 0));
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}
