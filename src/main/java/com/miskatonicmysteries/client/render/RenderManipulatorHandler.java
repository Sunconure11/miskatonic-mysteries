package com.miskatonicmysteries.client.render;

import com.miskatonicmysteries.ModConfig;
import com.miskatonicmysteries.client.model.entity.ModelGoatBlessing;
import com.miskatonicmysteries.client.render.entity.RenderGoatLegs;
import com.miskatonicmysteries.common.capability.blessing.BlessingCapability;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;

public class RenderManipulatorHandler {
    public static HashMap<Integer, EntityLivingBase> mobMob = new HashMap<>(); //actual mob - mob displayed

    @SuppressWarnings("unchecked")
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRender(RenderLivingEvent.Pre event) {//might remove the ifDirty part
        if (mobMob.get(event.getEntity().getEntityId()) != null) {
            event.setCanceled(true);

            EntityLivingBase entity = mobMob.get(event.getEntity().getEntityId());
            entity.renderYawOffset = event.getEntity().renderYawOffset;
            entity.prevRenderYawOffset = event.getEntity().prevRenderYawOffset;
            entity.cameraPitch = event.getEntity().cameraPitch;
            entity.posX = event.getEntity().posX;
            entity.posY = event.getEntity().posY - event.getEntity().getYOffset();
            entity.posZ = event.getEntity().posZ;
            entity.prevPosX = event.getEntity().prevPosX;
            entity.prevPosY = event.getEntity().prevPosY - event.getEntity().getYOffset();
            entity.prevPosZ = event.getEntity().prevPosZ;
            entity.lastTickPosX = event.getEntity().lastTickPosX;
            entity.lastTickPosY = event.getEntity().lastTickPosY - event.getEntity().getYOffset();
            entity.lastTickPosZ = event.getEntity().lastTickPosZ;
            entity.rotationPitch = event.getEntity().rotationPitch;
            entity.prevRotationPitch = event.getEntity().prevRotationPitch;
            entity.rotationYaw = event.getEntity().rotationYaw;
            entity.prevRotationYaw = event.getEntity().prevRotationYaw;
            entity.rotationYawHead = event.getEntity().rotationYawHead;
            entity.prevRotationYawHead = event.getEntity().prevRotationYawHead;
            entity.limbSwingAmount = event.getEntity().limbSwingAmount;
            entity.prevLimbSwingAmount = event.getEntity().prevLimbSwingAmount;
            entity.limbSwing = event.getEntity().limbSwing;
            entity.prevSwingProgress = event.getEntity().prevSwingProgress;
            entity.swingProgress = event.getEntity().swingProgress;
            entity.swingProgressInt = event.getEntity().swingProgressInt;
            entity.setHeldItem(EnumHand.MAIN_HAND, event.getEntity().getHeldItemMainhand());
            entity.setHeldItem(EnumHand.OFF_HAND, event.getEntity().getHeldItemOffhand());
            int slot = 0;
            for (ItemStack piece : event.getEntity().getArmorInventoryList()) {
                entity.setItemStackToSlot(slot == 0 ? EntityEquipmentSlot.FEET : slot == 1 ? EntityEquipmentSlot.LEGS : slot == 2 ? EntityEquipmentSlot.CHEST : EntityEquipmentSlot.HEAD, piece);
                slot++;
            }
            entity.motionX = event.getEntity().motionX;
            entity.motionY = event.getEntity().motionY;
            entity.motionZ = event.getEntity().motionZ;
            entity.ticksExisted = event.getEntity().ticksExisted;
            GL11.glPushMatrix();
            Minecraft.getMinecraft().getRenderManager().renderEntity(entity, event.getX(), event.getY(), event.getZ(), entity.rotationYaw, event.getPartialRenderTick(), false);
            GL11.glPopMatrix();
        }
    }

    @SubscribeEvent
    public void renderBlessing(RenderPlayerEvent.Pre event) {
        if (ModConfig.client.playerModelOverrides) {
            if (BlessingCapability.Util.hasBlessing(event.getEntityPlayer(), Blessing.SHUB) && !(event.getRenderer() instanceof RenderGoatLegs) && event.getEntityPlayer() instanceof AbstractClientPlayer) {
                event.setCanceled(true);
                boolean smolArms = !((AbstractClientPlayer) event.getEntityPlayer()).getSkinType().equals("default");
                RenderGoatLegs render = new RenderGoatLegs(Minecraft.getMinecraft().getRenderManager(), new ModelGoatBlessing(0, smolArms), smolArms);
                render.doRender((AbstractClientPlayer) event.getEntityPlayer(), event.getX(), event.getY(), event.getZ(), ((AbstractClientPlayer) event.getEntityPlayer()).rotationYaw, 0.0625F);
            }
        }
    }
}
