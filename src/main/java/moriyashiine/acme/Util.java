package moriyashiine.acme;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class Util {
	public static <T extends Block> T create(Item item,  T block, String name)
	{
		block.setRegistryName(name);
		block.setTranslationKey(ACME.MODID + "." + name);
		block.setCreativeTab(ACME.tab);
		if (item != null)
		{
			block.setRegistryName(name);
			block.setTranslationKey(ACME.MODID + "." + name);
			ACME.proxy.registerTexture(item);
			ForgeRegistries.ITEMS.register(item);
		}
		return block;
	}
	
	public static <T extends Item> T create(T item, String name) {
		item.setRegistryName(name);
		item.setTranslationKey(ACME.MODID + "." + name);
		item.setCreativeTab(ACME.tab);
		return item;
	}
	
	public static Item create(String name)
	{
		return create(new Item(), name);
	}
}