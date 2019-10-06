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
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.translate((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
        EnumFacing facing = te.getWorld().getBlockState(te.getPos()).getValue(BlockAltar.FACING); //south had west, check, west had south, check
        GlStateManager.rotate(90 * (facing == EnumFacing.NORTH ? 1 : facing == EnumFacing.EAST ? 0 : facing == EnumFacing.SOUTH ? 3 : 2), 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(67.5F, 0.0F, 0.0F, 1.0F); //0-22.5
        GlStateManager.translate(-0.075, -0.2, 0);
        this.bindTexture(TEXTURE_BOOK); //this will be adapted depending on the book used
        this.modelBook.render(null, 0, 0, 0, 1.2F, 0, 0.0425F); //perhaps adapt some values
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
}
