package com.miskatonicmysteries.common.world.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.biome.BiomeJungle;
import net.minecraft.world.biome.BiomeMushroomIsland;

public class BiomeShub extends Biome {
    public BiomeShub() {
        super(new BiomeProperties("corrupted_lands_goat").setTemperature(0.8F).setRainfall(1.2F));
    }

    @Override
    public boolean isMutation() {
        return true;
    }

    @Override
    public boolean isHighHumidity() {
        return true;
    }

}
