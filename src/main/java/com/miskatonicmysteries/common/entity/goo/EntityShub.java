package com.miskatonicmysteries.common.entity.goo;

import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.registry.ModBiomes;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import javax.annotation.Nullable;
import java.util.UUID;

public class EntityShub extends AbstractOldOne {
    public boolean mouthOpen = false;
    public boolean sitting = false;

    public float sittingProgress = 0;
    public float openingProgress = 0;

    public EntityShub(World worldIn) {
        super(worldIn);
        setSize(5, 16);
    }

    @Override
    public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, EnumHand hand) {
        if (world.isRemote) {
            if (player.isSneaking()) {
                if (hand == EnumHand.MAIN_HAND) {
                    setSitting(!sitting);
                }
            } else {
                if (hand == EnumHand.MAIN_HAND) {
                    openMouth(!isMouthOpen());
                }
            }
        }
        return super.applyPlayerInteraction(player, vec, hand);
    }

    @Override
    public void onLivingUpdate() {

        if (mouthOpen && openingProgress < 1) {
            openingProgress += 0.05F;
        } else if (!mouthOpen && openingProgress > 0) {
            openingProgress -= 0.05F;
        }

        if (sitting && sittingProgress < 1) {
            sittingProgress += 0.01F;
        } else if (!sitting && sittingProgress > 0) {
            sittingProgress -= 0.01F;
        }
        super.onLivingUpdate();
    }


    @Override
    protected void initEntityAI() {
        //  this.tasks.addTask(0, new EntityAISwimming(this));
        // this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        //    this.tasks.addTask(2, new EntityAILookIdle(this));
        super.initEntityAI();
    }

    public boolean isSitting() {
        return sitting && sittingProgress >= 1;
    }

    public void setSitting(boolean sit) {
        this.sitting = sit;
    }

    public boolean isMouthOpen() {
        return mouthOpen && openingProgress >= 1;
    }

    public void openMouth(boolean open) {
        this.mouthOpen = open;
    }

    @Override
    public Blessing getAssociatedBlessing() {
        return Blessing.SHUB;
    }

    @Override
    public Biome getDistortionBiome() {
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
    protected void doBlockCollisions() {
        AxisAlignedBB axisalignedbb = this.getEntityBoundingBox();
        BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain(axisalignedbb.minX + 0.001D, axisalignedbb.minY + 0.001D, axisalignedbb.minZ + 0.001D);
        BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos1 = BlockPos.PooledMutableBlockPos.retain(axisalignedbb.maxX - 0.001D, axisalignedbb.maxY - 0.001D, axisalignedbb.maxZ - 0.001D);
        BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos2 = BlockPos.PooledMutableBlockPos.retain();

        if (this.world.isAreaLoaded(blockpos$pooledmutableblockpos, blockpos$pooledmutableblockpos1)) {
            for (int i = blockpos$pooledmutableblockpos.getX(); i <= blockpos$pooledmutableblockpos1.getX(); ++i) {
                for (int j = blockpos$pooledmutableblockpos.getY(); j <= blockpos$pooledmutableblockpos1.getY(); ++j) {
                    for (int k = blockpos$pooledmutableblockpos.getZ(); k <= blockpos$pooledmutableblockpos1.getZ(); ++k) {
                        blockpos$pooledmutableblockpos2.setPos(i, j, k);
                        IBlockState iblockstate = this.world.getBlockState(blockpos$pooledmutableblockpos2);
                        if (!(iblockstate instanceof BlockLog || iblockstate instanceof BlockLeaves)) {
                            try {
                                iblockstate.getBlock().onEntityCollidedWithBlock(this.world, blockpos$pooledmutableblockpos2, iblockstate, this);
                                this.onInsideBlock(iblockstate);
                            } catch (Throwable throwable) {
                                CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Colliding entity with block");
                                CrashReportCategory crashreportcategory = crashreport.makeCategory("Block being collided with");
                                CrashReportCategory.addBlockInfo(crashreportcategory, blockpos$pooledmutableblockpos2, iblockstate);
                                throw new ReportedException(crashreport);
                            }
                        }
                    }
                }
            }
        }

        blockpos$pooledmutableblockpos.release();
        blockpos$pooledmutableblockpos1.release();
        blockpos$pooledmutableblockpos2.release();
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return super.getRenderBoundingBox().grow(10);
    }

    @Override
    public AxisAlignedBB getEntityBoundingBox() {
        return super.getEntityBoundingBox().grow(0, -5 * sittingProgress * 0.5, 0).offset(0, -5 * sittingProgress * 0.5, 0);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        compound.setBoolean("isSitting", sitting);
        compound.setBoolean("mouthOpen", mouthOpen);

        compound.setFloat("sittingProgress", sittingProgress);
        compound.setFloat("openingProgress", openingProgress);
        super.writeEntityToNBT(compound);
    }


    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        sitting = compound.getBoolean("isSitting");
        mouthOpen = compound.getBoolean("mouthOpen");

        sittingProgress = compound.getFloat("sittingProgress");
        openingProgress = compound.getFloat("openingProgress");
        super.readEntityFromNBT(compound);
    }
}
