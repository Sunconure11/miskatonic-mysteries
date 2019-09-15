package com.miskatonicmysteries.common.world.gen.structures;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.world.gen.ModWorldGen;
import com.miskatonicmysteries.common.world.gen.processor.CthulhuStructureProcessor;
import com.miskatonicmysteries.common.world.gen.processor.ShubStructureProcessor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

public class WorldGenCthulhuShrine extends WorldGenerator {
    public WorldGenCthulhuShrine() {
        super(true);
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        /*BlockPos actualPos = null;
        for (BlockPos pos : BlockPos.getAllInBox(position.add(-2, -1, -2), position.add(2, 1, 2))){
            if (canSpawnHere(worldIn, pos)){
                actualPos = pos;
                break;
            }
        }
        if (actualPos == null) {
            return false;
        }*/
        WorldServer worldServer = (WorldServer) worldIn;
        MinecraftServer minecraftServer = worldIn.getMinecraftServer();
        TemplateManager templateManager = worldServer.getStructureTemplateManager();
        Template template = templateManager.getTemplate(minecraftServer, new ResourceLocation(MiskatonicMysteries.MODID, "shrines/cthulhu/shrine_cthulhu_stone_" + (1 + rand.nextInt(3))));
        if (rand.nextFloat() < 0.4) {
            template = templateManager.getTemplate(minecraftServer, new ResourceLocation(MiskatonicMysteries.MODID, "shrines/cthulhu/shrine_cthulhu_prismarine_" + (1 + rand.nextInt(2))));
        }
        IBlockState iBlockState = worldIn.getBlockState(position.down());
        if (iBlockState.getBlock().equals(Blocks.STONE)) {
            BlockPos offset = new BlockPos(Math.round((float)template.getSize().getX() / 2F) + 1, 0, Math.round((float)template.getSize().getZ() / 2F) + 1);
            worldIn.notifyBlockUpdate(position, iBlockState, iBlockState, 3);
            boolean centerX = template.getSize().getX() % 2 == 0;
            boolean centerZ = template.getSize().getZ() % 2 == 0;
            ModWorldGen.generateDome(Math.round((float) template.getSize().getX() / 2F) + 6, template.getSize().getY() + 4, Math.round((float) template.getSize().getZ() / 2F) + 6, position.add(offset), worldIn);//position.add((float)template.getSize().getX() / 2F, -1, (float)template.getSize().getZ() / 2F), worldIn);
            //make special dome method that randomizes it a bit more, and does not replace non-solid blocks with air
            //StructureBoundingBox box = new StructureBoundingBox(offset, offset.add(template.getSize()));
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(Rotation.NONE).setIntegrity(1);//.setBoundingBox(new StructureBoundingBox(position, position.add(template.getSize())));
            template.addBlocksToWorld(worldIn, position.add(centerX ? 1 : 2, 0, centerZ ? 1 : 2), new CthulhuStructureProcessor(position.getY() - 1), placementsettings, 2);
            System.out.println("generated at " + position); //subtract(offset).add(centerX ? 1 : 2, 0, centerZ ? 1 : 2)
            return true;
        }
        return false;
    }

    private boolean canSpawnHere(World world, BlockPos pos){//int x, int z){
       // BlockPos posIn = pos; //new BlockPos(x, ModWorldGen.getCaveGround(world, x, z), z);
        int count = 0;
        for (int xIn = -2; xIn < 2; xIn++){
            for (int yIn = -2; yIn < 2; yIn++){
                for (int zIn = -2; zIn < 2; zIn++){
                    if (ModWorldGen.isBlockSolid(world, pos.add(xIn, yIn, zIn))){
                        count++;
                    }
                    if (count >= 4){
                        return pos.getY() <= 6;
                    }
                }
            }
        }
        return false;
    }


   /* private boolean canSpawnHere(Template template, World world, BlockPos posAboveGround) {
        int zwidth = template.getSize().getZ();
        int xwidth = template.getSize().getX();
        boolean corner1 = isCornerValid(world, posAboveGround.add(0, 0, 0), 0);
        boolean corner2 = isCornerValid(world, posAboveGround.add(xwidth, 0, 0), 0);
        boolean corner3 = isCornerValid(world, posAboveGround.add(xwidth, 0, zwidth), 0);
        boolean corner4 = isCornerValid(world, posAboveGround.add(0, 0, zwidth), 0);
        return posAboveGround.getY() > 11 && corner1 && corner2 && corner3 && corner4;
    }

    private boolean isCornerValid(World world, BlockPos pos, int heightVar) {
        int highestBlock = ModWorldGen.getActualGround(world, pos.getX(), pos.getZ());
        return highestBlock >= pos.getY() - heightVar && highestBlock <= pos.getY() + heightVar; //maxHeight && minHeight
    }*/
}
