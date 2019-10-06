package com.miskatonicmysteries.common.world.gen.village;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.world.gen.processor.HasturStructureProcessor;
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
    public static final int MAX_X = 12, MAX_Y = 9, MAX_Z = 12;
    public VillageComponentHasturShrine(StructureBoundingBox boundingBox, EnumFacing par5){
        this.setCoordBaseMode(par5);
        this.boundingBox = boundingBox;
    }

    public void init(){

    }

    public static VillageComponentHasturShrine buildComponent(List pieces, int p1, int p2, int p3, EnumFacing p4){
        StructureBoundingBox maxBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(p1, p2, p3, 0, 0, 0, MAX_X, MAX_Y, MAX_Z, p4);
        return canVillageGoDeeper(maxBoundingBox) && StructureComponent.findIntersecting(pieces, maxBoundingBox) == null ? new VillageComponentHasturShrine(maxBoundingBox, p4) : null;
    }

    @Override
    public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
        if(this.averageGroundLvl < 0){
            this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);
            if(this.averageGroundLvl < 0){
                return true;
            }
            this.boundingBox.offset(0, this.averageGroundLvl-this.boundingBox.maxY+MAX_Y-2, 0);
        }
        WorldServer worldServer = (WorldServer) worldIn;
        MinecraftServer minecraftServer = worldIn.getMinecraftServer();
        TemplateManager templateManager = worldServer.getStructureTemplateManager();
        Template template = templateManager.getTemplate(minecraftServer, new ResourceLocation(MiskatonicMysteries.MODID,"shrines/hastur/shrine_hastur_" + (1 + worldIn.rand.nextInt(2)))); //replace with plains
        if (BiomeDictionary.hasType(worldIn.getBiome(new BlockPos(getXWithOffset(0, 0), averageGroundLvl, getZWithOffset(0, 0))), BiomeDictionary.Type.SAVANNA)) {
            template = templateManager.getTemplate(minecraftServer, new ResourceLocation(MiskatonicMysteries.MODID, "shrines/hastur/shrine_hastur_savanna_" + (1 + worldIn.rand.nextInt(2))));
        }
        if (BiomeDictionary.hasType(worldIn.getBiome(new BlockPos(getXWithOffset(0, 0), averageGroundLvl, getZWithOffset(0, 0))), BiomeDictionary.Type.SANDY)){
            return true;
        }
        EnumFacing facing = this.getCoordBaseMode();

        Mirror mirror;
        Rotation rotation;
        if(facing == EnumFacing.SOUTH){
            mirror = Mirror.NONE;
            rotation = Rotation.NONE;
        }
        else if(facing == EnumFacing.WEST){
            mirror = Mirror.NONE;
            rotation = Rotation.CLOCKWISE_90;
        }
        else if(facing == EnumFacing.EAST){
            mirror = Mirror.LEFT_RIGHT;
            rotation = Rotation.CLOCKWISE_90;
        }
        else{
            mirror = Mirror.LEFT_RIGHT;
            rotation = Rotation.NONE;
        }
        for(int x = 0; x < template.getSize().getX(); x++){
            for(int z = 0; z < template.getSize().getZ(); z++){
                this.clearCurrentPositionBlocksUpwards(worldIn, x, 0, z, boundingBox);
                this.replaceAirAndLiquidDownwards(worldIn, Blocks.DIRT.getDefaultState(), x, 0, z, boundingBox);
            }
        }
        PlacementSettings settings = new PlacementSettings().setRotation(rotation).setMirror(mirror);
        template.addBlocksToWorld(worldIn, new BlockPos(getXWithOffset(0, 0), averageGroundLvl, getZWithOffset(0, 0)), new HasturStructureProcessor(averageGroundLvl), settings, 2);
        return true;
    }

    @Override
    protected VillagerRegistry.VillagerProfession chooseForgeProfession(int count, VillagerRegistry.VillagerProfession prof) {
        return ForgeRegistries.VILLAGER_PROFESSIONS.getValue(new ResourceLocation("priest")); //todo might use a cultist villager instead?
    }

}
