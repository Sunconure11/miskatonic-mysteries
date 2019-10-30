package com.miskatonicmysteries.common.handler.effects;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.sanity.ISanity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class InsanityEffectStrength extends InsanityEffect {
    public InsanityEffectStrength() {
        super(new ResourceLocation(MiskatonicMysteries.MODID, "strength"), 0.005F, 70, EnumTrigger.HIT);
    }

    @Override
    public float getChance(World world, EntityPlayer player, ISanity sanity) {
        return super.getChance(world, player, sanity) * ((1 - player.getHealth() / player.getMaxHealth()) * 2);
    }

    @Override
    public boolean handle(World world, EntityPlayer player, ISanity sanity) {
        player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 400, 1));
        return true;
    }
}
