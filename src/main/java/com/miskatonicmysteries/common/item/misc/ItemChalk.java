package com.miskatonicmysteries.common.item.misc;

import com.miskatonicmysteries.common.block.BlockOctagram;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBed;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemChalk extends ItemBlock {
    public ItemChalk(BlockOctagram block) {
        super(block);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            return EnumActionResult.SUCCESS;
        } else if (facing != EnumFacing.UP) {
            return EnumActionResult.FAIL;
        }

        if (!worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos)) {
            pos = pos.up();
        }
        boolean flag = block instanceof BlockOctagram && ((BlockOctagram) block).canPlace(worldIn, pos);
        if (flag && canPlaceBlockOnSide(worldIn, pos, facing, player, player.getHeldItem(hand))) {
            IBlockState state = block.getDefaultState().withProperty(BlockOctagram.FACING, EnumFacing.getHorizontal(MathHelper.floor((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3));
            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    worldIn.setBlockState(pos.add(x, 0, z), state.withProperty(BlockOctagram.PART, x == 0 && z == 0 ? BlockOctagram.EnumPartType.CENTER : BlockOctagram.EnumPartType.OUTER));
                }
            }
            worldIn.playSound(null, pos, SoundEvents.BLOCK_SNOW_BREAK, SoundCategory.BLOCKS, 0.5F, 2F);
            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    worldIn.notifyNeighborsRespectDebug(pos.add(x, 0, z), worldIn.getBlockState(pos.add(x, 0, z)).getBlock(), false);
                }
            }
            if (player instanceof EntityPlayerMP) {
                CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP) player, pos, player.getHeldItem(hand));
            }
            player.getHeldItem(hand).shrink(1);
            return EnumActionResult.SUCCESS;
        } else {
            return EnumActionResult.FAIL;
        }
    }

    @Override
    public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side, EntityPlayer player, ItemStack stack) {
        if (side != EnumFacing.UP) {
            return false;
        }
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                if (!(block instanceof BlockOctagram && ((BlockOctagram) block).canPlace(worldIn, pos.add(x, 0, z))) || !super.canPlaceBlockOnSide(worldIn, pos.add(x, 0, z), side, player, stack)) {
                    return false;
                }
            }
        }
        return true;
    }
}
