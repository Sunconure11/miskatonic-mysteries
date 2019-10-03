package com.miskatonicmysteries.util;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.block.tile.BlockTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@SuppressWarnings("WeakerAccess")
public class Util {
	public static <T extends Block> T create(Item item, T block, SoundType sound, float hardness, float resistance, String tool, int level, String name) {
		block.setRegistryName(name);
		block.setUnlocalizedName(name);
		block.setCreativeTab(MiskatonicMysteries.tab);
		ObfuscationReflectionHelper.setPrivateValue(Block.class, block, sound, "blockSoundType", "field_149762_H");
		block.setHardness(hardness);
		block.setResistance(resistance);
		if (!tool.equals("none"))
			block.setHarvestLevel(tool, level);
		if (item != null) {
			item.setRegistryName(name);
			item.setUnlocalizedName(name);
			MiskatonicMysteries.proxy.registerTexture(item);
			ForgeRegistries.ITEMS.register(item);
		}

		if (block instanceof BlockTileEntity){
			GameRegistry.registerTileEntity(((BlockTileEntity) block).getTileEntityClass(), block.getRegistryName());
		}
		return block;
	}
	
	public static <T extends Block> T create(T block, SoundType sound, float hardness, float resistance, String tool, int level, String name) {
		return create(new ItemBlock(block), block, sound, hardness, resistance, tool, level, name);
	}
	
	public static <T extends Item> T create(T item, String name) {
		item.setRegistryName(name);
		item.setUnlocalizedName(name);
		item.setCreativeTab(MiskatonicMysteries.tab);
		return item;
	}
	
	public static Item create(String name) {
		return create(new Item(), name);
	}
	
	public static int getArmorPieces(EntityLivingBase living, ItemArmor.ArmorMaterial mat) {
		int fin = 0;
		for (ItemStack stack : living.getArmorInventoryList()) if (stack.getItem() instanceof ItemArmor && ((ItemArmor) stack.getItem()).getArmorMaterial() == mat) fin++;
		return fin;
	}
	
	public static void giveAndConsumeItem(EntityPlayer player, EnumHand hand, ItemStack stack) {
		if (!player.isCreative()) player.getHeldItem(hand).shrink(1);
		if (player.getHeldItem(hand).isEmpty()) player.setHeldItem(hand, stack);
		else giveItem(player, stack);
	}
	
	public static void giveItem(EntityPlayer player, ItemStack stack) {
		if (!player.inventory.addItemStackToInventory(stack)) player.dropItem(stack, false);
	}

	public static List<?> iterableToShuffledList(Iterable<?> iterable){
		List<?> list = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
		Collections.shuffle(list);
		return list;
	}
}