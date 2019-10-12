package com.miskatonicmysteries.client.render;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Supplier;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = MiskatonicMysteries.MODID, value = Side.CLIENT)
public class ParticleRenderHandler {
    private static final List<Particle> PARTICLES = new CopyOnWriteArrayList<>();

    @SubscribeEvent
    public static void onRenderLast(RenderWorldLastEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        mc.mcProfiler.func_194340_a(() -> MiskatonicMysteries.MODID + ":renderParticles");
        renderParticles(event.getPartialTicks());
        mc.mcProfiler.endSection();
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (Minecraft.getMinecraft().world == null) {
            clearParticles();
        } else if (!Minecraft.getMinecraft().isGamePaused()) {
            Minecraft.getMinecraft().mcProfiler.func_194340_a(() -> MiskatonicMysteries.MODID + ":updateParticles");
            updateParticles();
            Minecraft.getMinecraft().mcProfiler.endSection();
        }
    }

    public static void spawnParticle(Supplier<Particle> particle) {
        Minecraft mc = Minecraft.getMinecraft();
        switch ( ModConfig.client.particleAmount) {
            case STANDARD:
                break;
            case REDUCED:
                if (mc.world.rand.nextInt(3) != 0) {
                    return;
                }
                break;
        }
        PARTICLES.add(particle.get());
    }

    public static void updateParticles() {
        updateList(PARTICLES);
    }

    private static void updateList(List<Particle> particles) {
        for (int i = particles.size() - 1; i >= 0; i--) {
            Particle particle = particles.get(i);
            particle.onUpdate();
            if (!particle.isAlive())
                particles.remove(i);
        }
    }

    public static void renderParticles(float partialTicks) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = mc.player;
        if (player != null) {
            float x = ActiveRenderInfo.getRotationX();
            float z = ActiveRenderInfo.getRotationZ();
            float yz = ActiveRenderInfo.getRotationYZ();
            float xy = ActiveRenderInfo.getRotationXY();
            float xz = ActiveRenderInfo.getRotationXZ();

            Particle.interpPosX = player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks;
            Particle.interpPosY = player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks;
            Particle.interpPosZ = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks;
            Particle.cameraViewDir = player.getLook(partialTicks);

            GlStateManager.pushMatrix();

            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.alphaFunc(516, 0.003921569F);
            GlStateManager.disableCull();
            GlStateManager.depthMask(false);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            Tessellator t = Tessellator.getInstance();
            BufferBuilder buffer = t.getBuffer();
            for (Particle particle : PARTICLES) {
                particle.renderParticle(buffer, player, partialTicks, x, xz, z, yz, xy);
            }
            GlStateManager.enableCull();
            GlStateManager.depthMask(true);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA); //ONE_MINUS_SRC_ALPHA
            GlStateManager.disableBlend();
            GlStateManager.alphaFunc(516, 0.1F);

            GlStateManager.popMatrix();
        }
    }

    public static int getParticleAmount(boolean depth) {
        return PARTICLES.size();
    }

    public static void clearParticles() {
        if (!PARTICLES.isEmpty())
            PARTICLES.clear();
    }
}
