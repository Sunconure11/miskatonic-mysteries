package com.miskatonicmysteries.common.network.message.world;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.world.gen.BiomeManipulator;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.HashSet;

public class PacketChangeBiome implements IMessage {
    BlockPos[] positions;
    Biome biome;

    public PacketChangeBiome(World world, BlockPos pos) {
        this(world.getBiomeForCoordsBody(pos), pos);
    }

    public PacketChangeBiome(Biome biome, BlockPos... positions) {
        this.positions = positions;
        this.biome = biome;
    }

    public PacketChangeBiome() {

    }

    @Override
    public void fromBytes(ByteBuf buf) {
        biome = Biome.getBiome(buf.readByte());
        int length = buf.readShort();
        positions = new BlockPos[length];
        for (int i = 0; i < length; i++) {
            positions[i] = BlockPos.fromLong(buf.readLong());
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeByte(Biome.getIdForBiome(biome));
        buf.writeShort(positions.length);
        for (BlockPos position : positions) {
            buf.writeLong(position.toLong());
        }
    }

    public static class Handler implements IMessageHandler<PacketChangeBiome, IMessage> {
        @Override
        public IMessage onMessage(PacketChangeBiome message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                World world = MiskatonicMysteries.proxy.getPlayer(ctx).world;
                BiomeManipulator.setMultiBiome(world, message.biome, message.positions);

                HashSet<ChunkPos> finishedPos = new HashSet<>();
                for (BlockPos pos : message.positions) {
                    if (finishedPos.add(new ChunkPos(pos))) {
                        int chunkX = pos.getX() >> 4;
                        int chunkZ = pos.getZ() >> 4;

                        world.markBlockRangeForRenderUpdate(chunkX << 4, 0, chunkZ << 4, (chunkX << 4) + 15, 256, (chunkZ << 4) + 15);
                    }
                }
            });
            return null;
        }

    }
}
