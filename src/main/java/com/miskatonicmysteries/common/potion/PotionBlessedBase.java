package com.miskatonicmysteries.common.potion;

import com.miskatonicmysteries.ModConfig;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.misc.IHasAssociatedBlessing;
import com.miskatonicmysteries.registry.ModPotions;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PotionBlessedBase extends ModPotion implements IHasAssociatedBlessing{
    public Blessing blessing;
    public static Map<Blessing, PotionBlessedBase> BLESSING_POTIONS = new HashMap<Blessing, PotionBlessedBase>();

    public PotionBlessedBase(String name, int color, Blessing blessing) {
        super(name, false, color);
        this.blessing = blessing;
        blessing.potionEffect = new PotionEffect(this, 200, 0, false, false);
        BLESSING_POTIONS.put(blessing, this);
    }

    @Override
    public void applyAttributesModifiersToEntity(EntityLivingBase entityLivingBaseIn, AbstractAttributeMap attributeMapIn, int amplifier) {
        if (entityLivingBaseIn instanceof EntityPlayer)
            blessing.onAdded((EntityPlayer) entityLivingBaseIn);
        super.applyAttributesModifiersToEntity(entityLivingBaseIn, attributeMapIn, amplifier);
    }

    @Override
    public void removeAttributesModifiersFromEntity(EntityLivingBase entityLivingBaseIn, AbstractAttributeMap attributeMapIn, int amplifier) {
        if (entityLivingBaseIn instanceof EntityPlayer)
            blessing.onRemoved((EntityPlayer) entityLivingBaseIn);
        super.removeAttributesModifiersFromEntity(entityLivingBaseIn, attributeMapIn, amplifier);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return new ArrayList<>();
    }

    @Override
    public Blessing getAssociatedBlessing() {
        return blessing;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean shouldRenderHUD(PotionEffect effect) {
        return ModConfig.client.renderBlessingEffect;
    }
}
