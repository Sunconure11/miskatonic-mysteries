package com.miskatonicmysteries.common.world.gen.processor;

import com.miskatonicmysteries.common.block.BlockCandles;
import com.miskatonicmysteries.common.block.BlockMural;
import com.miskatonicmysteries.registry.ModObjects;
import com.miskatonicmysteries.util.WorldGenUtil;
import net.minecraft.block.BlockStone;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.Template;

import javax.annotation.Nullable;

public class CthulhuStructureProcessor extends OldStructureProcessor {
    public CthulhuStructureProcessor(int groundY, Rotation rotation, Mirror mirror) {
        super(groundY, false, true, true, Biomes.OCEAN, rotation, mirror);
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
                if (r <= 0.5) {
                    return super.processBlock(worldIn, pos, new Template.BlockInfo(pos, ModObjects.candles.getDefaultState().withProperty(BlockCandles.CANDLES, worldIn.rand.nextInt(4) + 1), null));
                } else if (r <= 0.2) {
                    switch (worldIn.rand.nextInt(4)) {
                        case 0:
                            return super.processBlock(worldIn, pos, new Template.BlockInfo(pos, ModObjects.statue_cthulhu_prismarine.getDefaultState(), null));
                        case 1:
                            return super.processBlock(worldIn, pos, new Template.BlockInfo(pos, ModObjects.statue_cthulhu_mossy.getDefaultState(), null));
                        case 2:
                            return super.processBlock(worldIn, pos, new Template.BlockInfo(pos, ModObjects.statue_cthulhu_gold.getDefaultState(), null));
                        case 3:
                            return super.processBlock(worldIn, pos, new Template.BlockInfo(pos, ModObjects.statue_cthulhu_stone.getDefaultState(), null));
                    }
                }else{
                    return super.processBlock(worldIn, pos, new Template.BlockInfo(pos, Blocks.AIR.getDefaultState(), null));
                }
        }
        if (!blockInfoIn.blockState.getBlock().equals(Blocks.AIR) && pos.getY() <= groundY){
            boolean flag = true;
            for (int i = 1; flag; i++){
                if (WorldGenUtil.isBlockSolid(worldIn, pos.add(0, -i, 0))) {
                    flag = false;
                }else{
                    worldIn.setBlockState(pos.add(0, -i, 0), Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.STONE));
                }
            }
        }

        return super.processBlock(worldIn, pos, blockInfoIn);//blockInfoIn.blockState.getBlock().equals(Blocks.AIR) ? new Template.BlockInfo(pos, worldIn.getBlockState(pos), null) :
    }
}
