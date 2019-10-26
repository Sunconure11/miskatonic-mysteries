package com.miskatonicmysteries.common.entity;

import com.miskatonicmysteries.common.entity.cultist.AbstractCultist;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.event.world.GetCollisionBoxesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nullable;

public class EntityDarkYoung extends EntityMob implements IEntityMultiPart{
    private static final DataParameter<Integer> TYPE = EntityDataManager.createKey(EntityDarkYoung.class, DataSerializers.VARINT);

    //  public MultiPartEntityPart[] tentacleSegments;
   // public MultiPartEntityPart tentacleTip = new MultiPartEntityPart(this, "tentacle", 0.5F, 0.5F);

    public EntityDarkYoung(World worldIn) {
        super(worldIn);
        setSize(1.3F, 2.5F);
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return super.getRenderBoundingBox().grow(1);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4.0D);

    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1, true));
        this.tasks.addTask(2, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));

        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
        super.initEntityAI();
    }

    @Override
    protected void entityInit() {
        this.dataManager.register(TYPE, 0);
        super.entityInit();
    }

    public int getType(){
        return dataManager.get(TYPE);
    }

    public void setType(int type){
        dataManager.set(TYPE, type);
    }



    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        setType(world.rand.nextInt(4));
        return super.onInitialSpawn(difficulty, livingdata);
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return super.getLootTable();
    } //add the matching loot table later

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public boolean attackEntityFromPart(MultiPartEntityPart partEntityPart, DamageSource source, float damage) {
        return false;
    }
}
