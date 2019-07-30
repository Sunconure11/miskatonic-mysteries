package moriyashiine.acme;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class Util {
	public static <T extends Block> T create(Item item, T block, SoundType sound, float hardness, float resistance, String tool, int level, String name)
	{
		block.setRegistryName(name);
		block.setTranslationKey(ACME.MODID + "." + name);
		block.setCreativeTab(ACME.tab);
		ObfuscationReflectionHelper.setPrivateValue(Block.class, block, sound, "blockSoundType", "field_149762_H");
		block.setHardness(hardness);
		block.setResistance(resistance);
		block.setHarvestLevel(tool, level);
		if (item != null)
		{
			item.setRegistryName(name);
			item.setTranslationKey(ACME.MODID + "." + name);
			ACME.proxy.registerTexture(item);
			ForgeRegistries.ITEMS.register(item);
		}
		return block;
	}
	
	public static <T extends Block> T create(T block, SoundType sound, float hardness, float resistance, String tool, int level, String name) {
		return create(new ItemBlock(block), block, sound, hardness, resistance, tool, level, name);
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