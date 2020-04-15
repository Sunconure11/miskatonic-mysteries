package com.miskatonicmysteries.common.entity;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.blessing.BlessingCapability;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.entity.cultist.AbstractCultist;
import com.miskatonicmysteries.common.entity.processor.PathNavigateGroundIgnoreSpecial;
import com.miskatonicmysteries.common.misc.IHasAssociatedBlessing;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketAnimation;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import scala.actors.threadpool.Arrays;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public class EntityDarkYoung extends EntityTameable implements IEntityMultiPart, IHasAssociatedBlessing, IIgnoreMaterials {
    private static final DataParameter<Byte> TYPE = EntityDataManager.createKey(EntityDarkYoung.class, DataSerializers.BYTE);

    public EntityDarkYoung(World worldIn) {
        super(worldIn);
        setSize(2.5F, 4.5F);
        experienceValue = 12;
        navigator = new PathNavigateGroundIgnoreSpecial(this, worldIn);
    }


    @Override
    public void onLivingUpdate() {
        this.updateArmSwingProgress();
        super.onLivingUpdate();
    }


    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setByte("Type", getType());
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        setType(compound.getByte("Type"));
        super.readFromNBT(compound);
    }

    @Nullable
    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return null;
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return super.getRenderBoundingBox().grow(1);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getType() == 1 ? 0.2 : 0.4);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(getType() == 1 ? 5.0D : 4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(getType() == 1 ? 8.0 : 4.0D); //change values somewhere else if it's the variant

    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityDarkYoungAIAttackMelee(this, 1, true));
        this.tasks.addTask(2, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));

        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(3, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(4, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(5, new EntityAITargetNonTamed(this, EntityPlayer.class, false, p -> BlessingCapability.Util.getBlessing((EntityPlayer) p) != getAssociatedBlessing()));
        this.targetTasks.addTask(6, new EntityAITargetNonTamed(this, AbstractCultist.class, false, c -> ((AbstractCultist) c).getAssociatedBlessing() != getAssociatedBlessing()));
        this.targetTasks.addTask(7, new EntityAITargetNonTamed(this, EntityVillager.class, false, null));

        super.initEntityAI();
    }

    @Override
    protected void entityInit() {
        this.dataManager.register(TYPE, (byte) 0);
        super.entityInit();
    }

    public byte getType(){
        return dataManager.get(TYPE);
    }

    public void setType(byte type){
        dataManager.set(TYPE, type);
    }

    @Override
    public boolean getCanSpawnHere() {
        if (!world.isDaytime() && super.getCanSpawnHere() && world.canSeeSky(getPosition().up()) && world.getDifficulty() != EnumDifficulty.PEACEFUL) {
            return world.rand.nextFloat() < world.getCurrentMoonPhaseFactor() + 0.02F;
        }
        return false;
    }

    @Override
    public boolean hitByEntity(Entity entityIn) {
        System.out.println(getType());
        return super.hitByEntity(entityIn);
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        setType((byte) (world.rand.nextBoolean() ? 1 : 0));
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getType() == 1 ? 0.2 : 0.4);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(getType() == 1 ? 5.0D : 4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(getType() == 1 ? 8.0 : 4.0D);
        return super.onInitialSpawn(difficulty, livingdata);
    }

    @Override
    protected int getExperiencePoints(EntityPlayer player) {
        return super.getExperiencePoints(player) * 2;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return new ResourceLocation(MiskatonicMysteries.MODID, "dark_young");
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public boolean attackEntityFromPart(MultiPartEntityPart partEntityPart, DamageSource source, float damage) {
        return false;
    }

    @Override
    public float getEyeHeight() {
        return this.height * 0.5F;
    }

    @Override
    public Blessing getAssociatedBlessing() {
        return Blessing.SHUB;
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
                this.motionX *= 0.1D;
                this.motionZ *= 0.1D;
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
    public boolean isEntityInsideOpaqueBlock() {
        return false;
    }

    @Override
    public Predicate<IBlockState> checkIgnore() {
        return state -> state.getBlock() instanceof BlockLog || state.getBlock() instanceof BlockLeaves;
    }

    public static class EntityDarkYoungAIAttackMelee extends EntityAIAttackMelee {
        public EntityDarkYoungAIAttackMelee(EntityCreature creature, double speedIn, boolean useLongMemory) {
            super(creature, speedIn, useLongMemory);
        }

        @Override
        protected double getAttackReachSqr(EntityLivingBase attackTarget) {
            return Math.sqrt(super.getAttackReachSqr(attackTarget)) + 1.5F; //what a joke
        }
    }
}
