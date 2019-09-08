package moriyashiine.acme.common.world.gen;

import com.google.common.base.Predicate;
import com.miskatonicmysteries.common.world.gen.structures.WorldGenShubShrine;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class ModWorldGen implements IWorldGenerator {
    private final WorldGenerator shubShrine = new WorldGenShubShrine();

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        System.out.println("owo");
        generateStructure(shubShrine, world, random, 2, chunkX, chunkZ, 2, 4, b -> BiomeDictionary.hasType(b, BiomeDictionary.Type.PLAINS) || BiomeDictionary.hasType(b, BiomeDictionary.Type.FOREST) || BiomeDictionary.hasType(b, BiomeDictionary.Type.BEACH));
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
        boolean corner2 = isCornerValid(world, posAboveGround.add(xwidth, 0, zwidth));
        // if Y > 31 and all corners pass the test, it's okay to spawn the structure
        return posAboveGround.getY() > 31 && corner1 && corner2;
    }

    private static int getGround(World world, int x, int z) {
        int y = world.getHeight(x, z);
        boolean foundGround = false;
        while (!foundGround && y-- >= 31) {
            Block blockAt = world.getBlockState(new BlockPos(x, y, z)).getBlock();
            foundGround = blockAt == Blocks.GRASS || blockAt == Blocks.SAND || blockAt == Blocks.SNOW || blockAt == Blocks.SNOW_LAYER || blockAt == Blocks.GLASS || blockAt == Blocks.MYCELIUM;
            if (blockAt == Blocks.FLOWING_WATER || blockAt == Blocks.WATER) {
                y = -1;
                break;
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
