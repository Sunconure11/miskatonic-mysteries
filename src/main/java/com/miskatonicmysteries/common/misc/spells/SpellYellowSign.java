package com.miskatonicmysteries.common.misc.spells;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.blessing.BlessingCapability;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.registry.ModObjects;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SpellYellowSign extends Spell {
    public SpellYellowSign() {
        super(new ResourceLocation(MiskatonicMysteries.MODID, "yellow_sign"), Ingredient.fromStacks(new ItemStack(Items.DYE, 1, 11)));
        this.castTime = 1;
        this.cooldownTime = 3600;
    }

    @Override
    public boolean check(EntityPlayer caster) {
        if (caster.experienceLevel >= 3 && BlessingCapability.Util.getBlessing(caster) == Blessing.HASTUR) {
            return super.check(caster);
        }
        return false;
    }

    @Override
    public void startCasting(EntityPlayer caster, BlockPos pos, EnumFacing facing) {
        World world = caster.world;
        IBlockState oldState = world.getBlockState(pos);
        Block block = oldState.getBlock();

        if (!block.isReplaceable(world, pos))
        {
            pos = pos.offset(facing);
        }

        if (caster.canPlayerEdit(pos, facing, ItemStack.EMPTY) && world.mayPlace(ModObjects.yellow_sign, pos, false, facing, caster)) {
            IBlockState state = ModObjects.yellow_sign.getStateForPlacement(world, pos, facing, 0.5F, 0.5F, 0.5F, 0, caster, caster.getActiveHand());
            if (!placeSign(world, pos, caster, state)){
                onCancelled(caster);
            }

        }
        super.startCasting(caster, pos, facing);
    }

    private boolean placeSign(World world, BlockPos pos, EntityPlayer caster, IBlockState newState){
        if (!world.setBlockState(pos, newState, 11)) return false;

        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() == ModObjects.yellow_sign) {
            ModObjects.yellow_sign.onBlockPlacedBy(world, pos, state, caster, caster.getHeldItem(caster.getActiveHand()));

            if (caster instanceof EntityPlayerMP)
                CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)caster, pos, caster.getHeldItem(caster.getActiveHand()));
        }
        return true;
    }

    @Override
    public void price(EntityPlayer caster) {
        caster.inventory.clearMatchingItems(Items.DYE, 11, 1, null);
        caster.addExperienceLevel(-3);
    }
}
