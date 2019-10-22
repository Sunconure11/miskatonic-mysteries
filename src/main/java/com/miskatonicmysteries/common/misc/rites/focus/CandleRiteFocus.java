package com.miskatonicmysteries.common.misc.rites.focus;

import com.miskatonicmysteries.common.block.BlockCandles;
import com.miskatonicmysteries.registry.ModObjects;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class CandleRiteFocus extends BlockRiteFocus {
    public CandleRiteFocus() {
        super(0.1F, 10, 8, BlockCandles.class);
    }

    @Override
    public int getConduitAmount(@Nullable World world, @Nullable BlockPos pos) {
        if (world != null && pos != null){
            if (world.getBlockState(pos).getBlock() instanceof BlockCandles){
                return super.getConduitAmount(world, pos) * world.getBlockState(pos).getValue(BlockCandles.CANDLES);
            }
        }
        return super.getConduitAmount(world, pos);
    }
}
