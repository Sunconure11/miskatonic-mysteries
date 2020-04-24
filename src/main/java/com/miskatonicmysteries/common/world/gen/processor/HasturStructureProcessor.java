package com.miskatonicmysteries.common.world.gen.processor;

import com.miskatonicmysteries.common.block.BlockCandles;
import com.miskatonicmysteries.common.block.BlockMural;
import com.miskatonicmysteries.common.block.BlockStatue;
import com.miskatonicmysteries.registry.ModObjects;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraftforge.common.BiomeDictionary;

import javax.annotation.Nullable;

public class HasturStructureProcessor extends OldStructureProcessor {
    public HasturStructureProcessor(Biome biome, int groundY, Rotation rotation, Mirror mirror) { //todo fix crash
        super(groundY, true, true, false, biome, rotation, mirror);
    }

    @Nullable
    @Override
    public Template.BlockInfo processBlock(World worldIn, BlockPos pos, Template.BlockInfo blockInfoIn) {
        if (blockInfoIn.blockState.getBlock().equals(ModObjects.stone_hastur_mural)) {
            if (worldIn.rand.nextBoolean()) {
                return super.processBlock(worldIn, pos, new Template.BlockInfo(pos, ModObjects.moss_stone_hastur_mural.getDefaultState().withProperty(BlockMural.FACING, blockInfoIn.blockState.getValue(BlockMural.FACING)), null));
            }
        } else if (blockInfoIn.blockState.getBlock().equals(ModObjects.terracotta_hastur_mural)) {
            if (worldIn.rand.nextBoolean()) {
                return super.processBlock(worldIn, pos, new Template.BlockInfo(pos, ModObjects.yellow_terracotta_hastur_mural.getDefaultState().withProperty(BlockMural.FACING, blockInfoIn.blockState.getValue(BlockMural.FACING)), null));
            }
        } else if (blockInfoIn.blockState.getBlock().equals(Blocks.NETHER_BRICK_STAIRS)) {
            float r = worldIn.rand.nextFloat();
            if (r <= 0.5) {
                return super.processBlock(worldIn, pos, new Template.BlockInfo(pos, ModObjects.candles.getDefaultState().withProperty(BlockCandles.CANDLES, worldIn.rand.nextInt(4) + 1).withProperty(BlockCandles.LIT, worldIn.rand.nextBoolean()), null));
            } else if (r <= 0.8 && !hasStatueInChunk(worldIn, pos)) {
                EnumFacing facing = blockInfoIn.blockState.getValue(BlockStairs.FACING);
                int addDegrees = blockInfoIn.blockState.getValue(BlockStairs.SHAPE) == BlockStairs.EnumShape.INNER_LEFT  ? 1 :  blockInfoIn.blockState.getValue(BlockStairs.SHAPE) == BlockStairs.EnumShape.INNER_RIGHT ? -1 : 0;
                switch (worldIn.rand.nextInt(3)) {
                    case 0:
                        return super.processBlock(worldIn, pos, addStatue(worldIn, pos, ModObjects.statue_hastur_terracotta, facing, addDegrees));
                    case 1:
                        return super.processBlock(worldIn, pos, addStatue(worldIn, pos, ModObjects.statue_hastur_mossy, facing, addDegrees));
                    case 2:
                        return super.processBlock(worldIn, pos, addStatue(worldIn, pos, ModObjects.statue_hastur_stone, facing, addDegrees));
                }
            }else{
                return super.processBlock(worldIn, pos, new Template.BlockInfo(pos, Blocks.AIR.getDefaultState(), null));
            }
        }

        return super.processBlock(worldIn, pos, blockInfoIn);
    }

    protected IBlockState getTaigaVariants(IBlockState block) {
        if (block.getBlock() == Blocks.LOG || block.getBlock() == Blocks.LOG2) {
            return Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE).withProperty(BlockLog.LOG_AXIS, block.getValue(BlockLog.LOG_AXIS));
        }

        if (block.getBlock() == Blocks.PLANKS) {
            return Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.SPRUCE);
        }

        if (block.getBlock() == Blocks.OAK_STAIRS) {
            return Blocks.SPRUCE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, block.getValue(BlockStairs.FACING));
        }

        if (block.getBlock() == Blocks.OAK_FENCE) {
            return Blocks.SPRUCE_FENCE.getDefaultState();
        }
        return block;
    }
}
