package moriyashiine.acme.common.capability.blessing;

import moriyashiine.acme.common.capability.blessing.blessings.Blessing;
import moriyashiine.acme.common.network.PacketHandler;
import moriyashiine.acme.common.network.message.capability.PacketSyncBlessing;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class BlessingCapability implements IBlessingCapability {
    private String blessing;
    private boolean dirty;

    public BlessingCapability() {
        blessing = "none";
    }

    @Override
    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    @Override
    public Blessing getBlessing() {
        return Blessing.getBlessing(blessing);
    }

    @Override
    public void setBlessing(Blessing blessing) {
        this.blessing = blessing.getName();
        setDirty(true);
    }

    @Override
    public boolean isDirty() {
        return dirty;
    }


    public static class Util {
        public static boolean setBlessing(Blessing blessing, EntityPlayer player) {
            if (blessing != null && !player.world.isRemote && getBlessingCapability(player) != null) {
                getBlessingCapability(player).setBlessing(blessing);
                return true;
            }
            return false;
        }

        public static Blessing getBlessing(EntityPlayer player) {
            if (getBlessingCapability(player) != null) {
                return getBlessingCapability(player).getBlessing();
            }
            return Blessing.NONE;
        }

        public static IBlessingCapability getBlessingCapability(EntityPlayer player) {
            return player.getCapability(BlessingProvider.BLESSING, null);
        }

        public static boolean hasBlessing(EntityPlayer player, Blessing blessing){
            return getBlessing(player).getName().equals(blessing.getName());
        }

        public static void transferToClone(PlayerEvent.Clone event) {
            if (getBlessingCapability(event.getEntityPlayer()) != null) {
                IBlessingCapability blessing = getBlessingCapability(event.getEntityPlayer());
                IBlessingCapability oldBlessing = getBlessingCapability(event.getOriginal());
                blessing.setBlessing(oldBlessing.getBlessing());
                syncBlessing(event.getEntityPlayer(), blessing);
            }
        }

        public static void syncBlessing(EntityPlayer player, IBlessingCapability blessing) {
            PacketHandler.sendTo(player, new PacketSyncBlessing(blessing));
        }
    }
}
