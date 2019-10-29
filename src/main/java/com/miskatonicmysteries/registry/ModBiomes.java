package com.miskatonicmysteries.registry;

import com.miskatonicmysteries.MiskatonicMysteries;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(MiskatonicMysteries.MODID)
public class ModBiomes {
    public static void registerBiomes(){

    }

    private static Biome initBiome(Biome biome, String name, boolean genInWorld, BiomeManager.BiomeType biomeType, BiomeDictionary.Type... type){
        biome.setRegistryName(MiskatonicMysteries.MODID, name);
        ForgeRegistries.BIOMES.register(biome);
        BiomeDictionary.addTypes(biome, type);
        if (genInWorld) {
            BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(biome, 10));
            BiomeManager.addSpawnBiome(biome);
        }
        return biome;
    }
}
