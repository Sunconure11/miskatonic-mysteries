package com.miskatonicmysteries.common.misc.spells;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.blessing.BlessingCapability;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.entity.projectile.EntityWaterProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class SpellPersonalFragment extends Spell {
    public SpellPersonalFragment() {
        super(new ResourceLocation(MiskatonicMysteries.MODID, "personal_fragment"));
        this.castTime = 60;
        this.cooldownTime = 200;
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
        for (int i = 0; i < 360; i += 36) {
            EntityWaterProjectile projectile = new EntityWaterProjectile(caster.world, caster);
            projectile.shoot(caster, 0, i, -1.5F, 2F, 0F);
            if (!caster.world.isRemote)
                caster.world.spawnEntity(projectile);
        }
        super.cast(caster);
    }

    @Override
    public void price(EntityPlayer caster) {
        caster.addExperienceLevel(-2);
    }
}
