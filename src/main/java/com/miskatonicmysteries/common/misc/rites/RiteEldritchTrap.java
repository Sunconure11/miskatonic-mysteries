package com.miskatonicmysteries.common.misc.rites;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.block.tile.TileEntityOctagram;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.EnumDifficulty;

import javax.annotation.Nullable;

public class RiteEldritchTrap extends OctagramRite {
    public RiteEldritchTrap() {
        super(new ResourceLocation(MiskatonicMysteries.MODID, "eldritch_trap"), 100, 50, 200, EnumType.PRIMED, Blessing.NONE, Blessing.NONE, Ingredient.fromItem(Items.DIAMOND), Ingredient.fromItem(Items.DIAMOND), Ingredient.fromItem(Items.DIAMOND), Ingredient.fromItem(Items.DIAMOND), Ingredient.fromItem(Items.DIAMOND), Ingredient.fromItem(Items.DIAMOND), Ingredient.fromItem(Items.DIAMOND), Ingredient.fromItem(Items.DIAMOND));//Ingredient.fromItem(Items.ROTTEN_FLESH), Ingredient.fromItem(Items.ROTTEN_FLESH),Ingredient.fromItem(Items.SPIDER_EYE), Ingredient.fromItem(Items.SPIDER_EYE), Ingredient.fromItem(Items.GOLD_NUGGET), Ingredient.fromItem(Items.GOLD_NUGGET), Ingredient.fromStacks(new ItemStack(Blocks.RED_MUSHROOM)), Ingredient.fromStacks(new ItemStack(Blocks.RED_MUSHROOM)));
    }

    @Override
    public boolean test(TileEntityOctagram octagram) {
        return octagram.getWorld().getWorldInfo().getDifficulty() != EnumDifficulty.PEACEFUL;
    }

    @Override
    public void doRitual(TileEntityOctagram octagram, @Nullable EntityPlayer caster) {

    }

    @Override
    public void effect(TileEntityOctagram octagram, @Nullable EntityPlayer caster) {
    }

    @Override
    public boolean checkShouldTrigger(TileEntityOctagram octagram, @Nullable EntityPlayer closest) {
        return closest != null && closest.getDistanceSqToCenter(octagram.getPos()) < 2;
    }
}
