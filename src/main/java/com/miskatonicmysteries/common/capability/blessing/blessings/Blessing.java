package com.miskatonicmysteries.common.capability.blessing.blessings;

import com.miskatonicmysteries.common.capability.blessing.BlessingCapability;
import com.miskatonicmysteries.common.potion.ModPotion;
import com.miskatonicmysteries.registry.ModPotions;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import java.util.HashMap;
import java.util.Map;

public class Blessing {
    protected String name;
    public PotionEffect potionEffect;
    private static Map<String, Blessing> blessings = new HashMap<>();

    public static final Blessing NONE = addBlessing(new Blessing("none"));
    public static final Blessing SHUB = addBlessing(new ShubBlessing());
    public static final Blessing HASTUR = addBlessing(new Blessing("hastur"));
    public static final Blessing CTHULHU = addBlessing(new Blessing("cthulhu"));
    public static final Blessing YOG_SOTOTH = addBlessing(new Blessing("yog_sothoth"));


    public Blessing(String name) {
        this.name = name;
    }

    public Blessing(String name, PotionEffect potion) {
        this.name = name;
        this.potionEffect = potion;
    }

    public PotionEffect getPotionEffect() {
        return potionEffect;
    }

    public String getName() {
        return name;
    }

    public void onAdded(EntityPlayer player){}

    public void onRemoved(EntityPlayer player){}

    public boolean hasBlessing(EntityPlayer player){
        return BlessingCapability.Util.getBlessing(player) == this;
    }

    public boolean hasBlessingWithPotion(EntityPlayer player){
        return BlessingCapability.Util.getBlessing(player) == this;
    }

    public static Blessing addBlessing(Blessing blessing){
        blessings.put(blessing.name, blessing);
        return blessing;
    }

    public static Blessing getBlessing(String name){
        return blessings.getOrDefault(name, NONE);
    }

    public static Blessing getBlessingWithNull(String name){
        return blessings.getOrDefault(name, null);
    }

    public static String[] getBlessings(){
        return blessings.keySet().toArray(new String[blessings.keySet().size()]);
    }
}
