package com.miskatonicmysteries.client.render.entity.layer;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.client.render.entity.RenderDarkYoung;
import com.miskatonicmysteries.common.entity.EntityDarkYoung;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerDarkYoungEyes implements LayerRenderer<EntityDarkYoung> {
    private final RenderDarkYoung darkYoungRenderer;
    public static final ResourceLocation EYES_TEX = new ResourceLocation(MiskatonicMysteries.MODID, "textures/entity/dark_young/dark_young_1_eyes.png");
    public static final ResourceLocation EYES_TEX_ALT = new ResourceLocation(MiskatonicMysteries.MODID, "textures/entity/dark_young/dark_young_2_eyes.png");

    public LayerDarkYoungEyes(RenderDarkYoung render) {
        this.darkYoungRenderer = render;
    }

    @Override
    public void doRenderLayer(EntityDarkYoung entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (!entitylivingbaseIn.isInvisible()) {
            this.darkYoungRenderer.bindTexture(entitylivingbaseIn.getType() == 0 ? EYES_TEX : EYES_TEX_ALT);
            GlStateManager.pushMatrix();
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();


            GlStateManager.color(1.0F, 1.0F, 1.0F, 1F);

            int i = 81680;
            int j = i % 65536;
            int k = i / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j, k);
            Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
            this.darkYoungRenderer.getMainModel(entitylivingbaseIn).render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            Minecraft.getMinecraft().entityRenderer.setupFogColor(false);


            GlStateManager.color(1.0F, 1.0F, 1.0F, 1F);

            i = entitylivingbaseIn.getBrightnessForRender();
            j = i % 65536;
            k = i / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j, k);
            this.darkYoungRenderer.setLightmap(entitylivingbaseIn);

            GlStateManager.disableAlpha();
            GlStateManager.disableBlend();


            GlStateManager.popMatrix();
        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return true;
    }
}
