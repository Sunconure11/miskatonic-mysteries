package com.miskatonicmysteries.common.handler;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.ModConfig;
import com.miskatonicmysteries.common.entity.goo.AbstractOldOne;
import com.miskatonicmysteries.common.world.ExtendedWorld;
import com.miskatonicmysteries.common.world.gen.BiomeManipulator;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid = MiskatonicMysteries.MODID)
public class WorldHandler {
    @SubscribeEvent
    public static void updateOverrides(TickEvent.WorldTickEvent event){
        if (!event.world.isRemote && event.world.getTotalWorldTime() % ModConfig.entities.goos.greatOldOneManipulationInterval * 2 == 0 && !ExtendedWorld.get(event.world).STORED_OVERRIDE_BIOMES.isEmpty()){
            BiomeManipulator.resetRandomOverriddenBiome(event.world);
        }
    }
}
