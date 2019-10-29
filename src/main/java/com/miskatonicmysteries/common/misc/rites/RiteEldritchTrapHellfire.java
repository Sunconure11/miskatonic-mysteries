package com.miskatonicmysteries.common.misc.rites;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.block.tile.TileEntityOctagram;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.util.WorldGenUtil;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleSpell;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class RiteEldritchTrapHellfire extends OctagramRite {
    public RiteEldritchTrapHellfire() {
        super(new ResourceLocation(MiskatonicMysteries.MODID, "hellfire_eldritch_trap"), 175, 20, 260, EnumType.PRIMED, Blessing.NONE, Blessing.NONE, Ingredient.fromItem(Items.ROTTEN_FLESH), Ingredient.fromItem(Items.ROTTEN_FLESH), Ingredient.fromItem(Items.BLAZE_POWDER), Ingredient.fromItem(Items.BLAZE_POWDER), Ingredient.fromItem(Items.NETHER_WART), Ingredient.fromItem(Items.NETHER_WART), Ingredient.fromStacks(new ItemStack(Blocks.SOUL_SAND)), Ingredient.fromStacks(new ItemStack(Blocks.SOUL_SAND)));
    }

    @Override
    public boolean test(TileEntityOctagram octagram) {
        return octagram.getWorld().getWorldInfo().getDifficulty() != EnumDifficulty.PEACEFUL;
    }

    @Override
    public void doRitual(TileEntityOctagram octagram, @Nullable EntityPlayer caster) {
        if (octagram.getWorld().isRemote)
            spawnParticles(octagram);
    }

    @SideOnly(Side.CLIENT)
    private void spawnParticles(TileEntityOctagram octagram) {
        Particle p = new ParticleSpell.MobFactory().createParticle(0, octagram.getWorld(), octagram.getPos().getX() + 0.5 + (octagram.getWorld().rand.nextGaussian() / 2), octagram.getPos().getY(), octagram.getPos().getZ() + 0.5 + (octagram.getWorld().rand.nextGaussian() / 2), 0, 0, 0);
        p.setRBGColorF(0.05F, 0.5F, 0.4F);
        Minecraft.getMinecraft().effectRenderer.addEffect(p);
    }

    @SideOnly(Side.CLIENT)
    private void spawnSpawnParticles(TileEntityOctagram octagram) { //nice name lol
        Particle p = new ParticleSpell.MobFactory().createParticle(0, octagram.getWorld(), octagram.getPos().getX() + 0.5 + (octagram.getWorld().rand.nextGaussian() / 2), octagram.getPos().getY(), octagram.getPos().getZ() + 0.5 + (octagram.getWorld().rand.nextGaussian() / 2), octagram.getWorld().rand.nextGaussian(), 0, octagram.getWorld().rand.nextGaussian());
        p.setRBGColorF(0.5F, 0.3F, 0.0F);
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
        List<EntityLivingBase> triggers = octagram.getWorld().getEntitiesWithinAABB(EntityLivingBase.class, Block.FULL_BLOCK_AABB.grow(2, 0, 2).offset(octagram.getPos()), l -> l instanceof IMob || l instanceof EntityPlayer);
        for (int i = 0; i < 4 + world.rand.nextInt(4) + triggers.size(); i++) {
            EntityLiving entity = null;
            switch (world.rand.nextInt(3)) {
                case 0: {
                    entity = new EntityPigZombie(world);
                    entity.setRevengeTarget(triggers.isEmpty() ? trigger : triggers.get(world.rand.nextInt(triggers.size())));
                    break;
                }
                case 1: {
                    entity = new EntityBlaze(world);
                    if (world.rand.nextInt(3) == 0) {
                        entity = new EntityCaveSpider(world);
                    }
                    break;
                }
                case 2: {
                    entity = new EntityMagmaCube(world);
                    if (world.rand.nextInt(8) == 0) {
                        entity = new EntityGhast(world);
                    }
                    break;
                }
            }

            if (entity != null) {
                entity.setDropItemsWhenDead(false);
                entity.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 1000, 0));
                entity.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 1000, 0));
                entity.setAttackTarget(triggers.isEmpty() ? trigger : triggers.get(world.rand.nextInt(triggers.size())));
                WorldGenUtil.spawnEntity(entity, world, octagram.getPos().up(), 2 + world.rand.nextInt(10), 5);
            }
        }
    }


    @Override
    public boolean checkShouldTrigger(TileEntityOctagram octagram, @Nullable EntityPlayer closest) {
        return !octagram.getWorld().getEntitiesWithinAABB(EntityLivingBase.class, Block.FULL_BLOCK_AABB.grow(1, 0, 1).offset(octagram.getPos()), l -> l instanceof IMob || l instanceof EntityPlayer).isEmpty();

    }
}
