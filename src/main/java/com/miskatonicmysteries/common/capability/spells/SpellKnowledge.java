package com.miskatonicmysteries.common.capability.spells;

import com.miskatonicmysteries.common.misc.spells.Spell;
import com.miskatonicmysteries.common.network.PacketHandler;
import com.miskatonicmysteries.common.network.message.capability.PacketSyncSpellKnowledge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;

import java.util.*;

public class SpellKnowledge implements ISpellKnowledge {
    private int curSpell;
    private int castingProgress = -1;
    private final LinkedHashMap<Spell, Integer> COOLDOWNS = new LinkedHashMap<>();
    public static final int MAX_SPELLS = 7;
    private boolean isDirty;
    public SpellKnowledge(){

    }

    @Override
    public Spell[] getSpells() {
        return COOLDOWNS.keySet().toArray(new Spell[COOLDOWNS.keySet().size()]);
    }

    @Override
    public LinkedHashMap<Spell, Integer> getSpellCooldowns(boolean dirty) {
        if(dirty){
            setDirty(true);
            return COOLDOWNS;
        }
        return new LinkedHashMap<>(COOLDOWNS);
    }

    @Override
    public boolean addSpell(Spell spell) {
        if (COOLDOWNS.containsKey(spell)) return false;
        setDirty(true);
        COOLDOWNS.put(spell, 0);
        if (COOLDOWNS.size() > MAX_SPELLS) {
            Map<Spell, Integer> map = rotateSpellMap(COOLDOWNS, 1);
            COOLDOWNS.clear();
            COOLDOWNS.putAll(map);
            COOLDOWNS.remove(getSpells()[MAX_SPELLS]);
            return false;
        }
        return true;
    }

    @Override
    public int getCurrentSpell() {
        return curSpell;
    }

    @Override
    public void setCurrentSpell(int num) {
        setDirty(true);
        this.curSpell = num;
    }

    @Override
    public int getCurrentCastingProgess() {
        return castingProgress;
    }

    @Override
    public void setCurrentCastingProgress(int progress) {
        setDirty(true);
        this.castingProgress = progress;
    }

    @Override
    public boolean isDirty() {
        return isDirty;
    }

    @Override
    public void setDirty(boolean dirty) {
        this.isDirty = dirty;
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
        public static int getCooldownFor(Spell spell, EntityPlayer player){
            return getKnowledge(player).getSpellCooldowns(false).getOrDefault(spell, 0);
        }

        public static void setCooldownFor(Spell spell, int cooldown, EntityPlayer player, boolean ignoreIncrease) {
            if (cooldown >= getCooldownFor(spell, player) || ignoreIncrease) {
                getKnowledge(player).getSpellCooldowns(true).replace(spell, getCooldownFor(spell, player), cooldown);
            }
        }

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
            if (isSpellSelected(player)) {
                return getKnowledge(player).getSpells()[getKnowledge(player).getCurrentSpell()];
            }
            return null;
        }

        public static boolean isSpellSelected(EntityPlayer player){
            return getKnowledge(player).getCurrentSpell() > -1 && getKnowledge(player).getCurrentSpell() < getKnowledge(player).getSpells().length;
        }

        public static ISpellKnowledge getKnowledge(EntityPlayer player) {
            return player.getCapability(SpellKnowledgeProvider.SPELL_KNOWLEDGE, null);
        }

        public static void transferToClone(PlayerEvent.Clone event) {
            if (event.getEntityPlayer().hasCapability(SpellKnowledgeProvider.SPELL_KNOWLEDGE, null)) {
                ISpellKnowledge knowledge = event.getEntityPlayer().getCapability(SpellKnowledgeProvider.SPELL_KNOWLEDGE, null);
                ISpellKnowledge oldKnowledge = event.getOriginal().getCapability(SpellKnowledgeProvider.SPELL_KNOWLEDGE, null);
                oldKnowledge.setCurrentCastingProgress(-1);
                oldKnowledge.setCurrentSpell(-1);
                for (Spell spell : oldKnowledge.getSpells()){
                    setCooldownFor(spell,0, event.getOriginal(), true);
                }
                SpellKnowledgeProvider.SPELL_KNOWLEDGE.readNBT(knowledge, null, SpellKnowledgeProvider.SPELL_KNOWLEDGE.writeNBT(oldKnowledge, null));

                syncKnowledge(event.getEntityPlayer(), knowledge);
            }
        }

        public static void syncKnowledge(EntityPlayer player, ISpellKnowledge spellKnowledge) {
            PacketHandler.sendTo(player, new PacketSyncSpellKnowledge(spellKnowledge));
        }
    }
}
