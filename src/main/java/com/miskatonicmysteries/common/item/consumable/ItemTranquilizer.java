package com.miskatonicmysteries.common.item.consumable;

import com.miskatonicmysteries.common.capability.spells.SpellKnowledge;
import com.miskatonicmysteries.registry.ModPotions;
import com.miskatonicmysteries.registry.ModSpells;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemTranquilizer extends ItemFood{
    public ItemTranquilizer() {
        super(0, 0, false);
        setAlwaysEdible();
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 40;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
       // if (entityLiving instanceof EntityPlayer) {
       //     SpellKnowledge.Util.addSpell(ModSpells.heal, (EntityPlayer) entityLiving);
       //     SpellKnowledge.Util.addSpell(ModSpells.kill, (EntityPlayer) entityLiving);
       // }
        entityLiving.addPotionEffect(new PotionEffect(ModPotions.tranquilized, 1000, 0));
        stack.shrink(1);
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}
