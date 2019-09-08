package com.miskatonicmysteries.common.network;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.network.message.capability.PacketSyncSanity;
import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.network.message.capability.PacketSyncSanity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {
    public static SimpleNetworkWrapper network;

    private static int nextId = 0;

    public static int next() {
        return nextId++;
    }
    public static void init() {
        network = NetworkRegistry.INSTANCE.newSimpleChannel(MiskatonicMysteries.MODID);

        network.registerMessage(new PacketSyncSanity.Handler(), PacketSyncSanity.class, next(), Side.CLIENT);
    }

    public static void sendTo(EntityPlayer player, IMessage message) {
        network.sendTo(message, (EntityPlayerMP) player);
    }
}
