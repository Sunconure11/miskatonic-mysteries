package com.miskatonicmysteries.common.misc.spells;

import com.miskatonicmysteries.MiskatonicMysteries;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;

public class SpellGrowth extends Spell {
    public SpellGrowth() {
        super(new ResourceLocation(MiskatonicMysteries.MODID, "growth"), Ingredient.fromStacks(new ItemStack(Items.DYE, 1, EnumDyeColor.WHITE.getDyeDamage())));
        this.castTime = 0;
        this.cooldownTime = 20;
    }

    @Override
    public boolean check(EntityPlayer caster) {
        return super.check(caster);
    }

    @Override
    public void startCasting(EntityPlayer caster, BlockPos pos, EnumFacing facing) {
        Iterable<BlockPos> posList = BlockPos.getAllInBox(pos.add(1, 1, 1), pos.add(-1, -1, -1));
        posList.forEach(posIn -> {
            if (caster.getRNG().nextBoolean()){
                ItemDye.applyBonemeal(ItemStack.EMPTY, caster.world, posIn);
            }
        });
        super.startCasting(caster, pos, facing);
    }

    @Override
    public void whileCasting(EntityPlayer caster) {
        super.whileCasting(caster);
    }

    @Override
    public void cast(EntityPlayer caster) {
        caster.world.playSound(null, caster.getPosition(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.PLAYERS, 1, 0.8F);
    }

    @Override
    public void price(EntityPlayer caster) {
        caster.inventory.clearMatchingItems(Items.DYE, EnumDyeColor.WHITE.getMetadata(), 1, null);
    }
}
