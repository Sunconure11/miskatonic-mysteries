package com.miskatonicmysteries.common.capability.spells;

import com.miskatonicmysteries.common.misc.spells.Spell;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ISpellKnowledge {
    Spell[] getSpells();

    Map<Spell, Integer> getSpellCooldowns();

    boolean addSpell(Spell spell);

    int getCurrentSpell();

    void setCurrentSpell(int num);

    int getCurrentCastingProgess();

    void setCurrentCastingProgress(int progress);
}
