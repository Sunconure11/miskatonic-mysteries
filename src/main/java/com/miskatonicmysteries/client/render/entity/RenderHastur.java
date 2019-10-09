package com.miskatonicmysteries.client.render.entity;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.client.model.entity.ModelHastur;
import com.miskatonicmysteries.common.entity.goo.EntityHastur;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderHastur extends RenderOldOne<EntityHastur> {
    public RenderHastur(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelHastur(), 2.25F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityHastur entity) {
        return new ResourceLocation(MiskatonicMysteries.MODID, "textures/entity/hastur.png");
    }

    @Override
    protected void preRenderCallback(EntityHastur entitylivingbaseIn, float partialTickTime) {
        GlStateManager.scale(2, 2, 2);
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
    }
}
