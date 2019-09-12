package com.miskatonicmysteries.common.potion;

import com.google.common.collect.ImmutableList;
import com.miskatonicmysteries.registry.ModPotions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class PotionHungerExotic extends ModPotion {
    private static final List<String> validFoods = ImmutableList.of(
            "rotten_flesh",

            //compat
            "playerflesh",
            "villagerflesh",
            "witchflesh",
            "playerfleshcooked",
            "villagerfleshcooked",

            "wendigoheart",
            "groundedplayerheart",

            "heart_human",
            "heart"
    );
    public PotionHungerExotic() {
        super("hunger_exotic", true, 8001024);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        List<ItemStack> stacks = new ArrayList<>();
        return stacks;
    }

    @SubscribeEvent
    public void onItemUsed(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntityLiving().getActivePotionEffect(ModPotions.hunger_exotic) != null) {
            ItemStack stack = event.getItem();
            boolean flag = false;
            for (int id : OreDictionary.getOreIDs(stack)) {
                if (validFoods.contains(OreDictionary.getOreName(id))) {
                    flag = true;
                    break;
                }
            }
            if (flag || validFoods.contains(stack.getItem().getRegistryName().getPath())) {
               event.getEntityLiving().removeActivePotionEffect(ModPotions.hunger_exotic);
            }
        }
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {//remove mania, but only if it's weaker or equally strong
        super.performEffect(entityLivingBaseIn, amplifier);
    }
}
