package com.miskatonicmysteries.registry;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.client.render.entity.RenderHastur;
import com.miskatonicmysteries.client.render.entity.RenderHasturCultist;
import com.miskatonicmysteries.client.render.entity.RenderShub;
import com.miskatonicmysteries.client.render.entity.RenderShubCultist;
import com.miskatonicmysteries.common.entity.cultist.EntityHasturCultist;
import com.miskatonicmysteries.common.entity.cultist.EntityShubCultist;
import com.miskatonicmysteries.common.entity.goo.EntityHastur;
import com.miskatonicmysteries.common.entity.goo.EntityShub;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {
    public static int entityId = 0;
    public static void init(){
        registerEntity("cultist_shub", EntityShubCultist.class, 64, 7171193, 3221760);
        registerEntity("cultist_hastur", EntityHasturCultist.class, 64, 7171193, 13021696);
        registerEntity("shub_niggurath", EntityShub.class, 128);
        registerEntity("hastur", EntityHastur.class, 128);
    }

    public static void registerRenderers(){
        //add render for shub cultist, add field for when it's U P G R A D E D or not; this will also be relevant in the model
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
