package com.miskatonicmysteries.common.entity.cultist;

import com.miskatonicmysteries.common.capability.blessing.BlessingCapability;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.misc.IHasAssociatedBlessing;
import com.miskatonicmysteries.registry.ModObjects;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractCultist extends EntityTameable implements INpc, IMerchant, IHasAssociatedBlessing {
    private static final DataParameter<Boolean> ARMS_FOLDED = EntityDataManager.createKey(AbstractCultist.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> FOLLOW_MODE = EntityDataManager.createKey(AbstractCultist.class, DataSerializers.VARINT); //0 Standard, 1 Sit, 2 Wander

    protected EntityCultistAIWander cultistAIWander;

    @Nullable
    protected EntityPlayer buyingPlayer;

    @Nullable
    protected MerchantRecipeList buyingList;

    public AbstractCultist(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 1.95F);
        ((PathNavigateGround) this.getNavigator()).setBreakDoors(true);
        this.setCanPickUpLoot(true);
    }

    public List<EntityVillager.ITradeList> getCurrencyTradeList() {
        List<EntityVillager.ITradeList> trades = new ArrayList<>();
        trades.add((merchant, recipeList, random) -> recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 3 + random.nextInt(2)), new ItemStack(ModObjects.gold_oceanic, 1 + random.nextInt(2)))));
        trades.add((merchant, recipeList, random) -> recipeList.add(new MerchantRecipe(new ItemStack(Items.GOLD_INGOT, 4 + random.nextInt(2)), new ItemStack(ModObjects.gold_oceanic, 1 + random.nextInt(2)))));
        trades.add((merchant, recipeList, random) -> recipeList.add(new MerchantRecipe(new ItemStack(ModObjects.gold_oceanic, 1 + random.nextInt(2)), new ItemStack(ModObjects.candles, 4 + random.nextInt(3)))));
        return trades;
    }

    public abstract List<EntityVillager.ITradeList> getEquipmentTradeList();

    public abstract List<EntityVillager.ITradeList> getMiscTradeList();

    public abstract Blessing getAssociatedBlessing();

    public abstract List<ItemStack> getAvailableWeapons();

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(6.0D);
    }

    @Override
    public void onLivingUpdate() {
        setArmsFolded((getHeldItemMainhand().isEmpty() && getHeldItemOffhand().isEmpty() && isTamed()));
        if (!armsFolded()) {
            this.updateArmSwingProgress();
        }
        super.onLivingUpdate();
    }

    //Todo set the thing to a blockpos when standing near it (if it has an associated blessing)
    @Override
    protected void initEntityAI() {
        if (aiSit == null)
            this.aiSit = new EntityCultistAISit(this); //replace this with "cultist sit", which is bound to a block

        if (cultistAIWander == null)
            this.cultistAIWander = new EntityCultistAIWander(this);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityCultistAITrade(this));
        this.tasks.addTask(2, this.aiSit);// extend AIWander to also consider a second field: wander
        this.tasks.addTask(3, new EntityAIAvoidEntity<>(this, EntityGolem.class, 20, 0.5, 1));
        this.tasks.addTask(5, new EntityAIAttackMelee(this, 1, true));
        //fix cultists not being able to deal damage
        this.tasks.addTask(6, new EntityAIFollowOwner(this, 1.5F, 4.0F, 8.0F));
        this.tasks.addTask(7, new EntityAIMate(this, 1.0D)); //maybe?
        this.tasks.addTask(8, this.cultistAIWander);
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityPlayer.class, 12.0F));
        this.tasks.addTask(10, new EntityAILookIdle(this));

        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(4, new EntityAITargetNonTamed(this, EntityPlayer.class, false, p -> BlessingCapability.Util.getBlessing((EntityPlayer) p) != getAssociatedBlessing()));
        this.targetTasks.addTask(5, new EntityAITargetNonTamed(this, AbstractCultist.class, false, c -> ((AbstractCultist) c).getAssociatedBlessing() != getAssociatedBlessing()));
        this.targetTasks.addTask(6, new EntityAITargetNonTamed(this, EntityLiving.class, false, l -> l instanceof IHasAssociatedBlessing && ((IHasAssociatedBlessing) l).getAssociatedBlessing() != getAssociatedBlessing()));
        //will also attack pillagers and their beasts in 1.14
    }

    public boolean attackEntityAsMob(Entity entityIn) {
        float f = (float) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
        int i = 0;

        if (entityIn instanceof EntityLivingBase) {
            f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(), ((EntityLivingBase) entityIn).getCreatureAttribute());
            i += EnchantmentHelper.getKnockbackModifier(this);
        }

        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);

        if (flag) {
            if (i > 0 && entityIn instanceof EntityLivingBase) {
                ((EntityLivingBase) entityIn).knockBack(this, (float) i * 0.5F, (double) MathHelper.sin(this.rotationYaw * 0.017453292F), (double) (-MathHelper.cos(this.rotationYaw * 0.017453292F)));
                this.motionX *= 0.6D;
                this.motionZ *= 0.6D;
            }

            int j = EnchantmentHelper.getFireAspectModifier(this);

            if (j > 0) {
                entityIn.setFire(j * 4);
            }

            if (entityIn instanceof EntityPlayer) {
                EntityPlayer entityplayer = (EntityPlayer) entityIn;
                ItemStack itemstack = this.getHeldItemMainhand();
                ItemStack itemstack1 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack() : ItemStack.EMPTY;

                if (!itemstack.isEmpty() && !itemstack1.isEmpty() && itemstack.getItem().canDisableShield(itemstack, itemstack1, entityplayer, this) && itemstack1.getItem().isShield(itemstack1, entityplayer)) {
                    float f1 = 0.25F + (float) EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;

                    if (this.rand.nextFloat() < f1) {
                        entityplayer.getCooldownTracker().setCooldown(itemstack1.getItem(), 100);
                        this.world.setEntityState(entityplayer, (byte) 30);
                    }
                }
            }

            this.applyEnchantments(this, entityIn);
            this.setLastAttackedEntity(entityIn);
        }

        return flag;
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
        this.dataManager.register(FOLLOW_MODE, 0);
        super.entityInit();
    }

    public void setArmsFolded(boolean folded) {
        dataManager.set(ARMS_FOLDED, folded);
    }

    public boolean armsFolded() {
        return dataManager.get(ARMS_FOLDED);
    }

    public void setWandering(boolean wandering) {
        dataManager.set(FOLLOW_MODE, wandering ? 2 : 1);
    }

    public boolean isWandering() {
        return dataManager.get(FOLLOW_MODE) > 1;
    }


    @Override
    public void setSitting(boolean sitting) {
        dataManager.set(FOLLOW_MODE, sitting ? 1 : 0);
    }

    @Override
    public boolean isSitting() {
        return dataManager.get(FOLLOW_MODE) == 1;
    }

    public boolean isNeutral() {
        return isTamed() && getOwnerId() == null;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.aiSit != null) {
            this.aiSit.setSitting(false);
        }
        return super.attackEntityFrom(source, amount);
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        if (rand.nextFloat() < 0.3F)
            setTamed(true);//these are neutral
        if (!isTamed() && getAvailableWeapons() != null && !getAvailableWeapons().isEmpty()) {
            setItemStackToSlot(EntityEquipmentSlot.MAINHAND, getAvailableWeapons().get(rand.nextInt(getAvailableWeapons().size())));
        }
        cultistAIWander = new EntityCultistAIWander(this);
        setHomePosAndDistance(getPosition(), 20);
        return super.onInitialSpawn(difficulty, livingdata);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        setArmsFolded(compound.getBoolean("armsFolded"));
        setWandering(compound.getBoolean("wandering"));
        if (aiSit instanceof EntityCultistAISit) {
            this.setWandering(isWandering());
        }
        setTamed(compound.getBoolean("tamed"));

        if (compound.hasKey("recipes"))
            buyingList = new MerchantRecipeList(compound.getCompoundTag("recipes"));
        super.readEntityFromNBT(compound);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        compound.setBoolean("armsFolded", armsFolded());
        compound.setBoolean("wandering", isWandering());
        compound.setBoolean("tamed", isTamed());
        if (buyingList != null)
            compound.setTag("recipes", buyingList.getRecipiesAsTags());
        super.writeEntityToNBT(compound);
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        if (hand == EnumHand.MAIN_HAND) {
            if (player.isSneaking() && this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(player.getHeldItem(hand))) {
                dataManager.set(FOLLOW_MODE, dataManager.get(FOLLOW_MODE) > 1 ? 0 : dataManager.get(FOLLOW_MODE) + 1);
                if (isWandering()) {
                    //scan for murals later on, for now just set the current position to the bound one
                    setHomePosAndDistance(getPosition(), 20);
                }
                player.sendStatusMessage(new TextComponentTranslation("message.cultist." + (isWandering() ? "wander" : isSitting() ? "sit" : "follow")), true);
                this.navigator.clearPath();
                setAttackTarget(null);
                this.isJumping = false;
               /*if (world.isRemote) {
                    player.sendStatusMessage(new TextComponentString(I18n.format("message.cultist." + (isWandering() ? "wander" : isSitting() ? "sit" : "follow"))), true);
                }*/
                return true;
            } else if (!player.isSneaking() && isTamed()) {
                if (this.buyingList == null) {
                    this.populateBuyingList();
                }

                if (hand == EnumHand.MAIN_HAND) {
                    player.addStat(StatList.TALKED_TO_VILLAGER);
                }

                if (!this.world.isRemote && !this.buyingList.isEmpty()) {
                    this.setCustomer(player);
                    player.displayVillagerTradeGui(this);
                } else if (this.buyingList.isEmpty()) {
                    return super.processInteract(player, hand);
                }
                return true;
            }
        }
        return super.processInteract(player, hand);
    }


    public void populateBuyingList() {
        if (buyingList == null) {
            buyingList = new MerchantRecipeList();
        }
        List<EntityVillager.ITradeList> currencyTrades = this.getCurrencyTradeList();
        //pick 1 currency trade
        if (currencyTrades != null && !currencyTrades.isEmpty()) {
            Collections.shuffle(currencyTrades);
            currencyTrades.get(0).addMerchantRecipe(this, this.buyingList, this.rand);
        }

        List<EntityVillager.ITradeList> equipmentTrades = this.getEquipmentTradeList();
        //pick up to 2 random equipment trades
        if (equipmentTrades != null) {
            Collections.shuffle(equipmentTrades);
            int amount = 1 + world.rand.nextInt(2);
            for (int i = 0; i < amount; i++) {
                if (i < equipmentTrades.size())
                    equipmentTrades.get(i).addMerchantRecipe(this, this.buyingList, this.rand);
            }
        }

        List<EntityVillager.ITradeList> miscTrades = this.getMiscTradeList();
        //pick up to one random misc trade
        if (miscTrades != null) {
            Collections.shuffle(miscTrades);
            int amount = world.rand.nextInt(2);
            for (int i = 0; i < amount; i++) {
                if (i < miscTrades.size())
                    miscTrades.get(i).addMerchantRecipe(this, this.buyingList, this.rand);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 12) {
            this.spawnParticles(EnumParticleTypes.HEART);
        } else if (id == 14) {
            this.spawnParticles(EnumParticleTypes.VILLAGER_HAPPY);
        } else {
            super.handleStatusUpdate(id);
        }
    }

    @SideOnly(Side.CLIENT)
    private void spawnParticles(EnumParticleTypes particleType) {
        for (int i = 0; i < 5; ++i) {
            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            this.world.spawnParticle(particleType, this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + 1.0D + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d0, d1, d2);
        }
    }


    static class EntityCultistAISit extends EntityAISit {
        private final AbstractCultist cultist;

        public EntityCultistAISit(AbstractCultist entityIn) {
            super(entityIn);
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

    static class EntityCultistAIWander extends EntityAIMoveTowardsRestriction {
        protected AbstractCultist cultist;

        public EntityCultistAIWander(AbstractCultist entityIn) {
            super(entityIn, 1.0D);
            this.cultist = entityIn;
        }

        @Override
        public boolean shouldExecute() {
            return cultist.isWandering() && super.shouldExecute();
        }
    }

    static class EntityCultistAITrade extends EntityAIBase {
        private final AbstractCultist cultist;

        public EntityCultistAITrade(AbstractCultist villagerIn) {
            this.cultist = villagerIn;
            this.setMutexBits(5);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute() {
            if (!this.cultist.isEntityAlive()) {
                return false;
            } else if (this.cultist.isInWater()) {
                return false;
            } else if (!this.cultist.onGround) {
                return false;
            } else if (this.cultist.velocityChanged) {
                return false;
            } else if (!this.cultist.isNeutral()) {
                return false;
            } else {
                EntityPlayer entityplayer = this.cultist.getCustomer();

                if (entityplayer == null) {
                    return false;
                } else if (this.cultist.getDistanceSq(entityplayer) > 16.0D) {
                    return false;
                } else {
                    return entityplayer.openContainer != null;
                }
            }
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            this.cultist.getNavigator().clearPath();
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask() {
            this.cultist.setCustomer(null);
        }
    }


    //work on all the trading stuff later
    @Override
    public void setCustomer(@Nullable EntityPlayer player) {
        this.buyingPlayer = player;
    }

    @Nullable
    @Override
    public EntityPlayer getCustomer() {
        return buyingPlayer;
    }

    @Nullable
    @Override
    public MerchantRecipeList getRecipes(EntityPlayer player) {
        if (this.buyingList == null) {
            this.populateBuyingList();
        }
        return buyingList;
    }

    @Override
    public void setRecipes(@Nullable MerchantRecipeList recipeList) {

    }

    @Override
    public void useRecipe(MerchantRecipe recipe) {
        if (world.rand.nextBoolean())
            recipe.incrementToolUses(); //might work on the recipe exhaustion stuff later
        //recipe.increaseMaxTradeUses(1);
        this.livingSoundTime = -this.getTalkInterval();
        this.playSound(SoundEvents.ENTITY_VILLAGER_YES, this.getSoundVolume(), this.getSoundPitch());
        int i = 3 + this.rand.nextInt(4);

        if (recipe.getRewardsExp()) {
            this.world.spawnEntity(new EntityXPOrb(this.world, this.posX, this.posY + 0.5D, this.posZ, i));
        }
    }

    @Override
    public void verifySellingItem(ItemStack stack) {
        //change the sounds accordingly
        if (!this.world.isRemote && this.livingSoundTime > -this.getTalkInterval() + 20) {
            this.livingSoundTime = -this.getTalkInterval();
            this.playSound(stack.isEmpty() ? SoundEvents.ENTITY_VILLAGER_NO : SoundEvents.ENTITY_VILLAGER_YES, this.getSoundVolume(), this.getSoundPitch());
        }
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public BlockPos getPos() {
        return getPosition();
    }


}
