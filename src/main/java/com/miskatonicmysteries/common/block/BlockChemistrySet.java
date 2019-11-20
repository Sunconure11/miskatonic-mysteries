package com.miskatonicmysteries.common.block;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.client.particles.ParticleOccultFlame;
import com.miskatonicmysteries.common.block.tile.BlockTileEntity;
import com.miskatonicmysteries.common.block.tile.TileEntityAltar;
import com.miskatonicmysteries.common.block.tile.TileEntityChemistrySet;
import com.miskatonicmysteries.common.network.PacketHandler;
import com.miskatonicmysteries.registry.ModObjects;
import com.miskatonicmysteries.util.InventoryUtil;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockRedstoneTorch;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEndRod;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Random;

public class BlockChemistrySet extends BlockTileEntity<TileEntityChemistrySet> {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyBool LIT = PropertyBool.create("lit");

    public BlockChemistrySet() {
        super(Material.ROCK);
        setLightOpacity(0);
        setLightLevel(5);
        setDefaultState(getDefaultState().withProperty(LIT, false).withProperty(FACING, EnumFacing.EAST));
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntityChemistrySet set = getTileEntity(worldIn, pos);
        set.tank.setTileEntity(set);
        if (worldIn.isRemote) return true;
        if (set.isDone() && set.collectResults(playerIn, playerIn.getHeldItem(hand))) return true;
        if (playerIn.getHeldItem(hand).hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)) {
            IFluidHandlerItem cap = playerIn.getHeldItem(hand).getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
            if (cap instanceof FluidBucketWrapper) {
                FluidBucketWrapper wrapper = (FluidBucketWrapper) cap;
                FluidStack fluid = wrapper.getFluid();
                if ((fluid == null || set.tank.canFillFluidType(fluid) && FluidUtil.interactWithFluidHandler(playerIn, hand, set.tank))) {
                    worldIn.updateComparatorOutputLevel(pos, this);
                    worldIn.notifyBlockUpdate(pos, worldIn.getBlockState(pos), worldIn.getBlockState(pos), 2);
                }
            }
        } else if (playerIn.getHeldItem(hand).getItem() instanceof ItemFlintAndSteel) {
            worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty(LIT, true));
            playerIn.getHeldItem(hand).damageItem(1, playerIn);
        } else if (playerIn.isSneaking() || playerIn.getHeldItem(hand).isEmpty()) {
            int slot = InventoryUtil.getNextStackSlot(set.inventory);
            if (slot > -1) {
                ItemHandlerHelper.giveItemToPlayer(playerIn, set.inventory.extractItem(slot, 1, false));
            }else{
                worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty(LIT, false));
            }
        } else {
            int slot = InventoryUtil.getFreeSlot(set.inventory);
            if (slot > -1) {
                InventoryUtil.insertCurrentItemStack(playerIn, set.inventory, slot);
                worldIn.updateComparatorOutputLevel(pos, this);
                return true;
            }
            if (set.inventory.getStackInSlot(0).isEmpty() && TileEntityAltar.BOOK_TEXTURES.containsKey(playerIn.inventory.getCurrentItem().getItem())) {
                InventoryUtil.insertCurrentItemStack(playerIn, set.inventory, 0);
                worldIn.updateComparatorOutputLevel(pos, this);
                return true;
            }
        }
        PacketHandler.updateTE(set);
        set.markDirty();
        return false;
    }


    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        InventoryUtil.dropAllItems(worldIn, getTileEntity(worldIn, pos).inventory, pos);
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, LIT);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int meta = 0;
        meta |= state.getValue(FACING).getIndex();
        if (state.getValue(LIT))
            meta |= 6;
        return meta;
    }


    @Override
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
        return new AxisAlignedBB(0, 0, 0, 0, 0, 0);
    }


    @Override
    public IBlockState getStateFromMeta(int meta) {
        if (meta < 2 || meta > 5) {
            meta = 2;
        }
        return getDefaultState().withProperty(FACING, EnumFacing.getFront(meta)).withProperty(LIT, (meta & 6) > 0);
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
        return super.getLightValue(state, world, pos);
    }

    @Override
    public int getLightValue(IBlockState state) {
        return state.getValue(LIT) ? super.getLightValue(state) : 0;
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

            world.setBlockState(pos, state.withProperty(FACING, entityFacing).withProperty(LIT, false), 2);
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
    public Class<TileEntityChemistrySet> getTileEntityClass() {
        return TileEntityChemistrySet.class;
    }

    @Override
    public TileEntityChemistrySet createTileEntity(World world, IBlockState state) {
        return new TileEntityChemistrySet();
    }
}