package com.miskatonicmysteries.common.entity.goo;

import com.google.common.base.Optional;
import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.ModConfig;
import com.miskatonicmysteries.client.particles.ParticleGOOSmoke;
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
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
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
    //todo next: add GOO AI for occasional walking and sitting afterwards, then for growing if there is enough space, shrinking if there is too little
    //then add the possiblity of the particle form to the travel AI (this will make the GOO much faster)  (also fix the stuff clipping if the hitboxes expand)
    protected static final DataParameter<Optional<UUID>> SUMMONER = EntityDataManager.createKey(AbstractOldOne.class, DataSerializers.OPTIONAL_UNIQUE_ID);
    protected static final DataParameter<Boolean> SITTING = EntityDataManager.createKey(AbstractOldOne.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> PARTICLE_FORM = EntityDataManager.createKey(AbstractOldOne.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Float> SIZE = EntityDataManager.createKey(AbstractOldOne.class, DataSerializers.FLOAT);


    //rendering var
    public float sittingProgress = 0;

    public AbstractOldOne(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void entityInit() {
        this.dataManager.register(SIZE, 1F);
        this.dataManager.register(PARTICLE_FORM, false);
        this.dataManager.register(SITTING, false);
        this.dataManager.register(SUMMONER, Optional.absent());
        super.entityInit();
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        //occasional travel, maybe, may sit if commanded to (must be given some treat i guess)--- travel AI will include the particle form transformation (or maybe I'll make it separate so it does it automatically...
        //(as to above) maybe an AI task that checks the path distance and space to decide if it should transform (this will allow it to clip through blocks and reduce the hitbox by a lot)
        this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 18.0F));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        super.initEntityAI();
    }

    public float getSize(){
        return dataManager.get(SIZE);
    }

    public void setSize(float size){
        dataManager.set(SIZE, size);
    }

    public boolean isParticleForm(){
        return dataManager.get(PARTICLE_FORM);
    }

    public void setParticleForm(boolean activate){
        dataManager.set(PARTICLE_FORM, activate);
    }

    public boolean isSitting() {
        return dataManager.get(SITTING);
    }

    public void setSitting(boolean sit) {
        this.dataManager.set(SITTING, sit);
    }

    @Override
    public void onLivingUpdate() {
        if (!isParticleForm() && ticksExisted % ModConfig.EntitySettings.goos.greatOldOneManipulationInterval == 0) {
            manipulateEnvironment();
        }

        if (world.isRemote && isParticleForm()){
            spawnTravelParticles();
        }

        if (isSitting() && sittingProgress < 1) {
            sittingProgress += 0.01F;
        } else if (!isSitting() && sittingProgress > 0) {
            sittingProgress -= 0.01F;
        }

        super.onLivingUpdate();
    }

    @SideOnly(Side.CLIENT)
    public void spawnTravelParticles(){
        MiskatonicMysteries.proxy.generateParticle(new ParticleGOOSmoke(world, posX + rand.nextDouble(), posY + 0.5D + rand.nextDouble(), posZ + rand.nextDouble(), getParticleColor()));
    }

    public abstract int getParticleColor();

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
        compound.setBoolean("isSitting", isSitting());
        compound.setBoolean("isParticleForm", isParticleForm());
        compound.setFloat("GOOSize", getSize());
        compound.setFloat("sittingProgress", sittingProgress);
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

        setSitting(compound.getBoolean("isSitting"));
        setParticleForm(compound.getBoolean("isParticleForm"));
        setSize(compound.getFloat("GOOSize"));
        sittingProgress = compound.getFloat("sittingProgress");
        super.readEntityFromNBT(compound);
    }

    public abstract Blessing getAssociatedBlessing();

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    public EnumPushReaction getPushReaction() {
        return EnumPushReaction.BLOCK;
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
