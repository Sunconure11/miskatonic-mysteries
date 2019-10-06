package com.miskatonicmysteries.common.world.gen;

import com.google.common.collect.HashMultimap;
import com.miskatonicmysteries.common.network.PacketHandler;
import com.miskatonicmysteries.common.network.message.world.PacketChangeBiome;
import io.netty.util.internal.ConcurrentSet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.server.management.PlayerChunkMap;
import net.minecraft.server.management.PlayerChunkMapEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.relauncher.Side;
import vazkii.patchouli.common.network.NetworkHandler;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BiomeManipulator {
   /* public static final Map<BiomeDictionary.Type, ItemRef> types;

    static {
        types = ImmutableMap.<BiomeDictionary.Type, ItemRef>builder()
                .put(BiomeDictionary.Type.END, ItemRef.wrap(Items.ENDER_PEARL))
                .put(BiomeDictionary.Type.NETHER, ItemRef.wrap(Items.NETHER_WART))
                .build();

    }*/

    public static void setMultiBiome(World world, Biome biome, BlockPos... poses) {
        byte id = (byte) Biome.getIdForBiome(biome);
        HashMultimap<ChunkPos, BlockPos> changes = HashMultimap.create();
        for (BlockPos pos : poses) {
            changes.put(new ChunkPos(pos), pos);
        }
        for (ChunkPos chunkPos : changes.keySet().toArray(new ChunkPos[changes.keySet().size()])) {
            Chunk chunk = world.getChunkFromChunkCoords(chunkPos.x, chunkPos.z);
            byte[] biomeArray = chunk.getBiomeArray();
            Set<BlockPos> changeSet = changes.get(chunkPos);
            for (Iterator<BlockPos> iterator = changeSet.iterator(); iterator.hasNext(); ) {
                BlockPos pos = iterator.next();
                int i = pos.getX() & 15;
                int j = pos.getZ() & 15;
                if (biomeArray[j << 4 | i] == id) {
                    iterator.remove();
                } else {
                    biomeArray[j << 4 | i] = id;
                }
            }
        }

        if (world instanceof WorldServer) {
            PlayerChunkMap playerChunkMap = ((WorldServer) world).getPlayerChunkMap();
            for (ChunkPos chunkPos : changes.keySet()) {
                Set<BlockPos> changeSet = changes.get(chunkPos);
                if (changeSet.isEmpty()) continue;

                PlayerChunkMapEntry entry = playerChunkMap.getEntry(chunkPos.x, chunkPos.z);
                if (entry != null) {
                    PacketHandler.network.sendToAll(new PacketChangeBiome(biome, changeSet.toArray(new BlockPos[0])));
                }
            }
        }
    }

    public static void setBiome(World world, Biome biome, BlockPos pos) {
        Chunk chunk = world.getChunkFromBlockCoords(pos);

        int i = pos.getX() & 15;
        int j = pos.getZ() & 15;

        byte id = (byte) Biome.getIdForBiome(biome);

        byte b = chunk.getBiomeArray()[j << 4 | i];

        if (b == id) return;

        chunk.getBiomeArray()[j << 4 | i] = id;
        chunk.markDirty();

        if (world instanceof WorldServer) {
            PlayerChunkMap playerChunkMap = ((WorldServer) world).getPlayerChunkMap();
            int chunkX = pos.getX() >> 4;
            int chunkZ = pos.getZ() >> 4;

            PlayerChunkMapEntry entry = playerChunkMap.getEntry(chunkX, chunkZ);
            if (entry != null) {
                PacketHandler.network.sendToAll(new PacketChangeBiome(biome, pos));
            }
        }
    }
}
