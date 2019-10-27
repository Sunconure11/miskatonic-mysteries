package com.miskatonicmysteries.registry;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.block.tile.TileEntityOctagram;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.misc.rites.OctagramRite;
import com.miskatonicmysteries.common.misc.rites.RiteEldritchTrap;
import com.miskatonicmysteries.common.misc.rites.RiteManiacsMeeting;
import com.miskatonicmysteries.common.misc.rites.effect.*;
import com.miskatonicmysteries.common.misc.spells.*;
import com.miskatonicmysteries.util.InventoryUtil;
import net.minecraft.init.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

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

    public static Spell HEAL;
    public static Spell FEAST;
    public static Spell YELLOW_SIGN;
    public static Spell TIDE_WAVE;


    public static OctagramRite MANIACS_MEETING_GOAT;
    public static OctagramRite ELDRITCH_TRAP;


    public static void init(){
        initRites();
        initRiteEffects();
        initSpells();

        initRecipes();
    }

    private static void initRites() {
        MANIACS_MEETING_GOAT = new RiteManiacsMeeting(Blessing.SHUB, Ingredient.fromItem(Items.DIAMOND), Ingredient.fromItem(Items.EMERALD), Ingredient.fromItem(Items.DIAMOND), Ingredient.fromItem(Items.DIAMOND), Ingredient.fromItem(Items.DIAMOND), Ingredient.fromItem(Items.DIAMOND), Ingredient.fromItem(Items.DIAMOND), Ingredient.fromItem(Items.DIAMOND));
        ELDRITCH_TRAP = new RiteEldritchTrap();
    }

    private static void initRiteEffects() {
        new RiteEffectOverload(new ResourceLocation(MiskatonicMysteries.MODID, "rite_effect_overload_basic"), 0);
        new RiteEffectYellowSign();
        new RiteEffectPush();
        new RiteEffectWeakness();
    }

    private static void initSpells() {
        HEAL = new SpellHeal();
        FEAST = new SpellFeast();
        YELLOW_SIGN = new SpellYellowSign();
        TIDE_WAVE = new SpellTideWave();
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
