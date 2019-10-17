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
import net.minecraft.util.math.MathHelper;
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
    protected static final ResourceLocation POINTER = new ResourceLocation(MiskatonicMysteries.MODID, "textures/misc/pointer.png");
    public static float spellSelectionAlpha = 0;
    public static double selectionPos = 0;
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
        handleTransformations(player, knowledge);
        int posX = event.getResolution().getScaledWidth() - 16;
        int poxY = event.getResolution().getScaledHeight() / 2;
        if (spells.length > 0){
            for (int i = 0; i < spells.length; i++) {
                Spell spellIn = spells[i];
                ResourceLocation tex = spellIn.iconLocation;
                float castProgression = knowledge.getCurrentCastingProgess() < 0 ? 1 : MathHelper.clamp(knowledge.getCurrentCastingProgess() / (float) spellIn.getCastTime(), 0, 1);
                float cooldownProgression = 1 - MathHelper.clamp(SpellKnowledge.Util.getCooldownFor(spellIn, player) / (float) spellIn.getCooldownTime(), 0, 1);
                boolean isSelected = i == knowledge.getCurrentSpell() && i == selectionPos && cooldownProgression >= 1;
                Color baseColor = ColorUtil.blend(Color.WHITE, Color.DARK_GRAY, isSelected ? 1 - castProgression + 0.2 : 0, isSelected ? castProgression - 0.2 : 1);
                drawColoredTexturedModalRect(posX, poxY - (20 * i), i == 0 ? -90 : -91, 16, 16, baseColor,(spellSelectionAlpha * Math.max(cooldownProgression, 0.4F)) * (isSelected ? 1 : 0.8F), tex);
            }
            if (spells.length > 1)
                drawColoredTexturedModalRect(posX, poxY - (int) (20 * (selectionPos)),-90, 16, 16, Color.WHITE, spellSelectionAlpha, POINTER);
        }
    }

    private static int calcSlot(int i, ISpellKnowledge knowledge){
        int slot = knowledge.getCurrentSpell() - i;
        if (slot >= knowledge.getSpells().length - 1){
            slot = 0;
        }else if (slot < 0){
            slot = knowledge.getSpells().length -1;
        }
        return slot;
    }

    private static void handleTransformations(EntityPlayer player, ISpellKnowledge knowledge){
        //todo move that thing properly
        if (SpellKnowledge.Util.isSpellSelected(player)) {
            if (spellSelectionAlpha < 1)
                spellSelectionAlpha += 0.05F;
            double moveFactor = Math.min(Math.abs(knowledge.getCurrentSpell() - selectionPos), 0.2);
            if (selectionPos < knowledge.getCurrentSpell()){
                selectionPos += moveFactor; //do it like this
            }else if (selectionPos > knowledge.getCurrentSpell()){
                selectionPos -= moveFactor;
            }
        }else {
            if (spellSelectionAlpha > 0) {
                spellSelectionAlpha -= 0.05F;
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

    public static void drawColoredTexturedModalRect(int x, int y, int z, int width, int height, Color color, float alpha, ResourceLocation resTex) {
        GlStateManager.enableNormalize();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        float zLevel = z;
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
