package com.miskatonicmysteries.common.world.gen.processor;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.structure.template.ITemplateProcessor;
import net.minecraft.world.gen.structure.template.Template;
import org.lwjgl.Sys;

import javax.annotation.Nullable;

public class StructureProcessor implements ITemplateProcessor {
    private int groundY;

    public StructureProcessor(int groundY) {
        this.groundY = groundY;
    }

    @Nullable
    @Override
    public Template.BlockInfo processBlock(World worldIn, BlockPos pos, Template.BlockInfo blockInfoIn) {
        if (pos.getY() <= groundY){
            IBlockState filler = worldIn.getBiome(pos).fillerBlock;
            for (int y = -5; y < 0; y++) {
                if (worldIn.getBlockState(pos.add(0, y, 0)).getMaterial().equals(Material.AIR) || worldIn.getBlockState(pos.add(0, y, 0)).getMaterial().isReplaceable() || worldIn.getBlockState(pos.add(0, y, 0)).getMaterial().isLiquid()) {
                    worldIn.setBlockState(pos.add(0, y, 0), filler);
                }else{
                    filler = worldIn.getBlockState(pos.add(0, y, 0));
                }
            }
        }
        return blockInfoIn.blockState.getBlock().equals(Blocks.AIR) ? new Template.BlockInfo(pos, worldIn.getBlockState(pos), null) : blockInfoIn;
    }
}
