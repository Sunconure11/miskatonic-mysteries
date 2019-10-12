package com.miskatonicmysteries.common.network.message.event;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.client.render.RenderManipulatorHandler;
import com.miskatonicmysteries.common.capability.sanity.Sanity;
import com.miskatonicmysteries.common.capability.sanity.SanityProvider;
import com.miskatonicmysteries.common.handler.InsanityHandler;
import com.miskatonicmysteries.registry.ModPotions;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.List;

public class PacketHandleInsanityClient implements IMessage{
    private int event = -1;

    public PacketHandleInsanityClient(){

    }

    public PacketHandleInsanityClient(int eventCode) {
        this.event = eventCode;
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        this.event = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.event);
    }

    public static class Handler implements IMessageHandler<PacketHandleInsanityClient, IMessage> {

        @Override
        public IMessage onMessage(PacketHandleInsanityClient message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                    EntityPlayer player = MiskatonicMysteries.proxy.getPlayer(ctx);
                    World world = player.world;
                    if (message.event <= 15) {
                       SoundEvent sound = SoundEvents.ENTITY_CREEPER_PRIMED;
                        float pitch = 0.7F;
                        if (message.event <= 2) {
                            sound = SoundEvents.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD;
                        } else if (message.event <= 4) {
                            sound = SoundEvents.ENTITY_ZOMBIE_AMBIENT;
                        } else if (message.event <= 6) {
                            sound = SoundEvents.ENTITY_SKELETON_DEATH;
                        } else if (message.event <= 8) {
                            sound = SoundEvents.AMBIENT_CAVE;
                        } else if (message.event <= 10) {
                            sound = SoundEvents.AMBIENT_CAVE;
                            pitch = 0.3F;
                        } else if (message.event == 13) {
                            sound = SoundEvents.RECORD_13;
                            pitch = 0.5F;
                        }
                        InsanityHandler.playParanoiaSound(player, sound, pitch);
                    } else if (message.event <= 20) {
                        player.addPotionEffect(new PotionEffect(ModPotions.hunger_exotic, 4000, 0));
                        InsanityHandler.playParanoiaSound(player, SoundEvents.ENTITY_ELDER_GUARDIAN_CURSE, 0.2F);
                    } else if (message.event <= 25) {
                        List<Entity> livings = world.getEntitiesInAABBexcluding(player, Minecraft.getMinecraft().player.getEntityBoundingBox().grow(50, 10, 50), p -> p instanceof EntityLivingBase && (p instanceof IMob || p instanceof EntityPlayer));
                        int amount = (int) ((float) (100 - Sanity.Util.getSanity(player)) / 20F) + player.getRNG().nextInt(3) + 2;
                        for (int i = 0; i < amount; i++) {
                            Entity entityIn = livings.get(player.getRNG().nextInt(livings.size()));
                            if (entityIn instanceof EntityLivingBase) {
                                if (entityIn instanceof IMob) {
                                    RenderManipulatorHandler.mobMob.put(entityIn.getEntityId(), InsanityHandler.getFittingMob(entityIn.world, entityIn.getPosition(), true));
                                } else {
                                    RenderManipulatorHandler.mobMob.put(entityIn.getEntityId(), InsanityHandler.getFittingMob(entityIn.world, entityIn.getPosition(), false));
                                }
                            }
                        }
                    }
            });
            return null;
        }

    }
}
