package com.miskatonicmysteries.common.misc.spells;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.spells.SpellKnowledge;
import com.miskatonicmysteries.registry.ModPotions;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;

public class SpellDefyDeath extends Spell{
    public SpellDefyDeath() {
        super(new ResourceLocation(MiskatonicMysteries.MODID, "defy_death"));
        this.castTime = 600;
    }

    @Override
    public boolean check(EntityPlayer caster) {
        //Requires Max health and hunger in order to survive the cast
        if (caster.getHealth() >= 20.0f && caster.getFoodStats().getFoodLevel() >= 20) {
            return super.check(caster);
        }
        return false;
    }

    @Override
    public void whileCasting(EntityPlayer caster) {
        //Deals 20 damage over 30 seconds
        if (caster.ticksExisted % 30 == 0) {
            caster.attackEntityFrom(MiskatonicMysteries.VORE, 1);
            caster.world.playSound(null, caster.getPosition(), SoundEvents.BLOCK_PORTAL_TRAVEL, SoundCategory.PLAYERS, 1, 0.8F);
        }
        super.whileCasting(caster);
    }

    @Override
    public void cast(EntityPlayer caster) {
        caster.world.playSound(null, caster.getPosition(), SoundEvents.BLOCK_PORTAL_TRIGGER, SoundCategory.PLAYERS, 1, 0.8F);
        caster.addPotionEffect(new PotionEffect(ModPotions.defy_death, 20*60*30, 0));
    }

    @Override
    public void price(EntityPlayer caster) {
        caster.inventory.clearMatchingItems(Items.GOLDEN_APPLE, 0, 1, null);
        caster.inventory.clearMatchingItems(Items.ROTTEN_FLESH, 0, 16, null);
        caster.addExperienceLevel(-10);

    }

    @Override
    public void onCancelled(EntityPlayer caster){
        caster.onKillCommand();
        SpellKnowledge.Util.setCooldownFor(this, getCooldownTime(), caster, false);
    }

}
