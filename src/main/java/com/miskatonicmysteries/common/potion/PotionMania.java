package com.miskatonicmysteries.common.potion;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PotionMania extends ModPotion {
    public PotionMania() {
        super("mania", false, 7606784);

    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {
        if(entityLivingBaseIn instanceof EntityLiving && (((EntityLiving) entityLivingBaseIn).world.rand.nextInt(100) == 0 || ((EntityLiving) entityLivingBaseIn).getAttackTarget() == null)){
            EntityLiving entityLiving = (EntityLiving) entityLivingBaseIn;
            List<EntityLivingBase> possibleTargets = entityLiving.world.getEntitiesWithinAABB(EntityLivingBase.class, entityLiving.getEntityBoundingBox().grow(4, 1, 4), l -> l != entityLiving);
            if (!possibleTargets.isEmpty()){
                EntityLivingBase target = possibleTargets.get(entityLiving.getRNG().nextInt(possibleTargets.size()));
                entityLiving.setAttackTarget(target);
                if (target instanceof EntityLiving && target.getActivePotionEffect(this) != null){
                    ((EntityLiving) target).setAttackTarget(entityLivingBaseIn);
                }
            }

        }
        super.performEffect(entityLivingBaseIn, amplifier);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return new ArrayList<>();
    }
}
