package com.miskatonicmysteries.common.potion;

import com.miskatonicmysteries.registry.ModPotions;
import net.minecraft.entity.EntityLivingBase;

public class PotionTranquilized extends ModPotion {
    public PotionTranquilized() {
        super("tranquilized", false, 3093071);
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {//remove mania, but only if it's weaker or equally strong
        if (entityLivingBaseIn.getActivePotionEffect(ModPotions.mania) != null && entityLivingBaseIn.getActivePotionEffect(ModPotions.mania).getAmplifier() <= amplifier){
            entityLivingBaseIn.removePotionEffect(ModPotions.mania);
        }
        super.performEffect(entityLivingBaseIn, amplifier);
    }
}
