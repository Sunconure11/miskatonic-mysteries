package moriyashiine.acme.common.item.tool;

import moriyashiine.acme.common.item.armor.ItemShubniggurathArmor;
import moriyashiine.acme.registry.ModObjects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemBlackGoatsGuttingDagger extends ItemSword {
	public ItemBlackGoatsGuttingDagger() {
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
				if (hasHelmet) event.setAmount(event.getAmount() + 5);
			}
		}
	}
	
	@SubscribeEvent
	public void livingDeath(LivingDeathEvent event) {
		if (!event.getEntityLiving().world.isRemote) {
			Entity source = event.getSource().getImmediateSource();
			if (source instanceof EntityLivingBase && ((EntityLivingBase) source).getHeldItemMainhand().getItem() == this && event.getEntityLiving().getRNG().nextInt(3) == 0)
				((EntityLivingBase) source).addPotionEffect(new PotionEffect(MobEffects.REGENERATION, (20 * 5), 1));
		}
	}
}