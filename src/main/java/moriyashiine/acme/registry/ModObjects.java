package moriyashiine.acme.registry;

import moriyashiine.acme.Util;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class ModObjects
{
	public static Block stone_cthulhu_mural = Util.create(new Block(Material.ROCK), SoundType.STONE, 1.5f, 30, "pickaxe", 0, "stone_cthulhu_mural");
	public static Block moss_stone_cthulhu_mural = Util.create(new Block(Material.ROCK), SoundType.STONE, 1.5f, 30, "pickaxe", 0, "moss_stone_cthulhu_mural");
	public static Block prismarine_cthulhu_mural = Util.create(new Block(Material.ROCK), SoundType.STONE, 1.5f, 30, "pickaxe", 0, "prismarine_cthulhu_mural");
	
	public static Block stone_hastur_mural = Util.create(new Block(Material.ROCK), SoundType.STONE, 1.5f, 30, "pickaxe", 0, "stone_hastur_mural");
	public static Block moss_stone_hastur_mural = Util.create(new Block(Material.ROCK), SoundType.STONE, 1.5f, 30, "pickaxe", 0, "moss_stone_hastur_mural");
	public static Block terracotta_hastur_mural = Util.create(new Block(Material.ROCK), SoundType.STONE, 1.25f, 21, "pickaxe", 0, "terracotta_hastur_mural");
	public static Block yellow_terracotta_hastur_mural = Util.create(new Block(Material.ROCK), SoundType.STONE, 1.25f, 21, "pickaxe", 0, "yellow_terracotta_hastur_mural");
	
	public static Block stone_shubniggurath_mural = Util.create(new Block(Material.ROCK), SoundType.STONE, 1.5f, 30, "pickaxe", 0, "stone_shubniggurath_mural");
	public static Block moss_stone_shubniggurath_mural = Util.create(new Block(Material.ROCK), SoundType.STONE, 1.5f, 30, "pickaxe", 0, "moss_stone_shubniggurath_mural");
}