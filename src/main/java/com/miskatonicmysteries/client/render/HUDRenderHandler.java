package com.miskatonicmysteries.client.render;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.ModConfig;
import com.miskatonicmysteries.common.capability.spells.ISpellKnowledge;
import com.miskatonicmysteries.common.capability.spells.SpellKnowledge;
import com.miskatonicmysteries.common.misc.spells.Spell;
import com.miskatonicmysteries.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Supplier;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = MiskatonicMysteries.MODID, value = Side.CLIENT)
public class HUDRenderHandler {
    protected static final ResourceLocation WIDGETS_TEX_PATH = new ResourceLocation("textures/gui/widgets.png");
    @SubscribeEvent
    public static void renderHUD(RenderGameOverlayEvent.Post event){
        if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR && !event.isCancelable()) {
            renderSpellSelection(event);
        }
    }

    private static void renderSpellSelection(RenderGameOverlayEvent.Post event) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = mc.player;
        ISpellKnowledge knowledge = SpellKnowledge.Util.getKnowledge(player);
        Spell[] spells = knowledge.getSpells();
        if (spells.length > 0){
            for (int i = 0; i < spells.length; i++) {
                Spell spellIn = spells[i];
                ResourceLocation tex = spellIn.iconLocation;
                int posX = event.getResolution().getScaledWidth() - 18;/// 2 - 88; // + 10;
                int poxY = event.getResolution().getScaledHeight() / 2; //draw these inventory selection slots for the spells, too
                //82 to 22 40 to 91
                float execFactor =  Math.min((float)Math.max(knowledge.getCurrentCastingProgess(), 0) / (float) Math.max(0, spellIn.getCastTime()), 1F);
                drawColoredTexturedModalRect(posX - 2, poxY + (i * 20), 16, 16, i == knowledge.getCurrentSpell() ? ColorUtil.blend(Color.WHITE, Color.YELLOW, 1 - execFactor, execFactor) : new Color(7698299), i == knowledge.getCurrentSpell() ? 0.8F : 0.4F, tex);
            }
        }
    }

    public static void drawColoredTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height, Color color, float alpha, ResourceLocation resTex) {
        GlStateManager.enableNormalize();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

        float zLevel = -90.0F;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
        bufferbuilder.pos(x, y + height, zLevel).tex(textureX * 0.00390625F, (textureY + height) * 0.00390625F).color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, alpha).endVertex();
        bufferbuilder.pos(x + width, y + height, zLevel).tex((textureX + width) * 0.00390625F,(textureY + height) * 0.00390625F).color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, alpha).endVertex();
        bufferbuilder.pos(x + width, y, zLevel).tex( (textureX + width) * 0.00390625F, textureY * 0.00390625F).color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, alpha).endVertex();
        bufferbuilder.pos(x, y, zLevel).tex(textureX * 0.00390625F,textureY * 0.00390625F).color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, alpha).endVertex();
        Minecraft.getMinecraft().getTextureManager().bindTexture(resTex);
        tessellator.draw();
        GlStateManager.disableBlend();
        GlStateManager.disableNormalize();
    }

    public static void drawColoredTexturedModalRect(int x, int y, int width, int height, Color color, float alpha, ResourceLocation resTex) {
        GlStateManager.enableNormalize();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

        float zLevel = -90.0F;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
        bufferbuilder.pos(x, y + height, zLevel).tex(0,1).color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, alpha).endVertex();
        bufferbuilder.pos(x + width,y + height,  zLevel).tex(1, 1).color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, alpha).endVertex();
        bufferbuilder.pos(x + width, y,  zLevel).tex(1, 0).color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, alpha).endVertex();
        bufferbuilder.pos(x, y, zLevel).tex(0, 0).color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, alpha).endVertex();
        Minecraft.getMinecraft().getTextureManager().bindTexture(resTex);
        tessellator.draw();
        GlStateManager.disableBlend();
        GlStateManager.disableNormalize();
    }
}
