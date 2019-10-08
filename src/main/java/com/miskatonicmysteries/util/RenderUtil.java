package com.miskatonicmysteries.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;

public class RenderUtil {
    //do note that this does not translate etc.
    public static void renderItem(ItemStack stack, World world){
        IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack, world, null);
        model = ForgeHooksClient.handleCameraTransforms(model, ItemCameraTransforms.TransformType.GROUND, false);

        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);
    }

    public static void renderItemEntity(ItemStack stack, double x, double y, double z, float partTicks) {
        ItemStack is = stack.copy();
        is.setCount(1);
        EntityItem entityitem = new EntityItem(Minecraft.getMinecraft().world, 0.0D, 0.0D, 0.0D, is);
        entityitem.hoverStart = 0.0F;
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.renderEntity(entityitem, x, y, z, 0.0F, partTicks, false);
    }

    public static void renderItemEntity(ItemStack stack) {
        renderItemEntity(stack, 0, 0, 0, 0);
    }
}
