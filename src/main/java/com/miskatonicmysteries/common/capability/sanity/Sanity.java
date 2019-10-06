package com.miskatonicmysteries.common.capability.sanity;

import com.miskatonicmysteries.common.network.PacketHandler;
import com.miskatonicmysteries.common.network.message.capability.PacketSyncSanity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;

import java.util.HashMap;
import java.util.Map;

public class Sanity implements ISanity {
    public static final int SANITY_MAX = 150;
    private int sanity;
    private boolean dirty;
    private Map<String, Integer> expansions = new HashMap<>();

    public Sanity() {
        this.sanity = Sanity.SANITY_MAX;
    }

    @Override
    public int getSanity() {
        return sanity;
    }

    @Override
    public void setSanity(int sanity) {
        if (sanity > getSanityMax()){
            this.sanity = getSanityMax();
            return;
        }
        if (sanity < 1){
            this.sanity = 1;
            return;
        }
        this.sanity = sanity;
        this.dirty = true;
    }

    @Override
    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    @Override
    public int getSanityMax() {
        return SANITY_MAX + getTotalIncrease();
    }

    @Override
    public int getTotalIncrease() {
        return this.expansions.values().parallelStream().reduce(0, (a, b) -> a + b);
    }

    @Override //DO NOT USE THIS FOR ADDING EXPANSIONS; that's what the helper method is for
    public Map<String, Integer> getExpansionMap() {
        return expansions;
    }

    @Override
    public void addExpansion(String id, int value) {
        setDirty(true);
        expansions.put(id, value);
    }

    @Override
    public boolean isDirty() {
        return dirty;
    }


    public static class Util {
        public static boolean setSanity(int amount, EntityPlayer player) {
            if (!player.world.isRemote && getSanityCapability(player) != null) {
                ISanity sanity = getSanityCapability(player);
                if (amount < 1)
                    return false;
                sanity.setSanity(amount);
                return true;
            }
            return false;
        }

        public static boolean addExpansion(String id, int value, EntityPlayer player) {
            if (getSanityCapability(player) != null) {
                ISanity sanity = getSanityCapability(player);
                if (sanity.getExpansionMap().keySet().contains(id)){
                    return false;
                }
                sanity.addExpansion(id, value);
                return true;
            }
            return false;
        }

        public static int getSanity(EntityPlayer player) {
            if (getSanityCapability(player) != null) {
                ISanity sanity = getSanityCapability(player);
                return sanity.getSanity();
            }
            return -1;
        }

        //this is really just a shortcut because I hate typing this all the time
        public static ISanity getSanityCapability(EntityPlayer player) {
            return player.getCapability(SanityProvider.SANITY, null);
        }

        public static void transferToClone(PlayerEvent.Clone event) {
            if (event.getEntityPlayer().hasCapability(SanityProvider.SANITY, null)) {
                ISanity sanity = event.getEntityPlayer().getCapability(SanityProvider.SANITY, null);
                ISanity oldSanity = event.getOriginal().getCapability(SanityProvider.SANITY, null);
                sanity.setSanity(oldSanity.getSanity());
                sanity.getExpansionMap().putAll(oldSanity.getExpansionMap());
                syncSanity(event.getEntityPlayer(), sanity);
            }
        }

        public static void syncSanity(EntityPlayer player, ISanity sanity) {
            if (sanity.getSanity() > sanity.getSanityMax()){
                sanity.setSanity(sanity.getSanityMax());
            }
            PacketHandler.sendTo(player, new PacketSyncSanity(sanity));
        }
    }
}
