package com.miskatonicmysteries.client.render.entity;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.client.model.entity.ModelCultistShub;
import com.miskatonicmysteries.common.entity.cultist.EntityShubCultist;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderShubCultist extends RenderBiped<EntityShubCultist> {
    public RenderShubCultist(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelCultistShub(), 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityShubCultist entity) {
        return new ResourceLocation(MiskatonicMysteries.MODID, "textures/entity/cultist_shub_" + (entity.isGoat() ? 2 : 1) + ".png");
    }

    @Override
    protected void preRenderCallback(EntityShubCultist entitylivingbaseIn, float partialTickTime) {
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
