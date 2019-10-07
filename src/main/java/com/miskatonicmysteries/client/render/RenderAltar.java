package com.miskatonicmysteries.client.render;

import com.miskatonicmysteries.common.block.BlockAltar;
import com.miskatonicmysteries.common.block.tile.TileEntityAltar;
import net.minecraft.block.BlockEnchantmentTable;
import net.minecraft.client.model.ModelBook;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntityEnchantmentTableRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.command.CommandLocate;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.item.ItemWrittenBook;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class RenderAltar extends TileEntitySpecialRenderer<TileEntityAltar> {
    public static final ResourceLocation TEXTURE_BOOK = new ResourceLocation("textures/entity/enchanting_table_book.png");
    private final ModelBook modelBook = new ModelBook();

    @Override
    public void render(TileEntityAltar te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        if (TileEntityAltar.BOOK_TEXTURES.containsKey(te.inventory.getStackInSlot(0).getItem())) {
            GlStateManager.pushMatrix();
            doStandardTransformations(te, x, y, z);
            bindTexture(TileEntityAltar.BOOK_TEXTURES.getOrDefault(te.inventory.getStackInSlot(0).getItem(), TEXTURE_BOOK));
            GlStateManager.rotate(90 * (1 - te.bookOpeningProgress), 0, 1, 0);
            float flip = (getWorld().getWorldTime() * te.flipSpeed) % 100;
            flip /= 100F;
            this.modelBook.render(null, 0, flip, flip, 1.185F * te.bookOpeningProgress + 0.015F, 0, 0.0425F); //perhaps adapt some values
            GlStateManager.popMatrix();
        }
    }

    public void doStandardTransformations(TileEntityAltar te, double x, double y, double z){
        GlStateManager.translate(x + 0.5F, y + 1.5F, z + 0.5F);
        EnumFacing facing = te.getWorld().getBlockState(te.getPos()).getValue(BlockAltar.FACING); //south had west, check, west had south, check
        GlStateManager.rotate(90 * (facing == EnumFacing.NORTH ? 1 : facing == EnumFacing.EAST ? 0 : facing == EnumFacing.SOUTH ? 3 : 2), 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(67.5F, 0.0F, 0.0F, 1.0F); //0-22.5
        GlStateManager.translate(-0.05 * (te.bookOpeningProgress * te.bookOpeningProgress) - 0.025, -0.2, 0);

    }
}
