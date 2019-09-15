package com.miskatonicmysteries.common.world.gen.structures.village;

import com.miskatonicmysteries.ModConfig;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

import java.util.List;
import java.util.Random;

public class VillageHasturShrineHandler implements VillagerRegistry.IVillageCreationHandler{

    @Override
    public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int i) {
        return new StructureVillagePieces.PieceWeight(VillageComponentHasturShrine.class, 6, random.nextDouble() < ModConfig.worldGen.chanceHasturShrines ? 1 : 0);
    }

    @Override
    public Class<?> getComponentClass() {
        return VillageComponentHasturShrine.class;
    }

    @Override
    public StructureVillagePieces.Village buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List<StructureComponent> pieces, Random random, int p1, int p2, int p3, EnumFacing facing, int p5) {
        return VillageComponentHasturShrine.buildComponent(pieces, p1, p2, p3, facing);
    }
}
