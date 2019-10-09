package com.miskatonicmysteries.client.render.entity;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.client.model.entity.ModelCultistHastur;
import com.miskatonicmysteries.client.model.entity.ModelHastur;
import com.miskatonicmysteries.common.entity.cultist.AbstractCultist;
import com.miskatonicmysteries.common.entity.cultist.EntityHasturCultist;
import com.miskatonicmysteries.common.entity.goo.EntityHastur;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderVillager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderHasturCultist extends RenderLiving<EntityHasturCultist> {
    public RenderHasturCultist(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelCultistHastur(), 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityHasturCultist entity) {
        return new ResourceLocation(MiskatonicMysteries.MODID, "textures/entity/cultist_hastur.png");
    }
}
