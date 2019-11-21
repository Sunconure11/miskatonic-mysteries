package com.miskatonicmysteries.common.entity.goo;

import com.google.common.base.Optional;
import com.google.common.collect.Iterables;
import com.miskatonicmysteries.ModConfig;
import com.miskatonicmysteries.common.capability.blessing.BlessingCapability;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.capability.sanity.Sanity;
import com.miskatonicmysteries.common.world.gen.BiomeManipulator;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

public abstract class AbstractOldOne extends EntityLiving implements IEntityOwnable {
    protected static final DataParameter<Optional<UUID>> SUMMONER = EntityDataManager.createKey(AbstractOldOne.class, DataSerializers.OPTIONAL_UNIQUE_ID);

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
        if (ticksExisted % ModConfig.EntitySettings.goos.greatOldOneManipulationInterval == 0) {
            manipulateEnvironment();
        }
        super.onLivingUpdate();
    }

    public void manipulateEnvironment() {
        if (getDistortionBiome() != null) {
            BlockPos randomPos = new BlockPos(posX - (getInfluenceRadius() / 2F) + world.rand.nextInt(getInfluenceRadius()), posY, posZ - (getInfluenceRadius() / 2F) + world.rand.nextInt(getInfluenceRadius()));
            if (world.getBiome(randomPos) != getDistortionBiome())
                BiomeManipulator.setBiome(world, getDistortionBiome(), true, randomPos);
        }
    }

    public int getInfluenceRadius(){
        return 32;
    }
    @Override
    public void onKillCommand() {
        setDead();
    }

    public Biome getDistortionBiome() {
        return null;
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
        } else {
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
        return (UUID) ((Optional) this.dataManager.get(SUMMONER)).orNull();
    }

    public boolean isOwner(@Nonnull Entity entity) {
        return entity.getUniqueID().equals(getSummonerId());
    }

    public void setOwnerId(@Nullable UUID uui) {
        this.dataManager.set(SUMMONER, Optional.fromNullable(uui));
    }

    @Nullable
    @Override
    public UUID getOwnerId() {
        return getSummonerId();
    }

    @Nullable
    @Override
    public Entity getOwner() {
        try {
            UUID uuid = this.getOwnerId();
            return uuid == null ? null : this.world.getPlayerEntityByUUID(uuid);
        } catch (IllegalArgumentException var2) {
            return null;
        }
    }

    @Override
    public boolean canEntityBeSeen(Entity entityIn) {
        if (entityIn instanceof EntityPlayer) {
            if (Sanity.Util.getSanityCapability((EntityPlayer) entityIn) != null) {
                if (Sanity.Util.getSanity((EntityPlayer) entityIn) < 30) {
                    return true;
                }
            }
            if (BlessingCapability.Util.getBlessing((EntityPlayer) entityIn) == getAssociatedBlessing()) {
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
