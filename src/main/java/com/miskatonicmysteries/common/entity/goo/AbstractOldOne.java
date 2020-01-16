package com.miskatonicmysteries.common.entity.goo;

import com.google.common.base.Optional;
import com.miskatonicmysteries.ModConfig;
import com.miskatonicmysteries.common.capability.blessing.BlessingCapability;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.capability.sanity.Sanity;
import com.miskatonicmysteries.common.world.ExtendedWorld;
import com.miskatonicmysteries.common.world.biome.GreatOldOneArea;
import com.miskatonicmysteries.util.ColorUtil;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.client.renderer.GlStateManager;
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
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.nio.FloatBuffer;
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
            ExtendedWorld extendedWorld = ExtendedWorld.get(world);
            if (!extendedWorld.GOO_AREAS.containsKey(randomPos)){
                ExtendedWorld.addGreatOldOneArea(world, this, randomPos, getDistortionBiome());
            }
            if (getRNG().nextBoolean()){
                extendedWorld.GOO_AREAS.forEach((areaPos, area) -> {
                    if (area.getGOOClass().isInstance(this) && areaPos.getDistance((int) posX, (int) posY, (int) posZ) <= getInfluenceRadius()){
                        area.update(this, world, areaPos);
                    }
                });
            }
        }
    }

    public static int getInfluenceRadius(){
        return 32;
    }

    @Override
    public void onKillCommand() {
        setDead();
    }

    public abstract GreatOldOneArea getDistortionBiome();

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

    public static AbstractOldOne getClosestGOO(World world, BlockPos pos, double distance, Blessing blessing){
        double lastDistance = -1.0D;
        AbstractOldOne greatOldOne = null;
        for (AbstractOldOne checkedOldOne : world.getEntities(AbstractOldOne.class, o -> blessing == null || o.getAssociatedBlessing() == blessing)) {
            double distanceSquared = checkedOldOne.getDistanceSq(pos);
            if ((distance < 0.0D || distanceSquared < distance * distance) && (lastDistance == -1.0D || distanceSquared < lastDistance)){
                lastDistance = distanceSquared;
                greatOldOne = checkedOldOne;
            }
        }
        return greatOldOne;
    }

    @SideOnly(Side.CLIENT)
    public double getFogDensity(@Nullable EntityPlayer player, double xAt, double yAt, double zAt, float oldDensity){
        double distance = getDistance(xAt, yAt, zAt);
        double ratio = Math.sqrt(1 - distance / (float) getInfluenceRadius());
        if (ratio < 0){
            ratio = 0;
        }
        return oldDensity + ratio * 3;
    }

    @SideOnly(Side.CLIENT)
    public Color getFogColor(@Nullable EntityPlayer player, double xAt, double yAt, double zAt, Color oldColor){
        double distance = getDistance(xAt, yAt, zAt);
        double ratio = Math.pow(1 - (distance / (float) getInfluenceRadius()), 2);
        if (ratio < 0){
            ratio = 0;
        }
        return ColorUtil.blend(oldColor, Color.BLACK, 1 - ratio, ratio);
    }

    @SideOnly(Side.CLIENT)
    public void renderSpecialFog(EntityViewRenderEvent.RenderFogEvent fogRender){

    }
}
