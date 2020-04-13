package com.miskatonicmysteries.client.render.entity;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.client.model.entity.dark_young.ModelAltDarkYoung;
import com.miskatonicmysteries.client.model.entity.dark_young.ModelDarkYoung;
import com.miskatonicmysteries.client.render.entity.layer.LayerDarkYoungEyes;
import com.miskatonicmysteries.common.entity.EntityDarkYoung;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderDarkYoung extends RenderLiving<EntityDarkYoung> {
    public static final ResourceLocation DARK_YOUNG_TEX = new ResourceLocation(MiskatonicMysteries.MODID, "textures/entity/dark_young/dark_young_1.png");
    public static final ResourceLocation DARK_YOUNG_TEX_ALT = new ResourceLocation(MiskatonicMysteries.MODID, "textures/entity/dark_young/dark_young_2.png");

    public static final ModelBase DARK_YOUNG_0 = new ModelDarkYoung();
    public static final ModelBase DARK_YOUNG_1 = new ModelAltDarkYoung();
    public RenderDarkYoung(RenderManager rendermanagerIn) {
        super(rendermanagerIn, DARK_YOUNG_0, 1F);
        this.addLayer(new LayerDarkYoungEyes(this));
    }

    @Override
    public void doRender(EntityDarkYoung entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
        GlStateManager.popMatrix();
    }

    @Override
    protected void preRenderCallback(EntityDarkYoung entitylivingbaseIn, float partialTickTime) {
        mainModel = getMainModel(entitylivingbaseIn);
        GlStateManager.scale(1.5, 1.5, 1.5);
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
    }

    public ModelBase getMainModel(EntityDarkYoung darkYoung) {
        if (darkYoung.getType() == 1){
            return DARK_YOUNG_1;
        }
        return DARK_YOUNG_0;
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityDarkYoung entity) {
        return entity.getType() == 0 ? DARK_YOUNG_TEX : DARK_YOUNG_TEX_ALT;
    }
}
