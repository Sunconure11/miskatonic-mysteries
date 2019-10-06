package com.miskatonicmysteries.proxy;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.client.render.RenderAltar;
import com.miskatonicmysteries.client.render.RenderManipulatorHandler;
import com.miskatonicmysteries.client.render.shaders.ShaderHandler;
import com.miskatonicmysteries.common.block.tile.TileEntityAltar;
import com.miskatonicmysteries.registry.ModEntities;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;

@SideOnly(Side.CLIENT)
public class ClientProxy extends ServerProxy {

    public void preInit(FMLPreInitializationEvent event){
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAltar.class, new RenderAltar());
        ModEntities.registerRenderers();
    }

    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ShaderHandler());
        MinecraftForge.EVENT_BUS.register(new RenderManipulatorHandler());
    }

    @Override
    public void registerTexture(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(MiskatonicMysteries.MODID + ":" + id, "inventory")); //ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), item instanceof ItemBlock ? "normal" : "inventory"));
    }
}