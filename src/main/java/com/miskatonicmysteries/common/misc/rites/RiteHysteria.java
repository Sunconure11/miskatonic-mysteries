package com.miskatonicmysteries.common.misc.rites;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.block.tile.TileEntityOctagram;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.capability.sanity.Sanity;
import com.miskatonicmysteries.common.network.PacketHandler;
import com.miskatonicmysteries.registry.ModObjects;
import com.miskatonicmysteries.registry.ModPotions;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleSpell;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class RiteHysteria extends OctagramRite {
    public RiteHysteria() {
        super(new ResourceLocation(MiskatonicMysteries.MODID, "hysteria"), 100, 100, 100, EnumType.PRIMED, Blessing.NONE, Blessing.NONE, Ingredient.fromStacks(new ItemStack(Blocks.RED_MUSHROOM)), Ingredient.fromStacks(new ItemStack(Blocks.RED_MUSHROOM)), Ingredient.fromItem(Items.NETHER_WART), Ingredient.fromItem(Items.NETHER_WART), Ingredient.fromItem(Items.MUTTON), Ingredient.fromItem(ModObjects.infested_wheat), Ingredient.fromItem(ModObjects.infested_wheat));
    }

    @Override
    public boolean test(TileEntityOctagram octagram) {
        return true;
    }

    @Override
    public void doRitual(TileEntityOctagram octagram, @Nullable EntityPlayer caster) {
        if (octagram.getWorld().isRemote)
            spawnParticles(octagram);
    }

    @SideOnly(Side.CLIENT)
    private void spawnParticles(TileEntityOctagram octagram) {
        Particle p = new ParticleSpell.MobFactory().createParticle(0, octagram.getWorld(), octagram.getPos().getX() + 0.5 + (octagram.getWorld().rand.nextGaussian() / 2), octagram.getPos().getY(), octagram.getPos().getZ() + 0.5 + (octagram.getWorld().rand.nextGaussian() / 2), 0, 0, 0);
        p.setRBGColorF(0.7F, 0.05F, 0);
        Minecraft.getMinecraft().effectRenderer.addEffect(p);
    }

    @SideOnly(Side.CLIENT)
    private void spawnSpawnParticles(TileEntityOctagram octagram) { //nice name lol
        Particle p = new ParticleSpell.MobFactory().createParticle(0, octagram.getWorld(), octagram.getPos().getX() + 0.5 + (octagram.getWorld().rand.nextGaussian() / 2), octagram.getPos().getY(), octagram.getPos().getZ() + 0.5 + (octagram.getWorld().rand.nextGaussian() / 2), octagram.getWorld().rand.nextGaussian(), 0, octagram.getWorld().rand.nextGaussian());
        p.setRBGColorF(0.7F, 0.05F, 0);
        Minecraft.getMinecraft().effectRenderer.addEffect(p);
    }


    @Override
    public void effect(TileEntityOctagram octagram, @Nullable EntityPlayer trigger) {
        World world = octagram.getWorld();
        if (world.isRemote) {
            for (int i = 0; i < 66; i++) {
                spawnSpawnParticles(octagram);
            }
        }
        if (!world.isRemote) {
            octagram.getWorld().getEntitiesWithinAABB(EntityLivingBase.class, Block.FULL_BLOCK_AABB.grow(1, 0, 1).offset(octagram.getPos()), l -> l instanceof EntityVillager || l instanceof EntityPlayer).forEach(
                    e -> {
                        if (e.getRNG().nextFloat() < 0.75F) {
                            e.addPotionEffect(new PotionEffect(ModPotions.mania, 4800));
                            if (e instanceof EntityPlayer && Sanity.Util.getSanityCapability((EntityPlayer) e).getHorrifiedCooldown() < 200) {
                                Sanity.Util.getSanityCapability((EntityPlayer) e).setHorrifiedCooldown(200);
                            }
                        }
                    }
            );
        }
    }


    @Override
    public boolean checkShouldTrigger(TileEntityOctagram octagram, @Nullable EntityPlayer closest) {
        return !octagram.getWorld().getEntitiesWithinAABB(EntityLivingBase.class, Block.FULL_BLOCK_AABB.grow(1, 0, 1).offset(octagram.getPos()), l -> l instanceof EntityVillager || l instanceof EntityPlayer).isEmpty();
    }
}
