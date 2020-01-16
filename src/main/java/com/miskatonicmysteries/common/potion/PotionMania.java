package com.miskatonicmysteries.common.potion;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.sanity.Sanity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSource;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PotionMania extends ModPotion {
    public PotionMania() {
        super("mania", false, 7606784);

    }

    @Override
    public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, EntityLivingBase entityLivingBaseIn, int amplifier, double health) {
        if (entityLivingBaseIn instanceof EntityPlayer){
            if (Sanity.Util.getSanity((EntityPlayer) entityLivingBaseIn) <= 10){
                EntityPlayer player = (EntityPlayer) entityLivingBaseIn;
                int sanity = Sanity.Util.getSanity(player);
                float chance = Math.max(1 - (sanity / 10), 0.5F);
                if (chance < player.getRNG().nextFloat() * (Math.max(1, amplifier / 1.5F))){
                    Sanity.Util.setSanity(15, player);
                    player.attackEntityFrom(MiskatonicMysteries.INSANITY, 666);
                }
            }
        }
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
