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

public class RiteEffectWeakness extends RiteEffectOverload {
    public RiteEffectWeakness() {
        super(new ResourceLocation(MiskatonicMysteries.MODID, "rite_effect_weakness"), 0.3F);
        this.baseChance = 0.8F;
    }

    @Override
    public void execute(TileEntityOctagram octagram, EnumTrigger trigger) {
        List<EntityLivingBase> targets = octagram.getWorld().getEntitiesWithinAABB(EntityLivingBase.class, Block.FULL_BLOCK_AABB.grow(6).offset(octagram.getPos()));
        for (EntityLivingBase player : targets) {
            player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, Math.round(1200 * octagram.instability), Math.round(1 * octagram.instability)));
            player.attackEntityFrom(DamageSource.MAGIC, 1);
        }
        super.execute(octagram, trigger);
    }
}