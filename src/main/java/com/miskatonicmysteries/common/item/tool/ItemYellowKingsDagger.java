package com.miskatonicmysteries.common.item.tool;

import com.miskatonicmysteries.common.item.armor.ItemHasturArmor;
import com.miskatonicmysteries.registry.ModObjects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemYellowKingsDagger extends ItemSword {
	public ItemYellowKingsDagger() {
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
					if (stack.getItem() instanceof ItemHasturArmor && ((ItemArmor) stack.getItem()).armorType == EntityEquipmentSlot.HEAD) {
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
			if (source instanceof EntityLivingBase && ((EntityLivingBase) source).getHeldItemMainhand().getItem() == this) {
				System.out.println(((EntityLivingBase) source).swingProgress);
				event.getEntityLiving().hurtResistantTime = 0;
				event.getEntityLiving().attackEntityFrom(DamageSource.MAGIC, (float) event.getEntityLiving().getRNG().nextInt(5) * (1 - ((EntityLivingBase) source).swingProgress));
			}
		}
	}
}