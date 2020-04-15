package com.miskatonicmysteries.client.render;

import com.miskatonicmysteries.ModConfig;
import com.miskatonicmysteries.client.model.entity.ModelGoatBlessing;
import com.miskatonicmysteries.client.render.entity.RenderGoatLegs;
import com.miskatonicmysteries.common.capability.blessing.BlessingCapability;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.capability.sanity.Sanity;
import com.miskatonicmysteries.common.capability.spells.SpellKnowledge;
import com.miskatonicmysteries.common.entity.goo.AbstractOldOne;
import com.miskatonicmysteries.registry.ModPotions;
import com.miskatonicmysteries.registry.ModRegistries;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.HashMap;
import java.util.Random;

public class RenderManipulatorHandler {
    public static HashMap<Integer, EntityLivingBase> mobMob = new HashMap<>(); //actual mob - mob displayed

    public static float cameraRoll = 0;
    public static boolean switchRollDirection = false;
    public static float cameraYaw = 0;
    public static boolean switchYawDirection = false;
    public static float cameraPitch = 0;
    public static boolean switchPitchDirection = false;

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void maniaSettings(EntityViewRenderEvent.CameraSetup event) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        if (player.getActivePotionEffect(ModPotions.mania) != null) {
            Random rand = player.getRNG();
            if (!Minecraft.getMinecraft().isGamePaused()) {
                cameraRoll += rand.nextFloat() / 3F * (switchRollDirection ? 1 : -1);
                if ((cameraRoll >= 1 && switchRollDirection) || (!switchRollDirection && cameraRoll <= -1)) {
                    switchRollDirection = !switchRollDirection;
                }
                cameraYaw += rand.nextFloat() / 3F * (switchYawDirection ? 1 : -1);
                if ((cameraYaw >= 1 && switchYawDirection) || (!switchYawDirection && cameraYaw <= -1)) {
                    switchYawDirection = !switchYawDirection;
                }
                cameraPitch += rand.nextFloat() / 3F * (switchPitchDirection ? 1 : -1);
                if ((cameraPitch >= 1 && switchPitchDirection) || (!switchPitchDirection && cameraPitch <= -1)) {
                    switchPitchDirection = !switchPitchDirection;
                }
            }
            event.setYaw(event.getYaw() + cameraYaw);
            event.setPitch(event.getPitch() + cameraPitch);
            event.setRoll(event.getRoll() + cameraRoll);
        }
    }

    @SuppressWarnings("unchecked")
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRender(RenderLivingEvent.Pre event) {
        if (mobMob.containsKey(event.getEntity().getEntityId())) {
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
    public void renderBlessing(RenderPlayerEvent.Pre event) { //the issue is that the capability has different values for other players, for some reason
        //might be fixed? if not, maybe try sending a packet to the server after having sent a packet to  the client (see CapabilityHandler)
        if (!(event.getRenderer() instanceof RenderGoatLegs) && BlessingCapability.Util.hasBlessing(event.getEntityPlayer(), Blessing.SHUB)) {
            event.setCanceled(true);
            boolean smolArms = false;
            try {
                smolArms = ObfuscationReflectionHelper.findField(RenderPlayer.class, "field_177140_a").getBoolean(event.getRenderer());
            } catch (Exception ignore) {}

            RenderGoatLegs render = new RenderGoatLegs(Minecraft.getMinecraft().getRenderManager(), new ModelGoatBlessing(0, smolArms), smolArms);
            render.doRender((AbstractClientPlayer) event.getEntityPlayer(), event.getX(), event.getY(), event.getZ(), ((AbstractClientPlayer) event.getEntityPlayer()).rotationYaw, event.getPartialRenderTick());
        }
    }
}
