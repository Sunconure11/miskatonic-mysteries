package com.miskatonicmysteries.common.entity.processor;

import com.google.common.collect.Sets;
import com.miskatonicmysteries.common.entity.IIgnoreMaterials;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.pathfinding.WalkNodeProcessor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;

import java.util.Set;
import java.util.function.Predicate;

public class WalkIngoreSpecialNodeProcessor extends WalkNodeProcessor {
    protected Predicate<IBlockState> ignoreBlockStates = state -> false;

    @Override
    public void init(IBlockAccess sourceIn, EntityLiving mob) {
        super.init(sourceIn, mob);
        if (mob instanceof IIgnoreMaterials) {
            ignoreBlockStates = ((IIgnoreMaterials) mob).checkIgnore();
        }
    }

    @Override
    protected PathNodeType getPathNodeTypeRaw(IBlockAccess access, int x, int y, int z) {
        BlockPos blockpos = new BlockPos(x, y, z);
        IBlockState iblockstate = access.getBlockState(blockpos);
        Block block = iblockstate.getBlock();

        PathNodeType type = block.getAiPathNodeType(iblockstate, access, blockpos);
        if (type != null) return type;

        if (ignoreBlockStates.test(iblockstate)) {
            return PathNodeType.OPEN;
        }
        return super.getPathNodeTypeRaw(access, x, y, z);
    }
}
