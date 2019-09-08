package moriyashiine.acme.common.handler;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.Sanity;
import com.miskatonicmysteries.common.capability.SanityProvider;
import moriyashiine.acme.MiskatonicMysteries;
import moriyashiine.acme.common.capability.SanityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityHandler {
    public static final ResourceLocation SANITY = new ResourceLocation(MiskatonicMysteries.MODID, "sanity");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent event) {
        if (event.getObject() instanceof EntityPlayer) {
            event.addCapability(SANITY, new SanityProvider());
        }
    }

    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event) {
        Sanity.transferToClone(event);
    }

}
