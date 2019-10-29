package com.miskatonicmysteries.common.handler.effects;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.sanity.ISanity;
import com.miskatonicmysteries.registry.ModInsanityEffects;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class InsanityEffectPublicConcern extends InsanityEffect {
    public InsanityEffectPublicConcern() {
        super(new ResourceLocation(MiskatonicMysteries.MODID, "public_concern"), 0.3F, 140);
    }

    @Override
    public float getChance(World world, EntityPlayer player, ISanity sanity) {
        return super.getChance(world, player, sanity) * ModInsanityEffects.getExponentialSanityFactor(sanity);
    }

    //todo, limit the amount of messages one may get per day
    @Override
    public boolean handle(World world, EntityPlayer player, ISanity sanity) {
        if (world.isRemote){
            List<Entity> players = world.getEntitiesInAABBexcluding(player, player.getEntityBoundingBox().grow(5, 2, 5), p -> p instanceof EntityPlayer);
            if (!players.isEmpty()) {
                EntityPlayer entityPlayerIn = (EntityPlayer) players.get(player.getRNG().nextInt(players.size()));
                if (!player.isInvisibleToPlayer(entityPlayerIn)) {
                    int messageIn = player.getRNG().nextInt(12);
                    entityPlayerIn.sendStatusMessage(new TextComponentString(TextFormatting.ITALIC + I18n.format("message.insanity_concern." + messageIn, player.getDisplayName())), false);
                }
                return true;
            }
        }
        return false;
    }
}
