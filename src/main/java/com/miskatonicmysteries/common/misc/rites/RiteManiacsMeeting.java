package com.miskatonicmysteries.common.misc.rites;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.block.tile.TileEntityOctagram;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.entity.cultist.AbstractCultist;
import com.miskatonicmysteries.common.entity.cultist.EntityHasturCultist;
import com.miskatonicmysteries.common.entity.cultist.EntityShubCultist;
import com.miskatonicmysteries.registry.ModPotions;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleSpell;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class RiteManiacsMeeting extends OctagramRite {
    protected Blessing associatedGOO;
    public RiteManiacsMeeting(Blessing associatedGOO, Ingredient... reagents) {
        super(new ResourceLocation(MiskatonicMysteries.MODID, "maniacs_meeting_" + associatedGOO.getName()), 250, 50, 200, EnumType.FOCUSED, associatedGOO, associatedGOO, reagents);
        this.associatedGOO = associatedGOO;
    }

    @Override
    public boolean test(TileEntityOctagram octagram) {
        return octagram.getWorld().getEntitiesWithinAABB(EntityVillager.class, Block.FULL_BLOCK_AABB.grow(5, 2, 5).offset(octagram.getPos()), v -> v.getActivePotionEffect(ModPotions.mania) != null).isEmpty();
    }

    @Override
    public void doRitual(TileEntityOctagram octagram, @Nullable EntityPlayer caster) {
        System.out.println(octagram.getWorld().isRemote);
        if (octagram.getWorld().isRemote)
            spawnParticles(octagram);
    }

    @SideOnly(Side.CLIENT)
    private void spawnParticles(TileEntityOctagram octagram) {
        Particle p = new ParticleSpell.MobFactory().createParticle(0, octagram.getWorld(), octagram.getPos().getX() + 0.5 + (octagram.getWorld().rand.nextGaussian() * 2), octagram.getPos().getY(), octagram.getPos().getZ() + 0.5 + (octagram.getWorld().rand.nextGaussian() * 2), 0, 0, 0);
        p.setRBGColorF(0.7F, 0.05F, 0);
        Minecraft.getMinecraft().effectRenderer.addEffect(p);
    }

    @Override
    public void effect(TileEntityOctagram octagram, @Nullable EntityPlayer caster) {
        if (caster != null) {
            List<EntityVillager> villagers = octagram.getWorld().getEntitiesWithinAABB(EntityVillager.class, Block.FULL_BLOCK_AABB.grow(5, 2, 5).offset(octagram.getPos()), v -> v.getActivePotionEffect(ModPotions.mania) != null);
            villagers.forEach(v -> {
                if (octagram.getWorld().rand.nextFloat() < 0.33F) {
                    AbstractCultist cultist = new EntityHasturCultist(octagram.getWorld());
                    if (associatedGOO == Blessing.SHUB) {
                        cultist = new EntityShubCultist(octagram.getWorld());
                    } else if (associatedGOO == Blessing.HASTUR) {
                        cultist = new EntityHasturCultist(octagram.getWorld());
                    }
                    cultist.setPositionAndRotation(v.posX, v.posY, v.posZ, v.rotationYaw, v.rotationPitch);
                    cultist.setTamedBy(caster);
                    cultist.setCustomNameTag(v.getCustomNameTag());
                    v.setDead();
                    cultist.onInitialSpawn(octagram.getWorld().getDifficultyForLocation(octagram.getPos()), null);
                    octagram.getWorld().spawnEntity(cultist);
                }
            });
        }
    }

    @Override
    public boolean checkShouldTrigger(TileEntityOctagram octagram, @Nullable EntityPlayer closest) {
        return true;
    }
}
