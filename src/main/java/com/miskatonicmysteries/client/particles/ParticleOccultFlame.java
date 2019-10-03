package com.miskatonicmysteries.client.particles;

import com.miskatonicmysteries.MiskatonicMysteries;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class ParticleOccultFlame extends net.minecraft.client.particle.ParticleFlame{
    public static final ResourceLocation NORMAL_TEX = new ResourceLocation(MiskatonicMysteries.MODID, "textures/particle/flame.png");
    public static final ResourceLocation ALT_TEX = new ResourceLocation(MiskatonicMysteries.MODID, "textures/particle/candleflame.png");
    public ParticleOccultFlame(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
        particleMaxAge += 20;
    }

    @Override
    public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
        Minecraft.getMinecraft().getTextureManager().bindTexture(NORMAL_TEX); //particleAge < 10 ? ALT_TEX : ALT_TEX);
        double x = this.prevPosX + (this.posX - this.prevPosX) * partialTicks - interpPosX;
        double y = this.prevPosY + (this.posY - this.prevPosY) * partialTicks - interpPosY;
        double z = this.prevPosZ + (this.posZ - this.prevPosZ) * partialTicks - interpPosZ;
        float sc = 0.1F * this.particleScale;

        int brightness = this.getBrightnessForRender(partialTicks);
        int sky = brightness >> 16 & 0xFFFF;
        int block = brightness & 0xFFFF;

        buffer.pos(x + (-rotationX * sc - rotationXY * sc), y + -rotationZ * sc, z + (-rotationYZ * sc - rotationXZ * sc))
                .tex(1, 1).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
                .lightmap(sky, block).endVertex();
        buffer.pos(x + (-rotationX * sc + rotationXY * sc), y + (rotationZ * sc), z + (-rotationYZ * sc + rotationXZ * sc))
                .tex(1, 0).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
                .lightmap(sky, block).endVertex();
        buffer.pos(x + (rotationX * sc + rotationXY * sc), y + (rotationZ * sc), z + (rotationYZ * sc + rotationXZ * sc))
                .tex(0, 0).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
                .lightmap(sky, block).endVertex();
        buffer.pos(x + (rotationX * sc - rotationXY * sc), y + (-rotationZ * sc), z + (rotationYZ * sc - rotationXZ * sc))
                .tex(0, 1).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
                .lightmap(sky, block).endVertex();
        Tessellator.getInstance().draw();
    }

    @Override
    public int getBrightnessForRender(float p_189214_1_) {
        return super.getBrightnessForRender(p_189214_1_);
    }
}
