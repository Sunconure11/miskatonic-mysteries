package com.miskatonicmysteries.common.entity;

import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.world.GetCollisionBoxesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nullable;

public class EntityDarkYoung extends EntityMob implements IEntityMultiPart{
    public MultiPartEntityPart[] tentacleSegments; // we may want to make more tongue parts
    public MultiPartEntityPart tentacleTip = new MultiPartEntityPart(this, "tentacle", 0.5F, 0.5F);

    public EntityDarkYoung(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void initEntityAI() {

        super.initEntityAI();
    }


    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return super.getLootTable();
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public boolean attackEntityFromPart(MultiPartEntityPart partEntityPart, DamageSource source, float damage) {
        return false;
    }
}
