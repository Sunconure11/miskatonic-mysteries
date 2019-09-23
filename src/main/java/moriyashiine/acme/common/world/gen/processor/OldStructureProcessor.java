package moriyashiine.acme.common.world.gen.processor;

import net.minecraft.block.BlockStoneBrick;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.template.ITemplateProcessor;
import net.minecraft.world.gen.structure.template.Template;

import javax.annotation.Nullable;

public class OldStructureProcessor implements ITemplateProcessor {
    protected int groundY;
    protected boolean placeDirt = false;
    protected  boolean crack = false, growMoss = false;
    protected Biome biome = Biomes.FOREST;

    public OldStructureProcessor(int groundY, boolean placeDirt, boolean crack, boolean growMoss, Biome biomeIn) {
        this.groundY = groundY;
        this.placeDirt = placeDirt;
        this.crack = crack;
        this.growMoss = growMoss;
        this.biome = biomeIn;
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
        if (placeDirt && pos.getY() == groundY){
            worldIn.setBlockState(pos.down(), worldIn.getBiome(pos).topBlock);
        }
        return blockInfoIn.blockState.getBlock().equals(Blocks.AIR) ? new Template.BlockInfo(pos, worldIn.getBlockState(pos), null) : blockInfoIn;
    }
}
