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
    public boolean isSitting = false;
    public EntityShub(World worldIn) {
        super(worldIn);
        setSize(3, 5.5F);
    }

    @Override
    public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, EnumHand hand) {
        if(world.isRemote) {
            if (player.isSneaking()) {
                if (hand == EnumHand.MAIN_HAND) {
                    setSitting(!isSitting);
                }
            }
            System.out.println(isSitting);
        }
        return super.applyPlayerInteraction(player, vec, hand);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(2, new EntityAILookIdle(this));
        super.initEntityAI();
    }

    @SideOnly(Side.CLIENT)
    public boolean isSitting(){
        return isSitting;
    }

    @SideOnly(Side.CLIENT)
    public void setSitting(boolean sit){
        this.isSitting = sit;
    }

    @Override
    public Blessing getAssociatedBlessing() {
        return Blessing.SHUB;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        return super.writeToNBT(compound);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
    }
}
