package com.miskatonicmysteries.common.handler;

import com.miskatonicmysteries.common.handler.event.InsanityEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class InsanityHandler {
    @SubscribeEvent
    public void handleInsanity(InsanityEvent insanityEvent){
        int event = insanityEvent.getPlayer().world.rand.nextInt(Math.abs(120 - insanityEvent.getSanity().getSanity()));
        System.out.println(event);
        if (event <= 15){
            SoundEvent sound = SoundEvents.ENTITY_CREEPER_PRIMED;
            float pitch = 0.7F;
            if (event <= 2){
                sound = SoundEvents.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD;
            }else
            if (event <= 4){
                sound = SoundEvents.ENTITY_ZOMBIE_AMBIENT;
            }else
            if (event <= 6){
                sound = SoundEvents.ENTITY_SKELETON_DEATH;
            }else
            if (event <= 8){
                sound = SoundEvents.AMBIENT_CAVE;
            }else
            if (event <= 10){
                sound = SoundEvents.ENTITY_ELDER_GUARDIAN_CURSE;
                pitch = 0.1F;
            }else
            if (event == 13){
                sound = SoundEvents.RECORD_13;
                pitch = 0.3F;
            }
            playParanoiaSound(insanityEvent.getPlayer(), sound, pitch);
        }
    }

    public void playParanoiaSound(EntityPlayer player, SoundEvent sound, float pitch){
        player.world.playSound(player, player.getPosition(), sound, SoundCategory.AMBIENT, 1.0F, pitch);
    }
}
