package com.miskatonicmysteries.common.item.armor;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.client.model.armor.ModelHasturArmor;
import com.miskatonicmysteries.registry.ModObjects;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class ItemHasturArmor extends ItemArmor {
	public ItemHasturArmor(EntityEquipmentSlot slot) {
		super(ModObjects.ARMOR_HASTUR, 0, slot);
	}

	@SideOnly(Side.CLIENT)
	@Nullable
	@Override
	public ModelBiped getArmorModel(EntityLivingBase living, ItemStack stack, EntityEquipmentSlot slot, ModelBiped _default) {
		ModelBiped model = new ModelHasturArmor(slot);
		model.setModelAttributes(_default);
		return model;
	}
	
	@Nullable
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return MiskatonicMysteries.MODID + ":textures/models/armor/hastur_cultist.png";
	}
}