package com.miskatonicmysteries.common.misc.rites.effect;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.block.tile.TileEntityOctagram;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class RiteEffectWither extends RiteEffectOverload {
    public RiteEffectWither() {
        super(new ResourceLocation(MiskatonicMysteries.MODID, "rite_effect_wither"), 0.5F);
        this.baseChance = 0.4F;
    }

    @Override
    public void execute(TileEntityOctagram octagram, EnumTrigger trigger) {
        List<EntityLivingBase> targets = octagram.getWorld().getEntitiesWithinAABB(EntityLivingBase.class, Block.FULL_BLOCK_AABB.grow(6).offset(octagram.getPos()));
        for (EntityLivingBase player : targets) {
            player.addPotionEffect(new PotionEffect(MobEffects.WITHER, Math.round(400 * octagram.instability), 0));
            player.attackEntityFrom(DamageSource.WITHER, 1);
        }
        super.execute(octagram, trigger);
    }
}