package com.miskatonicmysteries.common.entity.goo;

import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.entity.EntityDarkYoung;
import com.miskatonicmysteries.common.entity.IIgnoreMaterials;
import com.miskatonicmysteries.common.entity.processor.PathNavigateGroundIgnoreSpecial;
import com.miskatonicmysteries.common.world.biome.GreatOldOneArea;
import com.miskatonicmysteries.registry.ModBiomes;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityZombie;
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
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class EntityShub extends AbstractOldOne implements IIgnoreMaterials {
    private static final DataParameter<Boolean> MOUTH_OPEN = EntityDataManager.createKey(EntityShub.class, DataSerializers.BOOLEAN);
    public float openingProgress = 0;//todo add tentacle stuff later

    public EntityShub(World worldIn) {
        super(worldIn);
        setSize(5, 16);
        navigator = new PathNavigateGroundIgnoreSpecial(this, worldIn);
    }


    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6F);
    }

     @Override
    public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, EnumHand hand) {
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

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {

        return super.onInitialSpawn(difficulty, livingdata);
    }

    @Override
    public void onLivingUpdate() {
        if (isMouthOpen() && openingProgress < 1) {
            openingProgress += 0.05F;
        } else if (!isMouthOpen() && openingProgress > 0) {
            openingProgress -= 0.05F;
        }
        super.onLivingUpdate();
    }


    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
    }

    @Override
    protected void entityInit() {
        this.dataManager.register(MOUTH_OPEN, false);

        super.entityInit();
    }

    public boolean isMouthOpen() {
        return dataManager.get(MOUTH_OPEN);
    }

    public void openMouth(boolean open) {
        this.dataManager.set(MOUTH_OPEN, open);
    }

    @Override
    public Blessing getAssociatedBlessing() {
        return Blessing.SHUB;
    }


    @Override
    public Biome getDistortionBiome() {
        return Biomes.MUSHROOM_ISLAND;
    }

    @Override
    public float getEyeHeight() {
        return super.getEyeHeight() - (isSitting() ? 5 : 0);
    }

    @Override
    public float getCollisionBorderSize() {
        return 1;
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return super.getRenderBoundingBox().grow(5);
    }

    @Override
    public AxisAlignedBB getEntityBoundingBox() {
        AxisAlignedBB box = super.getEntityBoundingBox();
        return isSitting() ? box.setMaxY(box.maxY - 5) : box;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        compound.setBoolean("mouthOpen", isMouthOpen());
        compound.setFloat("openingProgress", openingProgress);
        super.writeEntityToNBT(compound);
    }


    @Override
    public int getParticleColor() {
        return 1445125;
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        openMouth(compound.getBoolean("mouthOpen"));
        openingProgress = compound.getFloat("openingProgress");
        super.readEntityFromNBT(compound);
    }

    @Override
    public boolean canRiderInteract() {
        return false;
    }

    @Override
    public boolean shouldRiderSit() {
        return false;
    }

    @Override
    public Predicate<IBlockState> checkIgnore() {
        return state -> state.getBlock() instanceof BlockLog || state.getBlock() instanceof BlockLeaves;
    }
}
