package com.miskatonicmysteries.proxy;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.client.render.ParticleRenderHandler;
import com.miskatonicmysteries.client.render.RenderAltar;
import com.miskatonicmysteries.client.render.RenderManipulatorHandler;
import com.miskatonicmysteries.client.render.RenderOctagram;
import com.miskatonicmysteries.client.render.shaders.ShaderHandler;
import com.miskatonicmysteries.common.block.tile.TileEntityAltar;
import com.miskatonicmysteries.common.block.tile.TileEntityOctagram;
import com.miskatonicmysteries.registry.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends ServerProxy {

    public void preInit(FMLPreInitializationEvent event){
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAltar.class, new RenderAltar());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOctagram.class, new RenderOctagram());

        ModEntities.registerRenderers();
    }

    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ShaderHandler());
        MinecraftForge.EVENT_BUS.register(new RenderManipulatorHandler());
    }

    @Override
    public void registerTexture(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(MiskatonicMysteries.MODID + ":" + id, "inventory"));
        //ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(MiskatonicMysteries.MODID, id), "inventory")); //ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), item instanceof ItemBlock ? "normal" : "inventory"));
    }

    public EntityPlayer getPlayer(MessageContext ctx){
        return Minecraft.getMinecraft().player;
    }

    @Override
    public void generateParticle(Particle particle) {
        ParticleRenderHandler.spawnParticle(() -> particle);
    }
}