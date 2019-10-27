package com.miskatonicmysteries.registry;

import com.miskatonicmysteries.ModConfig;
import com.miskatonicmysteries.common.capability.sanity.ISanity;
import com.miskatonicmysteries.common.capability.sanity.Sanity;
import com.miskatonicmysteries.common.handler.effects.*;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ModInsanityEffects {
    public static final Map<ResourceLocation, InsanityEffect> INSANITY_EFFECTS = new ConcurrentHashMap<>();

    public static final InsanityEffect EFFECT_SOUNDS = new InsanityEffectSounds();
    public static final InsanityEffect EFFECT_HUNGER = new InsanityEffectHunger();
    public static final InsanityEffect EFFECT_DISTORTION = new InsanityEffectDistortion();
    public static final InsanityEffect EFFECT_CONCERN = new InsanityEffectResistance();

    public static final InsanityEffect EFFECT_STRENGTH = new InsanityEffectStrength();
    public static final InsanityEffect EFFECT_RESISTANCE = new InsanityEffectResistance();

    public static final InsanityEffect EFFECT_FLUX = new InsanityEffectFlux();

    @Nullable
    public static InsanityEffect getRandomEffect(World world, EntityPlayer player, ISanity sanity, InsanityEffect.EnumTrigger trigger){
        List<InsanityEffect> effects = getAvailableEffects(world, player, sanity, trigger);
        Collections.shuffle(effects);
        for (InsanityEffect effect : effects){
            if (world.rand.nextFloat() < effect.getChance(world, player, sanity)){
                return effect;
            }
        }
        return null;
    }

    public static List<InsanityEffect> getInsanityEffects(){
        return new ArrayList<>(INSANITY_EFFECTS.values());
    }

    public static List<InsanityEffect> getAvailableEffects(World world, EntityPlayer player, ISanity sanity, InsanityEffect.EnumTrigger trigger){
        return getInsanityEffects().stream().filter(e -> isEffectAvailable(e, world, player, sanity) && e.getTrigger(world, player, sanity) == trigger).collect(Collectors.toList());
    }

    public static List<InsanityEffect> getStandardAvailableEffects(World world, EntityPlayer player, ISanity sanity){
        return getInsanityEffects().stream().filter(e -> e.standardAvailability(world, player, sanity)).collect(Collectors.toList());
    }

    public static List<InsanityEffect> getStandardAvailableEffects(World world, EntityPlayer player, ISanity sanity, InsanityEffect.EnumTrigger trigger){
        return getInsanityEffects().stream().filter(e -> e.standardAvailability(world, player, sanity) && e.getTrigger(world, player, sanity) == trigger).collect(Collectors.toList());
    }

    public static boolean isEffectAvailable(InsanityEffect effect, World world, EntityPlayer player, ISanity sanity){
        return Math.max(sanity.getSanity() - getManiaInsanityPenalty(player), 1) <= effect.getLevel(world, player, sanity);
    }

    public static String[] getInsanityEffectStrings(){
        List<String> strings = new ArrayList<>();
        for (ResourceLocation loc : INSANITY_EFFECTS.keySet()){
            strings.add(loc.toString());
        }
        return strings.toArray(new String[strings.size()]);
    }

    public static float getExponentialSanityFactor(ISanity sanity){
        float factor = sanity.getSanity() / (float) Sanity.SANITY_MAX;
        return factor * factor;
    }

    public static int getInsanityInterval(EntityLivingBase livingBase) {
        float maniaMultiplier = livingBase.getActivePotionEffect(ModPotions.mania) != null ? (float) 1 / ((livingBase.getActivePotionEffect(ModPotions.mania).getAmplifier() + 2F) * 3) : 1F;
        return (int) (livingBase.ticksExisted % (ModConfig.sanity.insanityInterval * maniaMultiplier));
    }

    public static int getManiaInsanityPenalty(EntityLivingBase livingBase) {
        return livingBase.getActivePotionEffect(ModPotions.mania) != null ? (livingBase.getActivePotionEffect(ModPotions.mania).getAmplifier() + 1) * 20 : 0;
    }
}
