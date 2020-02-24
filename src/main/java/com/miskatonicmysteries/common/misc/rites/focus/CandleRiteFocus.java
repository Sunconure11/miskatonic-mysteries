package com.miskatonicmysteries.common.misc.rites.focus;

import com.miskatonicmysteries.common.block.BlockCandles;
import com.miskatonicmysteries.common.block.tile.TileEntityOctagram;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class CandleRiteFocus extends BlockRiteFocus {
    public CandleRiteFocus() {
        super(0.1F, 8, 7, BlockCandles.class);
    }

    @Override
    public int getConduitAmount(@Nullable TileEntityOctagram octagram, @Nullable World world, @Nullable BlockPos pos) {
        if (world != null && pos != null){
            if (world.getBlockState(pos).getBlock() instanceof BlockCandles){
                return super.getConduitAmount(octagram, world, pos) * world.getBlockState(pos).getValue(BlockCandles.CANDLES);
            }
        }
        return super.getConduitAmount(octagram, world, pos);
    }
}
