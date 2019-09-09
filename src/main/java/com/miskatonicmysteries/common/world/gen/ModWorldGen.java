package com.miskatonicmysteries.common.world.gen;

import com.google.common.base.Predicate;
import com.miskatonicmysteries.ModConfig;
import com.miskatonicmysteries.common.world.gen.structures.WorldGenShubShrine;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.IWorldGenerator;

import javax.annotation.Nullable;
import java.util.Random;

public class ModWorldGen implements IWorldGenerator {
    private final WorldGenerator shubShrine = new WorldGenShubShrine(true);

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        System.out.println("owo");
        generateStructure(shubShrine, world, random, ModConfig.worldGen.chanceShubShrines, chunkX, chunkZ, 2, 4, b -> BiomeDictionary.hasType(b, BiomeDictionary.Type.FOREST)  || BiomeDictionary.hasType(b, BiomeDictionary.Type.DENSE));
    }

    private void generateStructure(WorldGenerator structure, World world, Random rand, double chance, int chunkX, int chunkz, int xOffset, int zOffset, Predicate<Biome> biomes) {
        BlockPos pos = new BlockPos(chunkX * 16 + xOffset, getGround(world, chunkX * 16 + xOffset, chunkz * 16 + zOffset), chunkz * 16 + zOffset);
        if (rand.nextDouble() < chance && biomes.test(world.getBiome(pos)) && biomes.test(world.getBiome(pos.add(7, 0, 7)))) structure.generate(world, rand, pos);
    }

    public static boolean canSpawnHere(Template template, World world, BlockPos posAboveGround) {
        int zwidth = template.getSize().getZ();
        int xwidth = template.getSize().getX();
        // check all the corners to see which ones are replaceable
        boolean corner1 = isCornerValid(world, posAboveGround);
        boolean corner2 = isCornerValid(world, posAboveGround.add(xwidth, 0, 0));
        boolean corner3 = isCornerValid(world, posAboveGround.add(xwidth, 0, zwidth));
        boolean corner4 = isCornerValid(world, posAboveGround.add(0, 0, zwidth));
        // if Y > 31 and all corners pass the test, it's okay to spawn the structure
        return posAboveGround.getY() > 31 && corner1 && corner2 && corner3 && corner4;
    }

   /* public static void flattenArea(Template template, World world, BlockPos posAboveGround, @Nullable Rotation rotation) {
        BlockPos ground = posAboveGround.add(0, -1, 0);
        BlockPos size = rotation != null ? template.transformedSize(rotation) : template.getSize();
        //Hinder ground from being air
        for (int x = size.getX(); x > 0; x++){
            for (int z = size.getZ(); z > 0; z++){
                if (world.getBlockState(ground.add(x, 0, z)).getMaterial().equals(Material.AIR)){
                    IBlockState filler = world.getBiome(posAboveGround).fillerBlock;
                    if (filler == null){
                        filler = Blocks.DIRT.getDefaultState();
                    }
                    System.out.println("X: " + x);
                    for (int y = -5; y < -1; y++){
                        if (!world.getBlockState(posAboveGround.add(x, y, z)).getMaterial().equals(Material.AIR)){
                            filler = world.getBlockState(posAboveGround.add(x, y, z));
                        }else{
                            world.setBlockState(posAboveGround.add(x, y, z), filler, 2);
                        }
                    }
                }
            }
        }
    }
*/
    private static int getGround(World world, int x, int z) {
        int y = world.getHeight(x, z);
        boolean foundGround = false;
        while (!foundGround && y-- >= 31) {
            Block blockAt = world.getBlockState(new BlockPos(x, y, z)).getBlock();
            foundGround = blockAt == Blocks.GRASS || blockAt == Blocks.SAND || blockAt == Blocks.SNOW || blockAt == Blocks.SNOW_LAYER || blockAt == Blocks.GLASS || blockAt == Blocks.MYCELIUM;
            if (blockAt == Blocks.WATER || blockAt == Blocks.FLOWING_WATER){
                return -1;
            }
        }
        return y;
    }

    private static boolean isCornerValid(World world, BlockPos pos) {
        int variation = 2;
        int highestBlock = getGround(world, pos.getX(), pos.getZ());
        return highestBlock > pos.getY() - variation && highestBlock < pos.getY() + variation;
    }
}
