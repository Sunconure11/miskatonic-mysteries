package com.miskatonicmysteries.common.world.biomes;

import net.minecraft.world.biome.Biome;

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
