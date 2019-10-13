package com.miskatonicmysteries.common.network.message.capability;

import com.miskatonicmysteries.common.capability.sanity.ISanity;
import com.miskatonicmysteries.common.capability.sanity.SanityProvider;
import com.miskatonicmysteries.common.capability.spells.ISpellKnowledge;
import com.miskatonicmysteries.common.capability.spells.SpellKnowledgeProvider;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

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
            Minecraft.getMinecraft().addScheduledTask(() -> {
                if (Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().player.hasCapability(SpellKnowledgeProvider.SPELL_KNOWLEDGE, null)) {
                    ISpellKnowledge c = Minecraft.getMinecraft().player.getCapability(SpellKnowledgeProvider.SPELL_KNOWLEDGE, null);
                    SpellKnowledgeProvider.SPELL_KNOWLEDGE.readNBT(c, null, message.data);
                }
            });
            return null;
        }

    }
}
