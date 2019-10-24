package com.miskatonicmysteries.client.particles;

import com.miskatonicmysteries.MiskatonicMysteries;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEnchantmentTable;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class ParticleOccultEnchant extends ParticleEnchantmentTable{
    protected double xTo, yTo, zTo;
    protected boolean followPos = false;
    public ParticleOccultEnchant(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, double xTo, double yTo, double zTo) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
        followPos = true;
        this.xTo = xTo;
        this.yTo = yTo;
        this.zTo = zTo;
    }

    public ParticleOccultEnchant(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
    }

    @Override
    public void onUpdate() {
        if (followPos) {
            double velX = motionX = (xTo - posX) / 20;
            double velY = motionY = (yTo - posY) / 20;
            double velZ = motionZ = (zTo - posZ) / 20;
            double vel = Math.sqrt(Math.abs(velX) + Math.abs(velY) + Math.abs(velZ));
            if (vel > 0.05) {
                double percentageX = velX / vel;
                double percentageY = velY / vel;
                double percentageZ = velZ / vel;
                motionX = 0.1 * percentageX;
                motionY = 0.1 * percentageY;
                motionZ = 0.1 * percentageZ;
            } else {
                motionX = velX;
                motionY = velY;
                motionZ = velZ;
            }
            this.prevPosX = this.posX;
            this.prevPosY = this.posY;
            this.prevPosZ = this.posZ;

            this.particleAge++;
            if (this.particleAge >= this.particleMaxAge) {
                this.setExpired();
            } else {
                this.motionY -= 0.04D * (double) this.particleGravity;
                this.move(this.motionX, this.motionY, this.motionZ);

            }
        }else{
            super.onUpdate();
        }
    }
}
