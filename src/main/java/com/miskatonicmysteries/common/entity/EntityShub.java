package com.miskatonicmysteries.common.entity;

import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityShub extends AbstractOldOne {
    public boolean mouthOpen = false;
    public boolean sitting = false;

    public float sittingProgress = 0;
    public float openingProgress = 0;

    public EntityShub(World worldIn) {
        super(worldIn);
        setSize(3, 5.5F);
    }

    @Override
    public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, EnumHand hand) {
        if(world.isRemote) {
            if (player.isSneaking()) {
                if (hand == EnumHand.MAIN_HAND) {
                    setSitting(!sitting);
                }
            }else{
                if (hand == EnumHand.MAIN_HAND) {
                    openMouth(!isMouthOpen());
                }
            }
        }
        return super.applyPlayerInteraction(player, vec, hand);
    }

    @Override
    public void onLivingUpdate() {
        if (mouthOpen && openingProgress < 1){
            openingProgress += 0.05F;
        }else if (!mouthOpen && openingProgress > 0){
            openingProgress -= 0.05F;
        }

        if (sitting && sittingProgress < 1){
            sittingProgress += 0.05F;
        }else if (!sitting && sittingProgress > 0){
            sittingProgress -= 0.05F;
        }
        super.onLivingUpdate();
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(2, new EntityAILookIdle(this));
        super.initEntityAI();
    }

    public boolean isSitting(){
        return sitting && sittingProgress >= 1;
    }
    public void setSitting(boolean sit){
        this.sitting = sit;
    }

    public boolean isMouthOpen(){
        return mouthOpen && openingProgress >= 1;
    }

    public void openMouth(boolean open){
        this.mouthOpen = open;
    }

    @Override
    public Blessing getAssociatedBlessing() {
        return Blessing.SHUB;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setBoolean("isSitting", sitting);
        compound.setBoolean("mouthOpen", mouthOpen);

        compound.setFloat("sittingProgress", sittingProgress);
        compound.setFloat("openingProgress", openingProgress);
        return super.writeToNBT(compound);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        sitting = compound.getBoolean("isSitting");
        mouthOpen = compound.getBoolean("mouthOpen");

        sittingProgress = compound.getFloat("sittingProgress");
        openingProgress = compound.getFloat("openingProgress");
        super.readEntityFromNBT(compound);
    }
}
