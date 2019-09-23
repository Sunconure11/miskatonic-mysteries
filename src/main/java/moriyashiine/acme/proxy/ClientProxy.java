package moriyashiine.acme.proxy;

import moriyashiine.acme.client.render.RenderManipulatorHandler;
import moriyashiine.acme.client.render.shaders.ShaderHandler;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;

@SideOnly(Side.CLIENT)
public class ClientProxy extends ServerProxy {

	public void init(FMLInitializationEvent event){
		MinecraftForge.EVENT_BUS.register(new ShaderHandler());
		MinecraftForge.EVENT_BUS.register(new RenderManipulatorHandler());
	}
	@Override
	public void registerTexture(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), item instanceof ItemBlock ? "normal" : "inventory"));
	}
}