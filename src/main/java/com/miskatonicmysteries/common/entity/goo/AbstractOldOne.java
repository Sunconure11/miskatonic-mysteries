package com.miskatonicmysteries.common.entity.goo;

import com.google.common.base.Optional;
import com.miskatonicmysteries.ModConfig;
import com.miskatonicmysteries.client.particles.ParticleColoredSmoke;
import com.miskatonicmysteries.common.capability.blessing.BlessingCapability;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.capability.sanity.Sanity;
import com.miskatonicmysteries.common.world.ExtendedWorld;
import com.miskatonicmysteries.common.world.gen.BiomeManipulator;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

public abstract class AbstractOldOne extends EntityCreature implements IEntityOwnable { //change back to EntityCreature
    //todo next: add GOO AI for occasional walking and sitting afterwards, then for growing if there is enough space, shrinking if there is too little
    //then add the possiblity of the particle form to the travel AI (this will make the GOO much faster)  (also fix the stuff clipping if the hitboxes expand)
    protected static final DataParameter<Optional<UUID>> SUMMONER = EntityDataManager.createKey(AbstractOldOne.class, DataSerializers.OPTIONAL_UNIQUE_ID);
    protected static final DataParameter<Boolean> SITTING = EntityDataManager.createKey(AbstractOldOne.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> PARTICLE_FORM = EntityDataManager.createKey(AbstractOldOne.class, DataSerializers.BOOLEAN);

    //also make GOOs "climb" blocks instead of just jumping (so things look less weird)
    public EntityAIGOOWander gooWanderAI;
    //rendering var
    public float sittingProgress = 0;
    public float minimumWidth, minimumHeight;

    public AbstractOldOne(World worldIn) {
        super(worldIn);
        isImmuneToFire = true;
        stepHeight = 1.5F;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(42.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(999);
    }

    @Override
    protected void entityInit() {
        this.dataManager.register(PARTICLE_FORM, false);
        this.dataManager.register(SITTING, false);
        this.dataManager.register(SUMMONER, Optional.absent());
        super.entityInit();
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        //will need a special version for this in which it does that stuff the way vex fly, so without clipping, if in the particle form (this will also increase speed)
        gooWanderAI = new EntityAIGOOWander(this);
        this.tasks.addTask(1, gooWanderAI);
        //occasional travel, maybe, may sit if commanded to (must be given some treat i guess)--- travel AI will include the particle form transformation (or maybe I'll make it separate so it does it automatically...
        //(as to above) maybe an AI task that checks the path distance and space to decide if it should transform (this will allow it to clip through blocks and reduce the hitbox by a lot)
        this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 18.0F));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        super.initEntityAI();
    }

    public boolean isParticleForm() {
        return dataManager.get(PARTICLE_FORM);
    }

    public void setParticleForm(boolean activate) {
        dataManager.set(PARTICLE_FORM, activate);
    }

    public boolean isSitting() {
        return dataManager.get(SITTING);
    }

    public void setSitting(boolean sit) {
        this.dataManager.set(SITTING, sit);
    }

    public float getMoveSpeed() {
        return isParticleForm() ? 5 : (float) getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
    }

    @Override
    public void onLivingUpdate() { //todo also: when in particle form, the size will continuously shrink; this will be represented in the spread of the particles decreasing
        if (gooWanderAI != null)
            setParticleForm(gooWanderAI.shouldGoIntoParticleForm && hasPath());
        if (isParticleForm()) {
            noClip = posY > 1;

            if (world.isRemote) spawnTravelParticles();
        }else{
            checkSpace();
            if (ticksExisted % ModConfig.entities.goos.greatOldOneManipulationInterval == 0) manipulateEnvironment();
        }

        if (isSitting() && sittingProgress < 1) {
            sittingProgress += 0.01F;
        } else if (!isSitting() && sittingProgress > 0) {
            sittingProgress -= 0.01F;
        }

        if (posY < -10) {
            setDead();
        }

        super.onLivingUpdate();
        noClip = false;
        setNoGravity(isParticleForm());
    }

    public void checkSpace(){
        //this should actually be done in AI lol (mutexBits same as movement AI, but has a higher priority as it checks if there is space)
        //if sitting
            //if there is space (no collision with expanded hitbox (hitbox + 1) && maximum size is not reached yet
                //grow

        //if there is no space with normal entity box -1 or 0.2
            //if the goo is not too small (Size > 1)
                //shrink
            //else
                //search for place with space && particleForm
    }
    @Override
    public void setDead() {
        if (world.isRemote) {
            spawnVanishingParticles();
        }
        super.setDead();
    }

    @SideOnly(Side.CLIENT)
    public void spawnVanishingParticles() {
        for (int i = 0; i < 50; i++)
            Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleColoredSmoke(world, posX + rand.nextDouble() * 2, posY + getEyeHeight() * 0.5 + rand.nextDouble() * 2, posZ + rand.nextDouble(), getParticleColor(), getParticleAlpha()));
    }

    @SideOnly(Side.CLIENT)
    public void spawnTravelParticles() {
        for (int i = 0; i < 5; i++)
            Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleColoredSmoke(world, posX + rand.nextDouble(), posY + getEyeHeight() * 0.5 + rand.nextDouble(), posZ + rand.nextDouble(), getParticleColor(), getParticleAlpha()));
    }

    public float getParticleAlpha() {
        return 1;
    }

    public abstract int getParticleColor();

    public void manipulateEnvironment() {
        if (getDistortionBiome() != null) {
            double a = Math.random() * 2 * Math.PI;
            double r = getInfluenceRadius() * Math.sqrt(Math.random()); //get random point within a circle
            BlockPos randomPos = new BlockPos(posX + Math.round((float) r * Math.cos(a)), posY, posZ + Math.round((float) r * Math.sin(a)));
            ExtendedWorld extendedWorld = ExtendedWorld.get(world);
            if (!extendedWorld.STORED_OVERRIDE_BIOMES.containsKey(randomPos)) {
                ExtendedWorld.addOverriddenBiome(world, randomPos);
                BiomeManipulator.setBiome(world, getDistortionBiome(), randomPos);
            }
        }
    }

    public int getInfluenceRadius() {
        return 64;
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        setHomePosAndDistance(getPosition(), 128);
        return super.onInitialSpawn(difficulty, livingdata);
    }

    @Override
    public void onKillCommand() {
        setDead();
    }

    public abstract Biome getDistortionBiome();

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
        compound.setFloat("sittingProgress", sittingProgress);
        compound.setFloat("minimumWidth", minimumWidth);
        compound.setFloat("minimumHeight", minimumHeight);
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
        sittingProgress = compound.getFloat("sittingProgress");
        minimumWidth = compound.getFloat("minimumWidth");
        minimumHeight = compound.getFloat("minimumHeight");
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
        return super.getRenderBoundingBox().grow(10);
    }

    public static AbstractOldOne getClosestGOO(World world, BlockPos pos, double distance, Blessing blessing) {
        double lastDistance = -1.0D;
        AbstractOldOne greatOldOne = null;
        for (AbstractOldOne checkedOldOne : world.getEntities(AbstractOldOne.class, o -> blessing == null || o.getAssociatedBlessing() == blessing)) {
            double distanceSquared = checkedOldOne.getDistanceSq(pos);
            if ((distance < 0.0D || distanceSquared < distance * distance) && (lastDistance == -1.0D || distanceSquared < lastDistance)) {
                lastDistance = distanceSquared;
                greatOldOne = checkedOldOne;
            }
        }
        return greatOldOne;
    }

    public static class EntityAIGOOWander extends EntityAIBase {
        protected final AbstractOldOne goo;
        protected double x;
        protected double y;
        protected double z;
        protected int executionChance;
        protected boolean mustUpdate;
        public boolean shouldGoIntoParticleForm;

        public EntityAIGOOWander(AbstractOldOne creatureIn) {
            this.goo = creatureIn;
            this.executionChance = 2;
            this.setMutexBits(1);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute() {
            if (!this.mustUpdate) {
                if (this.goo.getIdleTime() >= 100) {
                    return false;
                }

                if (this.goo.getRNG().nextInt(this.executionChance) != 0) {
                    return false;
                }
            }

            Vec3d vec3d = this.getPosition();

            if (vec3d == null) {
                return false;
            } else {
                this.x = vec3d.x;
                this.y = vec3d.y;
                this.z = vec3d.z;
                this.mustUpdate = false;
                return true;
            }
        }

        @Nullable
        protected Vec3d getPosition() {
            return RandomPositionGenerator.getLandPos(this.goo, 28, 3);
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            return !this.goo.getNavigator().noPath();
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            this.goo.getNavigator().tryMoveToXYZ(this.x, this.y, this.z, goo.getMoveSpeed());
            shouldGoIntoParticleForm = goo.hasPath() && goo.getNavigator().getPath().getCurrentPathLength() > 64;
        }

        /**
         * Makes task to bypass chance
         */
        public void makeUpdate() {
            this.mustUpdate = true;
        }

        /**
         * Changes task random possibility for execution
         */
        public void setExecutionChance(int newchance) {
            this.executionChance = newchance;
        }
    }
}
