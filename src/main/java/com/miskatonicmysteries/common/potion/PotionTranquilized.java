package com.miskatonicmysteries.common.potion;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.sanity.Sanity;
import com.miskatonicmysteries.registry.ModPotions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PotionTranquilized extends ModPotion {
    public PotionTranquilized() {
        super("tranquilized", false, 3093071);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {//remove mania, but only if it's weaker or equally strong
        if (entityLivingBaseIn.getActivePotionEffect(ModPotions.mania) != null && entityLivingBaseIn.getActivePotionEffect(ModPotions.mania).getAmplifier() <= amplifier){
            entityLivingBaseIn.removePotionEffect(ModPotions.mania);
        }
        super.performEffect(entityLivingBaseIn, amplifier);
    }

    @SubscribeEvent
    public void onSleep(PlayerWakeUpEvent event){
        if (event.getEntityPlayer().getActivePotionEffect(this) != null){
            if(event.getEntityPlayer().getHealth() < 6){
                event.getEntityPlayer().attackEntityFrom(MiskatonicMysteries.SLEEP, 20);
            }else {
                Sanity.Util.setSanity(Sanity.Util.getSanity(event.getEntityPlayer()) + 10 + event.getEntityPlayer().getRNG().nextInt(4), event.getEntityPlayer());
            }
            event.getEntityPlayer().removePotionEffect(this);
        }
    }
}
