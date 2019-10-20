package com.miskatonicmysteries.common.misc.rites.focus;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictRiteFocus extends RiteFocus {
    protected String[] oreDict;
    public OreDictRiteFocus(float instabilityRate, int conduitAmount, int sameTypeMax, EnumType type, String... oreDict) {
        super(instabilityRate, conduitAmount, sameTypeMax, type);
        this.oreDict = oreDict;
        this.selector = (s -> {
            if (s instanceof ItemStack) {
                for (String ore : oreDict) {
                    if (OreDictionary.containsMatch(false, OreDictionary.getOres(ore), (ItemStack) s)) {
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
