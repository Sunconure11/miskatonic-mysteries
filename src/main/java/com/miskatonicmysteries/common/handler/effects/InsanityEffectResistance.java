package com.miskatonicmysteries.common.handler.effects;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.sanity.ISanity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class InsanityEffectResistance extends InsanityEffect {
    public InsanityEffectResistance() {
        super(new ResourceLocation(MiskatonicMysteries.MODID, "resistance"), 0.005F, 70, EnumTrigger.HIT);
    }

    @Override
    public float getChance(World world, EntityPlayer player, ISanity sanity) {
        return super.getChance(world, player, sanity) * (player.getHealth() <= 6 ? 30 : (1 - player.getHealth() / player.getMaxHealth()) * 2);
    }

    @Override
    public boolean handle(World world, EntityPlayer player, ISanity sanity) {
        player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 1000, 1));
        player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 1));
        return true;
    }
}
