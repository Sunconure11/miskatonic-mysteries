package com.miskatonicmysteries.common.network.message.client;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.spells.ISpellKnowledge;
import com.miskatonicmysteries.common.capability.spells.SpellKnowledge;
import com.miskatonicmysteries.common.handler.SpellHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketCastSpell implements IMessage{
    private EnumFacing facing;
    private BlockPos pos;
    public PacketCastSpell(){

    }

    public PacketCastSpell(BlockPos pos, EnumFacing facing){
        this.pos = pos;
        this.facing = facing == null ? EnumFacing.NORTH : facing;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.facing = EnumFacing.getFront(buf.readInt());
        this.pos = BlockPos.fromLong(buf.readLong());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(facing.getIndex());
        buf.writeLong(pos.toLong());
    }

    public static class Handler implements IMessageHandler<PacketCastSpell, IMessage> {

        @Override
        public IMessage onMessage(PacketCastSpell message, MessageContext ctx) {
            MiskatonicMysteries.proxy.getThreadListener(ctx).addScheduledTask(() -> {
                EntityPlayer player = MiskatonicMysteries.proxy.getPlayer(ctx);
                ISpellKnowledge knowledge = SpellKnowledge.Util.getKnowledge(player);
                if (knowledge.getCurrentCastingProgess() < 0) {
                    SpellHandler.castSpell(player, knowledge, message.pos, message.facing);
                }
            });
            return null;
        }
    }
}
