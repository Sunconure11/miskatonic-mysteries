package com.miskatonicmysteries.common.handler;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.ModConfig;
import com.miskatonicmysteries.common.capability.blessing.BlessingCapability;
import com.miskatonicmysteries.common.capability.blessing.BlessingProvider;
import com.miskatonicmysteries.common.capability.blessing.IBlessingCapability;
import com.miskatonicmysteries.common.capability.sanity.ISanity;
import com.miskatonicmysteries.common.capability.sanity.Sanity;
import com.miskatonicmysteries.common.capability.sanity.SanityProvider;
import com.miskatonicmysteries.common.handler.event.InsanityEvent;
import com.miskatonicmysteries.registry.ModPotions;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityHandler {
    public static final ResourceLocation SANITY = new ResourceLocation(MiskatonicMysteries.MODID, "sanity");
    public static final ResourceLocation BLESSING = new ResourceLocation(MiskatonicMysteries.MODID, "blessing");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent event) {
        if (event.getObject() instanceof EntityPlayer) {
            event.addCapability(SANITY, new SanityProvider());
            event.addCapability(BLESSING, new BlessingProvider());
        }
    }

    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event) {
        Sanity.Util.transferToClone(event);
        BlessingCapability.Util.transferToClone(event);
    }

    @SubscribeEvent
    public void onPlayerUpdate(LivingEvent.LivingUpdateEvent event){
        if (event.getEntityLiving().hasCapability(SanityProvider.SANITY, null)){
            ISanity sanity = event.getEntityLiving().getCapability(SanityProvider.SANITY, null);
            if (event.getEntityLiving() instanceof EntityPlayer){
                if (!event.getEntityLiving().world.isRemote && sanity.isDirty()) {
                    Sanity.Util.syncSanity((EntityPlayer) event.getEntityLiving(), sanity);
                    sanity.setDirty(false);
                }
                if (event.getEntityLiving().ticksExisted > 0 && sanity.getSanity() < 100 && event.getEntityLiving().getActivePotionEffect(ModPotions.tranquilized) == null && event.getEntityLiving().ticksExisted % (ModConfig.sanity.insanityInterval - (100 - sanity.getSanity())) == 0){
                    MinecraftForge.EVENT_BUS.post(new InsanityEvent((EntityPlayer) event.getEntityLiving(), sanity, event));
                }
            }
        }

        if (event.getEntityLiving().hasCapability(BlessingProvider.BLESSING, null)){
            IBlessingCapability blessing = event.getEntityLiving().getCapability(BlessingProvider.BLESSING, null);
            if (event.getEntityLiving() instanceof EntityPlayer && blessing.isDirty() && !event.getEntityLiving().world.isRemote){
                BlessingCapability.Util.syncBlessing((EntityPlayer) event.getEntityLiving(), blessing);
            }
        }
    }
}
