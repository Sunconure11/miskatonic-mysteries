package com.miskatonicmysteries.common.block;

import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.misc.IHasAssociatedBlessing;
import com.miskatonicmysteries.util.InventoryUtil;
import com.miskatonicmysteries.util.Util;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockMural extends Block implements IHasAssociatedBlessing{
	public ItemStack item = ItemStack.EMPTY;
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	protected Blessing blessing;
	public BlockMural(Material mat, Blessing blessing) {
		super(mat);
		this.blessing = blessing;
	}

	@Override
	public Blessing getAssociatedBlessing() {
		return blessing;
	}

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getIndex();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		if (meta < 2 || meta > 5) {
			meta = 2;
		}
		return getDefaultState().withProperty(FACING, EnumFacing.getFront(meta));
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack) {
		EnumFacing entityFacing = entity.getHorizontalFacing();

		if (!world.isRemote) {
			if (entityFacing == EnumFacing.NORTH) {
				entityFacing = EnumFacing.SOUTH;
			} else if (entityFacing == EnumFacing.EAST) {
				entityFacing = EnumFacing.WEST;
			} else if (entityFacing == EnumFacing.SOUTH) {
				entityFacing = EnumFacing.NORTH;
			} else if (entityFacing == EnumFacing.WEST) {
				entityFacing = EnumFacing.EAST;
			}

			world.setBlockState(pos, state.withProperty(FACING, entityFacing), 2);
		}
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing face, float hitX, float hitY, float hitZ) {
		if (player.getHeldItem(hand).getItem() instanceof ItemWritableBook) {
			if (!world.isRemote) InventoryUtil.giveAndConsumeItem(player, hand, item);
			return true;
		}
		return super.onBlockActivated(world, pos, state, player, hand, face, hitX, hitY, hitZ);
	}
}