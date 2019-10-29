package com.miskatonicmysteries.client.render.entity;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.client.model.entity.ModelCultist;
import com.miskatonicmysteries.common.entity.cultist.EntityHasturCultist;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderHasturCultist extends RenderBiped<EntityHasturCultist> {
    public RenderHasturCultist(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelCultist(), 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityHasturCultist entity) {
        return new ResourceLocation(MiskatonicMysteries.MODID, "textures/entity/cultist_hastur.png");
    }

    @Override
    protected void preRenderCallback(EntityHasturCultist entitylivingbaseIn, float partialTickTime) {
        float f = 0.9375F;

        if (entitylivingbaseIn.getGrowingAge() < 0) {
            f = (float)((double)f * 0.5D);
            this.shadowSize = 0.25F;
        }
        else {
            this.shadowSize = 0.5F;
        }

        GlStateManager.scale(f, f, f);
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
    }
}
