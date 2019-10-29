package com.miskatonicmysteries.common.network;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.network.message.capability.PacketSyncBlessing;
import com.miskatonicmysteries.common.network.message.capability.PacketSyncSanity;
import com.miskatonicmysteries.common.network.message.capability.PacketSyncSpellKnowledge;
import com.miskatonicmysteries.common.network.message.client.PacketCastSpell;
import com.miskatonicmysteries.common.network.message.client.PacketHandleKey;
import com.miskatonicmysteries.common.network.message.client.PacketYellowSign;
import com.miskatonicmysteries.common.network.message.event.PacketHandleInsanity;
import com.miskatonicmysteries.common.network.message.world.PacketChangeBiome;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.server.management.PlayerChunkMapEntry;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
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
        network.registerMessage(new PacketSyncBlessing.Handler(), PacketSyncBlessing.class, next(), Side.CLIENT);
        network.registerMessage(new PacketSyncSpellKnowledge.Handler(), PacketSyncSpellKnowledge.class, next(), Side.CLIENT);

        network.registerMessage(new PacketHandleInsanity.Handler(), PacketHandleInsanity.class, next(), Side.CLIENT);
        network.registerMessage(new PacketHandleInsanity.Handler(), PacketHandleInsanity.class, next(), Side.SERVER);

        network.registerMessage(new PacketChangeBiome.Handler(), PacketChangeBiome.class, next(), Side.CLIENT);

        network.registerMessage(new PacketHandleKey.Handler(), PacketHandleKey.class, next(), Side.SERVER);
        network.registerMessage(new PacketYellowSign.Handler(), PacketYellowSign.class, next(), Side.SERVER);

        network.registerMessage(new PacketCastSpell.Handler(), PacketCastSpell.class, next(), Side.SERVER);
        network.registerMessage(new PacketCastSpell.Handler(), PacketCastSpell.class, next(), Side.CLIENT);
    }

    public static void sendTo(EntityPlayer player, IMessage message) {
        network.sendTo(message, (EntityPlayerMP) player);
    }

    public static void updateTE(TileEntity tile) {
        if (tile != null) {
            SPacketUpdateTileEntity packet = tile.getUpdatePacket();

            if (packet != null && tile.getWorld() instanceof WorldServer) {
                PlayerChunkMapEntry chunk = ((WorldServer) tile.getWorld()).getPlayerChunkMap().getEntry(tile.getPos().getX() >> 4, tile.getPos().getZ() >> 4);
                if (chunk != null) {
                    chunk.sendPacket(packet);
                }
            }
        }
    }

    public static void updateTE(World world, BlockPos pos) {
        TileEntity tile = world.getTileEntity(pos);
        updateTE(tile);
    }

}
