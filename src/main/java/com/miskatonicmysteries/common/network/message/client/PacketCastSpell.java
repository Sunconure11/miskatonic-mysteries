package com.miskatonicmysteries.common.network.message.client;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.spells.ISpellKnowledge;
import com.miskatonicmysteries.common.capability.spells.SpellKnowledge;
import com.miskatonicmysteries.common.handler.SpellHandler;
import com.miskatonicmysteries.common.misc.spells.Spell;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketCastSpell implements IMessage{
    private EnumFacing facing;
    private BlockPos pos;
    private int mode;
    private String spell;
    public PacketCastSpell(){

    }

    public PacketCastSpell(int mode, BlockPos pos, EnumFacing facing){
        this.mode = mode;
        this.pos = pos;
        this.facing = facing == null ? EnumFacing.NORTH : facing;
    }

    public PacketCastSpell(int mode, String spell){
        this.mode = mode;
        this.spell = spell;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.mode = buf.readInt();
        if (mode == 0) {
            this.facing = EnumFacing.getFront(buf.readInt());
            this.pos = BlockPos.fromLong(buf.readLong());
        }else{
            spell = ByteBufUtils.readUTF8String(buf);
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(mode);
        if (mode == 0) {
            buf.writeInt(facing.getIndex());
            buf.writeLong(pos.toLong());
        }else{
            ByteBufUtils.writeUTF8String(buf, spell);
        }
    }

    public static class Handler implements IMessageHandler<PacketCastSpell, IMessage> {

        @Override
        public IMessage onMessage(PacketCastSpell message, MessageContext ctx) {
            MiskatonicMysteries.proxy.getThreadListener(ctx).addScheduledTask(() -> {
                EntityPlayer player = MiskatonicMysteries.proxy.getPlayer(ctx);
                ISpellKnowledge knowledge = SpellKnowledge.Util.getKnowledge(player);
                if (message.mode == 0 && knowledge.getCurrentCastingProgess() < 0) {
                    SpellHandler.castSpell(player, knowledge, message.pos, message.facing);
                }else {
                    Spell spell = Spell.SPELLS.get(message.spell);
                    if (message.mode == 1) {
                        spell.whileCasting(player);
                    } else if (message.mode == 2) {
                        spell.cast(player);
                    }
                }
            });
            return null;
        }
    }
}
