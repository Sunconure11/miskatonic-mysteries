package moriyashiine.acme.common.capability;

import com.miskatonicmysteries.common.network.PacketHandler;
import com.miskatonicmysteries.common.network.message.capability.PacketSyncSanity;
import moriyashiine.acme.common.network.message.capability.PacketSyncSanity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class Sanity implements ISanity {
    public static final int SANITY_MAX = 150;
    private int sanity; //150 is for now the standard

    public Sanity() {
        this.sanity = Sanity.SANITY_MAX;
    }

    //================
    @Override
    public int getSanity() {
        return sanity;
    }

    @Override
    public void setSanity(int sanity) {
        if (sanity > SANITY_MAX){
            this.sanity = SANITY_MAX;
            return;
        }
        if (sanity < 0){
            this.sanity = 0;
            return;
        }
        this.sanity = sanity;
    }
    //===============

    public static boolean setSanity(int amount, EntityPlayer player){
        if (!player.world.isRemote && getSanityCapability(player) != null){
            ISanity sanity = getSanityCapability(player);
            if (amount < 0 || amount > SANITY_MAX)
                return false;
            sanity.setSanity(amount);
            syncSanity(player, sanity);
            return true;
        }
        return false;
    }

    public static int getSanity(EntityPlayer player){
        if (getSanityCapability(player) != null){
            ISanity sanity = getSanityCapability(player);
            return sanity.getSanity();
        }
        return -1;
    }

    //this is really just a shortcut because I hate typing this all the time
    public static ISanity getSanityCapability(EntityPlayer player){
        return player.getCapability(SanityProvider.SANITY, null);
    }

    public static void transferToClone(PlayerEvent.Clone event){
        if (event.getEntityPlayer().hasCapability(SanityProvider.SANITY, null)) {
            ISanity sanity = event.getEntityPlayer().getCapability(SanityProvider.SANITY, null);
            ISanity oldSanity = event.getOriginal().getCapability(SanityProvider.SANITY, null);
            sanity.setSanity(oldSanity.getSanity());
            syncSanity(event.getEntityPlayer(), sanity);
        }
    }

    public static void syncSanity(EntityPlayer player, ISanity sanity){
        PacketHandler.sendTo(player, new PacketSyncSanity(sanity));
    }
}
