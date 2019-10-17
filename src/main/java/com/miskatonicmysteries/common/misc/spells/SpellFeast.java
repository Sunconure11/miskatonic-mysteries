package com.miskatonicmysteries.common.misc.spells;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.spells.SpellKnowledge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;

public class SpellFeast extends Spell {
    //nom nom nom my own human flesh
    public SpellFeast() {
        super(new ResourceLocation(MiskatonicMysteries.MODID, "feast"));
        this.castTime = 100;
    }

    @Override
    public boolean check(EntityPlayer caster) {
        if (caster.getFoodStats().needFood()) {
            return super.check(caster);
        }
        return false;
    }

    @Override
    public void whileCasting(EntityPlayer caster) {
            if (caster.ticksExisted % 20 == 0) {
                caster.attackEntityFrom(MiskatonicMysteries.VORE, 1);
                caster.getFoodStats().addStats(2, 1);
                caster.world.playSound(null, caster.getPosition(), SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.PLAYERS, 1, 0.8F);
            }
        super.whileCasting(caster);
    }

    @Override
    public void cast(EntityPlayer caster) {
            caster.getFoodStats().addStats(1, 3);
            caster.world.playSound(null, caster.getPosition(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 1, 0.8F);
    }

    @Override
    public void price(EntityPlayer caster) {
        caster.attackEntityFrom(MiskatonicMysteries.VORE, 2);
    }
}
