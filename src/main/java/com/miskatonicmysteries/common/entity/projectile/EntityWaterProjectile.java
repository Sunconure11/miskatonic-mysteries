package com.miskatonicmysteries.common.entity.projectile;

import com.miskatonicmysteries.common.block.BlockFluidMMWater;
import com.miskatonicmysteries.registry.ModObjects;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityWaterProjectile extends EntityThrowable {
    public int damage = 8;
    public EntityWaterProjectile(World worldIn) {
        super(worldIn);
    }

    public EntityWaterProjectile(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
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
        ticksExisted++;
        if (ticksExisted > 20) {
            setDead();
        } else {
            if (ticksExisted < 10 && world.isAirBlock(getPosition()) || world.getBlockState(getPosition()).getBlock().isReplaceable(world, getPosition())) {
                if (world.rand.nextBoolean())
                    world.setBlockState(getPosition(), ModObjects.block_water_mm.getDefaultState().withProperty(BlockFluidMMWater.LEVEL, 2));
            }
            if (world.isRemote) {
                world.spawnParticle(EnumParticleTypes.WATER_SPLASH, posX, posY, posZ, 0, 0, 0);
            }
            super.onUpdate();
        }
    }

    @Override
    public boolean isInWater() {
        return false;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.entityHit != null && result.entityHit != getThrower()) {
            if (result.entityHit instanceof EntityBlaze) {
                damage += 4;
            }
            result.entityHit.attackEntityFrom(new EntityDamageSourceIndirect(DamageSource.DROWN.getDamageType(), this, this.getThrower()).setProjectile(), damage);
        }

    }
}
