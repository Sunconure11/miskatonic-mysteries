package com.miskatonicmysteries.common.misc.spells;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.blessing.BlessingCapability;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.util.ResourceLocation;

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
            System.out.println(caster.world.isRemote);
            EntitySnowball projectile = new EntitySnowball(caster.world, caster);
            // EntityWaterProjectile projectile = new EntityWaterProjectile(caster.world);
            projectile.shoot(caster, 0, i, 0, 2F, 0F);
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
