package com.miskatonicmysteries.common.handler;

import com.miskatonicmysteries.common.capability.spells.ISpellKnowledge;
import com.miskatonicmysteries.common.capability.spells.SpellKnowledge;
import com.miskatonicmysteries.common.misc.spells.Spell;
import net.minecraft.entity.player.EntityPlayer;

public class SpellHandler {

    public static void continueCast(ISpellKnowledge knowledge, Spell spell, EntityPlayer player) {
        if (!player.world.isRemote) {
            for (Spell spellIn : knowledge.getSpellCooldowns().keySet()) {
                int value = knowledge.getSpellCooldowns().get(spellIn);
                if (value <= 0)
                    knowledge.getSpellCooldowns().replace(spellIn, value, value - 1);
            }
            if (knowledge.getCurrentCastingProgess() > -1 && spell != null && SpellKnowledge.Util.isSpellSelected(player)) {
                if (knowledge.getCurrentCastingProgess() > 0) {
                    spell.whileCasting(player);
                    knowledge.setCurrentCastingProgress(knowledge.getCurrentCastingProgess() - 1);
                }
                if (knowledge.getCurrentCastingProgess() <= 0) {
                    spell.cast(player);
                    knowledge.setCurrentCastingProgress(-1);
                    knowledge.setCurrentSpell(-1); //reset the selected spell
                }
            }
        }
    }

    public static void castSpell(EntityPlayer player, ISpellKnowledge knowledge){
        if (SpellKnowledge.Util.isSpellSelected(player)){
            Spell selectedSpell = SpellKnowledge.Util.getCurrentSpell(player);
            if (selectedSpell.check(player)){
                selectedSpell.startCasting(player);
                selectedSpell.price(player);
            }else{
                selectedSpell.onCheckFalse(player);
                knowledge.setCurrentSpell(-1);
                //play sound or so?
            }
        }
    }
}
