package com.miskatonicmysteries.registry;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.block.tile.TileEntityOctagram;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.misc.rites.OctagramRite;
import com.miskatonicmysteries.common.misc.rites.RiteManiacsMeeting;
import com.miskatonicmysteries.common.misc.spells.Spell;
import com.miskatonicmysteries.common.misc.spells.SpellFeast;
import com.miskatonicmysteries.common.misc.spells.SpellHeal;
import com.miskatonicmysteries.common.misc.spells.SpellYellowSign;
import com.miskatonicmysteries.util.InventoryUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ModRites {
    public static Map<ResourceLocation, OctagramRite> RITES = new ConcurrentHashMap<>();

    public static OctagramRite MANIACS_MEETING_GOAT = new RiteManiacsMeeting(Blessing.SHUB, Ingredient.fromItem(Items.DIAMOND), Ingredient.fromItem(Items.DIAMOND), Ingredient.fromItem(Items.DIAMOND), Ingredient.fromItem(Items.DIAMOND), Ingredient.fromItem(Items.DIAMOND), Ingredient.fromItem(Items.DIAMOND), Ingredient.fromItem(Items.DIAMOND), Ingredient.fromItem(Items.DIAMOND));

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
        for(Ingredient ingredient : rite.ingredients){
            for (ItemStack stack : checkStacks){
                if (ingredient.apply(stack)){
                    checkStacks.remove(stack);
                    break;
                }else{
                    return false;
                }
            }
        }
        return checkStacks.isEmpty();
    }
}
