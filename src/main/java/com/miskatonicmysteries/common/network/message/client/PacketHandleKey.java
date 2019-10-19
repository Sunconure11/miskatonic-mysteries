package com.miskatonicmysteries.common.network.message.client;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.spells.ISpellKnowledge;
import com.miskatonicmysteries.common.capability.spells.SpellKnowledge;
import com.miskatonicmysteries.common.capability.spells.SpellKnowledgeProvider;
import com.miskatonicmysteries.common.network.PacketHandler;
import com.miskatonicmysteries.common.network.message.capability.PacketSyncSpellKnowledge;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketHandleKey implements IMessage {
    private int mode;

    public PacketHandleKey() {

    }

    public PacketHandleKey(int mode) {
        this.mode = mode;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.mode = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(mode);
    }

    public static class Handler implements IMessageHandler<PacketHandleKey, IMessage> {

        @Override
        public IMessage onMessage(PacketHandleKey message, MessageContext ctx) {
            MiskatonicMysteries.proxy.getThreadListener(ctx).addScheduledTask(() -> {
                EntityPlayer player = MiskatonicMysteries.proxy.getPlayer(ctx);
                ISpellKnowledge knowledge = SpellKnowledge.Util.getKnowledge(player);
                switch (message.mode) {
                    case 0: //UP
                        knowledge.setCurrentSpell(cycleSpell(knowledge, true));
                        break;
                    case 1: //DOWN
                        knowledge.setCurrentSpell(cycleSpell(knowledge, false));
                        break;
                    case 2: //CANCEL
                        if (knowledge.getCurrentCastingProgess() > 0  && SpellKnowledge.Util.getCurrentSpell(player) != null){
                            SpellKnowledge.Util.getCurrentSpell(player).onCancelled(player);
                        }
                        knowledge.setCurrentSpell(-1);
                        knowledge.setCurrentCastingProgress(-1);
                        break;
                    default:
                }
                PacketHandler.sendTo(player, new PacketSyncSpellKnowledge(knowledge));
            });
            return null;
        }

        private int cycleSpell(ISpellKnowledge knowledge, boolean up) {
            int currentInt = knowledge.getCurrentSpell();
            if (knowledge.getCurrentSpell() != -1 && knowledge.getCurrentCastingProgess() > -1) return currentInt;
            if (!up) {
                if (currentInt > 0) {
                    currentInt--;
                } else {
                    currentInt = knowledge.getSpells().length - 1;
                }
            } else {
                if (currentInt < knowledge.getSpells().length - 1) {
                    currentInt++;
                } else {
                    currentInt = 0;
                }
            }

            return currentInt;
        }

    }
}
