package com.miskatonicmysteries.common.entity.cultist;

import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.entity.goo.EntityShub;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityShubCultist extends AbstractCultist {
    private static final DataParameter<Boolean> GOAT = EntityDataManager.<Boolean>createKey(AbstractCultist.class, DataSerializers.BOOLEAN);
    public EntityShubCultist(World worldIn) {
        super(worldIn);
    }

    @Override
    public Blessing getAssociatedBlessing() {
        return Blessing.SHUB;
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

    public void setGoat(boolean bah){
        dataManager.set(GOAT, bah);
    }

    public boolean isGoat(){
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
        cultist.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(cultist)), (IEntityLivingData)null);
        return cultist;
    }
}
