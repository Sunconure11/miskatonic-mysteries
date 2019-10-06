package com.miskatonicmysteries.common.handler;

import com.miskatonicmysteries.common.capability.sanity.Sanity;
import com.miskatonicmysteries.common.handler.event.InsanityEvent;
import com.miskatonicmysteries.common.network.PacketHandler;
import com.miskatonicmysteries.common.network.message.event.PacketHandleInsanityClient;
import com.miskatonicmysteries.registry.ModPotions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class InsanityHandler {
    //todo, add config to some of this stuff
    //also todo, change the way the chance is calculated
    @SubscribeEvent
    public void handleInsanity(InsanityEvent insanityEvent) {
        if (!insanityEvent.getPlayer().world.isRemote) {
            int event = insanityEvent.getPlayer().world.rand.nextInt(Math.abs(115 - insanityEvent.getSanity().getSanity())); //max 115
            if (insanityEvent.getUpcomingEvent() instanceof LivingEvent.LivingUpdateEvent) {
                if (event <= 15) {
                    //SoundEvents, which are handled client side
                    PacketHandler.sendTo(insanityEvent.getPlayer(), new PacketHandleInsanityClient(event));
                } else if (event <= 20) {
                    //"Exotic Cravings"
                    insanityEvent.getPlayer().addPotionEffect(new PotionEffect(ModPotions.hunger_exotic, 4000, 0));
                    PacketHandler.sendTo(insanityEvent.getPlayer(), new PacketHandleInsanityClient(event));
                } else if (event <= 25) {
                    //Entity distortion; this is also handled client side
                    PacketHandler.sendTo(insanityEvent.getPlayer(), new PacketHandleInsanityClient(event));
                }
            }else if (insanityEvent.getUpcomingEvent() instanceof LivingAttackEvent){
                if (event >= 15 && event <= 70){
                    if ((insanityEvent.getPlayer().getHealth() <= 6 && event >= 20) || event >= 65){
                        insanityEvent.getPlayer().addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 3600, 1, false, false));
                        insanityEvent.getPlayer().addPotionEffect(new PotionEffect(MobEffects.SPEED, 6000, 1, false, false));
                    }else
                    if (event >= 60) {
                        insanityEvent.getPlayer().addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 6000, 2, false, false));
                    }
                }
            }
        }
    }

    public static void playParanoiaSound(EntityPlayer player, SoundEvent sound, float pitch){
        if (player.world.isRemote)
        player.world.playSound(player, player.getPosition(), sound, SoundCategory.AMBIENT, 1.0F, pitch);
    }


    public static EntityLivingBase getFittingMob(World world, BlockPos pos, boolean friendlyOnes){
        List<Biome.SpawnListEntry> spawns = world.getBiome(pos).getSpawnableList(friendlyOnes ? EnumCreatureType.CREATURE : EnumCreatureType.MONSTER);
        if(spawns.size() <= 0) {
            return new EntityZombie(world);
        } else {
            try {
                return spawns.get(world.rand.nextInt(spawns.size())).entityClass.getConstructor(World.class).newInstance(world);
            } catch(Exception e) {
                return  new EntityZombie(world);
            }
        }
    }

    @SubscribeEvent
    public void onHurt(LivingAttackEvent event){ // PlayerEvent.ItemCraftedEvent!
        if (event.getEntityLiving() instanceof EntityPlayer && Sanity.Util.getSanity((EntityPlayer) event.getEntityLiving()) < 90){
            MinecraftForge.EVENT_BUS.post(new InsanityEvent((EntityPlayer) event.getEntityLiving(), Sanity.Util.getSanityCapability((EntityPlayer) event.getEntityLiving()),event));
        }
    }


}
