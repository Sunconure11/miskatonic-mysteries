package com.miskatonicmysteries.common.network.message.client;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.spells.ISpellKnowledge;
import com.miskatonicmysteries.common.capability.spells.SpellKnowledge;
import com.miskatonicmysteries.common.handler.CapabilityHandler;
import com.miskatonicmysteries.common.handler.SpellHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketCastSpell implements IMessage{
    public PacketCastSpell(){

    }


    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<PacketCastSpell, IMessage> {

        @Override
        public IMessage onMessage(PacketCastSpell message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                EntityPlayer player = MiskatonicMysteries.proxy.getPlayer(ctx);
                ISpellKnowledge knowledge = SpellKnowledge.Util.getKnowledge(player);
                if (knowledge.getCurrentCastingProgess() < 0) {
                    SpellHandler.castSpell(player, knowledge);
                }
            });
            return null;
        }
    }
}
