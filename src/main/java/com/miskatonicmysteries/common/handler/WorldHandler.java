package com.miskatonicmysteries.common.handler;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.ModConfig;
import com.miskatonicmysteries.common.entity.IIgnoreMaterials;
import com.miskatonicmysteries.common.world.ExtendedWorld;
import com.miskatonicmysteries.common.world.gen.BiomeManipulator;
import com.miskatonicmysteries.registry.ModObjects;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityVindicator;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.world.GetCollisionBoxesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid = MiskatonicMysteries.MODID)
public class WorldHandler {
    @SubscribeEvent
    public static void updateOverrides(TickEvent.WorldTickEvent event){
        if (!event.world.isRemote && event.world.rand.nextInt(ModConfig.EntitySettings.goos.greatOldOneManipulationInterval * 2) == 0 && !ExtendedWorld.get(event.world).overridenBiomes.isEmpty()){
            BiomeManipulator.resetRandomOverridenBiome(event.world);
        }
    }
}
