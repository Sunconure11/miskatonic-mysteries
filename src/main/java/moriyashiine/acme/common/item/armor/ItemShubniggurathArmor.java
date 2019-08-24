package moriyashiine.acme.common.item.armor;

import moriyashiine.acme.ACME;
import moriyashiine.acme.client.model.armor.ModelShubniggurathArmor;
import moriyashiine.acme.registry.ModObjects;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class ItemShubniggurathArmor extends ItemArmor {
	public ItemShubniggurathArmor(EntityEquipmentSlot slot) {
		super(ModObjects.ARMOR_SHUBNIGGURATH, 0, slot);
	}
	
	@Nullable
	@Override
	public ModelBiped getArmorModel(EntityLivingBase living, ItemStack stack, EntityEquipmentSlot slot, ModelBiped _default) {
		ModelBiped model = new ModelShubniggurathArmor(slot, stack.getItem() == ModObjects.shubniggurath_cultist_hoodmask);
		model.setModelAttributes(_default);
		return model;
	}
	
	@Nullable
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return ACME.MODID + ":textures/models/armor/shubniggurath_cultist.png";
	}
}