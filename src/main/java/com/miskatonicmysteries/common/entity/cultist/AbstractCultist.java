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
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableManager;
import scala.Int;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public abstract class AbstractCultist extends EntityTameable implements INpc, IMerchant {
    private static final DataParameter<Boolean> ARMS_FOLDED = EntityDataManager.<Boolean>createKey(AbstractCultist.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> FOLLOW_MODE = EntityDataManager.<Integer>createKey(AbstractCultist.class, DataSerializers.VARINT); //0 Standard, 1 Sit, 2 Wander
    protected EntityCultistAIWander cultistAIWander;
    public AbstractCultist(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 1.95F);
        ((PathNavigateGround)this.getNavigator()).setBreakDoors(true);
        this.setCanPickUpLoot(true);
    }

    public abstract Blessing getAssociatedBlessing();

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(6.0D);
    }

    //Todo still somehow set the thing to a blockpos when standing near it
    @Override
    protected void initEntityAI() {
        if (aiSit == null)
            this.aiSit = new EntityCultistAISit(this); //replace this with "cultist sit", which is bound to a block

        if (cultistAIWander == null)
            this.cultistAIWander = new EntityCultistAIWander(this);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);// extend AIWander to also consider a second field: wander
        //this.tasks.addTask(3, new EntityAIAvoidEntity<EntityIronGolem>());
        this.tasks.addTask(5, new EntityAIAttackMelee(this, 1, true));
        this.tasks.addTask(6, new EntityAIFollowOwner(this, 1, 4.0F, 8.0F));
        this.tasks.addTask(7, new EntityAIMate(this, 1.0D)); //maybe?
        this.tasks.addTask(8, this.cultistAIWander);
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

    public abstract List<ItemStack> getAvailableWeapons();

    @Override
    protected void entityInit() {
        this.dataManager.register(ARMS_FOLDED, true);
        this.dataManager.register(FOLLOW_MODE, 0);
        super.entityInit();
    }

    public void setArmsFolded(boolean folded){
        dataManager.set(ARMS_FOLDED, folded);
    }

    public boolean armsFolded(){
        return dataManager.get(ARMS_FOLDED);
    }

    public void setWandering(boolean wandering){
        dataManager.set(FOLLOW_MODE, wandering ? 2 : 1);
    }

    public boolean isWandering(){
        return dataManager.get(FOLLOW_MODE) > 1;
    }

    @Override
    public void setSitting(boolean sitting) {
        dataManager.set(FOLLOW_MODE, sitting ? 1 : 0);
    }

    @Override
    public boolean isSitting() {
        return dataManager.get(FOLLOW_MODE) > 0;
    }

    @Override
    public void onLivingUpdate() {
        setArmsFolded(getHeldItemMainhand().isEmpty() && getHeldItemOffhand().isEmpty());
        super.onLivingUpdate();
    }

    public boolean isNeutral(){
        return isTamed() && getOwnerId() == null;
    }

    //these are tests
    @Nullable
    @Override
    public EntityLivingBase getOwner() {
        return world.getClosestPlayer(posX, posY, posZ, 20, false);//super.getOwner();
    }

    @Override
    public boolean isTamed() {
        return true;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.aiSit != null){
            this.aiSit.setSitting(false);
        }
        return super.attackEntityFrom(source, amount);
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        setArmsFolded(world.rand.nextBoolean());
        if (getAvailableWeapons() != null && !getAvailableWeapons().isEmpty()){
            setItemStackToSlot(EntityEquipmentSlot.MAINHAND, getAvailableWeapons().get(rand.nextInt(getAvailableWeapons().size())));
        }
        cultistAIWander = new EntityCultistAIWander(this);
        cultistAIWander.boundPos = getPos();
        return super.onInitialSpawn(difficulty, livingdata);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        setArmsFolded(compound.getBoolean("armsFolded"));
        setWandering(compound.getBoolean("wandering"));
        if (this.cultistAIWander != null) {
            this.cultistAIWander.setBoundPos(BlockPos.fromLong(compound.getLong("wanderingPos")));
        }
        if (aiSit instanceof EntityCultistAISit){
            this.setWandering(isWandering());
        }
        super.readEntityFromNBT(compound);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        compound.setBoolean("armsFolded", armsFolded());
        compound.setBoolean("wandering", isWandering());
        if (this.cultistAIWander != null && cultistAIWander.getBoundPos() != null) {
            compound.setLong("wanderingPos", cultistAIWander.boundPos.toLong());
        }
        super.writeEntityToNBT(compound);
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        if (hand == EnumHand.MAIN_HAND) {
            if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(player.getHeldItem(hand))) {
                dataManager.set(FOLLOW_MODE, dataManager.get(FOLLOW_MODE) > 1 ? 0 : dataManager.get(FOLLOW_MODE) + 1);
                this.navigator.clearPath();
                setAttackTarget(null);

                this.isJumping = false;
                return true;
            }
        }
        return super.processInteract(player, hand);
    }

    static class EntityCultistAISit extends EntityAISit{
        //private boolean isWandering;
        private final AbstractCultist cultist;

        public EntityCultistAISit(AbstractCultist entityIn) {
            super(entityIn); //120 originally
          //  isWandering = entityIn.isWandering();
            cultist = entityIn;
            setMutexBits(5);
        }

        @Override
        public boolean shouldExecute() {
            return !isWandering() && super.shouldExecute();
        }

        @Override
        public void startExecuting() {
            if (!isWandering())
                super.startExecuting();
        }

        public boolean isWandering() {
            return cultist.isWandering();
        }

        @Override
        public boolean shouldContinueExecuting() {
            return false;
        }
    }

    static class EntityCultistAIWander extends EntityAIWander{ //completely set this to AIWander
        private BlockPos boundPos;

        public EntityCultistAIWander(AbstractCultist entityIn) {
            super(entityIn, 1.0D, 2); //120 originally
            setMutexBits(1);
        }

        @Override
        public boolean shouldExecute() {
            return boundPos != null && isWandering() && super.shouldExecute();
        }

        public boolean isWandering() {
            return ((AbstractCultist) entity).isWandering();
        }

        @Nullable
        @Override
        protected Vec3d getPosition() {
            Vec3d pos;
            int i = 0;
            do {
                pos = RandomPositionGenerator.getLandPos(entity, 10, 7);
                i++;
            }while((pos == null ||
                    pos.distanceTo(new Vec3d(boundPos.getX() + 0.5, boundPos.getY() + 0.5, boundPos.getZ() + 0.5)) > 16)
                    && i < 10 );
            return pos;
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
