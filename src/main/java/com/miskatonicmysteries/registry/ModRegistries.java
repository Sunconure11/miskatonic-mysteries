package com.miskatonicmysteries.registry;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.block.tile.TileEntityOctagram;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.misc.rites.*;
import com.miskatonicmysteries.common.misc.rites.effect.*;
import com.miskatonicmysteries.common.misc.spells.*;
import com.miskatonicmysteries.util.InventoryUtil;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ModRegistries {
    public static Map<ResourceLocation, OctagramRite> RITES = new ConcurrentHashMap<>();
    public static final Map<ResourceLocation, RiteEffect> RITE_EFFECTS = new ConcurrentHashMap<>();

    public static Spell REGENERATION;
    public static Spell FEAST;

  //  public static Spell YELLOW_SIGN;
 //   public static Spell TIDE_WAVE;
 //   public static Spell DEFY_DEATH;


    public static OctagramRite MANIACS_MEETING_GOAT;
    public static OctagramRite MANIACS_MEETING_YELLOW_KING;

    public static OctagramRite ELDRITCH_TRAP;
    public static OctagramRite HELLFIRE_ELDRITCH_TRAP;
    public static OctagramRite HYSTERIA;
    public static OctagramRite BLANK_SLATE;

    public static OctagramRite LEARN_SPELL_REGENERATION;
    public static OctagramRite LEARN_SPELL_FEAST;

    public static void init(){
        initSpells();
        initOctagramTypes();
        initRites();
        initRiteEffects();
        initRecipes();
    }

    private static void initOctagramTypes() {
        MiskatonicMysteries.proxy.addOctagramTexture(Blessing.SHUB, new ResourceLocation(MiskatonicMysteries.MODID, "textures/blocks/octagram_shub.png"));
        MiskatonicMysteries.proxy.addOctagramTexture(Blessing.HASTUR, new ResourceLocation(MiskatonicMysteries.MODID, "textures/blocks/octagram_hastur.png"));

    }
    private static void initRites() {
        MANIACS_MEETING_GOAT = new RiteManiacsMeeting(Blessing.SHUB, Ingredient.fromItem(ModObjects.flesh_dark_young), Ingredient.fromItem(Items.EMERALD), Ingredient.fromItem(ModObjects.gold_oceanic), Ingredient.fromItem(ModObjects.blotter), Ingredient.fromItems(ModObjects.black_goats_gutting_dagger, ModObjects.black_goats_horned_dagger), Ingredient.fromItems(ModObjects.shubniggurath_cultist_mask, ModObjects.shubniggurath_cultist_hoodmask), Ingredient.fromStacks(new ItemStack(Blocks.WOOL, 1, 15)), Ingredient.fromStacks(new ItemStack(Blocks.WOOL, 1, 15)));
        MANIACS_MEETING_YELLOW_KING = new RiteManiacsMeeting(Blessing.HASTUR, Ingredient.fromStacks(new ItemStack(Blocks.GOLD_BLOCK)), Ingredient.fromItem(Items.EMERALD), Ingredient.fromItem(ModObjects.gold_oceanic), Ingredient.fromItem(ModObjects.blotter), Ingredient.fromItems(ModObjects.yellow_kings_dagger), Ingredient.fromItems(ModObjects.hastur_cultist_hood), Ingredient.fromStacks(new ItemStack(Blocks.WOOL, 1, 4)), Ingredient.fromStacks(new ItemStack(Blocks.WOOL, 1, 4)));

        ELDRITCH_TRAP = new RiteEldritchTrap();
        HELLFIRE_ELDRITCH_TRAP = new RiteEldritchTrapHellfire();
        HYSTERIA = new RiteHysteria();
        BLANK_SLATE = new RiteBlankSlate();
        LEARN_SPELL_REGENERATION = new RiteLearnSpell(REGENERATION, 100, 40, 200, Blessing.NONE, Blessing.NONE, Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.REGENERATION)), Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.REGENERATION)), Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.HEALING)), Ingredient.fromItem(Items.GOLDEN_APPLE), Ingredient.fromItem(Items.GOLDEN_APPLE), Ingredient.fromItem(Items.GHAST_TEAR), Ingredient.fromItem(Items.GHAST_TEAR));
        LEARN_SPELL_FEAST = new RiteLearnSpell(FEAST, 100, 40, 200, Blessing.NONE, Blessing.NONE, Ingredient.fromStacks(OreDictionary.getOres("meatRaw").toArray(new ItemStack[]{})), Ingredient.fromStacks(OreDictionary.getOres("meatRaw").toArray(new ItemStack[]{})), Ingredient.fromItem(Items.GOLDEN_APPLE), Ingredient.fromItem(Items.GOLDEN_APPLE), Ingredient.fromStacks(new ItemStack(Items.CAKE)), Ingredient.fromStacks(new ItemStack(Items.CAKE)));
    }

    private static void initRiteEffects() {
        new RiteEffectOverload(new ResourceLocation(MiskatonicMysteries.MODID, "rite_effect_overload_basic"), 0);
        new RiteEffectYellowSign();
        new RiteEffectPush();
        new RiteEffectWeakness();
    }

    private static void initSpells() {
        REGENERATION = new SpellHeal();
        FEAST = new SpellFeast();
        //YELLOW_SIGN = new SpellYellowSign();
        //  TIDE_WAVE = new SpellTideWave();
        // DEFY_DEATH = new SpellDefyDeath();
    }

    private static void initRecipes() {

    }

    public static class Util {

        @Nullable
        public static RiteEffect getRandomEffect(TileEntityOctagram octagram, int attempts, RiteEffect.EnumTrigger trigger) {
            List<RiteEffect> effects = getAvailableEffects(octagram, trigger);
            Collections.shuffle(effects);
            int i = 0;
            for (RiteEffect effect : effects) {
                if (i >= attempts) break;
                if (octagram.getWorld().rand.nextFloat() < effect.getBaseChance(octagram) && effect.test(octagram, trigger)) {
                    return effect;
                }
                i++;
            }
            return null;
        }

        public static List<RiteEffect> getRiteEffects() {
            return new ArrayList<>(RITE_EFFECTS.values());
        }

        public static List<RiteEffect> getAvailableEffects(TileEntityOctagram octagram, RiteEffect.EnumTrigger trigger) {
            return getRiteEffects().stream().filter(e -> isEffectAvailable(e, octagram) && e.getTrigger(octagram) == trigger).collect(Collectors.toList());
        }

        public static boolean isEffectAvailable(RiteEffect effect, TileEntityOctagram octagram) {
            return octagram.instability >= effect.getInstabilityLevel(octagram);
        }

        public static OctagramRite getRite(TileEntityOctagram octagram) {
            return RITES.values().stream().filter(r -> InventoryUtil.areItemStackListsEqual(r.ingredients, octagram.inventory)).findFirst().orElse(null);
        }
    }
}
