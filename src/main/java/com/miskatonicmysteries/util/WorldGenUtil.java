package com.miskatonicmysteries.util;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.loot.ILootContainer;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static net.minecraft.world.WorldEntitySpawner.canCreatureTypeSpawnAtLocation;

public class WorldGenUtil {

    public static void spawnEntities(Biome.SpawnListEntry spawnEntry, World worldIn, int centerX, int centerZ, int diameterX, int diameterZ, Random randomIn) {
        int entityCount = spawnEntry.minGroupCount + randomIn.nextInt(1 + spawnEntry.maxGroupCount - spawnEntry.minGroupCount);
        IEntityLivingData ientitylivingdata = null;
        int posX = centerX + randomIn.nextInt(diameterX);
        int posZ = centerZ + randomIn.nextInt(diameterZ);
        int posXOrig = posX;
        int posZOrig = posZ;

        for (int j1 = 0; j1 < entityCount; ++j1) {
            boolean flag = false;

            for (int k1 = 0; !flag && k1 < 4; ++k1) {
                BlockPos blockpos = worldIn.getTopSolidOrLiquidBlock(new BlockPos(posX, 0, posZ));

                if (canCreatureTypeSpawnAtLocation(EntityLiving.SpawnPlacementType.ON_GROUND, worldIn, blockpos)) {
                    EntityLiving entityliving;

                    try {
                        entityliving = spawnEntry.newInstance(worldIn);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        continue;
                    }

                    if (net.minecraftforge.event.ForgeEventFactory.canEntitySpawn(entityliving, worldIn, posX + 0.5f, (float) blockpos.getY(), posZ + 0.5f, false) == net.minecraftforge.fml.common.eventhandler.Event.Result.DENY)
                        continue;
                    entityliving.setLocationAndAngles((double) ((float) posX + 0.5F), (double) blockpos.getY(), (double) ((float) posZ + 0.5F), randomIn.nextFloat() * 360.0F, 0.0F);
                    worldIn.spawnEntity(entityliving);
                    ientitylivingdata = entityliving.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityliving)), ientitylivingdata);
                    flag = true;
                }

                posX += randomIn.nextInt(5) - randomIn.nextInt(5);

                for (posZ += randomIn.nextInt(5) - randomIn.nextInt(5); posX < centerX || posX >= centerX + diameterX || posZ < centerZ || posZ >= centerZ + diameterX; posZ = posZOrig + randomIn.nextInt(5) - randomIn.nextInt(5)) {
                    posX = posXOrig + randomIn.nextInt(5) - randomIn.nextInt(5);
                }
            }
        }
    }

    public static void spawnEntity(EntityLiving entityliving, World world, BlockPos origPos, int attempts, float maxDistance) {
        float posX = origPos.getX();
        float posZ = origPos.getZ();
        float posXOrig = posX;
        float posZOrig = posZ;
        for (int j1 = 0; j1 < attempts; ++j1) {
            boolean flag = false;

            for (int k1 = 0; !flag && k1 < 4; ++k1) {
                BlockPos blockpos = getTopSolidOrLiquidBlock(world, new BlockPos(posX, origPos.getY(), posZ));

                if (canCreatureTypeSpawnAtLocation(EntityLiving.SpawnPlacementType.ON_GROUND, world, blockpos)) {
                    if (ForgeEventFactory.canEntitySpawn(entityliving, world, posX + 0.5f, (float) blockpos.getY(), posZ + 0.5f, false) == Event.Result.DENY)
                        continue;
                    entityliving.setLocationAndAngles((double) (posX + 0.5F), (double) blockpos.getY(), (double) (posZ + 0.5F), world.rand.nextFloat() * 360.0F, 0.0F);
                    world.spawnEntity(entityliving);
                    entityliving.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(entityliving)), null);
                    return;
                }

                posX += world.rand.nextInt(5) - world.rand.nextInt(5);

                for (posZ += world.rand.nextInt(5) - world.rand.nextInt(5); posX < origPos.getX() || posX >= origPos.getX() + maxDistance || posZ < origPos.getZ() || posZ >= origPos.getZ() + maxDistance; posZ = posZOrig + world.rand.nextInt(5) - world.rand.nextInt(5)) {
                    posX = posXOrig + world.rand.nextInt(5) - world.rand.nextInt(5);
                }
            }
        }
    }

    public static BlockPos getTopSolidOrLiquidBlock(World world, BlockPos pos) {
        Chunk chunk = world.getChunkFromBlockCoords(pos);
        BlockPos blockpos;
        BlockPos blockpos1;

        for (blockpos = new BlockPos(pos.getX(), pos.getY() + 5, pos.getZ()); blockpos.getY() >= 0; blockpos = blockpos1) {
            blockpos1 = blockpos.down();
            IBlockState state = chunk.getBlockState(blockpos1);

            if (state.getMaterial().blocksMovement() && !state.getBlock().isLeaves(state, world, blockpos1) && !state.getBlock().isFoliage(world, blockpos1)) {
                break;
            }
        }

        return blockpos;
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
            if (world.isAirBlock(new BlockPos(x, y, z))) {
                return y;
            }
        }
        return -1;
    }

    public static int getCaveGround(World world, int x, int z) {
        int y = getCaveTop(world, x, z);
        while (y-- >= 6) { //no spawns under that height
            if (!world.isAirBlock(new BlockPos(x, y, z))) {//if (isBlockSolid(world, new BlockPos(x, y, z))){
                return y + 1;
            }
        }
        return -1;
    }

    public static boolean checkIndestructable(World world, BlockPos pos, Block... whitelistBlock) {
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof ILootContainer) {
            return true;
        }

        IBlockState state = world.getBlockState(pos);
        if (state != null) {
            Block block = state.getBlock();
            if (Arrays.asList(whitelistBlock).contains(block)) {
                return true;
            }
            return block == null || (!block.isAir(state, world, pos) && !(block.getHarvestLevel(state) >= 0F));
        }
        return true;
    }

    public static boolean isBlockSolid(World world, BlockPos pos) {
        return !(world.getBlockState(pos).getMaterial().isLiquid() || world.isAirBlock(pos) || world.getBlockState(pos).getMaterial().isReplaceable() || !world.getBlockState(pos).getMaterial().blocksMovement());
    }

    public static boolean isCornerValid(World world, BlockPos pos, int heightVar, boolean cave) {
        return isCornerValid(world, pos, heightVar, heightVar, cave);
    }

    public static boolean isCornerValid(World world, BlockPos pos, int maxHeightVar, int minHeightVar, boolean cave) {
        int highestBlock = cave ? getCaveGround(world, pos.getX(), pos.getZ()) : getGround(world, pos.getX(), pos.getZ());
        return highestBlock >= pos.getY() - minHeightVar && highestBlock <= pos.getY() + maxHeightVar; //maxHeight && minHeight
    }

    public static Rotation getRandomRotation(Random rand) {
        return rand.nextBoolean() ? rand.nextBoolean() ? Rotation.NONE : Rotation.CLOCKWISE_90 : rand.nextBoolean() ? Rotation.COUNTERCLOCKWISE_90 : Rotation.CLOCKWISE_180;
    }

    public static boolean growRandomStuff(World world, Iterable<BlockPos.MutableBlockPos> blocks) {
        List<BlockPos.MutableBlockPos> positions = (List<BlockPos.MutableBlockPos>) ListUtil.iterableToShuffledList(blocks);
        for (BlockPos pos : positions) {
            if (world.getBlockState(pos).getBlock() instanceof IGrowable) {
                IGrowable growable = (IGrowable) world.getBlockState(pos).getBlock();
                if (growable.canUseBonemeal(world, world.rand, pos, world.getBlockState(pos))) {
                    growable.grow(world, world.rand, pos, world.getBlockState(pos));
                    return true;
                }
            }
        }
        return false;
    }

    public static void generateSphere(int radius, BlockPos center, World world) {
        for (double x = -radius; x < radius; x++) {
            for (double y = -radius; y < radius; y++) {
                for (double z = -radius; z < radius; z++) {
                    if (Math.sqrt((x * x) + (y * y) + (z * z)) < radius) {
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
        for (double x = -radiusX; x < radiusX; x++) {
            for (double y = 0; y < radiusY; y++) {
                for (double z = -radiusZ; z < radiusZ; z++) {
                    if (Math.sqrt((x * x) + (y * y) + (z * z)) < radiusZ) {
                        BlockPos pos = center.add(x, y, z);
                        world.setBlockToAir(pos);
                    }
                }
            }
        }
    }
}
