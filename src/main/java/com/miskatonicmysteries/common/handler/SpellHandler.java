package com.miskatonicmysteries.common.handler;

import com.miskatonicmysteries.common.capability.spells.ISpellKnowledge;
import com.miskatonicmysteries.common.capability.spells.SpellKnowledge;
import com.miskatonicmysteries.common.misc.spells.Spell;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class SpellHandler {

    public static void continueCast(ISpellKnowledge knowledge, Spell spell, EntityPlayer player) {
        if (!player.world.isRemote) {
            if (knowledge.getCurrentCastingProgess() <= 0) {
                for (Spell spellIn : knowledge.getSpellCooldowns(false).keySet()) {
                    int value = knowledge.getSpellCooldowns(false).get(spellIn);
                    if (value > 0)
                        knowledge.getSpellCooldowns(true).replace(spellIn, value, value - 1);
                }
            }
            if (knowledge.getCurrentCastingProgess() > -1 && spell != null && SpellKnowledge.Util.isSpellSelected(player)) {
                if (knowledge.getCurrentCastingProgess() > 0) {
                    spell.whileCasting(player);
                    knowledge.setCurrentCastingProgress(knowledge.getCurrentCastingProgess() - 1);
                }
                if (knowledge.getCurrentCastingProgess() <= 0) {
                    spell.cast(player);
                    SpellKnowledge.Util.setCooldownFor(spell, spell.getCooldownTime(), player, false);
                    knowledge.setCurrentCastingProgress(-1);
                    knowledge.setCurrentSpell(-1);
                }
            }
        }
    }

    public static void castSpell(EntityPlayer player, ISpellKnowledge knowledge, BlockPos pos, EnumFacing facing){
        if (SpellKnowledge.Util.isSpellSelected(player)){
            Spell selectedSpell = SpellKnowledge.Util.getCurrentSpell(player);
            if (selectedSpell != null && SpellKnowledge.Util.getCooldownFor(selectedSpell, player) <= 0) {
                if (selectedSpell.check(player)) {
                    selectedSpell.startCasting(player, pos, facing);
                    SpellKnowledge.Util.startCastingProgress(selectedSpell.getCastTime(), player);
                    selectedSpell.price(player);
                } else {
                    selectedSpell.onCheckFalse(player);
                    //play sound or so?
                }
            }
        }
    }
}
