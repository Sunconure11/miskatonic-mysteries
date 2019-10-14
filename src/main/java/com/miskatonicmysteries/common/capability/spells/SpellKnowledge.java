package com.miskatonicmysteries.common.capability.spells;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.misc.spells.Spell;
import com.miskatonicmysteries.common.network.PacketHandler;
import com.miskatonicmysteries.common.network.message.capability.PacketSyncSpellKnowledge;
import com.miskatonicmysteries.registry.ModSpells;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

public class SpellKnowledge implements ISpellKnowledge {
    //this entire code is a mess and needs to be more efficient, especially in updating (isDirty stuff?)
    private int curSpell;
    private int castingProgress = -1; //may be done with the map only? as in, negatives are cool downs, positives are castings
    private final Map<Spell, Integer> COOLDOWNS = new LinkedHashMap<>(); ////Store spells as strings again and oof all that jazz
    public static final int MAX_SPELLS = 7; //also make refreshing stuff more efficient

    public SpellKnowledge(){

    }

    @Override
    public Set<Spell> getSpells() {
        return COOLDOWNS.keySet();
    }

    @Override
    public Map<Spell, Integer> getSpellCooldowns() {
        return COOLDOWNS;
    }

    @Override
    public boolean addSpell(Spell spell) {
        COOLDOWNS.put(spell, 0);
        Map<Spell, Integer> map = rotateSpellMap(COOLDOWNS,1);
        COOLDOWNS.clear();
        COOLDOWNS.putAll(map);
        return true;
    }

    @Override
    public int getCurrentSpell() {
        return curSpell;
    }

    @Override
    public void setCurrentSpell(int num) {
        this.curSpell = num;
    }

    @Override
    public int getCurrentCastingProgess() {
        return castingProgress;
    }

    @Override
    public void setCurrentCastingProgress(int progress) {
        this.castingProgress = progress;
    }

    private Map<Spell, Integer> rotateSpellMap(Map<Spell, Integer> map, int distance){
        Map<Spell, Integer> resultMap = new LinkedHashMap<>();
        List<Spell> keys = new ArrayList(map.keySet());
        Collections.rotate(keys, distance);
        for(Spell key : keys) {
            resultMap.put(key, map.get(key));
        }
        return resultMap;
    }

    public static class Util {
        public static void startCastingProgress(int ticks, EntityPlayer player) {
            if (!player.world.isRemote) {
                getKnowledge(player).setCurrentCastingProgress(ticks);
            }
        }

        public static void addSpell(Spell spell, EntityPlayer player) {
            ISpellKnowledge knowledge = getKnowledge(player);
            knowledge.addSpell(spell);
        }

        public static Spell getCurrentSpell(EntityPlayer player) {
            if (!player.world.isRemote && isSpellSelected(player)) {
                return (Spell) getKnowledge(player).getSpells().toArray()[getKnowledge(player).getCurrentSpell()];
            }
            return null;
        }

        public static boolean isSpellSelected(EntityPlayer player){
            return getKnowledge(player).getCurrentSpell() > -1 && getKnowledge(player).getCurrentSpell() < getKnowledge(player).getSpells().size();
        }

        public static ISpellKnowledge getKnowledge(EntityPlayer player) {
            return player.getCapability(SpellKnowledgeProvider.SPELL_KNOWLEDGE, null);
        }

        public static void transferToClone(PlayerEvent.Clone event) {
            if (event.getEntityPlayer().hasCapability(SpellKnowledgeProvider.SPELL_KNOWLEDGE, null)) {
                ISpellKnowledge sanity = event.getEntityPlayer().getCapability(SpellKnowledgeProvider.SPELL_KNOWLEDGE, null);
                ISpellKnowledge oldSanity = event.getOriginal().getCapability(SpellKnowledgeProvider.SPELL_KNOWLEDGE, null);
                SpellKnowledgeProvider.SPELL_KNOWLEDGE.readNBT(sanity, null, SpellKnowledgeProvider.SPELL_KNOWLEDGE.writeNBT(oldSanity, null));
                syncKnowledge(event.getEntityPlayer(), sanity);
            }
        }

        public static void syncKnowledge(EntityPlayer player, ISpellKnowledge spellKnowledge) {
            PacketHandler.sendTo(player, new PacketSyncSpellKnowledge(spellKnowledge));
        }
    }
}
