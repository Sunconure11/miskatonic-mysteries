package com.miskatonicmysteries.common.world.gen.structures.village;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.world.gen.processor.OldStructureProcessor;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

import java.util.List;
import java.util.Random;

public class VillageComponentHasturShrine extends StructureVillagePieces.Church {
    public static final int MAX_X = 9, MAX_Y = 9, MAX_Z = 9;
    public VillageComponentHasturShrine(StructureBoundingBox boundingBox, EnumFacing par5){
        this.setCoordBaseMode(par5);
        this.boundingBox = boundingBox;
    }

    public static VillageComponentHasturShrine buildComponent(List pieces, int p1, int p2, int p3, EnumFacing p4){
        StructureBoundingBox maxBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(p1, p2, p3, 0, 0, 0, MAX_X, MAX_Y, MAX_Z, p4);
        return canVillageGoDeeper(maxBoundingBox) && StructureComponent.findIntersecting(pieces, maxBoundingBox) == null ? new VillageComponentHasturShrine(maxBoundingBox, p4) : null;
    }


    @Override
    public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {

        /*if(getAverageGroundLevel(worldIn, structureBoundingBoxIn) < 0){
            return true;
        }*/

        WorldServer worldServer = (WorldServer) worldIn;
        MinecraftServer minecraftServer = worldIn.getMinecraftServer();
        TemplateManager templateManager = worldServer.getStructureTemplateManager();
        Template template = templateManager.getTemplate(minecraftServer, new ResourceLocation(MiskatonicMysteries.MODID,"shrines/hastur/shrine_hastur_" + (1 + worldIn.rand.nextInt(2)))); //replace with plains

        /*this.boundingBox = new StructureBoundingBox(new BlockPos(0, 0, 0), template.getSize());
        this.boundingBox.offset(0, this.getAverageGroundLevel(worldIn, structureBoundingBoxIn) -this.boundingBox.maxY+template.getSize().getY() - 1, 0);
        *///todo, fix placement issues
        BlockPos pos = new BlockPos(getXWithOffset(0, 0), getYWithOffset(0), this.getZWithOffset(0, 0));
        this.fillWithBlocks(worldIn, boundingBox, 0, 0, 0, template.getSize().getX() -1, template.getSize().getY() -1, template.getSize().getZ() -1, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), true);
        if (BiomeDictionary.hasType(worldIn.getBiome(pos), BiomeDictionary.Type.SAVANNA)) {
            template = templateManager.getTemplate(minecraftServer, new ResourceLocation(MiskatonicMysteries.MODID, "shrines/hastur/shrine_hastur_savanna_" + (1 + worldIn.rand.nextInt(2))));
        }
        PlacementSettings settings = new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setBoundingBox(new StructureBoundingBox(pos, pos.add(template.getSize())));
        template.addBlocksToWorld(worldIn, pos, new OldStructureProcessor(pos.getY(), false, true, true, worldIn.getBiome(pos)) ,settings, 2);
        /*for(int i = 0; i < template.getSize().getX(); i++){
            for(int j = 0; j < template.getSize().getZ(); j++){
                this.clearCurrentPositionBlocksUpwards(worldIn, i, template.getSize().getY(), j, boundingBox);
                this.replaceAirAndLiquidDownwards(worldIn, Blocks.DIRT.getDefaultState(), i, -1, j, boundingBox);
            }
        }*/

        return true;
    }

    @Override
    protected VillagerRegistry.VillagerProfession chooseForgeProfession(int count, VillagerRegistry.VillagerProfession prof) {
        return ForgeRegistries.VILLAGER_PROFESSIONS.getValue(new ResourceLocation("priest")); //todo might use a cultist villager instead?
    }

}
