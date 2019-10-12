package com.miskatonicmysteries.common.entity.cultist;

import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.entity.goo.EntityShub;
import com.miskatonicmysteries.registry.ModObjects;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityShubCultist extends AbstractCultist {
    private static final DataParameter<Boolean> GOAT = EntityDataManager.<Boolean>createKey(AbstractCultist.class, DataSerializers.BOOLEAN);

    public EntityShubCultist(World worldIn) {
        super(worldIn);
    }


    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        setGoat(world.rand.nextBoolean());
        return super.onInitialSpawn(difficulty, livingdata);
    }

    @Override
    protected void entityInit() {
        this.dataManager.register(GOAT, true);
        super.entityInit();
    }

    public void setGoat(boolean bah) {
        dataManager.set(GOAT, bah);
    }

    public boolean isGoat() {
        return dataManager.get(GOAT);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        compound.setBoolean("goat", isGoat());
        super.writeEntityToNBT(compound);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        setGoat(compound.getBoolean("goat"));
        super.readEntityFromNBT(compound);
    }

    @Nullable
    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        EntityShubCultist cultist = new EntityShubCultist(this.world);
        cultist.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(cultist)), (IEntityLivingData) null);
        return cultist;
    }

    @Override
    public Blessing getAssociatedBlessing() {
        return Blessing.SHUB;
    }


    @Override
    public List<ItemStack> getAvailableWeapons() {
        List<ItemStack> weapons = new ArrayList<>();
        weapons.add(new ItemStack(ModObjects.black_goats_gutting_dagger));
        weapons.add(new ItemStack(ModObjects.black_goats_horned_dagger));
        weapons.add(new ItemStack(Items.WOODEN_SWORD));
        weapons.add(new ItemStack(Items.WOODEN_AXE));
        return weapons;
    }

    @Override
    public List<EntityVillager.ITradeList> getTradeList() {
        List<EntityVillager.ITradeList> trades = new ArrayList<>();
        trades.add(new EntityVillager.ITradeList() {
            @Override
            public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
                recipeList.add(new MerchantRecipe(new ItemStack(ModObjects.gold_oceanic), new ItemStack(ModObjects.necronomicon)));
            }
        });
        return trades;
    }
}
