package com.miskatonicmysteries.client.particles;

import com.miskatonicmysteries.util.ColorUtil;
import net.minecraft.client.particle.ParticleEnchantmentTable;
import net.minecraft.client.particle.ParticleSmokeNormal;
import net.minecraft.world.World;

import java.awt.*;

public class ParticleGOOSmoke extends ParticleSmokeNormal{
    public ParticleGOOSmoke(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, int decColor) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0, 0, 0, 1.2F);
        particleRed = (((decColor >> 16) & 255) / 255F) * (1F - this.rand.nextFloat() * 0.25F);
        particleGreen = (((decColor >> 8) & 255) / 255F) * (1F - this.rand.nextFloat() * 0.25F);
        particleBlue = ((decColor & 255) / 255F) * (1F - this.rand.nextFloat() * 0.25F);

    }
}
