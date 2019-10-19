package com.miskatonicmysteries.common.handler.effects;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.sanity.ISanity;
import com.miskatonicmysteries.common.capability.sanity.Sanity;
import com.miskatonicmysteries.registry.ModInsanityEffects;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class InsanityEffectSounds extends InsanityEffect {
    public InsanityEffectSounds() {
        super(new ResourceLocation(MiskatonicMysteries.MODID, "sounds"), 0.6F, 130);
    }

    @Override
    public float getChance(World world, EntityPlayer player, ISanity sanity) {
        return (float) Math.max(super.getChance(world, player, sanity) * ModInsanityEffects.getExponentialSanityFactor(sanity), 0.2);
    }

    @Override
    public boolean handle(World world, EntityPlayer player, ISanity sanity) {
        if (player.world.isRemote){
            SoundEvent sound = SoundEvents.ENTITY_CREEPER_PRIMED;
            float pitch = 0.7F;
            float volume = 1F;
            int rng = player.getRNG().nextInt(80);

            if (rng == 0){
                sound = SoundEvents.RECORD_13;
                pitch = 0.5F;
                volume = 0.7F;
            }else if (rng <= 10){
                sound = SoundEvents.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD;
            }else if (rng <= 20){
                sound = SoundEvents.ENTITY_ZOMBIE_AMBIENT;
            }else if (rng <= 25){
                sound = SoundEvents.ENTITY_SKELETON_DEATH;
            }else if (rng <= 60){
                sound = SoundEvents.AMBIENT_CAVE;
                if (rng <= 50) {
                    pitch = 0.3F;
                }else
                if (rng <= 40) {
                    pitch = 1.3F;
                }
            }else if (rng <= 75){
                sound = SoundEvents.BLOCK_CHORUS_FLOWER_DEATH;
                pitch = 0.3F;
                volume = 0.5F;
            }
            player.world.playSound(player, player.getPosition(), sound, SoundCategory.AMBIENT, volume, pitch);
            return true;
        }
        return false;
    }
}
