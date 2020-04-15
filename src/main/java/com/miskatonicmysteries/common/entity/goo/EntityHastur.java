package com.miskatonicmysteries.common.entity.goo;

import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.world.biome.GreatOldOneArea;
import com.miskatonicmysteries.registry.ModBiomes;
import net.minecraft.init.Biomes;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class EntityHastur extends AbstractOldOne {
    public EntityHastur(World worldIn) {
        super(worldIn);
        setSize(2.5F, 9);
    }

    @Override
    public Blessing getAssociatedBlessing() {
        return Blessing.HASTUR;
    }

    @Override
    public Biome getDistortionBiome() {
        return Biomes.MUSHROOM_ISLAND;
    }

    @Override
    public int getParticleColor() {
        return 13217024;
    }
}
