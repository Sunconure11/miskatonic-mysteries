package com.miskatonicmysteries.registry;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.client.render.entity.*;
import com.miskatonicmysteries.common.entity.EntityDarkYoung;
import com.miskatonicmysteries.common.entity.cultist.EntityHasturCultist;
import com.miskatonicmysteries.common.entity.cultist.EntityShubCultist;
import com.miskatonicmysteries.common.entity.goo.EntityHastur;
import com.miskatonicmysteries.common.entity.goo.EntityShub;
import com.miskatonicmysteries.common.entity.projectile.EntityWaterProjectile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {
    public static int entityId = 0;
    public static void init(){
        registerEntity("dark_young", EntityDarkYoung.class, 64, 458752, 4794624);
        registerEntity("cultist_shub", EntityShubCultist.class, 64, 7171193, 3221760);
        registerEntity("cultist_hastur", EntityHasturCultist.class, 64, 7171193, 13021696);
        registerEntity("shub_niggurath", EntityShub.class, 128);
        registerEntity("hastur", EntityHastur.class, 128);


        registerEntity("projectile_water", EntityWaterProjectile.class, 16);
    }

    public static void registerRenderers(){
        RenderingRegistry.registerEntityRenderingHandler(EntityDarkYoung.class, RenderDarkYoung::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityShubCultist.class, RenderShubCultist::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityHasturCultist.class, RenderHasturCultist::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityShub.class, RenderShub::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityHastur.class, RenderHastur::new);
    }

    private static void registerEntity(String name, Class<? extends Entity> entity, int range, int color1, int color2) {
        EntityRegistry.registerModEntity(new ResourceLocation(MiskatonicMysteries.MODID, name), entity, name, entityId++, MiskatonicMysteries.instance, range, 1, false, color1, color2);
    }

    private static void registerEntity(String name, Class<? extends Entity> entity, int range) {
        EntityRegistry.registerModEntity(new ResourceLocation(MiskatonicMysteries.MODID, name), entity, name, entityId++, MiskatonicMysteries.instance, range, 1, true);
    }
}
