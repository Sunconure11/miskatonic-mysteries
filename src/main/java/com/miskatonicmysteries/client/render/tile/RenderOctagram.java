package com.miskatonicmysteries.client.render.tile;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.block.BlockOctagram;
import com.miskatonicmysteries.common.block.tile.TileEntityOctagram;
import com.miskatonicmysteries.util.RenderUtil;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderOctagram extends TileEntitySpecialRenderer<TileEntityOctagram> {

    @Override
    public void render(TileEntityOctagram te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 0.5F, y + 0.01, z + 0.5F);
        GlStateManager.disableLighting();
        GlStateManager.enableBlend();
        bindTexture(getTexture(te));
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.pushMatrix();
        EnumFacing facing = getWorld().getBlockState(te.getPos()).getValue(BlockOctagram.FACING);
        GlStateManager.rotate(facing == EnumFacing.EAST ? 90 : facing == EnumFacing.SOUTH ? 180 : facing == EnumFacing.WEST ? 270 : 0, 0, 1, 0);
        if (te.primed){
            GlStateManager.color(1, 1, 0.9F, 0.8F);
        }
        drawOctagram();
        GlStateManager.popMatrix();
        renderItems(te);
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }

    public void drawOctagram(){
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();

        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        float end = -1.5F;
        float start = 1.5F;
        buffer.pos(end, 0, start).tex(0, 1).endVertex();
        buffer.pos(start, 0, start).tex(1, 1).endVertex();
        buffer.pos(start, 0, end).tex(1, 0).endVertex();
        buffer.pos(end, 0, end).tex(0, 0).endVertex();

        tessellator.draw();
    }

    public void renderItems(TileEntityOctagram octagram){
        int pos;
        for (int x = -1; x <= 1; x++){
            for (int z = -1; z <= 1; z++){
                if (!(x == 0 && z == 0)) {
                    pos = BlockOctagram.getSlot(x, z);
                    GlStateManager.pushMatrix();
                    boolean corners = Math.abs(x) == 1 && Math.abs(z) == 1;
                    GlStateManager.translate((float) x * (corners ? 0.8F: 1), 0, (float) z * (corners ? 0.8: 1));
                    GlStateManager.rotate(90, 1, 0, 0);
                    GlStateManager.rotate(45 * pos + 90, 0,  0, 1); //remove that part to have them around the center
                    RenderUtil.renderItem(octagram.inventory.getStackInSlot(pos), getWorld());
                    GlStateManager.popMatrix();
                }
            }
        }
    }


    public ResourceLocation getTexture(TileEntityOctagram te){
        return new ResourceLocation(MiskatonicMysteries.MODID, "textures/blocks/" + te.getBlockType().getRegistryName().getResourcePath() + ".png");
    }
}
