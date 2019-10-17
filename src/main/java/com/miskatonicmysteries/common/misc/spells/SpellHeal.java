package com.miskatonicmysteries.common.misc.spells;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.spells.SpellKnowledge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class SpellHeal extends Spell {
    public SpellHeal() {
        super(new ResourceLocation(MiskatonicMysteries.MODID, "heal"));
        this.castTime = 120;
    }

    @Override
    public boolean check(EntityPlayer caster) {
        if (caster.getHealth() < caster.getMaxHealth()) {
            return super.check(caster);
        }
        return false;
    }

    @Override
    public void cast(EntityPlayer caster) {
        super.cast(caster);
            caster.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 2, false, false));
    }

    @Override
    public void price(EntityPlayer caster) {
            caster.getFoodStats().addStats(-3, -3);
            caster.getFoodStats().addExhaustion(1.5F);
    }
}
