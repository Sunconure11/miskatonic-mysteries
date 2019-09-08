package com.miskatonicmysteries.client.render.shaders;

import com.google.gson.JsonSyntaxException;
import com.miskatonicmysteries.ModConfig;
import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.Sanity;
import com.miskatonicmysteries.registry.ModPotions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class ShaderHandler {
    public static HashMap<Integer, ShaderGroup> shaderGroups = new HashMap<>();
    public static boolean resetShaders = false;

    private static int oldDisplayWidth = 0;
    private static int oldDisplayHeight = 0;

    public static final int SHADER_INSANITY = 0; //hopefully, once custom shaders come in, the intensity can hopefully be handled
    public static final int SHADER_TRANQ = 1; //in the code, instead of making
    public static final int SHADER_MANIA = 2; //separate shaders for each intensity

    public static ResourceLocation[] shader_resources = new ResourceLocation[]{new ResourceLocation("shaders/post/deconverge.json"), new ResourceLocation("shaders/post/antialias.json"), new ResourceLocation("shaders/post/phosphor.json")};
    //these shaders are temporary, I'm just not good enough to make shaders :(

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void doRender(TickEvent.PlayerTickEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        if (ModConfig.client.useShaders && event.side == Side.CLIENT) {
            if (event.phase == TickEvent.Phase.START) {
                handleShaders(event, mc);
            }
        }
    }

    public void handleShaders(TickEvent.PlayerTickEvent event, Minecraft mc) {
        handleShader(Sanity.getSanity(event.player) < 100, SHADER_INSANITY); //just an example
        handleShader(event.player.getActivePotionEffect(ModPotions.tranquilized) != null, SHADER_TRANQ);
        handleShader(event.player.getActivePotionEffect(ModPotions.mania) != null, SHADER_MANIA);
    }

    public static void handleShader(boolean condition, int shaderId) {
        if (condition) {
            if (!shaderGroups.containsKey(shaderId)) {
                setShader(shader_resources[shaderId], shaderId);
            }
        } else if (shaderGroups.containsKey(shaderId)) {
            deactivateShader(shaderId);
        }
    }

    public static void setShader(ResourceLocation shader, int shaderId) {
        try {
            ShaderGroup target = new ShaderGroup(Minecraft.getMinecraft().getTextureManager(), Minecraft.getMinecraft().getResourceManager(), Minecraft.getMinecraft().getFramebuffer(), shader_resources[shaderId]);
            if (OpenGlHelper.areShadersSupported()) {
                if (shaderGroups.containsKey(shaderId)) {
                    ((ShaderGroup) shaderGroups.get(shaderId)).deleteShaderGroup();
                    shaderGroups.remove(shaderId);
                }

                try {
                    if (target == null) {
                        deactivateShader(shaderId);
                    } else {
                        resetShaders = true;
                        shaderGroups.put(shaderId, target);
                    }
                } catch (Exception var4) {
                    shaderGroups.remove(shaderId);
                }
            }
        } catch (JsonSyntaxException exception) {
            MiskatonicMysteries.LOGGER.fatal("An incorrect shader file syntax was detected!");
            exception.printStackTrace();
        } catch (IOException exception) {
            MiskatonicMysteries.LOGGER.fatal("Shader file was not found!");
            exception.printStackTrace();
        }
    }

    public static void deactivateShader(int shaderId) {
        if (shaderGroups.containsKey(shaderId)) {
            (shaderGroups.get(shaderId)).deleteShaderGroup();
        }

        shaderGroups.remove(shaderId);
    }

    @SubscribeEvent
    public void renderShaders(RenderGameOverlayEvent.Pre event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            Minecraft mc = Minecraft.getMinecraft();
            if (OpenGlHelper.areShadersSupported() && shaderGroups.size() > 0) {
                updateShaderFrameBuffers(mc);
                GL11.glMatrixMode(5890);
                GL11.glLoadIdentity();

                for (Iterator var2 = shaderGroups.values().iterator(); var2.hasNext(); GL11.glPopMatrix()) {
                    ShaderGroup sg = (ShaderGroup) var2.next();
                    GL11.glPushMatrix();

                    try {
                        sg.render(event.getPartialTicks());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                mc.getFramebuffer().bindFramebuffer(true);
            }
        }

    }

    public static void updateShaderFrameBuffers(Minecraft mc) {
        if (resetShaders || mc.displayWidth != oldDisplayWidth || oldDisplayHeight != mc.displayHeight) {
            Iterator var1 = shaderGroups.values().iterator();

            while (var1.hasNext()) {
                ShaderGroup sg = (ShaderGroup) var1.next();
                sg.createBindFramebuffers(mc.displayWidth, mc.displayHeight);
            }

            oldDisplayWidth = mc.displayWidth;
            oldDisplayHeight = mc.displayHeight;
            resetShaders = false;
        }

    }
}
