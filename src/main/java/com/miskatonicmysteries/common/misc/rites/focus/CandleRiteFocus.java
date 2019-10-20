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

public class CandleRiteFocus extends RiteFocus {
    public CandleRiteFocus() {
        super(0.1F, 10, 8, EnumType.PLACED);
        this.selector = (s -> s instanceof Block && s instanceof BlockCandles);
    }

    @Override
    public int getConduitAmount(@Nullable World world, @Nullable BlockPos pos) {
        System.out.println("check pos " + pos);
        if (world != null && pos != null){
            System.out.println("check first");
            if (world.getBlockState(pos).getBlock() instanceof BlockCandles){
                System.out.println("test then and see " + world.getBlockState(pos).getValue(BlockCandles.CANDLES));
                return super.getConduitAmount(world, pos) * world.getBlockState(pos).getValue(BlockCandles.CANDLES);
            }
        }
        return super.getConduitAmount(world, pos);
    }
}
