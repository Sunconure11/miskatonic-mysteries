package com.miskatonicmysteries.common.entity.goo;

import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.entity.EntityDarkYoung;
import com.miskatonicmysteries.common.world.biome.GreatOldOneArea;
import com.miskatonicmysteries.registry.ModBiomes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Biomes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class EntityShub extends AbstractOldOne implements IEntityMultiPart {
    private static final DataParameter<Boolean> MOUTH_OPEN = EntityDataManager.createKey(EntityShub.class, DataSerializers.BOOLEAN);

    private static final DataParameter<Boolean> SITTING = EntityDataManager.createKey(EntityShub.class, DataSerializers.BOOLEAN);

    public float sittingProgress = 0;
    public float openingProgress = 0;

    //tentacle code from https://github.com/Angry-Pixel/The-Betweenlands/blob/1.12-dev/src/main/java/thebetweenlands/common/entity/mobs/EntityShambler.java
//    public MultiPartEntityPart tentacle_grip = new MultiPartEntityPart(this, "tentacle_grip", 0.5F, 0.5F);

    public EntityShub(World worldIn) {
        super(worldIn);
        setSize(5, 16);
    }

    @Override
    public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, EnumHand hand) {
            //player.startRiding(this, true);
            if (player.isSneaking()) {
                if (hand == EnumHand.MAIN_HAND) {
                    setSitting(!isSitting());
                }
            } else {
                if (hand == EnumHand.MAIN_HAND) {
                    openMouth(!isMouthOpen());
                }
            }
        return super.applyPlayerInteraction(player, vec, hand);
    }

    @Override
    public void onLivingUpdate() {
            if (isMouthOpen() && openingProgress < 1) {
                openingProgress += 0.05F;
            } else if (!isMouthOpen() && openingProgress > 0) {
                openingProgress -= 0.05F;
            }

            if (isSitting() && sittingProgress < 1) {
                sittingProgress += 0.01F;
            } else if (!isSitting() && sittingProgress > 0) {
                sittingProgress -= 0.01F;
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

    @Override
    protected void entityInit() {
        this.dataManager.register(MOUTH_OPEN, false);
        this.dataManager.register(SITTING, false);

        super.entityInit();
    }

    public boolean isSitting() {
        return dataManager.get(SITTING);// && sittingProgress >= 1;
    }

    public void setSitting(boolean sit) {
        this.dataManager.set(SITTING, sit);
    }

    public boolean isMouthOpen() {
        return dataManager.get(MOUTH_OPEN);// && openingProgress >= 1;
    }

    public void openMouth(boolean open) {
        this.dataManager.set(MOUTH_OPEN, open);
    }

    @Override
    public Blessing getAssociatedBlessing() {
        return Blessing.SHUB;
    }


    @Override
    public GreatOldOneArea getDistortionBiome() {
        return ModBiomes.SHUB;
    }

    @Override
    public float getEyeHeight() {
        return super.getEyeHeight() - 5 * sittingProgress;
    }

    @Override
    public float getCollisionBorderSize() {
        return 1;
    }



    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return super.getRenderBoundingBox().grow(20);
    }

    @Override
    public AxisAlignedBB getEntityBoundingBox() {
        return super.getEntityBoundingBox().grow(0, -5 * sittingProgress * 0.5, 0).offset(0, -5 * sittingProgress * 0.5, 0);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        compound.setBoolean("isSitting", isSitting());
        compound.setBoolean("mouthOpen", isMouthOpen());

        compound.setFloat("sittingProgress", sittingProgress);
        compound.setFloat("openingProgress", openingProgress);
        super.writeEntityToNBT(compound);
    }


    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        setSitting(compound.getBoolean("isSitting"));
        openMouth(compound.getBoolean("mouthOpen"));

        sittingProgress = compound.getFloat("sittingProgress");
        openingProgress = compound.getFloat("openingProgress");
        super.readEntityFromNBT(compound);
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public boolean attackEntityFromPart(MultiPartEntityPart part, DamageSource source, float damage) {
        return attackEntityFrom(source, damage);
    }


    @Override
    public void updatePassenger(Entity entity) {

        if(entity instanceof EntityPlayerMP) {
            NetHandlerPlayServer handler = ((EntityPlayerMP) entity).connection;
            try {
                ReflectionHelper.findField(NetHandlerPlayServer.class, "floating", "field_184344_B", "B").set(handler, false);
                ReflectionHelper.findField(NetHandlerPlayServer.class, "floatingTickCount", "field_147365_f", "C").set(handler, 0);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        if (entity instanceof EntityLivingBase) {
           /* double a = Math.toRadians(rotationYaw);
            double offSetX = 0;//Math.sin(a) * getTongueLength() > 0 ? -0.125D : -0.35D;
            double offSetZ = 0;//-Math.cos(a) * getTongueLength() > 0 ? -0.125D : -0.35D;
            entity.setPosition(tentacle_grip.posX + offSetX, tentacle_grip.posY - entity.height * 0.3D, tentacle_grip.posZ + offSetZ);
            if (entity.isSneaking())
                entity.setSneaking(false);*/
        }
    }

    @Override
    public boolean canRiderInteract() {
        return false;
    }

    @Override
    public boolean shouldRiderSit() {
        return false;
    }
}
