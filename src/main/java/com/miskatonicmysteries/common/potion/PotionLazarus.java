package com.miskatonicmysteries.common.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PotionLazarus extends ModPotion {
    public static AttributeModifier HEALTH_MOD = new AttributeModifier("lazarus_health", 0, 0);
    public PotionLazarus() {
        super("lazarus", true, 12856852);
        registerPotionAttributeModifier(SharedMonsterAttributes.MAX_HEALTH, HEALTH_MOD.getID().toString(), 0, 0);
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {
        if (amplifier > 0){
            entityLivingBaseIn.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 210, amplifier));
        }
        super.performEffect(entityLivingBaseIn, amplifier);
    }

    @Override
    public double getAttributeModifierAmount(int amplifier, AttributeModifier modifier) {
        return amplifier * -2;
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return new ArrayList<>();
    }
}
