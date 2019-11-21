package com.miskatonicmysteries.common.world.gen;

import com.google.common.collect.HashMultimap;
import com.miskatonicmysteries.common.network.PacketHandler;
import com.miskatonicmysteries.common.network.message.world.PacketChangeBiome;
import com.miskatonicmysteries.common.world.ExtendedWorld;
import net.minecraft.server.management.PlayerChunkMap;
import net.minecraft.server.management.PlayerChunkMapEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;

import java.util.Iterator;
import java.util.Set;

public class BiomeManipulator {
    public static void setMultiBiome(World world, Biome biome, boolean storeOld, BlockPos... poses) {
        byte id = (byte) Biome.getIdForBiome(biome);
        HashMultimap<ChunkPos, BlockPos> changes = HashMultimap.create();
        for (BlockPos pos : poses) {
            changes.put(new ChunkPos(pos), pos);
            if (storeOld)
                ExtendedWorld.addBiomeToOverrides(world, pos, world.getBiome(pos));
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
                    PacketHandler.network.sendToAll(new PacketChangeBiome(biome, storeOld, changeSet.toArray(new BlockPos[0])));
                }
            }
        }
    }

    public static void resetOverridenBiomes(World world, BlockPos pos, int radius){
        ExtendedWorld extendedWorld = ExtendedWorld.get(world);
        extendedWorld.overridenBiomes.forEach((biomePos, biome) -> {
            if (biomePos.getDistance(pos.getX(), pos.getY(), pos.getZ()) <= radius){
                setBiome(world, biome, false, biomePos);
                extendedWorld.overridenBiomes.remove(biomePos);
            }
        });
        extendedWorld.setDirty(true);
    }

    public static void resetRandomOverridenBiome(World world){
        ExtendedWorld extendedWorld = ExtendedWorld.get(world);
        BlockPos randomPos = extendedWorld.overridenBiomes.keySet().toArray(new BlockPos[extendedWorld.overridenBiomes.size()])[world.rand.nextInt(extendedWorld.overridenBiomes.size())];
        setBiome(world, extendedWorld.overridenBiomes.get(randomPos), false, randomPos);
        extendedWorld.overridenBiomes.remove(randomPos);
        extendedWorld.setDirty(true);
    }

    public static void setBiome(World world, Biome biome, boolean storeOld, BlockPos pos) {
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
                if (storeOld)
                    ExtendedWorld.addBiomeToOverrides(world, pos, world.getBiome(pos));
                PacketHandler.network.sendToAll(new PacketChangeBiome(biome, storeOld, pos));
            }
        }
    }
}
