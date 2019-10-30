package com.miskatonicmysteries.registry;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.ModConfig;
import com.miskatonicmysteries.client.render.entity.*;
import com.miskatonicmysteries.common.entity.EntityDarkYoung;
import com.miskatonicmysteries.common.entity.cultist.EntityHasturCultist;
import com.miskatonicmysteries.common.entity.cultist.EntityShubCultist;
import com.miskatonicmysteries.common.entity.goo.EntityHastur;
import com.miskatonicmysteries.common.entity.goo.EntityShub;
import com.miskatonicmysteries.common.entity.projectile.EntityPersonalProjectile;
import com.miskatonicmysteries.common.entity.projectile.EntityWaterProjectile;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ModEntities {
    public static int entityId = 0;
    public static void init(){
        registerEntity("dark_young", EntityDarkYoung.class, 64, 458752, 4794624);
        registerEntity("cultist_shub", EntityShubCultist.class, 64, 7171193, 3221760);
        registerEntity("cultist_hastur", EntityHasturCultist.class, 64, 7171193, 13021696);
        registerEntity("shub_niggurath", EntityShub.class, 128);
        registerEntity("hastur", EntityHastur.class, 128);

        registerEntity("projectile_water", EntityWaterProjectile.class, 16);
        registerEntity("projectile_personal", EntityPersonalProjectile.class, 16);

        addNaturalSpawns();
    }

    public static void registerRenderers(){
        RenderingRegistry.registerEntityRenderingHandler(EntityDarkYoung.class, RenderDarkYoung::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityShubCultist.class, RenderShubCultist::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityHasturCultist.class, RenderHasturCultist::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityShub.class, RenderShub::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityHastur.class, RenderHastur::new);


        RenderingRegistry.registerEntityRenderingHandler(EntityWaterProjectile.class, manager -> new Render<EntityWaterProjectile>(manager) {
            @Nullable
            @Override
            protected ResourceLocation getEntityTexture(EntityWaterProjectile entity) {
                return null;
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPersonalProjectile.class, manager -> new Render<EntityPersonalProjectile>(manager) {
            @Nullable
            @Override
            protected ResourceLocation getEntityTexture(EntityPersonalProjectile entity) {
                return null;
            }
        });
    }

    private static void registerEntity(String name, Class<? extends Entity> entity, int range, int color1, int color2) {
        EntityRegistry.registerModEntity(new ResourceLocation(MiskatonicMysteries.MODID, name), entity, name, entityId++, MiskatonicMysteries.instance, range, 1, false, color1, color2);
    }

    private static void registerEntity(String name, Class<? extends Entity> entity, int range) {
        EntityRegistry.registerModEntity(new ResourceLocation(MiskatonicMysteries.MODID, name), entity, name, entityId++, MiskatonicMysteries.instance, range, 1, true);
    }

    private static void addNaturalSpawns() {
        addSpawn(EntityDarkYoung.class, ModConfig.entities.darkYoungSpawnRate, ModConfig.entities.darkYoungGroupMin, ModConfig.entities.darkYoungGroupMax, ModConfig.entities.darkYoungBiomes);
    }

    private static void addSpawn(Class<? extends EntityLiving> entityClass, int spawnRate, int minGroup, int maxGroup, String... types) {
        Set<Biome> biomes = new HashSet<>();
        for (String typeName : types) biomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.getType(typeName)));
        if (spawnRate > 0) {
            for (Biome biome : biomes) {
                if (biome != null) {
                    List<Biome.SpawnListEntry> spawnList = biome.getSpawnableList(EnumCreatureType.MONSTER);
                    spawnList.add(new Biome.SpawnListEntry(entityClass, spawnRate, minGroup, maxGroup));
                }
            }
        }
    }
}
