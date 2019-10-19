package com.miskatonicmysteries.common.handler.effects;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.client.render.RenderManipulatorHandler;
import com.miskatonicmysteries.common.capability.sanity.ISanity;
import com.miskatonicmysteries.common.capability.sanity.Sanity;
import com.miskatonicmysteries.common.handler.InsanityHandler;
import com.miskatonicmysteries.registry.ModPotions;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.List;

public class InsanityEffectDistortion extends InsanityEffect {
    public InsanityEffectDistortion() {
        super(new ResourceLocation(MiskatonicMysteries.MODID, "entity_distortion"), 0.4F, 75);
    }

    @Override
    public boolean handle(World world, EntityPlayer player, ISanity sanity) {
        player.addPotionEffect(new PotionEffect(ModPotions.mania, 60, 0));
        if (world.isRemote){
            List<Entity> livings = world.getEntitiesInAABBexcluding(player, player.getEntityBoundingBox().grow(50, 10, 50), p -> p instanceof EntityLivingBase && (p instanceof IMob || p instanceof EntityPlayer));
            if (!livings.isEmpty()) {
                int amount = Math.max((int) (8 * (1 - sanity.getSanity() / (float) sanity.getSanityMax())), 1);//(int) ((float) (100 - Sanity.Util.getSanity(player)) / 20F) + player.getRNG().nextInt(3) + 2;
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
                return true;
            }
        }
        return false;
    }
}
