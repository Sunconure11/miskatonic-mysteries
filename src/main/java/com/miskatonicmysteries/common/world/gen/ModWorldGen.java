package com.miskatonicmysteries.common.world.gen;

import com.google.common.base.Predicate;
import com.miskatonicmysteries.ModConfig;
import com.miskatonicmysteries.Util;
import com.miskatonicmysteries.common.item.tool.ItemTerraform;
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
    //Fixme, make world gen beautiful again :(
    private final WorldGenerator shubShrine1 = new WorldGenShubShrine(1, false);
    private final WorldGenerator shubShrine2 = new WorldGenShubShrine(2, false);
    private final WorldGenerator shubShrine3 = new WorldGenShubShrine(3, false);
    private final WorldGenerator shubShrine4 = new WorldGenShubShrine(4, false);
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        generateStructure(shubShrine1, world, random, ModConfig.worldGen.chanceShubShrines / 4F, chunkX, chunkZ, 1, 1, b -> BiomeDictionary.hasType(b, BiomeDictionary.Type.FOREST) || BiomeDictionary.hasType(b, BiomeDictionary.Type.DENSE));
        generateStructure(shubShrine2, world, random, ModConfig.worldGen.chanceShubShrines / 4F, chunkX, chunkZ, 1, 1, b -> BiomeDictionary.hasType(b, BiomeDictionary.Type.FOREST) || BiomeDictionary.hasType(b, BiomeDictionary.Type.DENSE));
        generateStructure(shubShrine3, world, random, ModConfig.worldGen.chanceShubShrines / 4F, chunkX, chunkZ, 1, 1, b -> BiomeDictionary.hasType(b, BiomeDictionary.Type.FOREST) || BiomeDictionary.hasType(b, BiomeDictionary.Type.DENSE));
        generateStructure(shubShrine4, world, random, ModConfig.worldGen.chanceShubShrines / 4F, chunkX, chunkZ, 1, 1, b -> BiomeDictionary.hasType(b, BiomeDictionary.Type.FOREST) || BiomeDictionary.hasType(b, BiomeDictionary.Type.DENSE));
    }

    private void generateStructure(WorldGenerator structure, World world, Random rand, double chance, int chunkX, int chunkz, int xOffset, int zOffset, Predicate<Biome> biomes) {
        BlockPos pos = new BlockPos(chunkX * 16 + xOffset, getGround(world, chunkX * 16 + xOffset, chunkz * 16 + zOffset), chunkz * 16 + zOffset);
        if (rand.nextDouble() < chance && biomes.test(world.getBiome(pos)) && biomes.test(world.getBiome(pos.add(7, 0, 7))))
            structure.generate(world, rand, pos);
    }

    private void generateCaveStuff(WorldGenerator structure, World world, Random rand, double chance, int chunkX, int chunkz, int xOffset, int zOffset, Predicate<Biome> biomes) {
        BlockPos pos = new BlockPos(chunkX * 16 + xOffset, getCaveGround(world, chunkX * 16 + xOffset, chunkX * 16 + zOffset), chunkz * 16 + zOffset);
        if (rand.nextDouble() < chance && biomes.test(world.getBiome(pos)) && biomes.test(world.getBiome(pos.add(7, 0, 7))))
            structure.generate(world, rand, pos);
    }

    public static boolean canSpawnHere(Template template, World world, BlockPos posAboveGround) {
        int zwidth = template.getSize().getZ();
        int xwidth = template.getSize().getX();
        boolean corner1 = isCornerValid(world, posAboveGround.add(-1, 0, -1), false);
        boolean corner2 = isCornerValid(world, posAboveGround.add(xwidth + 1, 0, -1), false);
        boolean corner3 = isCornerValid(world, posAboveGround.add(xwidth + 1, 0, zwidth + 1), false);
        boolean corner4 = isCornerValid(world, posAboveGround.add(1, 0, zwidth + 1), false);
        return posAboveGround.getY() > 31 && corner1 && corner2 && corner3 && corner4;
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
        boolean foundAir = false;
        while (!foundAir && y-- >= 6) { //no spawns under that height
            foundAir = !isBlockSolid(world, new BlockPos(x, y, z));
        }
        return foundAir ? y : world.rand.nextInt(25) + 10;
    }

    public static int getCaveGround(World world, int x, int z) {
        int y = getCaveTop(world, x, z);
        boolean foundGround = false;
        while (!foundGround && y-- >= 6) { //no spawns under that height
            if (isBlockSolid(world, new BlockPos(x, y, z))){
                return y + 1;
            }
        }
        return world.rand.nextInt(25) + 10;
    }

    public static boolean checkIndestructable(World world, BlockPos pos, Block... whitelistBlock){
        TileEntity tile = world.getTileEntity(pos);
        if(tile instanceof ILootContainer){
            return true;
        }

        IBlockState state = world.getBlockState(pos);
        if(state != null){
            Block block = state.getBlock();
            //check if it's tree or grass that is generated here
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

    public static boolean isCornerValid(World world, BlockPos pos, boolean cave) {
        return isCornerValid(world, pos, 1, cave);
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

    public static boolean growTrees(World world, Iterable<BlockPos.MutableBlockPos> blocks){
        for (BlockPos pos : getPossibleGrowingPos(world, blocks)){
            WorldGenAbstractTree tree = new WorldGenBirchTree(true, false);//Blocks.LOG.getDefaultState(), Blocks.LEAVES.getDefaultState());// world.getBiome(pos).getRandomTreeFeature(world.rand);
            if (tree.generate(world, world.rand, pos.up())){
                System.out.println(":(");
                tree.generateSaplings(world, world.rand, pos.up());
            }
        }
        return false;
    }

    public static List<BlockPos> getPossibleGrowingPos(World world, Iterable<BlockPos.MutableBlockPos> blocks){
        List<BlockPos> pos = new ArrayList<>();
        for (BlockPos block : blocks){
            if (world.getBlockState(block).getBlock().equals(Blocks.GRASS)){
                pos.add(block);
            }
        }
        Collections.shuffle(pos);
        return pos;
    }

    public static boolean growVines(World world, Iterable<BlockPos.MutableBlockPos> blocks){
        List<BlockPos.MutableBlockPos> positions = (List<BlockPos.MutableBlockPos>) Util.iterableToShuffledList(blocks);
        for (BlockPos pos : positions){
            WorldGenVines vines = new WorldGenVines();
            if (vines.generate(world, world.rand, pos)){
                System.out.println(":)");
                return true;
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
