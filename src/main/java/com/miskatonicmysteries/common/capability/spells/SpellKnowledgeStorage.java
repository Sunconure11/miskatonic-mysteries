package com.miskatonicmysteries.common.capability.spells;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.sanity.ISanity;
import com.miskatonicmysteries.common.misc.spells.Spell;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;

import java.util.Map;

public class SpellKnowledgeStorage implements Capability.IStorage<ISpellKnowledge> {
    @Override
    public NBTBase writeNBT(Capability<ISpellKnowledge> capability, ISpellKnowledge instance, EnumFacing side) {
        final NBTTagCompound compound = new NBTTagCompound();
        compound.setInteger("curSpell", instance.getCurrentSpell());
        compound.setInteger("castingProgress", instance.getCurrentCastingProgess());
        NBTTagList list = new NBTTagList();
        compound.setTag("spells", list);
        instance.getSpellCooldowns(false).entrySet().stream().forEach(entry -> this.addNewCouple(entry, list));
        return compound;
    }

    @Override
    public void readNBT(Capability<ISpellKnowledge> capability, ISpellKnowledge instance, EnumFacing side, NBTBase nbt) {
        final NBTTagCompound compound = (NBTTagCompound) nbt;
        instance.setCurrentSpell(compound.getInteger("curSpell"));
        instance.setCurrentCastingProgress(compound.getInteger("castingProgress"));
        instance.getSpellCooldowns(true).clear();
        compound.getTagList("spells", Constants.NBT.TAG_COMPOUND).forEach(s -> this.loadCouple(instance, s));
    }

    private void addNewCouple(Map.Entry<Spell, Integer> entry, NBTTagList list) {
        NBTTagCompound couple = new NBTTagCompound();
        couple.setString("spell", entry.getKey().name.toString());
        couple.setInteger("cooldown", entry.getValue());
        list.appendTag(couple);
    }

    private void loadCouple(ISpellKnowledge sanity, NBTBase s) {
        NBTTagCompound tag = (NBTTagCompound) s;
        if (Spell.SPELLS.containsKey(tag.getString("spell"))) {
            sanity.getSpellCooldowns(true).put(Spell.SPELLS.get(tag.getString("spell")), tag.getInteger("cooldown"));
        }
    }
}
