package com.miskatonicmysteries.common.network.message.capability;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.sanity.ISanity;
import com.miskatonicmysteries.common.capability.sanity.SanityProvider;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSyncSanity implements IMessage{
    private NBTTagCompound data;

    public PacketSyncSanity(){

    }

    public PacketSyncSanity(ISanity cap) {
        data = (NBTTagCompound) SanityProvider.SANITY.writeNBT(cap, null);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.data = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, data);
    }

    public static class Handler implements IMessageHandler<PacketSyncSanity, IMessage> {

        @Override
        public IMessage onMessage(PacketSyncSanity message, MessageContext ctx) {
            MiskatonicMysteries.proxy.getThreadListener(ctx).addScheduledTask(() -> {
                if (Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().player.hasCapability(SanityProvider.SANITY, null)) {
                    ISanity c = Minecraft.getMinecraft().player.getCapability(SanityProvider.SANITY, null);
                    SanityProvider.SANITY.readNBT(c, null, message.data);
                }
            });
            return null;
        }

    }
}
