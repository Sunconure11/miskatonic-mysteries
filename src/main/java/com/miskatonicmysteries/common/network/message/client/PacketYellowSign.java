package com.miskatonicmysteries.common.network.message.client;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.blessing.BlessingCapability;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.capability.sanity.Sanity;
import com.miskatonicmysteries.common.capability.spells.ISpellKnowledge;
import com.miskatonicmysteries.common.capability.spells.SpellKnowledge;
import com.miskatonicmysteries.common.handler.SpellHandler;
import com.miskatonicmysteries.common.potion.ModPotion;
import com.miskatonicmysteries.registry.ModPotions;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketYellowSign implements IMessage{
    public PacketYellowSign(){

    }


    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<PacketYellowSign, IMessage> {

        @Override
        public IMessage onMessage(PacketYellowSign message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                EntityPlayer player = MiskatonicMysteries.proxy.getPlayer(ctx);
                if (Sanity.Util.getSanityCapability(player).getHorrifiedCooldown() <= 0 && player.getActivePotionEffect(ModPotions.mania) == null && BlessingCapability.Util.getBlessing(player) != Blessing.HASTUR){
                    player.addPotionEffect(new PotionEffect(ModPotions.mania, 3600, 0, true, true));
                    Sanity.Util.getSanityCapability(player).setHorrifiedCooldown(600);
                }
            });
            return null;
        }
    }
}
