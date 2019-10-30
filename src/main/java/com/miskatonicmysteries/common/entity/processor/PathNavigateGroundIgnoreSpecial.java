package com.miskatonicmysteries.common.entity.processor;

import net.minecraft.entity.EntityLiving;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.WalkNodeProcessor;
import net.minecraft.world.World;

public class PathNavigateGroundIgnoreSpecial extends PathNavigateGround {
    public PathNavigateGroundIgnoreSpecial(EntityLiving entitylivingIn, World worldIn) {
        super(entitylivingIn, worldIn);
    }

    @Override
    protected PathFinder getPathFinder() {
        this.nodeProcessor = new WalkIngoreSpecialNodeProcessor();
        this.nodeProcessor.setCanEnterDoors(true);
        return new PathFinder(this.nodeProcessor);
    }
}
