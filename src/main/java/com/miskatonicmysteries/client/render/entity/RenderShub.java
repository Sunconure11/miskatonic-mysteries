package com.miskatonicmysteries.client.render.entity;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.client.model.entity.ModelShub;
import com.miskatonicmysteries.common.entity.EntityShub;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderShub extends RenderLiving<EntityShub> {
    public RenderShub(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelShub(), 4);
    }

    @Override
    public void doRender(EntityShub entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    public boolean shouldRender(EntityShub livingEntity, ICamera camera, double camX, double camY, double camZ) {
        return super.shouldRender(livingEntity, camera, camX, camY, camZ); //change this later
    }

    @Override
    protected void preRenderCallback(EntityShub entitylivingbaseIn, float partialTickTime) {
        float angle = Float.valueOf(I18n.format("temp.1"));
        GlStateManager.scale(2.5F, 2.5F, 2.5F);
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityShub entity) {
        return new ResourceLocation(MiskatonicMysteries.MODID, "textures/entity/shub.png");
    }
}
