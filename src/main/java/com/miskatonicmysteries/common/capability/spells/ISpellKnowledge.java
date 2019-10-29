package com.miskatonicmysteries.common.capability.spells;

import com.miskatonicmysteries.common.misc.spells.Spell;

import java.util.Map;

public interface ISpellKnowledge {
    Spell[] getSpells();

    Map<Spell, Integer> getSpellCooldowns(boolean dirty);

    boolean addSpell(Spell spell);

    int getCurrentSpell();

    void setCurrentSpell(int num);

    int getCurrentCastingProgess();

    void setCurrentCastingProgress(int progress);

    boolean isDirty();

    void setDirty(boolean dirty);
}
