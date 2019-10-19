package com.miskatonicmysteries.common.misc.rites.focus;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RiteFocus {
    public static final Map<ItemStack, RiteFocus> FOCUS_MAP = new HashMap<>();
    public static final RiteFocus DEFAULT_FOCUS = new RiteFocus(0, 0, EnumType.HELD);
    private float instabilityRate;
    private int conduitAmount;
    private EnumType type;
    private Predicate<ItemStack> alternativePredicate;

    public RiteFocus(float instabilityRate, int conduitAmount, EnumType type) {
        this.instabilityRate = instabilityRate;
        this.conduitAmount = conduitAmount;
        this.type = type;
    }

    public RiteFocus(Item item, float instabilityRate, int conduitAmount) {
        this(instabilityRate, conduitAmount, EnumType.HELD);
        addFocus(new ItemStack(item), this);
    }

    public RiteFocus(Block block, float instabilityRate, int conduitAmount) {
        this(instabilityRate, conduitAmount, EnumType.PLACED);
        addFocus(new ItemStack(block), this);
    }

    public static void addFocus(ItemStack stack, RiteFocus focus){
        FOCUS_MAP.put(stack, focus);
    }

    public static RiteFocus getFocusFor(Block block){
        return getFocusFor(new ItemStack(block));
    }

    public static RiteFocus getFocusFor(ItemStack stack){
        if (FOCUS_MAP.containsKey(stack)){
            return RiteFocus.FOCUS_MAP.getOrDefault(stack, DEFAULT_FOCUS);
        }
        List<RiteFocus> foci = FOCUS_MAP.values().stream().filter(f -> f.checkAlternatively(stack)).collect(Collectors.toList());
        if (foci.isEmpty()) return DEFAULT_FOCUS;
        return foci.get(0);
    }

    public RiteFocus addAlternatePredicate(Predicate<ItemStack> predicate){
        this.alternativePredicate = predicate;
        return this;
    }

    public boolean checkAlternatively(ItemStack stack){
        if (alternativePredicate != null){
            return alternativePredicate.test(stack);
        }
        return false;
    }

    public float getInstabilityRate() {
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
