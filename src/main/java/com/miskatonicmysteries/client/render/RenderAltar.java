package com.miskatonicmysteries.client.render;

import com.miskatonicmysteries.common.block.BlockAltar;
import com.miskatonicmysteries.common.block.tile.TileEntityAltar;
import net.minecraft.block.BlockEnchantmentTable;
import net.minecraft.client.model.ModelBook;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityEnchantmentTableRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderAltar extends TileEntitySpecialRenderer<TileEntityAltar> {
    private static final ResourceLocation TEXTURE_BOOK = new ResourceLocation("textures/entity/enchanting_table_book.png");
    private final ModelBook modelBook = new ModelBook();

    @Override
    public void render(TileEntityAltar te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        //todo, the book must be rotated to only flip with one thing
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
        //GlStateManager.enableNormalize();
        //GlStateManager.enableBlend();
        //GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        float xI = Float.valueOf(I18n.format("temp.1"));
        float yI = Float.valueOf(I18n.format("temp.2"));
        float zI = Float.valueOf(I18n.format("temp.3"));
        //GlStateManager.color(1, 1, 1, 1);
        //GlStateManager.enableNormalize();
        EnumFacing facing = te.getWorld().getBlockState(te.getPos()).getValue(BlockAltar.FACING); //south had west, check, west had south, check
        GlStateManager.rotate(90 * (facing == EnumFacing.NORTH ? 1 : facing == EnumFacing.EAST ? 0 : facing == EnumFacing.SOUTH ? 3 : 2), 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(67.5F, 0.0F, 0.0F, 1.0F); //0-22.5
        GlStateManager.translate(xI, yI, zI);
        this.bindTexture(TEXTURE_BOOK); //this will be adapted depending on the book used
        //GlStateManager.enableCull();
        this.modelBook.render(null, 0, 0, 0, 1.2F, 0, 0.0425F); //perhaps adapt some values
       // GlStateManager.disableBlend();
        //GlStateManager.disableNormalize();
        GlStateManager.popMatrix();
    }

    /*@Override
    public void render(TileEntityAltar te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        TileEntityEnchantmentTableRenderer
        super.render(te, x, y, z, partialTicks, destroyStage, alpha);
    }*/
}
