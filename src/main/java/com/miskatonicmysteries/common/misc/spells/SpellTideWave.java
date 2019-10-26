package com.miskatonicmysteries.common.misc.spells;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.blessing.BlessingCapability;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.entity.projectile.EntityWaterProjectile;
import com.miskatonicmysteries.common.network.PacketHandler;
import com.miskatonicmysteries.registry.ModObjects;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class SpellTideWave extends Spell {
    public SpellTideWave() {
        super(new ResourceLocation(MiskatonicMysteries.MODID, "tide_wave"));
        this.castTime = 5;
        this.cooldownTime = 1200;
    }

    @Override
    public boolean check(EntityPlayer caster) {
        if (caster.experienceLevel >= 2 && BlessingCapability.Util.getBlessing(caster) == Blessing.CTHULHU) {
            return super.check(caster);
        }
        return false;
    }

    @Override
    public void cast(EntityPlayer caster) {
        System.out.println("pitch: " + caster.rotationPitch);
        System.out.println("yaw: " + caster.rotationYaw);
        for (int i = 0; i < 360; i += 6){
            System.out.println("AAAAAA");
            EntityWaterProjectile projectile = new EntityWaterProjectile(caster.world);
            projectile.shoot(caster, 0, i, 0, 2F, 0F);
            projectile.setDamage(5);
           // if (!caster.world.isRemote)
                caster.world.spawnEntity(projectile);
        }
        super.cast(caster);
    }

    @Override
    public void price(EntityPlayer caster) {
        caster.addExperienceLevel(-2);
    }
}
