package com.miskatonicmysteries.registry;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.block.tile.TileEntityOctagram;
import com.miskatonicmysteries.common.misc.rites.OctagramRite;
import com.miskatonicmysteries.common.misc.spells.Spell;
import com.miskatonicmysteries.common.misc.spells.SpellFeast;
import com.miskatonicmysteries.common.misc.spells.SpellHeal;
import com.miskatonicmysteries.common.misc.spells.SpellYellowSign;
import com.miskatonicmysteries.util.InventoryUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ModRites {
    public static Map<ResourceLocation, OctagramRite> RITES = new ConcurrentHashMap<>();

    public static final OctagramRite MANIACSMEETING_GOAT  = new OctagramRite(new ResourceLocation(MiskatonicMysteries.MODID, "maniacs_meeting_goat"), 150, 100, OctagramRite.EnumType.FOCUSED, Ingredient.fromItem(Items.BOOK), Ingredient.fromItem(Items.BOOK), Ingredient.fromItem(Items.BOOK), Ingredient.fromItem(Items.BOOK), Ingredient.fromItem(Items.BOOK), Ingredient.fromItem(Items.BOOK), Ingredient.fromItem(Items.BOOK), Ingredient.fromItem(Items.BOOK)) {
        @Override
        public boolean test(TileEntityOctagram octagram) {
            return true;
        }

        @Override
        public void doRitual(TileEntityOctagram octagram, @Nullable EntityPlayer caster) {

        }

        @Override
        public void effect(TileEntityOctagram octagram, @Nullable EntityPlayer caster) {

        }
    };

    public static OctagramRite getRite(TileEntityOctagram octagram){
        //stacks must be empty to return the rite
        for(OctagramRite rite : RITES.values()){
            List<Ingredient> ingredients = rite.ingredients;
            List<ItemStack> stacks = InventoryUtil.getInventoryList(octagram.inventory);
                ingredients.forEach(stack -> stacks.removeIf(s -> ingredients.removeIf(i -> i.apply(s))));
            if (ingredients.isEmpty()){
                return rite;
            }
        }
        return null;
    }
}
