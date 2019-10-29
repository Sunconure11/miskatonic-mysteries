package com.miskatonicmysteries.common.world.gen.structures;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.entity.cultist.EntityShubCultist;
import com.miskatonicmysteries.common.world.gen.processor.ShubStructureProcessor;
import com.miskatonicmysteries.util.WorldGenUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
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
        if (canSpawnHere(template, worldServer, position)) { //better flattening is needed
            IBlockState iBlockState = worldIn.getBlockState(position);
            worldIn.notifyBlockUpdate(position, iBlockState, iBlockState, 3);
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(Rotation.NONE).setIntegrity(1).setBoundingBox(new StructureBoundingBox(position, position.add(template.getSize())));
            template.addBlocksToWorld(worldIn, position, new ShubStructureProcessor(position.getY(), worldIn.getBiome(position).isSnowyBiome()), placementsettings, 2);
            WorldGenUtil.spawnEntities(ENTRY_CULTISTS_SHUB, worldIn, Math.round(position.getX() + template.getSize().getX() / 4F), Math.round(position.getZ() + template.getSize().getZ() / 4F), 7, 7, rand);
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
