package com.miskatonicmysteries.common.capability.spells;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.misc.spells.Spell;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;

public class SpellKnowledgeStorage implements Capability.IStorage<ISpellKnowledge> {
    @Override
    public NBTBase writeNBT(Capability<ISpellKnowledge> capability, ISpellKnowledge instance, EnumFacing side) {
        final NBTTagCompound compound = new NBTTagCompound();
        compound.setInteger("curSpell", instance.getCurrentSpell());
        compound.setInteger("castingProgress", instance.getCurrentCastingProgess());
        NBTTagCompound list = new NBTTagCompound();
        compound.setTag("spells", list);
        instance.getSpells().stream().forEach(s ->{
            if (s != null){
                list.setInteger(s.name.toString(), instance.getSpellCooldowns().containsKey(s.name.toString()) ? instance.getSpellCooldowns().get(s.name.toString()) :0);
            }else{
                MiskatonicMysteries.LOGGER.error("While wring spells to NBT, an exception occurred; the spell in question will be skipped.");
            }
        });
        return compound;
    }

    @Override
    public void readNBT(Capability<ISpellKnowledge> capability, ISpellKnowledge instance, EnumFacing side, NBTBase nbt) {
        final NBTTagCompound compound = (NBTTagCompound) nbt;
        instance.setCurrentSpell(compound.getInteger("curSpell"));
        instance.setCurrentCastingProgress(compound.getInteger("castingProgress"));
        instance.getSpells().clear();
        instance.getSpellCooldowns().clear();
        ((NBTTagCompound) nbt).getCompoundTag("spells").getKeySet().stream().forEach(s -> {
            if (Spell.SPELLS.containsKey(s)) {
               // instance.getSpells().add(Spell.SPELLS.get(s));
                instance.getSpellCooldowns().put(Spell.SPELLS.get(s), ((NBTTagCompound) nbt).getCompoundTag("spells").getInteger("s"));
            }else{
                MiskatonicMysteries.LOGGER.error("While reading spells from NBT, an exception occurred; the spell in question will be skipped.");
            }
        });
    }
}
