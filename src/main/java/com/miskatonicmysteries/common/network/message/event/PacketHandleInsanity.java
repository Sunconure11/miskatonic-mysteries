package com.miskatonicmysteries.common.network.message.event;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.sanity.Sanity;
import com.miskatonicmysteries.registry.ModInsanityEffects;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketHandleInsanity implements IMessage{
    private ResourceLocation effect;

    public PacketHandleInsanity(){

    }

    public PacketHandleInsanity(ResourceLocation effect) {
        this.effect = effect;
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        this.effect = new ResourceLocation(ByteBufUtils.readUTF8String(buf));
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, effect.toString());
    }

    public static class Handler implements IMessageHandler<PacketHandleInsanity, IMessage> {

        @Override
        public IMessage onMessage(PacketHandleInsanity message, MessageContext ctx) {
            MiskatonicMysteries.proxy.getThreadListener(ctx).addScheduledTask(() -> {
                EntityPlayer player = MiskatonicMysteries.proxy.getPlayer(ctx);
                ModInsanityEffects.INSANITY_EFFECTS.get(message.effect).handle(player.world, player, Sanity.Util.getSanityCapability(player));
            });
            return null;
        }

    }
}
