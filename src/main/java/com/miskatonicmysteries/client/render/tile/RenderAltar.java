package com.miskatonicmysteries.client.render.tile;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.block.BlockAltar;
import com.miskatonicmysteries.common.block.tile.TileEntityAltar;
import com.miskatonicmysteries.registry.ModObjects;
import net.minecraft.client.model.ModelBook;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;

public class RenderAltar extends TileEntitySpecialRenderer<TileEntityAltar> {
    public static final ResourceLocation TEXTURE_BOOK = new ResourceLocation("textures/entity/enchanting_table_book.png");
    public static final HashMap<Item, ResourceLocation> BOOK_TEXTURES = new HashMap<Item, ResourceLocation>();

    static {
        BOOK_TEXTURES.put(ModObjects.research_notes_shubniggurath, new ResourceLocation(MiskatonicMysteries.MODID, "textures/misc/book_shub.png"));
        BOOK_TEXTURES.put(ModObjects.research_notes_hastur, new ResourceLocation(MiskatonicMysteries.MODID, "textures/misc/book_hastur.png"));
        BOOK_TEXTURES.put(ModObjects.research_notes_cthulhu, new ResourceLocation(MiskatonicMysteries.MODID, "textures/misc/book_cthulhu.png"));
        BOOK_TEXTURES.put(ModObjects.necronomicon, new ResourceLocation(MiskatonicMysteries.MODID, "textures/misc/book_necronomicon.png"));

        BOOK_TEXTURES.put(Items.BOOK, RenderAltar.TEXTURE_BOOK);
        BOOK_TEXTURES.put(Items.ENCHANTED_BOOK, RenderAltar.TEXTURE_BOOK);
        BOOK_TEXTURES.put(Items.WRITABLE_BOOK, RenderAltar.TEXTURE_BOOK);
        BOOK_TEXTURES.put(Items.WRITTEN_BOOK, RenderAltar.TEXTURE_BOOK);

    }

    private final ModelBook modelBook = new ModelBook();

    @Override
    public void render(TileEntityAltar te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        if (BOOK_TEXTURES.containsKey(te.inventory.getStackInSlot(0).getItem())) {
            GlStateManager.pushMatrix();
            GlStateManager.enableCull();
            doStandardTransformations(te, x, y, z);
            bindTexture(BOOK_TEXTURES.getOrDefault(te.inventory.getStackInSlot(0).getItem(), TEXTURE_BOOK));
            GlStateManager.rotate(90 * (1 - te.bookOpeningProgress), 0, 1, 0);
            float flip = (getWorld().getWorldTime() * te.flipSpeed) % 100;
            flip /= 100F;
            this.modelBook.render(null, 0, flip, flip, 1.185F * Math.max(te.bookOpeningProgress, 0) + 0.015F, 0, 0.0425F); //perhaps adapt some values
            GlStateManager.disableCull();
            GlStateManager.popMatrix();
        }
    }

    public void doStandardTransformations(TileEntityAltar te, double x, double y, double z){
        GlStateManager.translate(x + 0.5F, y + 1.5F, z + 0.5F);
        EnumFacing facing = te.getWorld().getBlockState(te.getPos()).getBlock() instanceof BlockAltar ? te.getWorld().getBlockState(te.getPos()).getValue(BlockAltar.FACING) : EnumFacing.NORTH; //south had west, check, west had south, check
        GlStateManager.rotate(90 * (facing == EnumFacing.NORTH ? 1 : facing == EnumFacing.EAST ? 0 : facing == EnumFacing.SOUTH ? 3 : 2), 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(67.5F, 0.0F, 0.0F, 1.0F); //0-22.5
        GlStateManager.translate(-0.05 * (te.bookOpeningProgress * te.bookOpeningProgress) - 0.025, -0.2, 0);

    }
}
