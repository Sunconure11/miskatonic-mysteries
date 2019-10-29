package com.miskatonicmysteries.common.capability.spells;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class SpellKnowledgeProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(ISpellKnowledge.class)
    public static final Capability<ISpellKnowledge> SPELL_KNOWLEDGE = null;
    private ISpellKnowledge instance = SPELL_KNOWLEDGE.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == SPELL_KNOWLEDGE;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == SPELL_KNOWLEDGE ? SPELL_KNOWLEDGE.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return SPELL_KNOWLEDGE.getStorage().writeNBT(SPELL_KNOWLEDGE, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        SPELL_KNOWLEDGE.getStorage().readNBT(SPELL_KNOWLEDGE, this.instance, null, nbt);
    }
}
