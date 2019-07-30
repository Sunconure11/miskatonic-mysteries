package moriyashiine.acme.registry;

import moriyashiine.acme.Util;
import moriyashiine.acme.common.block.BlockMural;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class ModObjects
{
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
}