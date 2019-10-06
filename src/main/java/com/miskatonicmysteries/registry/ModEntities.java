package com.miskatonicmysteries.registry;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.client.render.entity.RenderHastur;
import com.miskatonicmysteries.client.render.entity.RenderShub;
import com.miskatonicmysteries.common.entity.EntityHastur;
import com.miskatonicmysteries.common.entity.EntityShub;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {
    public static int entityId = 0;
    public static void init(){
        registerEntity("shub_niggurath", EntityShub.class, 64);
        registerEntity("hastur", EntityHastur.class, 64);
    }

    public static void registerRenderers(){
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
