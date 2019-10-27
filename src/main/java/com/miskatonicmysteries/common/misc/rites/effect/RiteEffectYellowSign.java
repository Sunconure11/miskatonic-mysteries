package com.miskatonicmysteries.common.misc.rites.effect;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.block.BlockOctagram;
import com.miskatonicmysteries.common.block.BlockYellowSign;
import com.miskatonicmysteries.common.block.tile.TileEntityOctagram;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.registry.ModObjects;
import com.miskatonicmysteries.registry.ModPotions;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class RiteEffectYellowSign extends RiteEffectWrongGOO {
    public RiteEffectYellowSign() {
        super(new ResourceLocation(MiskatonicMysteries.MODID, "rite_effect_sign"), 0.5F, 0.3F, Blessing.HASTUR);
    }

    @Override
    public void execute(TileEntityOctagram octagram, EnumTrigger trigger) {
        EnumFacing facing = octagram.getWorld().getBlockState(octagram.getPos()).getValue(BlockOctagram.FACING).getOpposite();
        octagram.getWorld().setBlockToAir(octagram.getPos());
        octagram.getWorld().setBlockState(octagram.getPos(), ModObjects.yellow_sign.getDefaultState()
                .withProperty(BlockYellowSign.FACING, facing));
        List<EntityLivingBase> targets = octagram.getWorld().getEntitiesWithinAABB(EntityLivingBase.class, Block.FULL_BLOCK_AABB.grow(5, 5, 5).offset(octagram.getPos()));
        targets.forEach(t -> t.addPotionEffect(new PotionEffect(ModPotions.mania, 1000, 0)));
    }
}