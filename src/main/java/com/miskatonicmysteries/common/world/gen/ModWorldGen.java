package com.miskatonicmysteries.common.world.gen;

import com.google.common.base.Predicate;
import com.miskatonicmysteries.ModConfig;
import com.miskatonicmysteries.Util;
import com.miskatonicmysteries.common.item.tool.ItemTerraform;
import com.miskatonicmysteries.common.world.gen.structures.WorldGenCthulhuShrine;
import com.miskatonicmysteries.common.world.gen.structures.WorldGenShubShrine;
import com.sun.xml.internal.ws.binding.FeatureListUtil;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.structure.StructureOceanMonument;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.storage.loot.ILootContainer;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.oredict.OreDictionary;
import scala.Mutable;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ModWorldGen implements IWorldGenerator {
    private final WorldGenerator shubShrine = new WorldGenShubShrine();

    private final WorldGenerator luluShrine = new WorldGenCthulhuShrine();

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        generateStructure(shubShrine, world, random, ModConfig.worldGen.chanceShubShrines, chunkX, chunkZ, 1, 1, b -> BiomeDictionary.hasType(b, BiomeDictionary.Type.FOREST) || BiomeDictionary.hasType(b, BiomeDictionary.Type.DENSE));

        //add this to also generate the shrines in the depths of the ocean and not only in caves generateOceanStructure(luluShrine, world, random, ModConfig.worldGen.chanceShubShrines, chunkX, chunkZ, 1, 1, b -> BiomeDictionary.hasType(b, BiomeDictionary.Type.OCEAN) || BiomeDictionary.hasType(b, BiomeDictionary.Type.BEACH));
        generateCaveStuff(luluShrine, world, random, ModConfig.worldGen.chanceCthulhuShrines, chunkX, chunkZ, 1, 1, b -> StructureOceanMonument.WATER_BIOMES.contains(b) || BiomeDictionary.hasType(b, BiomeDictionary.Type.OCEAN) || BiomeDictionary.hasType(b, BiomeDictionary.Type.BEACH));

    }

    private void generateStructure(WorldGenerator structure, World world, Random rand, double chance, int chunkX, int chunkz, int xOffset, int zOffset, Predicate<Biome> biomes) {
        BlockPos pos = new BlockPos(chunkX * 16 + xOffset, getGround(world, chunkX * 16 + xOffset, chunkz * 16 + zOffset), chunkz * 16 + zOffset);
        if (rand.nextDouble() < chance && biomes.test(world.getBiome(pos)) && biomes.test(world.getBiome(pos.add(7, 0, 7))))
            structure.generate(world, rand, pos);
    }

    /*private void generateOceanStructure(WorldGenerator structure, World world, Random rand, double chance, int chunkX, int chunkz, int xOffset, int zOffset, Predicate<Biome> biomes) {
        BlockPos pos = new BlockPos(chunkX * 16 + xOffset, getActualGround(world, chunkX * 16 + xOffset, chunkz * 16 + zOffset), chunkz * 16 + zOffset);
        if (rand.nextDouble() < chance && biomes.test(world.getBiome(pos)) && biomes.test(world.getBiome(pos.add(7, 0, 7))))
            structure.generate(world, rand, pos);
    }*/

    private void generateCaveStuff(WorldGenerator structure, World world, Random rand, double chance, int chunkX, int chunkz, int xOffset, int zOffset, Predicate<Biome> biomes) {
        BlockPos pos = new BlockPos(chunkX * 16 + xOffset, 31, chunkz * 16 + zOffset); //getCaveGround
        //replace cave top with cave ground
        if (rand.nextDouble() < chance && biomes.test(world.getBiome(pos)) && biomes.test(world.getBiome(pos.add(7, 0, 7))))
            structure.generate(world, rand, pos);
    }

    public static int getGround(World world, int x, int z) {
        int y = world.getHeight(x, z);
        boolean foundGround = false;
        while (!foundGround && y-- >= 31) {
            Block blockAt = world.getBlockState(new BlockPos(x, y, z)).getBlock();
            foundGround = blockAt == Blocks.GRASS || blockAt == Blocks.SAND || blockAt == Blocks.SNOW || blockAt == Blocks.SNOW_LAYER || blockAt == Blocks.GLASS || blockAt == Blocks.MYCELIUM;
            if (blockAt == Blocks.WATER || blockAt == Blocks.FLOWING_WATER) {
                return -1;
            }
        }
        return y + 1;
    }

   public static int getCaveTop(World world, int x, int z) {
        int y = 31;
        while (y-- >= 6) { //no spawns under that height
            if (world.isAirBlock(new BlockPos(x, y, z))){//!isBlockSolid(world, new BlockPos(x, y, z))){
                return y;
            }
        }
        return -1;
    }

    public static int getCaveGround(World world, int x, int z) {
        int y = getCaveTop(world, x, z);
        while (y-- >= 6) { //no spawns under that height
            if (!world.isAirBlock(new BlockPos(x, y, z))){//if (isBlockSolid(world, new BlockPos(x, y, z))){
                return y + 1;
            }
        }
        return -1;
    }

    /*public static int getFirstCaveGround(World world, int x, int z) {
        int y = 5;
        while (y++ <= 42) { //no spawns under that height
            if (world.getBlockState(new BlockPos(x, y, z)).getBlock().equals(Blocks.AIR)){//!isBlockSolid(world, new BlockPos(x, y, z))){
                return y;
            }
        }
        return -1;
    }*/

    public static boolean checkIndestructable(World world, BlockPos pos, Block... whitelistBlock){
        TileEntity tile = world.getTileEntity(pos);
        if(tile instanceof ILootContainer){
            return true;
        }

        IBlockState state = world.getBlockState(pos);
        if(state != null){
            Block block = state.getBlock();
            if(Arrays.asList(whitelistBlock).contains(block)){
                return true;
            }
            if(block != null && (block.isAir(state, world, pos) || block.getHarvestLevel(state) >= 0F)){
                return false;
            }
        }
        return true;
    }

    public static boolean isBlockSolid(World world, BlockPos pos){
        return !(world.getBlockState(pos).getMaterial().isLiquid() || world.isAirBlock(pos) || world.getBlockState(pos).getMaterial().isReplaceable() || !world.getBlockState(pos).getMaterial().blocksMovement());
    }

    public static boolean isCornerValid(World world, BlockPos pos, int heightVar, boolean cave) {
        return isCornerValid(world, pos, heightVar, heightVar, cave);
    }

    public static boolean isCornerValid(World world, BlockPos pos, int maxHeightVar, int minHeightVar, boolean cave) {
        int highestBlock = cave ? getCaveGround(world, pos.getX(), pos.getZ()) : getGround(world, pos.getX(), pos.getZ());
        return highestBlock >= pos.getY() - minHeightVar && highestBlock <= pos.getY() + maxHeightVar; //maxHeight && minHeight
    }

    public static Rotation getRandomRotation(Random rand){
        return rand.nextBoolean() ? rand.nextBoolean() ? Rotation.NONE  : Rotation.CLOCKWISE_90 : rand.nextBoolean() ?  Rotation.COUNTERCLOCKWISE_90 : Rotation.CLOCKWISE_180;
    }

    public static boolean growRandomStuff(World world, Iterable<BlockPos.MutableBlockPos> blocks){
        List<BlockPos.MutableBlockPos> positions = (List<BlockPos.MutableBlockPos>) Util.iterableToShuffledList(blocks);
        for (BlockPos pos : positions){
            if (world.getBlockState(pos).getBlock() instanceof IGrowable){
                IGrowable growable = (IGrowable) world.getBlockState(pos).getBlock();
                if (growable.canUseBonemeal(world, world.rand, pos, world.getBlockState(pos))){
                    growable.grow(world, world.rand, pos, world.getBlockState(pos));
                    return true;
                }
            }
        }
        return false;
    }

    public static void generateSphere(int radius, BlockPos center, World world) {
        for(double x = -radius; x < radius; x++){
            for(double y = -radius; y < radius; y++){
                for(double z = -radius; z < radius; z++){
                    if(Math.sqrt((x*x)+(y*y)+(z*z)) < radius){
                        BlockPos pos = center.add(x, y, z);
                        world.setBlockToAir(pos);
                    }
                }
            }
        }
    }

    public static void generateDome(int radius, BlockPos center, World world) {
        generateDome(radius, radius, radius, center, world);
    }

    public static void generateDome(int radiusX, int radiusY, int radiusZ, BlockPos center, World world) {
        for(double x = -radiusX; x < radiusX; x++){
            for(double y = 0; y < radiusY; y++){
                for(double z = -radiusZ; z < radiusZ; z++){
                    if(Math.sqrt((x*x)+(y*y)+(z*z)) < radiusZ){
                        BlockPos pos = center.add(x, y, z);
                        world.setBlockToAir(pos);
                    }
                }
            }
        }
    }

    public static void generateGrassDome(int radiusX, int radiusY, int radiusZ, BlockPos center, World world) {
        for(double x = -radiusX; x < radiusX; x++){
            for(double y = -3; y < radiusY; y++){
                for(double z = -radiusZ; z < radiusZ; z++){
                    if(Math.sqrt((x*x)+(y*y)+(z*z)) < radiusZ){
                        BlockPos pos = center.add(x, y, z);
                        if (!checkIndestructable(world, pos,  Blocks.LOG, Blocks.LEAVES, Blocks.TALLGRASS)) {
                            if (y == -2) {
                                world.setBlockState(pos, world.getBiome(pos).topBlock);
                                world.setBlockState(pos.down(), world.getBiome(pos).fillerBlock);
                            } else
                                world.setBlockToAir(pos);
                        }
                    }
                }
            }
        }
    }


    public static class CoordsWithBlock extends BlockPos {

        public final Block block;

        public CoordsWithBlock(BlockPos pos, Block block) {
            super(pos.getX(), pos.getY(), pos.getZ());
            this.block = block;
        }

    }
}
