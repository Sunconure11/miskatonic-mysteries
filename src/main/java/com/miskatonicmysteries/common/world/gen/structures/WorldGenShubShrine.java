package com.miskatonicmysteries.common.world.gen.structures;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.entity.cultist.EntityShubCultist;
import com.miskatonicmysteries.common.world.gen.processor.ShubStructureProcessor;
import com.miskatonicmysteries.util.WorldGenUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

public class WorldGenShubShrine extends WorldGenerator {
    private static final Biome.SpawnListEntry ENTRY_CULTISTS_SHUB = new Biome.SpawnListEntry(EntityShubCultist.class, 1, 2, 5);
    public WorldGenShubShrine() {
        super(true);
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        WorldServer worldServer = (WorldServer) worldIn;
        MinecraftServer minecraftServer = worldIn.getMinecraftServer();
        TemplateManager templateManager = worldServer.getStructureTemplateManager();
        Template template = templateManager.getTemplate(minecraftServer, new ResourceLocation(MiskatonicMysteries.MODID,"shrines/shub/shrine_shubniggurath_forest_" + (1 + rand.nextInt(4))));

        if (canSpawnHere(template, worldServer, position)) {
            IBlockState iBlockState = worldIn.getBlockState(position);
            worldIn.notifyBlockUpdate(position, iBlockState, iBlockState, 3);
            Mirror mirror;
            Rotation rotation;
            switch (rand.nextInt(3)){
                case 0:
                    mirror = Mirror.LEFT_RIGHT;
                    break;
                case 1:
                    mirror = Mirror.FRONT_BACK;
                    break;
                default:
                case 2:
                    mirror = Mirror.NONE;
                    break;
            }
            switch (rand.nextInt(4)){
                case 0:
                    rotation = Rotation.CLOCKWISE_90;
                    break;
                case 1:
                    rotation = Rotation.CLOCKWISE_180;
                    break;
                case 2:
                    rotation = Rotation.COUNTERCLOCKWISE_90;
                    break;
                default:
                case 3:
                    rotation = Rotation.NONE;
                    break;
            }

            PlacementSettings placementsettings = (new PlacementSettings()).setMirror(mirror).setRotation(rotation).setIntegrity(1).setBoundingBox(new StructureBoundingBox(position, position.add(template.getSize())));
            BlockPos basePos = template.getZeroPositionWithTransform(position, mirror, rotation);
            template.addBlocksToWorld(worldIn, basePos, new ShubStructureProcessor(basePos.getY(), worldIn.getBiome(basePos).isSnowyBiome(), rotation, mirror), placementsettings, 2);
            WorldGenUtil.spawnEntities(ENTRY_CULTISTS_SHUB, worldIn, Math.round(basePos.getX() + template.getSize().getX() / 4F), Math.round(basePos.getZ() + template.getSize().getZ() / 4F), 7, 7, rand);
            return true;
        }
        return false;
    }

    private boolean canSpawnHere(Template template, World world, BlockPos posAboveGround) {
        int zwidth = template.getSize().getZ();
        int xwidth = template.getSize().getX();
        boolean corner1 = WorldGenUtil.isCornerValid(world, posAboveGround.add(0, 0, 0), 1, false);
        boolean corner2 = WorldGenUtil.isCornerValid(world, posAboveGround.add(xwidth, 0, 0), 1, false);
        boolean corner3 = WorldGenUtil.isCornerValid(world, posAboveGround.add(xwidth, 0, zwidth), 1, false);
        boolean corner4 = WorldGenUtil.isCornerValid(world, posAboveGround.add(0, 0, zwidth), 1, false);
        return posAboveGround.getY() > 31 && corner1 && corner2 && corner3 && corner4;
    }
}
