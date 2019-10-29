package com.miskatonicmysteries.common.world.gen;

import com.google.common.base.Predicate;
import com.miskatonicmysteries.ModConfig;
import com.miskatonicmysteries.common.world.gen.structures.WorldGenCthulhuShrine;
import com.miskatonicmysteries.common.world.gen.structures.WorldGenShubShrine;
import com.miskatonicmysteries.util.WorldGenUtil;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class ModWorldGen implements IWorldGenerator {
    private final WorldGenerator shubShrine = new WorldGenShubShrine();

    private final WorldGenerator luluShrine = new WorldGenCthulhuShrine();

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        generateStructure(shubShrine, world, random, ModConfig.worldGen.chanceShubShrines, chunkX, chunkZ, 1, 1, b -> BiomeDictionary.hasType(b, BiomeDictionary.Type.FOREST) || BiomeDictionary.hasType(b, BiomeDictionary.Type.DENSE));
        //add this to also generate the shrines in the depths of the ocean and not only in caves generateOceanStructure(luluShrine, world, random, ModConfig.worldGen.chanceShubShrines, chunkX, chunkZ, 1, 1, b -> BiomeDictionary.hasType(b, BiomeDictionary.Type.OCEAN) || BiomeDictionary.hasType(b, BiomeDictionary.Type.BEACH));
        //generateCaveStuff(luluShrine, world, random, ModConfig.worldGen.chanceCthulhuShrines, chunkX, chunkZ, 1, 1, b -> StructureOceanMonument.WATER_BIOMES.contains(b) || BiomeDictionary.hasType(b, BiomeDictionary.Type.OCEAN) || BiomeDictionary.hasType(b, BiomeDictionary.Type.BEACH));

    }

    private void generateStructure(WorldGenerator structure, World world, Random rand, double chance, int chunkX, int chunkz, int xOffset, int zOffset, Predicate<Biome> biomes) {
        BlockPos pos = new BlockPos(chunkX * 16 + xOffset, WorldGenUtil.getGround(world, chunkX * 16 + xOffset, chunkz * 16 + zOffset), chunkz * 16 + zOffset);
        if (rand.nextDouble() < chance && biomes.test(world.getBiome(pos)) && biomes.test(world.getBiome(pos.add(7, 0, 7))))
            structure.generate(world, rand, pos);
    }


    private void generateCaveStuff(WorldGenerator structure, World world, Random rand, double chance, int chunkX, int chunkz, int xOffset, int zOffset, Predicate<Biome> biomes) {
        BlockPos pos = new BlockPos(chunkX * 16 + xOffset, 31, chunkz * 16 + zOffset); //getCaveGround
        //replace cave top with cave ground
        if (rand.nextDouble() < chance && biomes.test(world.getBiome(pos)) && biomes.test(world.getBiome(pos.add(7, 0, 7))))
            structure.generate(world, rand, pos);
    }

    public static class CoordsWithBlock extends BlockPos {

        public final Block block;

        public CoordsWithBlock(BlockPos pos, Block block) {
            super(pos.getX(), pos.getY(), pos.getZ());
            this.block = block;
        }

    }
}
