package com.miskatonicmysteries.common.potion;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class PotionDefyDeath extends ModPotion {
    public PotionDefyDeath() {
        super("defy_death", false, 3093071);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onDeath(LivingDamageEvent event){
        if (event.getEntityLiving().getActivePotionEffect(this) != null && event.getEntityLiving().getHealth() - event.getAmount() <= 0.0f && !(event.getSource().getDamageType() == "magic" || event.getSource().getDamageType() == "outOfWorld")){
            event.getEntityLiving().setHealth(10.0f);
            event.setAmount(0.0f);
        }
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return new ArrayList<>();
    }
}
