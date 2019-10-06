package com.miskatonicmysteries.common.entity;

import com.google.common.base.Optional;
import com.google.common.collect.Iterables;
import com.miskatonicmysteries.common.capability.blessing.BlessingCapability;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.capability.sanity.Sanity;
import com.miskatonicmysteries.common.world.gen.BiomeManipulator;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.command.CommandKill;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.UUID;

public abstract class AbstractOldOne extends EntityLiving {
    protected static final DataParameter<Optional<UUID>> SUMMONER = EntityDataManager.<Optional<UUID>>createKey(EntityTameable.class, DataSerializers.OPTIONAL_UNIQUE_ID);

    public AbstractOldOne(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void entityInit() {
        this.dataManager.register(SUMMONER, Optional.absent());
        super.entityInit();
    }

    @Override
    public void onLivingUpdate() {
        if (ticksExisted % 20 == 0){
            manipulateEnvironment();
        }
        super.onLivingUpdate();
    }

    public void manipulateEnvironment(){
        BiomeManipulator.setMultiBiome(world, Biomes.SAVANNA, Iterables.toArray(BlockPos.getAllInBox(getPosition().add(-10, -10, -10), getPosition().add(10, 10, 10)), BlockPos.class));
        //possibly add custom biome that will be converted by nearby biomes, if possible (certainly is)
    }

    @Override
    public boolean isEntityInvulnerable(DamageSource source) {
        return true;
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        if (this.getSummonerId() == null) {
            compound.setString("SummonerUUID", "");
        }
        else {
            compound.setString("SummonerUUID", this.getSummonerId().toString());
        }
        super.writeEntityToNBT(compound);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        String uuid = "";
        if (compound.hasKey("SummonerUUID", 8)) {
            uuid = compound.getString("SummonerUUID");
        }

        if (!uuid.isEmpty()) {
            this.setOwnerId(UUID.fromString(uuid));
        }
        super.readEntityFromNBT(compound);
    }

    public abstract Blessing getAssociatedBlessing();

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    public EnumPushReaction getPushReaction() {
        return EnumPushReaction.BLOCK;//super.getPushReaction();
    }

    @Nullable
    public UUID getSummonerId() {
        return (UUID)((Optional)this.dataManager.get(SUMMONER)).orNull();
    }

    public boolean isOwner(@Nonnull Entity entity){
        return entity.getUniqueID().equals(getSummonerId());
    }

    public void setOwnerId(@Nullable UUID uui) {
        this.dataManager.set(SUMMONER, Optional.fromNullable(uui));
    }


    @Override
    public boolean canEntityBeSeen(Entity entityIn) {
        if (entityIn instanceof EntityPlayer){
            if (Sanity.Util.getSanityCapability((EntityPlayer) entityIn) != null){
                if (Sanity.Util.getSanity((EntityPlayer) entityIn) < 30){
                    return true;
                }
            }
            if (BlessingCapability.Util.getBlessing((EntityPlayer) entityIn) == getAssociatedBlessing()){
                return true;
            }
        }
        return isOwner(entityIn);
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return super.getRenderBoundingBox().grow(3);
    }
}
