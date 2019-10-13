package com.miskatonicmysteries.common.capability.spells;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.misc.spells.Spell;
import com.miskatonicmysteries.common.network.PacketHandler;
import com.miskatonicmysteries.common.network.message.capability.PacketSyncSpellKnowledge;
import com.miskatonicmysteries.registry.ModSpells;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpellKnowledge implements ISpellKnowledge {
    private int curSpell;
    private int castingProgress = -1;
    private boolean dirty;
    private final List<Spell> SPELLS = new ArrayList<>();

    public static final int MAX_SPELLS = 7;

    public SpellKnowledge(){

    }

    @Override
    public List<Spell> getSpells() {
        return new ArrayList<>(SPELLS);
    }

    @Override
    public List<Spell> setSpells() {
        setDirty(true);
        return SPELLS;
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
    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    @Override
    public boolean isDirty() {
        return dirty;
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

    public static class Util {
        public static void startCastingProgress(int ticks, EntityPlayer player) {
            if (!player.world.isRemote) {
                getKnowledge(player).setCurrentCastingProgress(ticks);
            }
        }

        public static void addSpell(Spell spell, EntityPlayer player) {
            ISpellKnowledge knowledge = getKnowledge(player);
            if (knowledge.getSpells().size() >= MAX_SPELLS){
                knowledge.setSpells().remove(MAX_SPELLS - 1);
                Collections.rotate(knowledge.setSpells(), 1);
                knowledge.setDirty(true);
            }
            knowledge.setSpells().add(0, spell);
        }

        public static Spell getCurrentSpell(EntityPlayer player) {
            if (!player.world.isRemote && isSpellSelected(player)) {
                return getKnowledge(player).getSpells().get(getKnowledge(player).getCurrentSpell());
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
