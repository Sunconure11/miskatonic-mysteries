package com.miskatonicmysteries.common.block;

import com.miskatonicmysteries.common.block.tile.BlockTileEntity;
import com.miskatonicmysteries.common.block.tile.TileEntityOctagram;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.misc.IHasAssociatedBlessing;
import com.miskatonicmysteries.common.network.PacketHandler;
import com.miskatonicmysteries.common.network.message.client.PacketYellowSign;
import com.miskatonicmysteries.util.InventoryUtil;
import net.minecraft.block.*;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockYellowSign extends Block {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyBool ON_WALL = PropertyBool.create("on_wall");
    public static final AxisAlignedBB GROUND_AABB = new AxisAlignedBB(0, 0, 0, 1, 0.01, 1);

    protected static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.0D, 0, 0.0D, 0.01, 1, 1.0D);
    protected static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(1 - 0.01, 0, 0.0D, 1.0D, 1, 1.0D);
    protected static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.0D, 0, 0.0D, 1.0D, 1, 0.01);
    protected static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.0D, 0, 1 - 0.01, 1.0D, 1, 1.0D);


    public BlockYellowSign() {
        super(Material.CIRCUITS);
        this.setDefaultState(this.blockState.getBaseState().withProperty(ON_WALL, false).withProperty(FACING, EnumFacing.NORTH));
        setLightOpacity(0);
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return null;
    }


    public boolean canPlaceOnTop(IBlockAccess world, BlockPos pos){
        IBlockState state = world.getBlockState(pos.down());
        return state.isTopSolid() || state.getBlockFaceShape(world, pos.down(), EnumFacing.UP) == BlockFaceShape.SOLID;
    }



    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!state.getValue(ON_WALL)) {
            if (!canPlaceOnTop(worldIn, pos)) {
                worldIn.setBlockToAir(pos);
            }
        }else{
            EnumFacing enumfacing = state.getValue(FACING);

            if (!worldIn.getBlockState(pos.offset(enumfacing.getOpposite())).getMaterial().isSolid()){
                this.dropBlockAsItem(worldIn, pos, state, 0);
                worldIn.setBlockToAir(pos);
            }
        }
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
    }
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        if (state.getValue(ON_WALL)) {
            switch (state.getValue(FACING)) {
                case EAST:
                    return EAST_AABB;
                case WEST:
                    return WEST_AABB;
                case SOUTH:
                    return SOUTH_AABB;
                default:
                    return NORTH_AABB;
            }
        }
        return GROUND_AABB;
    }

    public EnumPushReaction getMobilityFlag(IBlockState state)
    {
        return EnumPushReaction.DESTROY;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }


    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing enumfacing = EnumFacing.getHorizontal(meta);
        return  (meta & 8) > 0 ? this.getDefaultState().withProperty(ON_WALL, false).withProperty(FACING, enumfacing) : this.getDefaultState().withProperty(ON_WALL, true).withProperty(FACING, enumfacing);//this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta & 6)).withProperty(PART, EnumPartType.CENTER.fromIndex(meta & 15));//(meta & 8) > 0 ? this.getDefaultState().withProperty(PART, EnumPartType.CENTER).withProperty(FACING, enumfacing) : this.getDefaultState().withProperty(PART, EnumPartType.OUTER).withProperty(FACING, enumfacing);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i = i | (state.getValue(FACING)).getHorizontalIndex();

        if (!state.getValue(ON_WALL)) {
            i |= 8;
        }
        return i;
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        boolean onWall = false;
        if (facing != EnumFacing.UP){
            onWall = true;
        }
        return getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(ON_WALL, onWall);
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        Minecraft mc = Minecraft.getMinecraft();
        BlockPos posTracked = mc.player.rayTrace(8, mc.getRenderPartialTicks()).getBlockPos();
        if (posTracked != null && pos.equals(posTracked)){
            PacketHandler.network.sendToServer(new PacketYellowSign());
        }
        super.randomDisplayTick(stateIn, worldIn, pos, rand);
    }

    @Override
    public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
        return super.canPlaceBlockOnSide(worldIn, pos, side) &&  worldIn.getBlockState(pos.offset(side.getOpposite())).getBlockFaceShape(worldIn, pos.offset(side.getOpposite()), side) == BlockFaceShape.SOLID;
    }


    @Override
    public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos) {
        return false;
    }

    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, ON_WALL);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Items.AIR;
    }

}