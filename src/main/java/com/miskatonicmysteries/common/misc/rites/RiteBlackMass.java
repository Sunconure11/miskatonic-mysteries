package com.miskatonicmysteries.common.misc.rites;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.block.tile.TileEntityOctagram;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.entity.EntityDarkYoung;
import com.miskatonicmysteries.registry.ModObjects;
import com.miskatonicmysteries.util.WorldGenUtil;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleSmokeLarge;
import net.minecraft.client.particle.ParticleSpell;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class RiteBlackMass extends OctagramRite {
    private static final Biome.SpawnListEntry DARK_YOUNG_SPAWN = new Biome.SpawnListEntry(EntityDarkYoung.class, 1, 1, 2);
    public RiteBlackMass() {
        super(new ResourceLocation(MiskatonicMysteries.MODID, "black_mass"), 200, 50, 200, EnumType.FOCUSED, Blessing.SHUB, Blessing.SHUB, Ingredient.fromItem(Items.MUTTON), Ingredient.fromItem(Items.MUTTON), Ingredient.fromItem(Items.ENDER_PEARL), Ingredient.fromItem(Items.ENDER_PEARL), Ingredient.fromItem(ModObjects.infested_wheat), Ingredient.fromItem(ModObjects.infested_wheat), Ingredient.fromStacks(new ItemStack(Blocks.WOOL, 1, EnumDyeColor.BLACK.getMetadata())), Ingredient.fromStacks(new ItemStack(Blocks.WOOL, 1, EnumDyeColor.BLACK.getMetadata())));
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
        Particle p = new ParticleSmokeLarge.Factory().createParticle(0, octagram.getWorld(), octagram.getPos().getX() + 0.5 + (octagram.getWorld().rand.nextGaussian() / 4), octagram.getPos().getY(), octagram.getPos().getZ() + 0.5 + (octagram.getWorld().rand.nextGaussian() / 4), 0, octagram.getWorld().rand.nextFloat() / 10F, 0);
        Minecraft.getMinecraft().effectRenderer.addEffect(p);
    }



    @Override
    public void effect(TileEntityOctagram octagram, @Nullable EntityPlayer trigger) {
        World world = octagram.getWorld();

        if (!world.isRemote) {
            WorldGenUtil.spawnEntities(DARK_YOUNG_SPAWN, world, octagram.getPos().getX(), octagram.getPos().getZ(), 16, 16, octagram.getWorld().rand);
        }
    }


    @Override
    public boolean checkShouldTrigger(TileEntityOctagram octagram, @Nullable EntityPlayer closest) {
        return !octagram.getWorld().getEntitiesWithinAABB(EntityLivingBase.class, Block.FULL_BLOCK_AABB.grow(1, 0, 1).offset(octagram.getPos()), l -> l instanceof IMob || l instanceof EntityPlayer).isEmpty();

    }
}
