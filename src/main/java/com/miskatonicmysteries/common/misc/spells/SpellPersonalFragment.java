package com.miskatonicmysteries.common.misc.spells;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.blessing.BlessingCapability;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.capability.sanity.Sanity;
import com.miskatonicmysteries.common.entity.projectile.EntityPersonalProjectile;
import com.miskatonicmysteries.common.entity.projectile.EntityWaterProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

public class SpellPersonalFragment extends Spell {
    public SpellPersonalFragment() {
        super(new ResourceLocation(MiskatonicMysteries.MODID, "personal_fragment"));
        this.castTime = 60;
        this.cooldownTime = 200;
    }

    @Override
    public boolean check(EntityPlayer caster) {
        return true;
    }

    @Override
    public void cast(EntityPlayer caster) {
        EntityPersonalProjectile projectile = new EntityPersonalProjectile(caster.world, caster);
        projectile.shoot(caster, caster.rotationPitch, caster.rotationYawHead, 0, 1F, 0F);
        float damageFactor = 9 * (1 - Sanity.Util.getSanity(caster) / (float) Sanity.SANITY_MAX);
        projectile.setDamage(3 + damageFactor);
        if (!caster.world.isRemote)
            caster.world.spawnEntity(projectile);
        super.cast(caster);
    }

    @Override
    public void price(EntityPlayer caster) {
        caster.attackEntityFrom(DamageSource.OUT_OF_WORLD, 2);
    }
}
