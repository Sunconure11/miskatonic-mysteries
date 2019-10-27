package com.miskatonicmysteries.common.handler;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.blessing.BlessingCapability;
import com.miskatonicmysteries.common.capability.blessing.BlessingProvider;
import com.miskatonicmysteries.common.capability.blessing.IBlessingCapability;
import com.miskatonicmysteries.common.capability.sanity.ISanity;
import com.miskatonicmysteries.common.capability.sanity.Sanity;
import com.miskatonicmysteries.common.capability.sanity.SanityProvider;
import com.miskatonicmysteries.common.capability.spells.ISpellKnowledge;
import com.miskatonicmysteries.common.capability.spells.SpellKnowledge;
import com.miskatonicmysteries.common.capability.spells.SpellKnowledgeProvider;
import com.miskatonicmysteries.common.handler.event.InsanityEvent;
import com.miskatonicmysteries.common.network.PacketHandler;
import com.miskatonicmysteries.common.network.message.client.PacketCastSpell;
import com.miskatonicmysteries.common.network.message.client.PacketHandleKey;
import com.miskatonicmysteries.proxy.ClientProxy;
import com.miskatonicmysteries.registry.ModInsanityEffects;
import com.miskatonicmysteries.registry.ModPotions;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid = MiskatonicMysteries.MODID)
public class CapabilityHandler {
    public static final ResourceLocation SANITY = new ResourceLocation(MiskatonicMysteries.MODID, "sanity");
    public static final ResourceLocation BLESSING = new ResourceLocation(MiskatonicMysteries.MODID, "blessing");
    public static final ResourceLocation SPELLS = new ResourceLocation(MiskatonicMysteries.MODID, "spells");

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent event) {
        if (event.getObject() instanceof EntityPlayer) {
            event.addCapability(SANITY, new SanityProvider());
            event.addCapability(BLESSING, new BlessingProvider());
            event.addCapability(SPELLS, new SpellKnowledgeProvider());
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        Sanity.Util.transferToClone(event);
        BlessingCapability.Util.transferToClone(event);
        SpellKnowledge.Util.transferToClone(event);
    }

    @SubscribeEvent
    public static void keyInput(TickEvent.PlayerTickEvent event) {
        if (event.player.world.isRemote) {
            if (ClientProxy.SPELL_CANCEL.isPressed()) {
                PacketHandler.network.sendToServer(new PacketHandleKey(2));
            } else if (ClientProxy.SPELL_DOWN.isPressed()) {
                PacketHandler.network.sendToServer(new PacketHandleKey(1));
            } else if (ClientProxy.SPELL_UP.isPressed()) {
                PacketHandler.network.sendToServer(new PacketHandleKey(0));
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerUpdate(LivingEvent.LivingUpdateEvent event) {
        if (event.getEntityLiving().hasCapability(SanityProvider.SANITY, null)) {
            ISanity sanity = event.getEntityLiving().getCapability(SanityProvider.SANITY, null);
            if (event.getEntityLiving() instanceof EntityPlayer) {
                if (!event.getEntityLiving().world.isRemote) {
                    if (sanity.getHorrifiedCooldown() > 0){
                        sanity.setHorrifiedCooldown(sanity.getHorrifiedCooldown() - 1);
                    }
                    if (sanity.isDirty()) {
                        Sanity.Util.syncSanity((EntityPlayer) event.getEntityLiving(), sanity);
                        sanity.setDirty(false);
                    }
                }
                if (event.getEntityLiving().ticksExisted > 0 && event.getEntityLiving().getActivePotionEffect(ModPotions.tranquilized) == null && ModInsanityEffects.getInsanityInterval(event.getEntityLiving()) == 0) {
                    MinecraftForge.EVENT_BUS.post(new InsanityEvent((EntityPlayer) event.getEntityLiving(), sanity, event));
                }

                if (event.getEntityLiving().hasCapability(BlessingProvider.BLESSING, null)) {
                    IBlessingCapability blessing = event.getEntityLiving().getCapability(BlessingProvider.BLESSING, null);
                    if (blessing.isDirty() && !event.getEntityLiving().world.isRemote) {
                        BlessingCapability.Util.syncBlessing((EntityPlayer) event.getEntityLiving(), blessing);
                        blessing.setDirty(false);
                    }
                }

                if (event.getEntityLiving().hasCapability(SpellKnowledgeProvider.SPELL_KNOWLEDGE, null)) {
                    ISpellKnowledge knowledge = event.getEntityLiving().getCapability(SpellKnowledgeProvider.SPELL_KNOWLEDGE, null);
                    SpellHandler.continueCast(knowledge, SpellKnowledge.Util.getCurrentSpell((EntityPlayer) event.getEntityLiving()), (EntityPlayer) event.getEntityLiving());
                    if (!event.getEntityLiving().world.isRemote) {
                        if (knowledge.isDirty()) {
                            SpellKnowledge.Util.syncKnowledge((EntityPlayer) event.getEntityLiving(), knowledge);
                            knowledge.setDirty(false);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void castSpell(PlayerInteractEvent.RightClickEmpty event) {
        if (event.getWorld().isRemote) {
            PacketHandler.network.sendToServer(new PacketCastSpell(event.getPos(), event.getFace()));
        }
    }

    @SubscribeEvent
    public static void castSpell(PlayerInteractEvent.RightClickBlock event){
        if (event.getWorld().isRemote) {
             PacketHandler.network.sendToServer(new PacketCastSpell(event.getPos(), event.getFace()));
        }
    }
}
