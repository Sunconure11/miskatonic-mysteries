package com.miskatonicmysteries.common.handler;

import com.miskatonicmysteries.client.render.shaders.RenderManipulatorHandler;
import com.miskatonicmysteries.common.handler.event.InsanityEvent;
import com.miskatonicmysteries.common.network.PacketHandler;
import com.miskatonicmysteries.common.network.message.event.PacketHandleInsanityClient;
import com.miskatonicmysteries.registry.ModPotions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class InsanityHandler {
    @SubscribeEvent
    public void handleInsanity(InsanityEvent insanityEvent) {
        if (!insanityEvent.getPlayer().world.isRemote) {
            int event = insanityEvent.getPlayer().world.rand.nextInt(Math.abs(120 - insanityEvent.getSanity().getSanity()));
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


}
