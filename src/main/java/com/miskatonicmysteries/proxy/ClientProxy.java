package com.miskatonicmysteries.proxy;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.client.render.ParticleRenderHandler;
import com.miskatonicmysteries.client.render.tile.RenderAltar;
import com.miskatonicmysteries.client.render.RenderManipulatorHandler;
import com.miskatonicmysteries.client.render.tile.RenderOctagram;
import com.miskatonicmysteries.client.render.shaders.ShaderHandler;
import com.miskatonicmysteries.common.block.tile.TileEntityAltar;
import com.miskatonicmysteries.common.block.tile.TileEntityOctagram;
import com.miskatonicmysteries.registry.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
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
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class ClientProxy extends ServerProxy {
    public static KeyBinding SPELL_UP;
    public static KeyBinding SPELL_DOWN;
    public static KeyBinding SPELL_CANCEL;

    public void preInit(FMLPreInitializationEvent event){
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAltar.class, new RenderAltar());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOctagram.class, new RenderOctagram());

        ModEntities.registerRenderers();
        registerKeybinds();
    }

    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ShaderHandler());
        MinecraftForge.EVENT_BUS.register(new RenderManipulatorHandler());
    }

    @Override
    public void registerTexture(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(MiskatonicMysteries.MODID + ":" + id, "inventory"));
    }

    @Override
    public EntityPlayer getPlayer(final MessageContext context) {
        if (context.side.isClient()) {
            return Minecraft.getMinecraft().player;
        } else {
            return context.getServerHandler().player;
        }
    }

    @Override
    public void generateParticle(Particle particle) {
        ParticleRenderHandler.spawnParticle(() -> particle);
    }

    public static void registerKeybinds() {
        SPELL_UP = new KeyBinding("key.miskmyst_up", Keyboard.KEY_UP, "category.miskatonicmysteries");
        SPELL_DOWN = new KeyBinding("key.miskmyst_down", Keyboard.KEY_DOWN, "category.miskatonicmysteries");
        SPELL_CANCEL = new KeyBinding("key.miskmyst_cancel", Keyboard.KEY_RIGHT, "category.miskatonicmysteries");
        ClientRegistry.registerKeyBinding(SPELL_UP);
        ClientRegistry.registerKeyBinding(SPELL_DOWN);
        ClientRegistry.registerKeyBinding(SPELL_CANCEL);
    }
}