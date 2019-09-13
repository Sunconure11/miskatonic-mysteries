package com.miskatonicmysteries.common.world.gen.processor;

import com.miskatonicmysteries.common.block.BlockMural;
import com.miskatonicmysteries.common.world.gen.ModWorldGen;
import com.miskatonicmysteries.registry.ModObjects;
import net.minecraft.block.*;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraftforge.common.BiomeDictionary;

import javax.annotation.Nullable;

public class HasturStructureProcessor extends OldStructureProcessor {
    public HasturStructureProcessor(int groundY, boolean coldBiomeIn) {
        super(groundY, true, true, !coldBiomeIn, Biomes.FOREST);
    }

    @Nullable
    @Override
    public Template.BlockInfo processBlock(World worldIn, BlockPos pos, Template.BlockInfo blockInfoIn) {
        if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.CONIFEROUS)){
            if (blockInfoIn.blockState.getBlock() instanceof BlockStairs){
                blockInfoIn = new Template.BlockInfo(pos, Blocks.SPRUCE_STAIRS.getDefaultState().getBlock().getStateFromMeta(blockInfoIn.blockState.getBlock().getMetaFromState(blockInfoIn.blockState)), null);
            }
            if (blockInfoIn.blockState.getBlock() instanceof BlockPlanks){
                blockInfoIn = new Template.BlockInfo(pos, Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.SPRUCE), null);
            }
            if (blockInfoIn.blockState.getBlock() == Blocks.LOG){
                blockInfoIn = new Template.BlockInfo(pos, Blocks.LOG.getDefaultState().getBlock().getStateFromMeta(blockInfoIn.blockState.getBlock().getMetaFromState(blockInfoIn.blockState)).withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE), null);
            }
            if (blockInfoIn.blockState.getBlock() instanceof BlockWoodSlab){
                blockInfoIn = new Template.BlockInfo(pos, Blocks.WOODEN_SLAB.getDefaultState().getBlock().getStateFromMeta(blockInfoIn.blockState.getBlock().getMetaFromState(blockInfoIn.blockState)).withProperty(BlockWoodSlab.VARIANT, BlockPlanks.EnumType.SPRUCE), null);
            }
        }


        if (blockInfoIn.blockState.getBlock().equals(ModObjects.stone_hastur_mural)){
            if (worldIn.rand.nextBoolean()){
                return super.processBlock(worldIn, pos, new Template.BlockInfo(pos, ModObjects.moss_stone_hastur_mural.getDefaultState().withProperty(BlockMural.FACING, blockInfoIn.blockState.getValue(BlockMural.FACING)), null));
            }
        }else if (blockInfoIn.blockState.getBlock().equals(ModObjects.terracotta_hastur_mural)){
            if (worldIn.rand.nextBoolean()){
                return super.processBlock(worldIn, pos, new Template.BlockInfo(pos, ModObjects.yellow_terracotta_hastur_mural.getDefaultState().withProperty(BlockMural.FACING, blockInfoIn.blockState.getValue(BlockMural.FACING)), null));
            }
        }else if (blockInfoIn.blockState.getBlock().equals(Blocks.DIAMOND_BLOCK)){
            switch (worldIn.rand.nextInt(2)){
                case 0: return super.processBlock(worldIn, pos, new Template.BlockInfo(pos, Blocks.AIR.getDefaultState(), null));//decorative statue
                case 1: return super.processBlock(worldIn, pos, new Template.BlockInfo(pos, Blocks.AIR.getDefaultState(), null));//actual statue
            }
        }
        /*if (worldIn.getBlockState(pos.down()).getBlock().equals(Blocks.AIR) && pos.getY() <= groundY){
            boolean flag = true;
            for (int i = 2; flag; i++){
                if (ModWorldGen.isBlockSolid(worldIn, pos.add(0, -i, 0))){
                    flag = false;
                }else{
                    worldIn.setBlockState(pos.add(0, -i, 0), worldIn.getBiome(pos).fillerBlock);
                }
            }
        }*/

        return super.processBlock(worldIn, pos, blockInfoIn);//blockInfoIn.blockState.getBlock().equals(Blocks.AIR) ? new Template.BlockInfo(pos, worldIn.getBlockState(pos), null) :
    }
}
