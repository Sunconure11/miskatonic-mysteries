package com.miskatonicmysteries.common.misc.rites.focus;

import com.miskatonicmysteries.common.block.BlockCandles;
import com.miskatonicmysteries.common.block.BlockStatue;
import com.miskatonicmysteries.common.block.tile.TileEntityOctagram;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class StatueRiteFocus extends BlockRiteFocus {
    public StatueRiteFocus() {
        super(0.1F, 100, 1, BlockStatue.class);
    }

    @Override
    public int getConduitAmount(@Nullable TileEntityOctagram octagram, @Nullable World world, @Nullable BlockPos pos) {
        if (world != null && pos != null){
            if (world.getBlockState(pos).getBlock() instanceof BlockStatue && octagram != null){
                if (isBlessingMatching(octagram, world, pos)) {
                    octagram.canOverload = !(isBlessingMatching(octagram, world, pos) && ((BlockStatue) world.getBlockState(pos).getBlock()).statue.isSpecial());
                    return super.getConduitAmount(octagram, world, pos);
                }
                return Math.round(super.getConduitAmount(octagram, world, pos) * 0.25F);
            }
        }
        return super.getConduitAmount(octagram, world, pos);
    }

    @Override
    public float getInstabilityRate(@Nullable TileEntityOctagram octagram, @Nullable World world, @Nullable BlockPos pos) {
        if (world != null && pos != null){
            if (world.getBlockState(pos).getBlock() instanceof BlockStatue && octagram != null){
                return !isBlessingMatching(octagram, world, pos) ? 0.9F : super.getInstabilityRate(octagram, world, pos);
            }
        }
        return super.getInstabilityRate(octagram, world, pos);
    }

    public boolean isBlessingMatching(TileEntityOctagram octagram, World world, BlockPos pos){
        if (world.getBlockState(pos).getBlock() instanceof BlockStatue){
            return ((BlockStatue) world.getBlockState(pos).getBlock()).associatedGOO == octagram.getAssociatedBlessing();
        }
        return false;
    }
}
