package com.miskatonicmysteries.registry;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.world.biome.AreaHastur;
import com.miskatonicmysteries.common.world.biome.AreaShub;
import com.miskatonicmysteries.common.world.biome.GreatOldOneArea;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.HashMap;
import java.util.Map;

@GameRegistry.ObjectHolder(MiskatonicMysteries.MODID)
public class ModBiomes {
    public static Map<String, GreatOldOneArea> GOO_EFFECT_MAP = new HashMap<>();

    public static GreatOldOneArea SHUB; //GOO areas will actually be biomes that are stored in the extendedWorld, so they may be reset after a while
    public static GreatOldOneArea HASTUR;

    public static void registerBiomes(){
        SHUB = new AreaShub();
        HASTUR = new AreaHastur();
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
