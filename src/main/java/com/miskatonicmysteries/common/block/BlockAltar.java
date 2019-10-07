package com.miskatonicmysteries.common.block;

import com.miskatonicmysteries.common.block.tile.BlockTileEntity;
import com.miskatonicmysteries.common.block.tile.TileEntityAltar;
import com.miskatonicmysteries.common.network.PacketHandler;
import com.miskatonicmysteries.util.InventoryUtil;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;

public class BlockAltar extends BlockTileEntity<TileEntityAltar> {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    private static final AxisAlignedBB AABB = new AxisAlignedBB(0, 0, 0, 1, 20.0/16, 1); //24

    public BlockAltar() {
        super(Material.ROCK);
        setLightOpacity(0);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntityAltar altar = getTileEntity(worldIn, pos);
        if (playerIn.isSneaking()){
           if (!altar.inventory.getStackInSlot(0).isEmpty()){
               ItemHandlerHelper.giveItemToPlayer(playerIn, altar.inventory.extractItem(0, 1, false));
           }
        }else{
            if (altar.inventory.getStackInSlot(0).isEmpty() && TileEntityAltar.BOOK_TEXTURES.containsKey(playerIn.inventory.getCurrentItem().getItem())){
                playerIn.inventory.setItemStack(altar.inventory.insertItem(0, playerIn.inventory.decrStackSize(playerIn.inventory.currentItem, 1), false));
            }else
            if (playerIn.getHeldItem(hand).isEmpty() && !worldIn.isRemote && hand == EnumHand.MAIN_HAND) {
                altar.bookOpen = !altar.bookOpen;
                altar.markDirty();
                worldIn.updateComparatorOutputLevel(pos, worldIn.getBlockState(pos).getBlock());
                PacketHandler.updateTE(altar);
                return true;
            }
        }
        PacketHandler.updateTE(altar);
        return false;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        InventoryUtil.dropAllItems(worldIn, getTileEntity(worldIn, pos).inventory, pos);
        super.breakBlock(worldIn, pos, state);
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
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return AABB;
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
        return new AxisAlignedBB(0, 0, 0, 0, 0,0);
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
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public Class<TileEntityAltar> getTileEntityClass() {
        return TileEntityAltar.class;
    }

    @Override
    public TileEntityAltar createTileEntity(World world, IBlockState state) {
        return new TileEntityAltar();
    }
}