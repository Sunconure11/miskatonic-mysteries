package com.miskatonicmysteries.common.handler.effects;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.sanity.ISanity;
import com.miskatonicmysteries.registry.ModPotions;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class InsanityEffectHunger extends InsanityEffect {
    public InsanityEffectHunger() {
        super(new ResourceLocation(MiskatonicMysteries.MODID, "hunger"), 0.1F, 90);
    }

    @Override
    public float getChance(World world, EntityPlayer player, ISanity sanity) {
        if (player.getActivePotionEffect(ModPotions.hunger_exotic) != null)
            return -1;
        return super.getChance(world, player, sanity) * (player.getFoodStats().needFood() ? 1 : 2);
    }

    @Override
    public boolean handle(World world, EntityPlayer player, ISanity sanity) {
        player.addPotionEffect(new PotionEffect(ModPotions.hunger_exotic, 4000, 0));
        if (world.isRemote)
            player.world.playSound(player, player.getPosition(), SoundEvents.ENTITY_ELDER_GUARDIAN_CURSE, SoundCategory.AMBIENT, 0.3F, 0.3F);
        return true;
    }
}
