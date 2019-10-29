package com.miskatonicmysteries.common.misc.rites;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.block.tile.TileEntityOctagram;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.capability.spells.ISpellKnowledge;
import com.miskatonicmysteries.common.capability.spells.SpellKnowledge;
import com.miskatonicmysteries.common.entity.cultist.AbstractCultist;
import com.miskatonicmysteries.common.entity.cultist.EntityHasturCultist;
import com.miskatonicmysteries.common.entity.cultist.EntityShubCultist;
import com.miskatonicmysteries.common.potion.ModPotion;
import com.miskatonicmysteries.registry.ModObjects;
import com.miskatonicmysteries.registry.ModPotions;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleSpell;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class RiteBlankSlate extends OctagramRite {

    public RiteBlankSlate() {
        super(new ResourceLocation(MiskatonicMysteries.MODID, "blank_slate"), 200, 20, 300, EnumType.FOCUSED, Blessing.NONE, Blessing.NONE, Ingredient.fromItem(ModObjects.tranquilizer), Ingredient.fromItem(ModObjects.tranquilizer), Ingredient.fromItem(Items.PRISMARINE_CRYSTALS), Ingredient.fromItem(Items.DIAMOND), Ingredient.fromItem(Items.STRING), Ingredient.fromItem(Items.STRING), Ingredient.fromItem(Items.IRON_INGOT), Ingredient.fromItem(Items.IRON_INGOT));
    }

    @Override
    public boolean test(TileEntityOctagram octagram) {
        return !octagram.getWorld().getEntitiesWithinAABB(EntityPlayer.class, Block.FULL_BLOCK_AABB.grow(2, 2, 2).offset(octagram.getPos()), p -> !SpellKnowledge.Util.getKnowledge(p).getSpellCooldowns(false).isEmpty()).isEmpty();
    }

    @Override
    public void doRitual(TileEntityOctagram octagram, @Nullable EntityPlayer caster) {
        if (octagram.getWorld().isRemote)
            spawnParticles(octagram);
    }

    @SideOnly(Side.CLIENT)
    private void spawnParticles(TileEntityOctagram octagram) {
        Particle p = new ParticleSpell.MobFactory().createParticle(0, octagram.getWorld(), octagram.getPos().getX() + 0.5 + (octagram.getWorld().rand.nextGaussian() * 2), octagram.getPos().getY(), octagram.getPos().getZ() + 0.5 + (octagram.getWorld().rand.nextGaussian() * 2), 0, 0, 0);
        p.setRBGColorF(0.8F, 0.8F, 0.8F);
        Minecraft.getMinecraft().effectRenderer.addEffect(p);
    }

    @Override
    public void effect(TileEntityOctagram octagram, @Nullable EntityPlayer caster) {
        if (!octagram.getWorld().isRemote) {
            List<EntityPlayer> players = octagram.getWorld().getEntitiesWithinAABB(EntityPlayer.class, Block.FULL_BLOCK_AABB.grow(2, 2, 2).offset(octagram.getPos()), p -> !SpellKnowledge.Util.getKnowledge(p).getSpellCooldowns(false).isEmpty());
            if (!players.isEmpty()){
                EntityPlayer player = players.get(0);
                ISpellKnowledge knowledge = SpellKnowledge.Util.getKnowledge(player);
                knowledge.getSpellCooldowns(true).clear();
                player.addPotionEffect(new PotionEffect(ModPotions.tranquilized, 600, 0, false, false));
                player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 1200, 0, false, false));
                player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 1200, 0, false, false));
            }
        }
    }

    @Override
    public boolean checkShouldTrigger(TileEntityOctagram octagram, @Nullable EntityPlayer closest) {
        return true;
    }
}
