package com.miskatonicmysteries.common.block;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.client.particles.ParticleOccultFlame;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockCandles extends Block {
	public static final Material CANDLE = new Material(MapColor.GRAY);
	public static final PropertyInteger CANDLES = PropertyInteger.create("candles", 1, 4);
	public static final PropertyBool LIT = PropertyBool.create("lit");

	public static final AxisAlignedBB BOX_1 = new AxisAlignedBB(6.0D / 16D, 0.0D, 6.0D / 16D, 10.0D / 16D, 6.0D / 16D, 10.0D / 16D);
	public static final AxisAlignedBB BOX_2 = new AxisAlignedBB(3.0D / 16D, 0.0D, 3.0D / 16D, 13.0D / 16D, 6.0D / 16D, 13.0D / 16D);
	public static final AxisAlignedBB BOX_3 = new AxisAlignedBB(2.0D / 16D, 0.0D, 2.0D / 16D, 14.0D / 16D, 6.0D / 16D, 14.0D / 16D);
	public static final AxisAlignedBB BOX_4 = new AxisAlignedBB(2.0D / 16D, 0.0D, 2.0D / 16D, 14.0D / 16D, 7.0D / 16D, 14.0D / 16D);

	public BlockCandles() {
		super(CANDLE);
        setLightOpacity(1);
	}

    @Override
    public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos) {
        return false;
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return worldIn.getBlockState(pos.down()).getBlock().canPlaceTorchOnTop(worldIn.getBlockState(pos.down()), worldIn, pos);
    }

    @Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, CANDLES, LIT);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int meta = 0;
		meta |= state.getValue(CANDLES);
		if (state.getValue(LIT))
			meta |= 4;
		return meta;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(CANDLES, (meta & 3) + 1).withProperty(LIT, (meta & 4) > 0);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		switch (state.getValue(CANDLES)){
			case 1:
			default:
				return BOX_1;
			case 2:
				return BOX_2;
			case 3:
				return BOX_3;
			case 4:
				return BOX_4;
		}
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack) {
		if (!world.isRemote) {
			world.setBlockState(pos, state.withProperty(CANDLES, 1), 2);
		}
	}

	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		return state.getValue(LIT) ? 11 + state.getValue(CANDLES) : 0;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (stateIn.getValue(LIT)) {
			double x = (double) pos.getX() + 0.5D;
			double y = (double) pos.getY() + 0.55D;
			double z = (double) pos.getZ() + 0.5D;
			MiskatonicMysteries.proxy.generateParticle(new ParticleOccultFlame(worldIn, x, y, z, 0, 0, 0).multipleParticleScaleBy(0.8F + (float) rand.nextGaussian() / 20F));
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			if (playerIn.getHeldItem(hand).getItem().equals(Item.getItemFromBlock(this))) {
				if (state.getValue(CANDLES) < 4) {
					worldIn.setBlockState(pos, state.withProperty(CANDLES, state.getValue(CANDLES) + 1), 3);
					playerIn.getHeldItem(hand).shrink(1);
				}
			} else if (playerIn.getHeldItem(hand).getItem() instanceof ItemFlintAndSteel) {
				if (!state.getValue(LIT)) {
					playerIn.world.playSound(playerIn, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 0.5F, 1.0F);
					worldIn.setBlockState(pos, state.withProperty(LIT, true), 3);
				}
			}
			return true;
		}else{
			return true;
		}
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
}