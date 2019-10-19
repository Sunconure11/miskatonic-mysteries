package com.miskatonicmysteries.common.network.message.capability;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.sanity.ISanity;
import com.miskatonicmysteries.common.capability.sanity.SanityProvider;
import com.miskatonicmysteries.common.capability.spells.ISpellKnowledge;
import com.miskatonicmysteries.common.capability.spells.SpellKnowledge;
import com.miskatonicmysteries.common.capability.spells.SpellKnowledgeProvider;
import com.miskatonicmysteries.common.capability.spells.SpellKnowledgeStorage;
import com.miskatonicmysteries.common.misc.spells.Spell;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.LinkedHashMap;

public class PacketSyncSpellKnowledge implements IMessage{
    private NBTTagCompound data;

    public PacketSyncSpellKnowledge(){

    }

    public PacketSyncSpellKnowledge(ISpellKnowledge cap) {
        data = (NBTTagCompound) SpellKnowledgeProvider.SPELL_KNOWLEDGE.writeNBT(cap, null);
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        this.data = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, data);
    }

    public static class Handler implements IMessageHandler<PacketSyncSpellKnowledge, IMessage> {

        @Override
        public IMessage onMessage(PacketSyncSpellKnowledge message, MessageContext ctx) {
            MiskatonicMysteries.proxy.getThreadListener(ctx).addScheduledTask(() -> {
                    ISpellKnowledge c = SpellKnowledge.Util.getKnowledge(MiskatonicMysteries.proxy.getPlayer(ctx));
                    SpellKnowledgeProvider.SPELL_KNOWLEDGE.getStorage().readNBT(SpellKnowledgeProvider.SPELL_KNOWLEDGE, c, null, message.data);
            });
            return null;
        }

    }
}
