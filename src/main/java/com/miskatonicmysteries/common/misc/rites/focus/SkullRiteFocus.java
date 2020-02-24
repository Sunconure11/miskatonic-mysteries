package com.miskatonicmysteries.common.misc.rites.focus;

import com.miskatonicmysteries.common.block.tile.TileEntityOctagram;
import net.minecraft.block.BlockSkull;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class SkullRiteFocus extends BlockRiteFocus {
    protected int skullType;
    public SkullRiteFocus(int skullType, float instabilityRate, int conduitAmount, int maxSameType) {
        super(instabilityRate, conduitAmount, maxSameType, BlockSkull.class);
        this.skullType = skullType;
    }

    @Override
    public int getConduitAmount(@Nullable TileEntityOctagram octagram, @Nullable World world, @Nullable BlockPos pos) {
        if (world != null && pos != null){
            if (isValid(world, pos)){
                return super.getConduitAmount(octagram, world, pos);
            }
        }
        return 0;
    }

    @Override
    public float getInstabilityRate(@Nullable TileEntityOctagram octagram, @Nullable World world, @Nullable BlockPos pos) {
        if (world != null && pos != null) {
            if (isValid(world, pos)) {
                return super.getInstabilityRate(octagram, world, pos);
            }
        }
        return 0;
    }

    private boolean isValid(World world, BlockPos pos){
        return world.getTileEntity(pos) instanceof TileEntitySkull && ((TileEntitySkull) world.getTileEntity(pos)).getSkullType() == skullType;
    }
}
