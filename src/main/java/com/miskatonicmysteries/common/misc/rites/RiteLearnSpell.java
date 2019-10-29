package com.miskatonicmysteries.common.misc.rites;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.client.particles.ParticleOccultEnchant;
import com.miskatonicmysteries.common.block.tile.TileEntityOctagram;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.capability.spells.ISpellKnowledge;
import com.miskatonicmysteries.common.capability.spells.SpellKnowledge;
import com.miskatonicmysteries.common.misc.spells.Spell;
import com.miskatonicmysteries.registry.ModPotions;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class RiteLearnSpell extends OctagramRite {
    protected Spell spellLearned;
    public RiteLearnSpell(Spell spellLearned, int focusPower, int overflowTolerance, int ticksNeeded, Blessing addressedGOO, Blessing knowledge, Ingredient... ingredients) {
        super(new ResourceLocation(MiskatonicMysteries.MODID, "learn_spell_" + spellLearned.name.getResourcePath()), focusPower, overflowTolerance, ticksNeeded, EnumType.FOCUSED, addressedGOO, knowledge, ingredients);
        this.spellLearned = spellLearned;
    }

    @Override
    public boolean test(TileEntityOctagram octagram) {
        return !octagram.getWorld().getEntitiesWithinAABB(EntityPlayer.class, Block.FULL_BLOCK_AABB.grow(1, 0, 1).offset(octagram.getPos())).isEmpty();
    }

    @Override
    public void doRitual(TileEntityOctagram octagram, @Nullable EntityPlayer caster) {
        List<EntityPlayer> players = octagram.getWorld().getEntitiesWithinAABB(EntityPlayer.class, Block.FULL_BLOCK_AABB.grow(1, 0, 1).offset(octagram.getPos()));
        if (!players.isEmpty()){
            caster = players.get(0);
        }
        if (caster != null){
            caster.move(MoverType.SELF, (octagram.getPos().getX() + 0.5F - caster.posX) / 20F, (octagram.getPos().getY() + 0.2F - caster.posY) / 20F,(octagram.getPos().getZ() - caster.posZ) / 20F);
        }
        if (octagram.getWorld().isRemote)
            spawnParticles(octagram, caster);
    }

    @SideOnly(Side.CLIENT)
    private void spawnParticles(TileEntityOctagram octagram, @Nullable  EntityPlayer caster) {
        if (caster != null){
            Particle p = new ParticleOccultEnchant(octagram.getWorld(), octagram.getPos().getX() + 0.5 + (octagram.getWorld().rand.nextGaussian() / 2), octagram.getPos().getY(), octagram.getPos().getZ() + 0.5 + (octagram.getWorld().rand.nextGaussian() / 2), 0, 0, 0, caster.posX, caster.posY + caster.getEyeHeight(), caster.posZ);
            Minecraft.getMinecraft().effectRenderer.addEffect(p);
        }
    }


    @Override
    public void effect(TileEntityOctagram octagram, @Nullable EntityPlayer caster) {
        if (!octagram.getWorld().isRemote && caster != null) {
            ISpellKnowledge knowledge = SpellKnowledge.Util.getKnowledge(caster);
            if (knowledge.getSpellCooldowns(false).size() >= SpellKnowledge.MAX_SPELLS){
                caster.addPotionEffect(new PotionEffect(MobEffects.WITHER, 2000, 10, false, false));
                caster.addPotionEffect(new PotionEffect(ModPotions.mania, 2000, 1, false, false));
                caster.attackEntityFrom(DamageSource.WITHER, 10);
            }else {
                SpellKnowledge.Util.addSpell(spellLearned, caster);
                caster.addPotionEffect(new PotionEffect(ModPotions.mania, 600, 0, false, false));
                caster.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 1200, 0, false, false));
                caster.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 1200, 0, false, false));
            }
        }
    }

    @Override
    public boolean checkShouldTrigger(TileEntityOctagram octagram, @Nullable EntityPlayer closest) {
        return true;
    }
}
