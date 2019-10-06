package com.miskatonicmysteries.client.render.entity;

import com.miskatonicmysteries.common.entity.AbstractOldOne;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public abstract class RenderOldOne<T extends AbstractOldOne> extends RenderLiving<T> {
    public RenderOldOne(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
        super(rendermanagerIn, modelbaseIn, shadowsizeIn);
    }

    @Override
    public boolean shouldRender(T livingEntity, ICamera camera, double camX, double camY, double camZ) {
        return livingEntity.canEntityBeSeen(Minecraft.getMinecraft().getRenderViewEntity());
    }
}
