package com.miskatonicmysteries.common.handler;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.ModConfig;
import com.miskatonicmysteries.common.world.ExtendedWorld;
import com.miskatonicmysteries.common.world.biome.GreatOldOneArea;
import com.miskatonicmysteries.common.world.gen.BiomeManipulator;
import com.miskatonicmysteries.util.ColorUtil;
import net.minecraft.block.BlockGrass;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.event.terraingen.BiomeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

@Mod.EventBusSubscriber(modid = MiskatonicMysteries.MODID)
public class WorldHandler {
    @SubscribeEvent
    public static void updateOverrides(TickEvent.WorldTickEvent event){
        if (event.world.rand.nextBoolean() && !event.world.isRemote && event.world.rand.nextInt(ModConfig.EntitySettings.goos.greatOldOneManipulationInterval) == 0 && !ExtendedWorld.get(event.world).GOO_AREAS.isEmpty()){
            BiomeManipulator.resetRandomOverridenBiome(event.world);
        }
    }
}
