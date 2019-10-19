package com.miskatonicmysteries.registry;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.block.*;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.item.armor.ItemHasturArmor;
import com.miskatonicmysteries.common.item.armor.ItemShubniggurathArmor;
import com.miskatonicmysteries.common.item.consumable.ItemMilkGoat;
import com.miskatonicmysteries.common.item.consumable.ItemTranquilizer;
import com.miskatonicmysteries.common.item.misc.ItemChalk;
import com.miskatonicmysteries.common.item.misc.ItemMMBook;
import com.miskatonicmysteries.common.item.tool.ItemBlackGoatsGuttingDagger;
import com.miskatonicmysteries.common.item.tool.ItemBlackGoatsHornedDagger;
import com.miskatonicmysteries.common.item.tool.ItemYellowKingsDagger;
import com.miskatonicmysteries.common.misc.rites.focus.RiteFocus;
import com.miskatonicmysteries.util.Util;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.tileentity.BannerPattern;
import net.minecraftforge.common.util.EnumHelper;

public class ModObjects {
    //todo, add water, but as a finite fluid used in Tide Wave (Spell)
    public static ItemArmor.ArmorMaterial ARMOR_HASTUR = EnumHelper.addArmorMaterial("hastur_cultist", MiskatonicMysteries.MODID + ":hastur_cultist", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);
    public static ItemArmor.ArmorMaterial ARMOR_SHUBNIGGURATH = EnumHelper.addArmorMaterial("shubniggurath_cultist", MiskatonicMysteries.MODID + ":shubniggurath_cultist", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);

    public static Item.ToolMaterial TOOL_CULTIST = EnumHelper.addToolMaterial("cultist", 2, 250, 6, 1.5f, 14);

    public static BlockMural stone_cthulhu_mural = Util.create(new BlockMural(Material.ROCK, Blessing.CTHULHU), SoundType.STONE, 1.5f, 30, "pickaxe", 0, "stone_cthulhu_mural");
    public static BlockMural moss_stone_cthulhu_mural = Util.create(new BlockMural(Material.ROCK, Blessing.CTHULHU), SoundType.STONE, 1.5f, 30, "pickaxe", 0, "moss_stone_cthulhu_mural");
    public static BlockMural prismarine_cthulhu_mural = Util.create(new BlockMural(Material.ROCK, Blessing.CTHULHU), SoundType.STONE, 1.5f, 30, "pickaxe", 0, "prismarine_cthulhu_mural");

    public static BlockMural stone_hastur_mural = Util.create(new BlockMural(Material.ROCK, Blessing.HASTUR), SoundType.STONE, 1.5f, 30, "pickaxe", 0, "stone_hastur_mural");
    public static BlockMural moss_stone_hastur_mural = Util.create(new BlockMural(Material.ROCK, Blessing.HASTUR), SoundType.STONE, 1.5f, 30, "pickaxe", 0, "moss_stone_hastur_mural");
    public static BlockMural terracotta_hastur_mural = Util.create(new BlockMural(Material.ROCK, Blessing.HASTUR), SoundType.STONE, 1.25f, 21, "pickaxe", 0, "terracotta_hastur_mural");
    public static BlockMural yellow_terracotta_hastur_mural = Util.create(new BlockMural(Material.ROCK, Blessing.HASTUR), SoundType.STONE, 1.25f, 21, "pickaxe", 0, "yellow_terracotta_hastur_mural");

    public static BlockMural stone_shubniggurath_mural = Util.create(new BlockMural(Material.ROCK, Blessing.SHUB), SoundType.STONE, 1.5f, 30, "pickaxe", 0, "stone_shubniggurath_mural");
    public static BlockMural moss_stone_shubniggurath_mural = Util.create(new BlockMural(Material.ROCK, Blessing.SHUB), SoundType.STONE, 1.5f, 30, "pickaxe", 0, "moss_stone_shubniggurath_mural");

    public static BlockCandles candles = Util.create(new BlockCandles(), SoundType.CLOTH, 0F, 0.2F, "none", 0, "candles");

    public static BlockAltar altar_stone = Util.create(new BlockAltar(), SoundType.STONE, 1.5F, 30, "pickaxe", 0,"altar_stone");
    public static BlockAltar altar_stone_mossy = Util.create(new BlockAltar(), SoundType.STONE, 1.5F, 30, "pickaxe", 0,"altar_stone_mossy");
    public static BlockAltar altar_netherbrick = Util.create(new BlockAltar(), SoundType.STONE, 1.5F, 30, "pickaxe", 0,"altar_netherbrick");
    public static BlockAltar altar_prismarine = Util.create(new BlockAltar(), SoundType.STONE, 1.5F, 30, "pickaxe", 0,"altar_prismarine");
    public static BlockAltar altar_sandstone = Util.create(new BlockAltar(), SoundType.STONE, 1.5F, 30, "pickaxe", 0,"altar_sandstone");
    public static BlockAltar altar_purpur = Util.create(new BlockAltar(), SoundType.STONE, 1.5F, 30, "pickaxe", 0,"altar_purpur");

    public static BlockAltar altar_coral_dead = Util.create(new BlockAltar(), SoundType.STONE, 1.5F, 30, "pickaxe", 0,"altar_coral_dead");

    public static BlockAltar altar_stone_enriched = Util.create(new BlockAltar(), SoundType.STONE, 1.5F, 30, "pickaxe", 0,"altar_stone_enriched");
    public static BlockAltar altar_brick_scorned = Util.create(new BlockAltar(), SoundType.STONE, 1.5F, 30, "pickaxe", 0,"altar_brick_scorned");

    public static BlockOctagram octagram_shub = Util.create(null, new BlockOctagram(Blessing.SHUB), SoundType.STONE, 3, 20, "shovel", 0, "octagram_shub");
    public static BlockOctagram octagram_hastur = Util.create(null, new BlockOctagram(Blessing.HASTUR), SoundType.STONE, 3, 20, "shovel", 0, "octagram_hastur");

    public static BlockYellowSign yellow_sign = Util.create(new BlockYellowSign(), SoundType.STONE, 3, 20, "shovel", 0, "yellow_sign");


    //ah, yes, the three physical states: block, fluid and item

    public static BlockFluidMMWater block_water_mm = new BlockFluidMMWater();

    public static ItemChalk chalk_goat = Util.create(new ItemChalk(octagram_shub), "chalk_goat");
    public static ItemChalk chalk_yellowking = Util.create(new ItemChalk(octagram_hastur), "chalk_yellowking");

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

    public static Item tallow = Util.create(new Item(), "tallow");

    public static Item milk_black_goat = Util.create(new ItemMilkGoat(), "milk_black_goat");

    public static Item gold_oceanic = Util.create("gold_oceanic");

    public static Item necronomicon = Util.create(new ItemMMBook("necronomicon", Blessing.NONE), "necronomicon");

    public static BannerPattern YELLOW_SIGN_PATTERN = null;
    public static void addBanners(){
        addPattern("yellow_sign", "yls", new ItemStack(ModObjects.yellow_sign)); //yellow sign instead
    }

    private static void addPattern(String name, String id, ItemStack craftingItem) {
        name = MiskatonicMysteries.MODID + "_" + name;
        id = "miskmyst_" + id;
        YELLOW_SIGN_PATTERN = EnumHelper.addEnum(BannerPattern.class, name.toUpperCase(), new Class[] { String.class, String.class, ItemStack.class }, name, id, craftingItem);
    }

    public static void addFoci(){
        new RiteFocus(gold_oceanic, 0.2F, 75);
        new RiteFocus(Items.DIAMOND, 0.2F, 75);
        new RiteFocus(Items.EMERALD, 0.2F, 75);
        new RiteFocus(Items.NETHER_STAR, 0.2F, 75);
        //heart of the sea and compat stuff
        new RiteFocus(yellow_kings_dagger, 0.2F, 75).addAlternatePredicate(s -> s.getItem() instanceof ItemSword && ((ItemSword) s.getItem()).getToolMaterialName().equals(TOOL_CULTIST.name()));
        new RiteFocus(Items.ENCHANTED_BOOK, 0.2F, 75).addAlternatePredicate(s -> (s.getItem() instanceof ItemTool || s.getItem() instanceof ItemSword) && !EnchantmentHelper.getEnchantments(s).isEmpty());
        //add more foci... probably change the way foci work... (basically, they'll check an oreDict entry instead, and the values may be set to depend on other factors (give ItemStack as parameter in this case)
    }
}