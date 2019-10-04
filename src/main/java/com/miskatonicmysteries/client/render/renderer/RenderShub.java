package com.miskatonicmysteries.client.render.renderer;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.client.render.models.ModelShub;
import com.miskatonicmysteries.common.entity.EntityShub;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelWolf;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderShub extends RenderLiving<EntityShub> {
    public RenderShub(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelShub(), 1.5F);
    }

    @Override
    public void doRender(EntityShub entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    public boolean shouldRender(EntityShub livingEntity, ICamera camera, double camX, double camY, double camZ) {
        return super.shouldRender(livingEntity, camera, camX, camY, camZ); //change this later
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityShub entity) {
        return new ResourceLocation(MiskatonicMysteries.MODID, "textures/entity/shub.png");
    }
}
