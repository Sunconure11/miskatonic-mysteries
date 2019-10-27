package com.miskatonicmysteries.common.misc.rites.effect;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.block.tile.TileEntityOctagram;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class RiteEffectPush extends RiteEffectOverload {
    public RiteEffectPush() {
        super(new ResourceLocation(MiskatonicMysteries.MODID, "rite_effect_push"), 0.5F);
        this.baseChance = 0.7F;
    }

    @Override
    public void execute(TileEntityOctagram octagram, EnumTrigger trigger) {
        List<EntityLivingBase> targets = octagram.getWorld().getEntitiesWithinAABB(EntityLivingBase.class, Block.FULL_BLOCK_AABB.grow(6).offset(octagram.getPos()));
        for (EntityLivingBase player : targets) {
            player.knockBack(player, 4 * octagram.instability, octagram.getPos().getX() - player.posX, octagram.getPos().getZ() - player.posZ);
            player.attackEntityFrom(DamageSource.FLY_INTO_WALL, 6 * octagram.instability);
        }
        super.execute(octagram, trigger);
    }
}