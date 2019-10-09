package com.miskatonicmysteries.common.entity.cultist;

import com.google.common.base.Optional;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.INpc;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.UUID;

public abstract class AbstractCultist extends EntityTameable implements INpc, IMerchant {
    private static final DataParameter<Boolean> ARMS_FOLDED = EntityDataManager.<Boolean>createKey(AbstractCultist.class, DataSerializers.BOOLEAN);

    public AbstractCultist(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 1.95F);
        ((PathNavigateGround)this.getNavigator()).setBreakDoors(true);
        this.setCanPickUpLoot(true);
    }

    public abstract Blessing getAssociatedBlessing();

    @Override
    protected void entityInit() {
        this.dataManager.register(ARMS_FOLDED, true);
        super.entityInit();
    }

    public void setArmsFolded(boolean folded){
        dataManager.set(ARMS_FOLDED, folded);
    }

    public boolean armsFolded(){
        return dataManager.get(ARMS_FOLDED);
    }
    //work on this AI later


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


    //this may be a test
    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        setArmsFolded(world.rand.nextBoolean());
        return super.onInitialSpawn(difficulty, livingdata);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        setArmsFolded(compound.getBoolean("armsFolded"));
        super.readEntityFromNBT(compound);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        compound.setBoolean("armsFolded", armsFolded());
        super.writeEntityToNBT(compound);
    }
}
