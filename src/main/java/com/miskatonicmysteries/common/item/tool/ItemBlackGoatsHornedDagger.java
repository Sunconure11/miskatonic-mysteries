package com.miskatonicmysteries.common.item.tool;

import com.miskatonicmysteries.common.item.armor.ItemShubniggurathArmor;
import com.miskatonicmysteries.registry.ModObjects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemBlackGoatsHornedDagger extends ItemSword {
	public ItemBlackGoatsHornedDagger() {
		super(ModObjects.TOOL_CULTIST);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void livingHurt(LivingHurtEvent event) {
		if (!event.getEntityLiving().world.isRemote) {
			Entity source = event.getSource().getImmediateSource();
			if (source instanceof EntityLivingBase && ((EntityLivingBase) source).getHeldItemMainhand().getItem() == this) {
				boolean hasHelmet = false;
				for (ItemStack stack : source.getArmorInventoryList()) {
					if (stack.getItem() instanceof ItemShubniggurathArmor && ((ItemArmor) stack.getItem()).armorType == EntityEquipmentSlot.HEAD) {
						hasHelmet = true;
						break;
					}
				}
				if (hasHelmet) event.setAmount(event.getAmount() + 5F * (1 - ((EntityLivingBase) source).swingProgress));
			}
		}
	}
	
	@SubscribeEvent
	public void livingDamage(LivingDamageEvent event) {
		if (!event.getEntityLiving().world.isRemote) {
			Entity source = event.getSource().getImmediateSource();
			if (source instanceof EntityLivingBase && ((EntityLivingBase) source).getHeldItemMainhand().getItem() == this && event.getEntityLiving().getRNG().nextInt(3) == 0) {
				event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, (20 * 5)));
				event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (20 * 5)));
			}
		}
	}
}