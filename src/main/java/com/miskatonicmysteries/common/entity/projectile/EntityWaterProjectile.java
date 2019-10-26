package com.miskatonicmysteries.common.entity.projectile;

import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityWaterProjectile extends EntityThrowable {
    public int damage = 4;
    public EntityWaterProjectile(World worldIn) {
        super(worldIn);
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("damage", damage);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        this.damage = compound.getInteger("damage");
        super.readFromNBT(compound);
    }

    @Override
    public void onUpdate() {
        System.out.println("ahaHAHHAHAHAHAHHAH");
        if (world.isRemote){
            world.spawnParticle(EnumParticleTypes.WATER_SPLASH, posX, posY, posZ, 0, 0,0);
        }
        super.onUpdate();
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.entityHit != null) {
            if (result.entityHit instanceof EntityBlaze) {
                damage += 3;
            }
            result.entityHit.attackEntityFrom(new EntityDamageSourceIndirect(DamageSource.DROWN.getDamageType(), this, this.getThrower()).setProjectile(), damage);
        }

    }
}
