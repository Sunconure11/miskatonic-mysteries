package com.miskatonicmysteries.registry;

import com.miskatonicmysteries.common.block.tile.TileEntityOctagram;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.capability.sanity.ISanity;
import com.miskatonicmysteries.common.handler.effects.InsanityEffect;
import com.miskatonicmysteries.common.misc.rites.OctagramRite;
import com.miskatonicmysteries.common.misc.rites.RiteEldritchTrap;
import com.miskatonicmysteries.common.misc.rites.RiteManiacsMeeting;
import com.miskatonicmysteries.common.misc.rites.effect.RiteEffect;
import com.miskatonicmysteries.common.misc.spells.*;
import com.miskatonicmysteries.util.InventoryUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class ModRegistries {
    public static Map<ResourceLocation, OctagramRite> RITES = new ConcurrentHashMap<>();
    public static final Map<ResourceLocation, RiteEffect> RITE_EFFECTS = new ConcurrentHashMap<>();

    public static Spell HEAL;
    public static Spell FEAST;
    public static Spell YELLOW_SIGN;
    public static Spell TIDE_WAVE;


    public static OctagramRite MANIACS_MEETING_GOAT = new RiteManiacsMeeting(Blessing.SHUB, Ingredient.fromItem(Items.DIAMOND), Ingredient.fromItem(Items.EMERALD), Ingredient.fromItem(Items.DIAMOND), Ingredient.fromItem(Items.DIAMOND), Ingredient.fromItem(Items.DIAMOND), Ingredient.fromItem(Items.DIAMOND), Ingredient.fromItem(Items.DIAMOND), Ingredient.fromItem(Items.DIAMOND));
    public static OctagramRite ELDRITCH_TRAP = new RiteEldritchTrap();


    public static void init(){
        HEAL = new SpellHeal();
        FEAST = new SpellFeast();
        YELLOW_SIGN = new SpellYellowSign();
        TIDE_WAVE = new SpellTideWave();
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
            for (OctagramRite rite : RITES.values()) {
                if (matches(rite, octagram.inventory)) {
                    return rite;
                }
            }
            return null;
        }


        public static boolean matches(OctagramRite rite, ItemStackHandler inventory) {
            CopyOnWriteArrayList<ItemStack> checkStacks = new CopyOnWriteArrayList<>();
            checkStacks.addAll(InventoryUtil.getInventoryList(inventory));
            System.out.println(checkStacks);
            int i = 0;
            for (Ingredient ingredient : rite.ingredients) {
                for (ItemStack stack : checkStacks) {
                    if (ingredient.apply(stack)) {
                        System.out.println(i++);
                        checkStacks.remove(stack);
                        break;
                    } else {
                        return false;
                    }
                }
            }
            return checkStacks.isEmpty();
        }
    }
}
