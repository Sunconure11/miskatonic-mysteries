package com.miskatonicmysteries.client.render.entity;

import com.miskatonicmysteries.common.entity.goo.AbstractOldOne;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSlime;

public abstract class RenderOldOne<T extends AbstractOldOne> extends RenderLiving<T> {
    float origShadowSize;
    public RenderOldOne(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
        super(rendermanagerIn, modelbaseIn, shadowsizeIn);
        origShadowSize = shadowsizeIn;
    }

    @Override
    protected void preRenderCallback(T entitylivingbaseIn, float partialTickTime) {
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
    }

    @Override
    public boolean shouldRender(T livingEntity, ICamera camera, double camX, double camY, double camZ) {
        return !livingEntity.isParticleForm() && (Minecraft.getMinecraft().player.isCreative() || livingEntity.canEntityBeSeen(Minecraft.getMinecraft().player));
    }
}
