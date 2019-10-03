package com.miskatonicmysteries.proxy;

import com.miskatonicmysteries.client.render.ParticleRenderHandler;
import net.minecraft.client.particle.Particle;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy {
	public void preInit(FMLPreInitializationEvent event) {

	}

	public void init(FMLInitializationEvent event){

	}

	public void registerTexture(Item item) {

	}

	public void generateParticle(Particle particle){
		ParticleRenderHandler.spawnParticle(() -> particle);
	}
}