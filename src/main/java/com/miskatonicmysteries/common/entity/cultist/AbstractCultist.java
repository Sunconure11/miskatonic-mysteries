package com.miskatonicmysteries.common.entity.cultist;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.UUID;

public abstract class AbstractCultist extends EntityTameable implements INpc, IMerchant {
    private static final DataParameter<Boolean> ARMS_FOLDED = EntityDataManager.<Boolean>createKey(AbstractCultist.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> WANDERING = EntityDataManager.<Boolean>createKey(AbstractCultist.class, DataSerializers.BOOLEAN);
    protected EntityCultistAISit cultistAISit;
    public AbstractCultist(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 1.95F);
        ((PathNavigateGround)this.getNavigator()).setBreakDoors(true);
        this.setCanPickUpLoot(true);
    }

    public abstract Blessing getAssociatedBlessing();

    @Override
    protected void initEntityAI() {
        this.cultistAISit = new EntityCultistAISit(this); //replace this with "cultist sit", which is bound to a block
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.cultistAISit);// extend AIWander to also consider a second field: wander
        //this.tasks.addTask(3, new EntityAIAvoidEntity<EntityIronGolem>());
        this.tasks.addTask(5, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(6, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(7, new EntityAIMate(this, 1.0D)); //maybe?
        this.tasks.addTask(8, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityPlayer.class, 12.0F));
        this.tasks.addTask(10, new EntityAILookIdle(this));

//        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
  //      this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
   //     this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true);
    //    this.targetTasks.addTask(4, new EntityAITargetNonTamed(this, EntityLivingBase.class, false, null);
        //method to find out if the cultist is friendly to another cultist or player; criteria include common blessing and clothing
    }

    @Override
    protected void updateEquipmentIfNeeded(EntityItem itemEntity) {
        if (itemEntity.getItem().getItem() instanceof ItemSword) {
            super.updateEquipmentIfNeeded(itemEntity);
        }
    }


    @Override
    protected void entityInit() {
        this.dataManager.register(ARMS_FOLDED, true);
        this.dataManager.register(WANDERING, false);
        super.entityInit();
    }

    public void setArmsFolded(boolean folded){
        dataManager.set(ARMS_FOLDED, folded);
    }

    public boolean armsFolded(){
        return dataManager.get(ARMS_FOLDED);
    }

    public void setWandering(boolean wandering){
        if (wandering)
            setSitting(true);
        dataManager.set(WANDERING, wandering);
    }

    public boolean isWandering(){
        return dataManager.get(WANDERING);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
    }

    public boolean isNeutral(){
        return isTamed() && getOwnerId() == null;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.aiSit != null){
            this.aiSit.setSitting(false);
        }
        return super.attackEntityFrom(source, amount);
    }

    //this may be a test
    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        setArmsFolded(world.rand.nextBoolean());

        //some spawn tamed, but with no owner
        return super.onInitialSpawn(difficulty, livingdata);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        setArmsFolded(compound.getBoolean("armsFolded"));
        setWandering(compound.getBoolean("wandering"));
        if (this.cultistAISit != null) {
            (this.cultistAISit).setWandering(compound.getBoolean("wandering"));
            (this.cultistAISit).setBoundPos(BlockPos.fromLong(compound.getLong("wanderingPos")));
        }
        super.readEntityFromNBT(compound);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        compound.setBoolean("armsFolded", armsFolded());
        compound.setBoolean("wandering", isWandering());
        if (this.cultistAISit != null && cultistAISit.getBoundPos() != null) {
            compound.setLong("wanderingPos", cultistAISit.boundPos.toLong());
        }
        super.writeEntityToNBT(compound);
    }

    static class EntityCultistAISit extends EntityAIWander{
        private final AbstractCultist cultist;
        private boolean isWandering;
        private BlockPos boundPos;

        public EntityCultistAISit(AbstractCultist entityIn) {
            super(entityIn, 1.0D, 120);
            this.cultist = entityIn;
            isWandering = entityIn.isWandering();
            setMutexBits(1);
        }

        @Override
        public boolean shouldExecute() {
            return isWandering && super.shouldExecute() || (!isWandering && cultist.isTamed() && !cultist.isInWater() && cultist.onGround);
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            if (!isWandering) {
                cultist.getNavigator().clearPath();
            }else{
                this.cultist.getNavigator().tryMoveToXYZ(this.x, this.y, this.z, this.speed);
            }
            this.cultist.setSitting(true);
            this.cultist.setWandering(!isWandering);
        }

        @Nullable
        @Override
        protected Vec3d getPosition() {
            return super.getPosition(); //calculate that so it tries to stay within the bounds (also store the bound position in NBT)
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask() {
            this.cultist.setSitting(false);
            this.cultist.setWandering(false);
        }

        public boolean isWandering() {
            return isWandering;
        }

        public void setWandering(boolean wandering) {
            isWandering = wandering;
        }

        public boolean shouldContinueExecuting() {
            return (!this.cultist.getNavigator().noPath() && isWandering);
        }


        public BlockPos getBoundPos() {
            return boundPos;
        }

        public void setBoundPos(BlockPos boundPos) {
            this.boundPos = boundPos;
        }
    }


    //work on all the trading stuff later
    @Override
    public void setCustomer(@Nullable EntityPlayer player) {

    }

    @Nullable
    @Override
    public EntityPlayer getCustomer() {
        return null;
    }

    @Nullable
    @Override
    public MerchantRecipeList getRecipes(EntityPlayer player) {
        return null;
    }

    @Override
    public void setRecipes(@Nullable MerchantRecipeList recipeList) {

    }

    @Override
    public void useRecipe(MerchantRecipe recipe) {

    }

    @Override
    public void verifySellingItem(ItemStack stack) {

    }

    @Override
    public World getWorld() {
        return null;
    }

    @Override
    public BlockPos getPos() {
        return null;
    }


}
