package com.miskatonicmysteries.common.misc.rites.focus;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSkull;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictRiteFocus extends RiteFocus {
    protected String[] oreDict;
    public OreDictRiteFocus(float instabilityRate, int conduitAmount, int sameTypeMax, EnumType type, String... oreDict) {
        super(instabilityRate, conduitAmount, sameTypeMax, type);
        this.oreDict = oreDict;
        this.selector = (s -> {
                ItemStack stack = null;
                if (s instanceof ItemStack){
                    stack = (ItemStack) s;
                }else if (s instanceof Block){
                    stack = new ItemStack((Block) s); //do note that this does not work with meta etc.
                }
                if (stack != null) {
                    for (String ore : oreDict) {
                        if (OreDictionary.containsMatch(false, OreDictionary.getOres(ore), stack)) {
                            return true;
                        }
                    }
                }
            return false;
        });
    }

    public static OreDictRiteFocus create(float instabilityRate, int conduitAmount, int sameTypeMax, EnumType type, String... oreDict){
        OreDictRiteFocus r = new OreDictRiteFocus(instabilityRate, conduitAmount, sameTypeMax, type, oreDict);
        addFocus(r);
        return r;
    }
}
