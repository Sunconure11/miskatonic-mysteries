package com.miskatonicmysteries.common.item.consumable;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.sanity.Sanity;
import com.miskatonicmysteries.common.potion.ModPotion;
import com.miskatonicmysteries.registry.ModPotions;
import com.miskatonicmysteries.util.InventoryUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.util.UUID;

public class ItemReAgent extends Item {

    public ItemReAgent() {
        super();
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onDeath(LivingDamageEvent event){
        if (!event.getEntityLiving().world.isRemote && event.getEntityLiving() instanceof EntityPlayer && (event.getEntityLiving().getHealth() - event.getAmount() <= 0) &&  (event.getSource().getDamageType() != "explosion.player" || event.getSource() != MiskatonicMysteries.VORE || event.getSource() != DamageSource.ON_FIRE)){
            ItemStack stack = InventoryUtil.getItemInHotbar((EntityPlayer) event.getEntityLiving(), this);
            if (!stack.isEmpty()){
                int useFactor = event.getEntityLiving().getActivePotionEffect(ModPotions.lazarus) != null ? event.getEntityLiving().getActivePotionEffect(ModPotions.lazarus).getAmplifier() : -1;
                if (useFactor < 4) {
                    event.setCanceled(true);
                    event.getEntityLiving().setHealth((event.getEntityLiving().getMaxHealth() / (3F + useFactor)));
                    event.getEntityLiving().removePotionEffect(ModPotions.lazarus);
                    event.getEntityLiving().addPotionEffect(new PotionEffect(ModPotions.lazarus, 24000, useFactor + 1));
                    if (useFactor < 0) {
                        event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 1800, 0));
                    }
                }
                stack.shrink(1);
            }
        }
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if (!target.world.isRemote && target instanceof EntityZombieVillager && target.isEntityAlive()) {
            EntityZombieVillager villager = (EntityZombieVillager) target;
            if (!villager.isConverting()){
                if (playerIn.getRNG().nextBoolean()){
                    try {
                        ObfuscationReflectionHelper.findMethod(EntityZombieVillager.class, "func_191991_a", void.class, UUID.class, int.class).invoke(villager, playerIn.getUniqueID(), 300);
                        playerIn.getCooldownTracker().setCooldown(this, 100);
                    }catch (Exception ignore){
                        ignore.printStackTrace();
                    }
                }else{
                    villager.attackEntityFrom(DamageSource.WITHER, 666);
                }
                stack.shrink(1);
            }
            return true;
        }
        return super.itemInteractionForEntity(stack, playerIn, target, hand);
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        playerIn.setActiveHand(handIn);
        return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
    }
}
