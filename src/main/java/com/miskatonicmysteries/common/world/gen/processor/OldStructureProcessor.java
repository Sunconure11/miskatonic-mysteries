package com.miskatonicmysteries.common.world.gen.processor;

import com.miskatonicmysteries.common.block.BlockMural;
import com.miskatonicmysteries.common.block.BlockStatue;
import com.miskatonicmysteries.common.block.tile.TileEntityStatue;
import com.miskatonicmysteries.registry.ModObjects;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.template.ITemplateProcessor;
import net.minecraft.world.gen.structure.template.Template;

import javax.annotation.Nullable;

public class OldStructureProcessor implements ITemplateProcessor {
    protected int groundY;
    protected boolean placeDirt;
    protected  boolean crack, growMoss;
    protected Biome biome;

    protected Mirror mirror;
    protected Rotation rotation;

    public OldStructureProcessor(int groundY, boolean placeDirt, boolean crack, boolean growMoss, Biome biomeIn, Rotation rotation, Mirror mirror) {
        this.groundY = groundY;
        this.placeDirt = placeDirt;
        this.crack = crack;
        this.growMoss = growMoss;
        this.biome = biomeIn;
        this.mirror = mirror;
        this.rotation = rotation;
    }

    @Nullable
    @Override
    public Template.BlockInfo processBlock(World worldIn, BlockPos pos, Template.BlockInfo blockInfoIn) {
        if (blockInfoIn.blockState.getBlock().equals(Blocks.STONE)){
            if (crack && worldIn.rand.nextBoolean()){
                blockInfoIn = new Template.BlockInfo(pos, Blocks.COBBLESTONE.getDefaultState(), null);
            }
        }else if (blockInfoIn.blockState.getBlock().equals(Blocks.STONEBRICK)){
            if (crack && worldIn.rand.nextBoolean()){
                blockInfoIn = new Template.BlockInfo(pos, blockInfoIn.blockState.withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.MOSSY), null);
            }
            if (growMoss && worldIn.rand.nextBoolean()){
                blockInfoIn = new Template.BlockInfo(pos, blockInfoIn.blockState.withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.CRACKED), null);
            }
        }
        if (blockInfoIn.blockState.getBlock().equals(Blocks.COBBLESTONE)){
            if (growMoss && worldIn.rand.nextBoolean()){
                blockInfoIn = new Template.BlockInfo(pos, Blocks.MOSSY_COBBLESTONE.getDefaultState(), null);
            }
        }

        if (blockInfoIn.blockState.getBlock() instanceof BlockMural){
            EnumFacing facing = blockInfoIn.blockState.getValue(BlockMural.FACING);
            blockInfoIn = new Template.BlockInfo(pos, blockInfoIn.blockState.withProperty(BlockMural.FACING, mirror.mirror(rotation.rotate(facing))), null);

        }
        if (placeDirt && pos.getY() == groundY){
            worldIn.setBlockState(pos.down(), worldIn.getBiome(pos).topBlock);
        }
        return blockInfoIn.blockState.getBlock().equals(Blocks.AIR) ? new Template.BlockInfo(pos, worldIn.getBlockState(pos), null) : blockInfoIn;
    }

    public Template.BlockInfo addStatue(World world, BlockPos pos, BlockStatue statue, EnumFacing facing, int addedRotation){
        TileEntityStatue statueTile = new TileEntityStatue();
        statueTile.setBlessing(statue.associatedGOO);
        statueTile.statue = statue.statue.getName();
        return new Template.BlockInfo(pos, statue.getDefaultState().withProperty(BlockStatue.FACING, BlockStatue.getRotationFromFacing(facing, addedRotation)), statueTile.serializeNBT());
    }

    public boolean hasStatueInChunk(World world, BlockPos pos){
        return world.getChunkFromBlockCoords(pos).getTileEntityMap().values().parallelStream().anyMatch(t -> t instanceof TileEntityStatue);
    }
}
