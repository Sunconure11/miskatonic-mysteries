package com.miskatonicmysteries.proxy;

import net.minecraft.client.particle.Particle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;


public class ServerProxy {
	public void preInit(FMLPreInitializationEvent event) {

	}

	public void init(FMLInitializationEvent event){

	}

	public void registerTexture(Item item, int meta, String id) {

	}

	public void generateParticle(Particle particle){

	}

	public EntityPlayer getPlayer(MessageContext ctx){
		if (ctx.side.isServer()) {
			return ctx.getServerHandler().player;
		} else {
			throw new RuntimeException("Tried to get the player from a client-side MessageContext on the dedicated server");
		}
	}
}