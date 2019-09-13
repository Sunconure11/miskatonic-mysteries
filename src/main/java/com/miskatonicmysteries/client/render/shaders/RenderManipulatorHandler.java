package com.miskatonicmysteries.client.render.shaders;

import com.miskatonicmysteries.common.capability.Sanity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.HashMap;

public class RenderManipulatorHandler {
    //todo, register this handler, change stuff, etc. etc. etc., call stuff in InsanityHandler, optimize stuff NGGHWAHGWGWAG
    public static HashMap<Integer, EntityLivingBase> mobMob = new HashMap<>(); //actual mob - mob displayed


    @SuppressWarnings("unchecked")
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRender(RenderLivingEvent.Pre event) {//might remove the ifDirty part
        System.out.println(mobMob);
        if(mobMob.get(event.getEntity().getEntityId()) != null) {
            event.setCanceled(true);

            EntityLivingBase entity = mobMob.get(event.getEntity().getEntityId());
            /*if(entity == null || entity.worldObj != event.entityPlayer.worldObj) {
                BiomeGenBase biome = event.entityPlayer.worldObj.getBiomeGenForCoords(MathHelper.floor_double(event.entityPlayer.posX), MathHelper.floor_double(event.entityPlayer.posZ));
                ArrayList<Biome.SpawnListEntry> spawnList = (ArrayList<Biome.SpawnListEntry>)biome.getSpawnableList(EnumCreatureType.monster);

                if(spawnList.size() <= 0)
                {
                    entity = new EntityZombie(event.entityPlayer.worldObj);
                } else
                {
                    int spawnIndex = event.entityPlayer.getRNG().nextInt(spawnList.size());
                    try
                    {
                        entity = (EntityLiving)spawnList.get(spawnIndex).entityClass.getConstructor(new Class[] {World.class}).newInstance(new Object[] {event.entityPlayer.worldObj});
                    } catch(Exception e)
                    {
                        entity = new EntityZombie(event.entityPlayer.worldObj);
                    }
                }

                playerMob.put(event.getEntityPlayer().getName(), entity);
            }*/
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
            //ItemStack[] equipped = Arrayevent.getEntity().getEquipmentAndArmor().getLastActiveItems();
            //entity.getArmorInventoryList(0, event.entityPlayer.getHeldItem());
            /*entity.setCurrentItemOrArmor(1, equipped[0]);
            entity.setCurrentItemOrArmor(2, equipped[1]);
            entity.setCurrentItemOrArmor(3, equipped[2]);
            entity.setCurrentItemOrArmor(4, equipped[3]);*///todo add equipment stuff later
            entity.motionX = event.getEntity().motionX;
            entity.motionY = event.getEntity().motionY;
            entity.motionZ = event.getEntity().motionZ;
            entity.ticksExisted = event.getEntity().ticksExisted;
            GL11.glPushMatrix();
            if (event.getEntity() instanceof EntityCreeper){
                CREEPERINFLATION((EntityCreeper) event.getEntity(), event.getPartialRenderTick());
            }
            Minecraft.getMinecraft().getRenderManager().renderEntity(entity, event.getX(), event.getY(), event.getZ(), entity.rotationYaw, event.getPartialRenderTick(), false);
            GL11.glPopMatrix();
        }
    }

    protected void CREEPERINFLATION(EntityCreeper entitylivingbaseIn, float partialTickTime) {
        float f = entitylivingbaseIn.getCreeperFlashIntensity(partialTickTime);
        float f1 = 1.0F + MathHelper.sin(f * 100.0F) * f * 0.01F;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        f = f * f;
        f = f * f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        GlStateManager.scale(f2, f3, f2);
    }

    /**
     * Gets an RGBA int color multiplier to apply.
     */
    protected int getColorMultiplier(EntityCreeper entitylivingbaseIn, float lightBrightness, float partialTickTime)
    {
        float f = entitylivingbaseIn.getCreeperFlashIntensity(partialTickTime);

        if ((int)(f * 10.0F) % 2 == 0)
        {
            return 0;
        }
        else
        {
            int i = (int)(f * 0.2F * 255.0F);
            i = MathHelper.clamp(i, 0, 255);
            return i << 24 | 822083583;
        }
    }

}
