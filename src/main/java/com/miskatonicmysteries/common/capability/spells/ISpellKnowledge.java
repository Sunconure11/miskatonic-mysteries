package com.miskatonicmysteries.common.capability.spells;

import com.miskatonicmysteries.common.misc.spells.Spell;

import java.util.List;
import java.util.Map;

public interface ISpellKnowledge {
    List<Spell> getSpells();

    List<Spell> setSpells();

    int getCurrentSpell();

    void setCurrentSpell(int num);

    void setDirty(boolean dirty);

    boolean isDirty();

    int getCurrentCastingProgess();

    void setCurrentCastingProgress(int progress);
}
