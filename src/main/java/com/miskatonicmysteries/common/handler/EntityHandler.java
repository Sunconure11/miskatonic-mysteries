package com.miskatonicmysteries.common.handler;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.registry.ModObjects;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityVindicator;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = MiskatonicMysteries.MODID)
public class EntityHandler {
    @SubscribeEvent
    public static void dropTallow(LivingDropsEvent event){
        if (event.getEntityLiving().isBurning()){
            EntityLivingBase living = event.getEntityLiving();
            if (living instanceof EntityPig || living instanceof EntityVillager || living instanceof EntityPlayer || living instanceof EntityVindicator) //or cultist
                event.getDrops().add(new EntityItem(living.world, living.posX, living.posY, living.posZ, new ItemStack(ModObjects.tallow, living.getRNG().nextInt(2 + event.getLootingLevel()))));
        }
    }
}
