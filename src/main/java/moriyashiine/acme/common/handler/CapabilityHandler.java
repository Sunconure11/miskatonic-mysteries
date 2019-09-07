package moriyashiine.acme.common.handler;

import moriyashiine.acme.ACME;
import moriyashiine.acme.common.capability.Sanity;
import moriyashiine.acme.common.capability.SanityProvider;
import moriyashiine.acme.util.SanityUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class CapabilityHandler {
    public static final ResourceLocation SANITY = new ResourceLocation(ACME.MODID, "sanity");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent event) {
        if (event.getObject() instanceof EntityPlayer) {
            event.addCapability(SANITY, new SanityProvider());
        }
    }

    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event) {
        SanityUtil.transferToClone(event);
    }

}
