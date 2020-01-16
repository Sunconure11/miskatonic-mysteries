package com.miskatonicmysteries.common.network.message.capability;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.blessing.BlessingProvider;
import com.miskatonicmysteries.common.capability.blessing.IBlessingCapability;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.capability.sanity.SanityProvider;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSyncBlessing implements IMessage{
    private String blessing;

    public PacketSyncBlessing(){
        blessing = "";
    }

    public PacketSyncBlessing(IBlessingCapability cap) {
        this.blessing = cap.getBlessing().getName();
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        this.blessing = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, blessing);
    }

    public static class Handler implements IMessageHandler<PacketSyncBlessing, IMessage> {

        @Override
        public IMessage onMessage(PacketSyncBlessing message, MessageContext ctx) {
            MiskatonicMysteries.proxy.getThreadListener(ctx).addScheduledTask(() -> {
                if (Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().player.hasCapability(SanityProvider.SANITY, null)) {
                    IBlessingCapability c = Minecraft.getMinecraft().player.getCapability(BlessingProvider.BLESSING, null);
                    c.setBlessing(Blessing.getBlessing(message.blessing));
                    c.setDirty(false);
                }
            });
            return null;
        }

    }
}
