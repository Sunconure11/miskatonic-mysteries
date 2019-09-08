package com.miskatonicmysteries.common.network.message.capability;

import com.miskatonicmysteries.common.capability.ISanity;
import com.miskatonicmysteries.common.capability.SanityProvider;
import io.netty.buffer.ByteBuf;
import com.miskatonicmysteries.common.capability.SanityProvider;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSyncSanity implements IMessage{
    private int sanity = 150;
    public PacketSyncSanity(){

    }

    public PacketSyncSanity(ISanity cap) {
        this.sanity = cap.getSanity();
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        this.sanity = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.sanity);
    }

    public static class Handler implements IMessageHandler<PacketSyncSanity, IMessage> {

        @Override
        public IMessage onMessage(PacketSyncSanity message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                if (Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().player.hasCapability(SanityProvider.SANITY, null)) {
                    ISanity c = Minecraft.getMinecraft().player.getCapability(SanityProvider.SANITY, null);
                    c.setSanity(message.sanity);
                }
            });
            return null;
        }

    }
}
