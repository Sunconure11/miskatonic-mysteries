package com.miskatonicmysteries.client.render.renderer;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.client.render.models.ModelShub;
import com.miskatonicmysteries.common.entity.EntityShub;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderShub extends RenderLiving<EntityShub> {
    public RenderShub(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelShub(), 1.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityShub entity) {
        return new ResourceLocation(MiskatonicMysteries.MODID, "textures/entity/shub.png");
    }
}
