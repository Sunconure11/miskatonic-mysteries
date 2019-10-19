package com.miskatonicmysteries.common.misc.rites.focus;

import com.miskatonicmysteries.common.block.tile.TileEntityOctagram;
import net.minecraft.item.Item;
import sun.jvm.hotspot.opto.Block;

import java.util.HashMap;
import java.util.Map;

public class RiteFocus {
    public static final Map<Item, RiteFocus> ITEM_FOCUS_MAP = new HashMap<>();
    public static final Map<Block, RiteFocus> BLOCK_FOCUS_MAP = new HashMap<>();

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
        addFocus(item, this);
    }

    public RiteFocus(Block block, int instabilityRate, int conduitAmount) {
        this(instabilityRate, conduitAmount, EnumType.PLACED);
        addFocus(block, this);
    }

    public static void addFocus(Block block, RiteFocus focus){
        BLOCK_FOCUS_MAP.put(block, focus);
    }

    public static void addFocus(Item item, RiteFocus focus){
        ITEM_FOCUS_MAP.put(item, focus);
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
