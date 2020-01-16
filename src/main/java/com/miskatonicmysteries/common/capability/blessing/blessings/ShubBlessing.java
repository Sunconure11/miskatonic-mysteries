package com.miskatonicmysteries.common.capability.blessing.blessings;

import com.miskatonicmysteries.registry.ModPotions;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ShubBlessing extends Blessing {
    private static final AttributeModifier GOAT_SPEED = new AttributeModifier("miskmyst_goatlegs", 0.04, 0);

    public ShubBlessing() {
        super("shub");
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void onAdded(EntityPlayer player) {
        if (player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).hasModifier(GOAT_SPEED))
            player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(GOAT_SPEED);
        super.onAdded(player);
    }

    @Override
    public void onRemoved(EntityPlayer player) {
        if (player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).hasModifier(GOAT_SPEED))
            player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(GOAT_SPEED);
        player.stepHeight = 0.5F;

        super.onRemoved(player);
    }

    @SubscribeEvent
    public void onJump(LivingEvent.LivingJumpEvent event) {
        if (event.getEntityLiving() instanceof EntityPlayer && hasBlessing((EntityPlayer)event.getEntityLiving())){
            ((EntityPlayer) event.getEntityLiving()).motionY *= 1.5;
        }
    }

    @SubscribeEvent
    public void onUpdate(LivingEvent.LivingUpdateEvent event) {
        if (event.getEntityLiving() instanceof EntityPlayer && hasBlessing((EntityPlayer)event.getEntityLiving())){
            ((EntityPlayer) event.getEntityLiving()).fallDistance *= 0.7F;
            ((EntityPlayer) event.getEntityLiving()).stepHeight = 1.25F;
        }
    }
}
