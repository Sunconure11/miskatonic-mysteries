package com.miskatonicmysteries.common.handler.effects;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.sanity.ISanity;
import com.miskatonicmysteries.registry.ModInsanityEffects;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class InsanityEffectFlux extends InsanityEffect {
    public InsanityEffectFlux() {
        super(new ResourceLocation(MiskatonicMysteries.MODID, "latent_flux"), 0.3F, 65, EnumTrigger.SPECIAL);
    }

    @Override
    public float getChance(World world, EntityPlayer player, ISanity sanity) {
        return super.getChance(world, player, sanity) * ModInsanityEffects.getExponentialSanityFactor(sanity);
    }

    @Override
    public boolean handle(World world, EntityPlayer player, ISanity sanity) {
        return true;
    }

    public boolean enchantItem(World world, EntityPlayer player, ISanity sanity, ItemStack stack){
        System.out.println("enchanted stack"); //need to handle that
        return true;
    }
}
