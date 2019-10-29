package com.miskatonicmysteries.common.misc.spells;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.registry.ModObjects;
import com.miskatonicmysteries.registry.ModPotions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleSpell;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SpellMania extends Spell {
    public SpellMania() {
        super(new ResourceLocation(MiskatonicMysteries.MODID, "spell_mania"), Ingredient.fromItem(ModObjects.infested_wheat));
        this.castTime = 80;
        this.cooldownTime = 20 * 60;
    }

    @Override
    public boolean check(EntityPlayer caster) {
        if (super.check(caster)){
            return caster.experienceLevel >= 3;
        }
        return false;
    }


    @Override
    public void cast(EntityPlayer caster) {
        if (caster.world.isRemote)
            spawnParticles(caster);
        caster.world.getEntitiesWithinAABB(EntityLivingBase.class, caster.getEntityBoundingBox().grow(5, 1, 5).offset(caster.posX, caster.posY, caster.posZ), l -> !l.isEntityEqual(caster)).forEach(
                e -> {
                    if (e.getRNG().nextBoolean()) {
                        e.addPotionEffect(new PotionEffect(ModPotions.mania, 400));
                    }
                }
        );
        super.cast(caster);
    }

    @SideOnly(Side.CLIENT)
    private void spawnParticles(EntityPlayer caster) {
        Particle p = new ParticleSpell.MobFactory().createParticle(0, caster.world, caster.posX, caster.posY + caster.getEyeHeight(), caster.posZ, caster.getRNG().nextGaussian(), 0, caster.getRNG().nextGaussian());
        p.setRBGColorF(0.7F, 0.05F, 0);
        Minecraft.getMinecraft().effectRenderer.addEffect(p);
    }


    @Override
    public void price(EntityPlayer caster) {
        caster.inventory.clearMatchingItems(ModObjects.infested_wheat, 0, 1, null);
        caster.addExperienceLevel(-3);
    }
}
