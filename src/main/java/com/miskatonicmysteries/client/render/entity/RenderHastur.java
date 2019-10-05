package com.miskatonicmysteries.client.render.entity;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.client.model.entity.ModelHastur;
import com.miskatonicmysteries.common.entity.EntityHastur;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderHastur extends RenderLiving<EntityHastur> {
    public RenderHastur(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelHastur(), 1.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityHastur entity) {
        return new ResourceLocation(MiskatonicMysteries.MODID, "textures/entity/hastur.png");
    }
}
