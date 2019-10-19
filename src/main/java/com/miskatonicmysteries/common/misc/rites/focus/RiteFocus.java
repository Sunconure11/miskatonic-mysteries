package com.miskatonicmysteries.common.misc.rites.focus;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class RiteFocus {
    public static final Map<ItemStack, RiteFocus> FOCUS_MAP = new HashMap<>();

    private int instabilityRate;
    private int conduitAmount;
    private EnumType type;

    public RiteFocus(int instabilityRate, int conduitAmount, EnumType type) {
        this.instabilityRate = instabilityRate;
        this.conduitAmount = conduitAmount;
        this.type = type;
    }

    public RiteFocus(Item item, int instabilityRate, int conduitAmount) {
        this(instabilityRate, conduitAmount, EnumType.HELD);
        addFocus(new ItemStack(item), this);
    }

    public RiteFocus(Block block, int instabilityRate, int conduitAmount) {
        this(instabilityRate, conduitAmount, EnumType.PLACED);
        addFocus(new ItemStack(block), this);
    }

    public static void addFocus(ItemStack stack, RiteFocus focus){
        FOCUS_MAP.put(stack, focus);
    }



    public int getInstabilityRate() {
        return instabilityRate;
    }

    public int getConduitAmount() {
        return conduitAmount;
    }

    public EnumType getType() {
        return type;
    }

    public enum EnumType {
        HELD,
        PLACED
    }
}
