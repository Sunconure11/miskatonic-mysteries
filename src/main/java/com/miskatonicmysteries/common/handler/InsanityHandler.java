package com.miskatonicmysteries.common.handler;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.sanity.Sanity;
import com.miskatonicmysteries.common.handler.effects.InsanityEffect;
import com.miskatonicmysteries.common.handler.effects.InsanityEffectFlux;
import com.miskatonicmysteries.common.handler.event.InsanityEvent;
import com.miskatonicmysteries.common.network.PacketHandler;
import com.miskatonicmysteries.common.network.message.client.PacketYellowSign;
import com.miskatonicmysteries.common.network.message.event.PacketHandleInsanity;
import com.miskatonicmysteries.registry.ModInsanityEffects;
import com.miskatonicmysteries.registry.ModObjects;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemShield;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.stream.StreamSupport;

@Mod.EventBusSubscriber(modid = MiskatonicMysteries.MODID)
public class InsanityHandler {

    @SubscribeEvent
    public static void handleInsanity(InsanityEvent insanityEvent) {
        EntityPlayer player = insanityEvent.getPlayer();
        World world = player.world;
        InsanityEffect effect = null;
        if (insanityEvent.getUpcomingEvent() instanceof LivingEvent.LivingUpdateEvent && !world.isRemote) {
            effect = ModInsanityEffects.getRandomEffect(world, player, insanityEvent.getSanity(), InsanityEffect.EnumTrigger.TICK);
        } else if (insanityEvent.getUpcomingEvent() instanceof LivingAttackEvent) {
            effect = ModInsanityEffects.getRandomEffect(world, player, insanityEvent.getSanity(), InsanityEffect.EnumTrigger.HIT);
        }

        if (effect != null) {
            effect.handle(world, player, insanityEvent.getSanity());
            if (world.isRemote) {
                PacketHandler.network.sendToServer(new PacketHandleInsanity(effect.getName()));
            } else {
                PacketHandler.sendTo(player, new PacketHandleInsanity(effect.getName()));
            }
        }
    }

    public static EntityLivingBase getFittingMob(World world, BlockPos pos, boolean friendlyOnes) {
        List<Biome.SpawnListEntry> spawns = world.getBiome(pos).getSpawnableList(EnumCreatureType.MONSTER);
        if (friendlyOnes)
            spawns.addAll(world.getBiome(pos).getSpawnableList(EnumCreatureType.CREATURE));

        if (spawns.size() <= 0) {
            return new EntityZombie(world);
        } else {
            try {
                return spawns.get(world.rand.nextInt(spawns.size())).entityClass.getConstructor(World.class).newInstance(world);
            } catch (Exception e) {
                return new EntityZombie(world);
            }
        }
    }

    @SubscribeEvent
    public static void onHurt(LivingAttackEvent event) {
        if (event.getEntityLiving() instanceof EntityPlayer && !event.getSource().isDamageAbsolute()) {
            MinecraftForge.EVENT_BUS.post(new InsanityEvent((EntityPlayer) event.getEntityLiving(), Sanity.Util.getSanityCapability((EntityPlayer) event.getEntityLiving()), event));
        }
    }

    @SubscribeEvent
    public static void onItemCraft(PlayerEvent.ItemCraftedEvent event) {
        World world = event.player.world;
        EntityPlayer player = event.player;

        if (ModInsanityEffects.isEffectAvailable(ModInsanityEffects.EFFECT_FLUX, world, player, Sanity.Util.getSanityCapability(player)) &&
                world.rand.nextFloat() < ModInsanityEffects.EFFECT_FLUX.getChance(world, player, Sanity.Util.getSanityCapability(player))) {
            if (ModInsanityEffects.EFFECT_FLUX.handle(world, player, Sanity.Util.getSanityCapability(player))) {
                ((InsanityEffectFlux) ModInsanityEffects.EFFECT_FLUX).enchantItem(world, player, Sanity.Util.getSanityCapability(player), event.crafting);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void handleYellowSign(EntityViewRenderEvent.CameraSetup event) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.world.rand.nextInt(60) == 0) {
            if (mc.pointedEntity != null) {
                if (StreamSupport.stream(mc.pointedEntity.getEquipmentAndArmor().spliterator(), true).anyMatch(stack -> stack.getItem() instanceof ItemShield && stack.getSubCompound("BlockEntityTag") != null && stack.getSubCompound("BlockEntityTag").getString("Pattern") == ModObjects.YELLOW_SIGN_PATTERN.getHashname())) {
                    PacketHandler.network.sendToServer(new PacketYellowSign());
                }
            } else {
                BlockPos pos = mc.getRenderViewEntity().rayTrace(8, (float) event.getRenderPartialTicks()).getBlockPos();
                if (pos != null) {
                    if (mc.world.getTileEntity(pos) instanceof TileEntityBanner) {
                        if (((TileEntityBanner) mc.world.getTileEntity(pos)).getPatternList().contains(ModObjects.YELLOW_SIGN_PATTERN)) {
                            PacketHandler.network.sendToServer(new PacketYellowSign());
                        }
                    }
                }
            }
        }
    }
}
