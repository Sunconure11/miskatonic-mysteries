package com.miskatonicmysteries.common.network.message.capability;

import com.miskatonicmysteries.common.capability.ISanity;
import com.miskatonicmysteries.common.capability.SanityProvider;
import io.netty.buffer.ByteBuf;
import com.miskatonicmysteries.common.capability.SanityProvider;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSyncSanity implements IMessage{
    private int sanity = 150;
    private int expansionAmount;
    private String[] expansionTags;
    private Integer[] expansionValues;

    public PacketSyncSanity(){

    }

    public PacketSyncSanity(ISanity cap) {
        this.sanity = cap.getSanity();
        this.expansionAmount = cap.getExpansionMap().size();
        if (!cap.getExpansionMap().isEmpty()) {
            this.expansionTags = cap.getExpansionMap().keySet().toArray(new String[cap.getExpansionMap().keySet().size()]);
            this.expansionValues = cap.getExpansionMap().values().toArray(new Integer[cap.getExpansionMap().values().size()]);
            this.expansionAmount = expansionTags.length;
        }
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        this.sanity = buf.readInt();
        this.expansionAmount = buf.readInt();
        this.expansionTags = new String[expansionAmount];
        this.expansionValues = new Integer[expansionAmount];
        for (int i = 0; i < expansionAmount; i++){
            this.expansionValues[i] = buf.readInt();
            this.expansionTags[i] = ByteBufUtils.readUTF8String(buf);
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.sanity);
        buf.writeInt(this.expansionAmount);
        for (int i = 0; i < expansionAmount; i++){
            buf.writeInt(this.expansionValues[i]);
            ByteBufUtils.writeUTF8String(buf, this.expansionTags[i]);
        }
    }

    public static class Handler implements IMessageHandler<PacketSyncSanity, IMessage> {

        @Override
        public IMessage onMessage(PacketSyncSanity message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                if (Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().player.hasCapability(SanityProvider.SANITY, null)) {
                    ISanity c = Minecraft.getMinecraft().player.getCapability(SanityProvider.SANITY, null);
                    c.setSanity(message.sanity);
                    c.getExpansionMap().clear();
                    for (int i = 0; i < message.expansionAmount; i++){
                        c.getExpansionMap().put(message.expansionTags[i], message.expansionValues[i]);
                    }
                }
            });
            return null;
        }

    }
}
