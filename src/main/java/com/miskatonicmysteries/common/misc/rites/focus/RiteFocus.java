package com.miskatonicmysteries.common.misc.rites.focus;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class RiteFocus {
    public static final List<RiteFocus> FOCI = new ArrayList<>();
    protected float instabilityRate;
    protected int conduitAmount;
    protected int maxSameType;
    protected EnumType type;
    protected Predicate<Object> selector;

    public RiteFocus(float instabilityRate, int conduitAmount, int sameTypeMax, EnumType type) {
        this.instabilityRate = instabilityRate;
        this.conduitAmount = conduitAmount;
        this.type = type;
        this.maxSameType = sameTypeMax;
    }

    public RiteFocus withSelector(Predicate<Object> selector){
        this.selector = selector;
        return this;
    }


    public float getInstabilityRate(@Nullable World world, @Nullable BlockPos pos) {
        return instabilityRate;
    }

    public int getConduitAmount(@Nullable World world, @Nullable BlockPos pos) {
        return conduitAmount;
    }

    public int getMaxSameType(@Nullable World world, @Nullable BlockPos pos) {
        return maxSameType;
    }


    public EnumType getType() {
        return type;
    }

    public enum EnumType {
        HELD,
        PLACED
    }

    public static void addFocus(RiteFocus focus){
        FOCI.add(focus);
    }

    public static List<RiteFocus> getFociFor(Object object, EnumType type){
        List<RiteFocus> foci = new ArrayList<>();
        FOCI.forEach(f -> {
            if (f.type == type && f.selector.test(object)){
                foci.add(f);
            }
        });
        return foci;
    }
}
