package com.miskatonicmysteries.common.world.gen.structures;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.world.gen.processor.CthulhuStructureProcessor;
import com.miskatonicmysteries.util.WorldGenUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.feature.WorldGenerator;
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
        WorldServer worldServer = (WorldServer) worldIn;
        MinecraftServer minecraftServer = worldIn.getMinecraftServer();
        TemplateManager templateManager = worldServer.getStructureTemplateManager();
        Template template = templateManager.getTemplate(minecraftServer, new ResourceLocation(MiskatonicMysteries.MODID, "shrines/cthulhu/shrine_cthulhu_stone_" + (1 + rand.nextInt(3))));
        if (rand.nextFloat() < 0.4) {
            template = templateManager.getTemplate(minecraftServer, new ResourceLocation(MiskatonicMysteries.MODID, "shrines/cthulhu/shrine_cthulhu_prismarine_" + (1 + rand.nextInt(2))));
        }
        int y = 1;
        for (int yIn = 0; yIn > -28; yIn--){
            if (worldIn.isAirBlock(position.add(0, yIn, 0))){
                y = yIn;
                break;
            }
        }
        if (y == 1){
            return false;
        }

        IBlockState iBlockState = worldIn.getBlockState(position.down());
            BlockPos offset = new BlockPos(Math.round((float)template.getSize().getX() / 2F) + 1, 0, Math.round((float)template.getSize().getZ() / 2F) + 1);
            worldIn.notifyBlockUpdate(position, iBlockState, iBlockState, 3);
            boolean centerX = template.getSize().getX() % 2 == 0;
            boolean centerZ = template.getSize().getZ() % 2 == 0;
        WorldGenUtil.generateDome(Math.round((float) template.getSize().getX() / 2F) + 3, template.getSize().getY() + 4, Math.round((float) template.getSize().getZ() / 2F) + 3, position.add(0, y, 0), worldIn);//position.add((float)template.getSize().getX() / 2F, -1, (float)template.getSize().getZ() / 2F), worldIn);
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(Rotation.NONE).setIntegrity(1);//.setBoundingBox(new StructureBoundingBox(position, position.add(template.getSize())));
            template.addBlocksToWorld(worldIn, position.subtract(offset).add(centerX ? 1 : 2, y, centerZ ? 1 : 2), new CthulhuStructureProcessor(position.getY() + y - 1), placementsettings, 2);
        return true;
    }
}
