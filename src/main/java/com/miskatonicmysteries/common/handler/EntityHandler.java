package com.miskatonicmysteries.common.handler;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.entity.EntityDarkYoung;
import com.miskatonicmysteries.common.entity.IIgnoreMaterials;
import com.miskatonicmysteries.registry.ModObjects;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityVindicator;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.world.GetCollisionBoxesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = MiskatonicMysteries.MODID)
public class EntityHandler {
    @SubscribeEvent
    public static void dropTallow(LivingDropsEvent event){
        if (event.getEntityLiving().isBurning()){
            EntityLivingBase living = event.getEntityLiving();
            if (living instanceof EntityPig || living instanceof EntityVillager || living instanceof EntityPlayer || living instanceof EntityVindicator) //or cultist
                event.getDrops().add(new EntityItem(living.world, living.posX, living.posY, living.posZ, new ItemStack(ModObjects.tallow, living.getRNG().nextInt(2 + event.getLootingLevel()))));
        }
    }

    @SubscribeEvent
    public static void clipThruWood(GetCollisionBoxesEvent event){
        if (event.getEntity() instanceof IIgnoreMaterials){
            World world = event.getWorld();
            event.getCollisionBoxesList().removeIf(box -> ((IIgnoreMaterials) event.getEntity()).checkIgnore().test(world.getBlockState(new BlockPos(box.minX, box.minY, box.minZ))));
        }
    }
}
