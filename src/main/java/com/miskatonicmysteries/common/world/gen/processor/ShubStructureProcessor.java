package com.miskatonicmysteries.common.world.gen.processor;

import com.miskatonicmysteries.registry.ModObjects;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.ITemplateProcessor;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

import javax.annotation.Nullable;

public class ShubStructureProcessor extends StructureProcessor {
    public ShubStructureProcessor(int groundY) {
        super(groundY);
    }

    @Nullable
    @Override
    public Template.BlockInfo processBlock(World worldIn, BlockPos pos, Template.BlockInfo blockInfoIn) {
        if (blockInfoIn.blockState.getBlock().equals(Blocks.EMERALD_BLOCK)){
            switch (worldIn.rand.nextInt(2)){
                case 0: return super.processBlock(worldIn, pos, new Template.BlockInfo(pos, ModObjects.moss_stone_shubniggurath_mural.getDefaultState(), null));
                case 1: return super.processBlock(worldIn, pos, new Template.BlockInfo(pos, ModObjects.stone_shubniggurath_mural.getDefaultState(), null));
            }
        }else if (blockInfoIn.blockState.getBlock().equals(Blocks.DIAMOND_BLOCK)){
            switch (worldIn.rand.nextInt(2)){
                case 0: return super.processBlock(worldIn, pos, new Template.BlockInfo(pos, Blocks.AIR.getDefaultState(), null));//decorative statue
                case 1: return super.processBlock(worldIn, pos, new Template.BlockInfo(pos, Blocks.AIR.getDefaultState(), null));//actual statue
            }
        }
        return super.processBlock(worldIn, pos, blockInfoIn);//blockInfoIn.blockState.getBlock().equals(Blocks.AIR) ? new Template.BlockInfo(pos, worldIn.getBlockState(pos), null) :
    }
}
