package com.miskatonicmysteries.common.misc.rites.focus;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;

public class EnchantmentRiteFocus extends RiteFocus {
    public EnchantmentRiteFocus() {
        super(0.15F, 20, 4, EnumType.HELD);
        this.selector = (s -> s instanceof ItemStack && !EnchantmentHelper.getEnchantments((ItemStack) s).isEmpty() || ((ItemStack)s).getItem() instanceof ItemEnchantedBook);
    }
}
