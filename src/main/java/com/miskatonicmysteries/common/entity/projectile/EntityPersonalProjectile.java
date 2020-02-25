package com.miskatonicmysteries.common.entity.projectile;

import com.miskatonicmysteries.common.block.BlockFluidMMWater;
import com.miskatonicmysteries.common.capability.sanity.Sanity;
import com.miskatonicmysteries.registry.ModObjects;
import com.miskatonicmysteries.registry.ModPotions;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityPersonalProjectile extends EntityThrowable { //nothing personal kid 😳
    public float damage = 3;
    public EntityPersonalProjectile(World worldIn) {
        super(worldIn);
    }

    public EntityPersonalProjectile(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getDamage() {
        return damage;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setFloat("damage", damage);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        this.damage = compound.getFloat("damage");
        super.readFromNBT(compound);
    }

    @Override
    public void onUpdate() {
        if (world.isRemote) {
            world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, posX, posY, posZ, 0, 0, 0);
        }
        super.onUpdate();
    }

    @Override
    public boolean isInWater() {
        return false;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.entityHit != null && result.entityHit != getThrower()) {
            result.entityHit.attackEntityFrom(new EntityDamageSourceIndirect(DamageSource.MAGIC.getDamageType(), this, this.getThrower()).setProjectile(), damage);
            if (getThrower() instanceof EntityPlayer){
                float chance = Sanity.Util.getSanity((EntityPlayer) getThrower()) / 30F;
                if (world.rand.nextFloat() > chance){
                    if (result.entityHit instanceof EntityLiving) ((EntityLiving) result.entityHit).addPotionEffect(new PotionEffect(ModPotions.mania,200, 1));
                }
            }
        }
    }

    @Override
    protected float getGravityVelocity() {
        return super.getGravityVelocity() / 2F;
    }
}
