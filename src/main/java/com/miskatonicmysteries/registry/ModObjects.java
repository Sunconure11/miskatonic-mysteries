package com.miskatonicmysteries.registry;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.Util;
import com.miskatonicmysteries.common.block.BlockMural;
import com.miskatonicmysteries.common.item.armor.ItemHasturArmor;
import com.miskatonicmysteries.common.item.armor.ItemShubniggurathArmor;
import com.miskatonicmysteries.common.item.consumable.ItemTranquilizer;
import com.miskatonicmysteries.common.item.tool.*;
import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.Util;
import com.miskatonicmysteries.common.block.BlockMural;
import com.miskatonicmysteries.common.item.consumable.ItemTranquilizer;
import com.miskatonicmysteries.common.item.tool.ItemBlackGoatsGuttingDagger;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class ModObjects {
	public static ItemArmor.ArmorMaterial ARMOR_HASTUR = EnumHelper.addArmorMaterial("hastur_cultist", MiskatonicMysteries.MODID + ":hastur_cultist", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);
	public static ItemArmor.ArmorMaterial ARMOR_SHUBNIGGURATH = EnumHelper.addArmorMaterial("shubniggurath_cultist", MiskatonicMysteries.MODID + ":shubniggurath_cultist", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);
	
	public static Item.ToolMaterial TOOL_CULTIST = EnumHelper.addToolMaterial("cultist", 2, 250, 6, 1.5f, 14);
	
	public static BlockMural stone_cthulhu_mural = Util.create(new BlockMural(Material.ROCK), SoundType.STONE, 1.5f, 30, "pickaxe", 0, "stone_cthulhu_mural");
	public static BlockMural moss_stone_cthulhu_mural = Util.create(new BlockMural(Material.ROCK), SoundType.STONE, 1.5f, 30, "pickaxe", 0, "moss_stone_cthulhu_mural");
	public static BlockMural prismarine_cthulhu_mural = Util.create(new BlockMural(Material.ROCK), SoundType.STONE, 1.5f, 30, "pickaxe", 0, "prismarine_cthulhu_mural");
	
	public static BlockMural stone_hastur_mural = Util.create(new BlockMural(Material.ROCK), SoundType.STONE, 1.5f, 30, "pickaxe", 0, "stone_hastur_mural");
	public static BlockMural moss_stone_hastur_mural = Util.create(new BlockMural(Material.ROCK), SoundType.STONE, 1.5f, 30, "pickaxe", 0, "moss_stone_hastur_mural");
	public static BlockMural terracotta_hastur_mural = Util.create(new BlockMural(Material.ROCK), SoundType.STONE, 1.25f, 21, "pickaxe", 0, "terracotta_hastur_mural");
	public static BlockMural yellow_terracotta_hastur_mural = Util.create(new BlockMural(Material.ROCK), SoundType.STONE, 1.25f, 21, "pickaxe", 0, "yellow_terracotta_hastur_mural");
	
	public static BlockMural stone_shubniggurath_mural = Util.create(new BlockMural(Material.ROCK), SoundType.STONE, 1.5f, 30, "pickaxe", 0, "stone_shubniggurath_mural");
	public static BlockMural moss_stone_shubniggurath_mural = Util.create(new BlockMural(Material.ROCK), SoundType.STONE, 1.5f, 30, "pickaxe", 0, "moss_stone_shubniggurath_mural");

	public static Item research_notes_cthulhu = Util.create("research_notes_cthulhu");
	public static Item research_notes_hastur = Util.create("research_notes_hastur");
	public static Item research_notes_shubniggurath = Util.create("research_notes_shubniggurath");
	
	public static Item hastur_cultist_hood = Util.create(new ItemHasturArmor(EntityEquipmentSlot.HEAD), "hastur_cultist_hood");
	public static Item hastur_cultist_robes = Util.create(new ItemHasturArmor(EntityEquipmentSlot.CHEST), "hastur_cultist_robes");
	public static Item hastur_cultist_pants = Util.create(new ItemHasturArmor(EntityEquipmentSlot.LEGS), "hastur_cultist_pants");
	public static Item yellow_kings_dagger = Util.create(new ItemYellowKingsDagger(), "yellow_kings_dagger");
	
	public static Item shubniggurath_cultist_mask = Util.create(new ItemShubniggurathArmor(EntityEquipmentSlot.HEAD), "shubniggurath_cultist_mask");
	public static Item shubniggurath_cultist_hoodmask = Util.create(new ItemShubniggurathArmor(EntityEquipmentSlot.HEAD), "shubniggurath_cultist_hoodmask");
	public static Item shubniggurath_cultist_robes = Util.create(new ItemShubniggurathArmor(EntityEquipmentSlot.CHEST), "shubniggurath_cultist_robes");
	public static Item shubniggurath_cultist_pants = Util.create(new ItemShubniggurathArmor(EntityEquipmentSlot.LEGS), "shubniggurath_cultist_pants");
	
	public static Item black_goats_gutting_dagger = Util.create(new ItemBlackGoatsGuttingDagger(), "black_goats_gutting_dagger");
	public static Item black_goats_horned_dagger = Util.create(new ItemBlackGoatsHornedDagger(), "black_goats_horned_dagger");

	public static Item tranquilizer = Util.create(new ItemTranquilizer(), "tranquilizer");

	public static Item terraform = Util.create(new ItemTerraform(), "item_terraform");
}