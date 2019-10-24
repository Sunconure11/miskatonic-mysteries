package com.miskatonicmysteries.common.world.gen.processor;

import com.miskatonicmysteries.common.block.BlockCandles;
import com.miskatonicmysteries.common.block.BlockMural;
import com.miskatonicmysteries.common.world.gen.ModWorldGen;
import com.miskatonicmysteries.registry.ModObjects;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.Template;

import javax.annotation.Nullable;

public class CthulhuStructureProcessor extends OldStructureProcessor {
    public CthulhuStructureProcessor(int groundY) {
        super(groundY, false, true, true, Biomes.OCEAN);
    }

    @Nullable
    @Override
    public Template.BlockInfo processBlock(World worldIn, BlockPos pos, Template.BlockInfo blockInfoIn) {
        if (blockInfoIn.blockState.getBlock().equals(ModObjects.stone_cthulhu_mural)){
            if (worldIn.rand.nextBoolean()){
                return super.processBlock(worldIn, pos, new Template.BlockInfo(pos, ModObjects.moss_stone_cthulhu_mural.getDefaultState().withProperty(BlockMural.FACING, blockInfoIn.blockState.getValue(BlockMural.FACING)), null));
            }
        }else
        if (blockInfoIn.blockState.getBlock().equals(ModObjects.prismarine_cthulhu_mural)){
            if (worldIn.rand.nextFloat() < 0.2F){
                return super.processBlock(worldIn, pos, new Template.BlockInfo(pos, ModObjects.stone_cthulhu_mural.getDefaultState().withProperty(BlockMural.FACING, blockInfoIn.blockState.getValue(BlockMural.FACING)), null));
            }else if (worldIn.rand.nextFloat() < 0.2F){
                return super.processBlock(worldIn, pos, new Template.BlockInfo(pos, ModObjects.moss_stone_cthulhu_mural.getDefaultState().withProperty(BlockMural.FACING, blockInfoIn.blockState.getValue(BlockMural.FACING)), null));
            }
        }else
            if (blockInfoIn.blockState.getBlock().equals(Blocks.DIAMOND_BLOCK)){
                float r = worldIn.rand.nextFloat();
                if (r <= 50){
                    return super.processBlock(worldIn, pos, new Template.BlockInfo(pos, ModObjects.candles.getDefaultState().withProperty(BlockCandles.CANDLES, worldIn.rand.nextInt(4) + 1), null));
                }else if (r <= 20){
                    switch (worldIn.rand.nextInt(2)) {
                        case 0:
                            return super.processBlock(worldIn, pos, new Template.BlockInfo(pos, Blocks.AIR.getDefaultState(), null));//decorative statue
                        case 1:
                            return super.processBlock(worldIn, pos, new Template.BlockInfo(pos, Blocks.AIR.getDefaultState(), null));//actual statue
                    }
                }else{
                    return super.processBlock(worldIn, pos, new Template.BlockInfo(pos, Blocks.AIR.getDefaultState(), null));
                }
        }
        if (!blockInfoIn.blockState.getBlock().equals(Blocks.AIR) && pos.getY() <= groundY){
            boolean flag = true;
            for (int i = 1; flag; i++){
                if (ModWorldGen.isBlockSolid(worldIn, pos.add(0, -i, 0))){
                    flag = false;
                }else{
                    if (blockInfoIn.blockState.isFullBlock() || blockInfoIn.blockState.isFullCube()){
                        worldIn.setBlockState(pos.add(0, -i, 0), blockInfoIn.blockState);
                    }
                }
            }
        }

        return super.processBlock(worldIn, pos, blockInfoIn);//blockInfoIn.blockState.getBlock().equals(Blocks.AIR) ? new Template.BlockInfo(pos, worldIn.getBlockState(pos), null) :
    }
}
