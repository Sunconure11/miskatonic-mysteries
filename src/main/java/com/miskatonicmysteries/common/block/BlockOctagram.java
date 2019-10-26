package com.miskatonicmysteries.common.block;

import com.miskatonicmysteries.common.block.tile.BlockTileEntity;
import com.miskatonicmysteries.common.block.tile.TileEntityOctagram;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.misc.IHasAssociatedBlessing;
import com.miskatonicmysteries.common.network.PacketHandler;
import com.miskatonicmysteries.util.InventoryUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
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

public class BlockOctagram extends BlockTileEntity<TileEntityOctagram> implements IHasAssociatedBlessing {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyEnum<EnumPartType> PART = PropertyEnum.create("part", EnumPartType.class); //maybe have many many parts instead, as in 9
    public static final AxisAlignedBB AABB = new AxisAlignedBB(0, 0, 0, 1, 0.01, 1);

    public static final int[][] PARTS = new int[3][3];

    static {
        PARTS[2][1] = 1;
        PARTS[2][2] = 2;
        PARTS[1][2] = 3;

        PARTS[0][2] = 4;
        PARTS[0][1] = 5;
        PARTS[0][0] = 6;

        PARTS[1][0] = 7;
        PARTS[2][0] = 8;

        PARTS[1][1] = 0;
    }

    protected Blessing blessing;

    public BlockOctagram(Blessing blessing) {
        super(Material.CIRCUITS);
        this.setDefaultState(this.blockState.getBaseState().withProperty(PART, EnumPartType.CENTER).withProperty(FACING, EnumFacing.NORTH));
        setLightOpacity(0);
        this.blessing = blessing;
    }

    @Override
    public Blessing getAssociatedBlessing() {
        return blessing;
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
        return new AxisAlignedBB(0, 0, 0, 0, 0, 0);
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return null;
    }

    public boolean canPlace(IBlockAccess world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos.down());
        return state.isTopSolid() || state.getBlockFaceShape(world, pos.down(), EnumFacing.UP) == BlockFaceShape.SOLID;
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!canPlace(worldIn, pos)) {
            worldIn.setBlockToAir(pos);
        }
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
    }

    /**
     * Called when the block is right clicked by a player.
     */
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (state.getValue(PART) != EnumPartType.CENTER) {
            BlockPos centerPos = getCenterPos(worldIn, pos);
            if (centerPos != null) {
                state = worldIn.getBlockState(centerPos);
                if (state.getBlock() != this) {
                    return true;
                }
                int distanceX = pos.getX() - centerPos.getX();
                int distanceZ = pos.getZ() - centerPos.getZ();
                this.onBlockActivated(worldIn, centerPos, state, playerIn, hand, facing, hitX + distanceX, hitY, hitZ + distanceZ);
            }
        } else {
            TileEntityOctagram octagram = getTileEntity(worldIn, pos);
            int floorHitX = (int) Math.floor(hitX);
            int floorHitZ = (int) Math.floor(hitZ);
            if (!(floorHitX == 0 && floorHitZ == 0)) {
                if (!worldIn.isRemote) {
                    int slot = getSlot(floorHitX, floorHitZ);
                    if (octagram.inventory.getStackInSlot(slot).isEmpty()) {
                        InventoryUtil.insertCurrentItemStack(playerIn, octagram.inventory, slot);
                    } else if (playerIn.isSneaking()) {
                        ItemHandlerHelper.giveItemToPlayer(playerIn, octagram.inventory.extractItem(slot, 1, false));
                    }
                }
            } else {
                octagram.interactCenter(worldIn, playerIn);
            }
        }
        return true;
    }

    public static int getSlot(int floorHitX, int floorHitZ) {

        return PARTS[1 - floorHitX][1 - floorHitZ] - 1;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }


    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return AABB;
    }

    @SideOnly(Side.CLIENT)
    public boolean hasCustomBreakingProgress(IBlockState state) {
        return true;
    }

    public EnumPushReaction getMobilityFlag(IBlockState state) {
        return EnumPushReaction.DESTROY;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    /**
     * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
     * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
     */
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return ItemStack.EMPTY;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        if (worldIn.getTileEntity(pos) instanceof TileEntityOctagram) {
            InventoryUtil.dropAllItems(worldIn, getTileEntity(worldIn, pos).inventory, pos);
        }
        super.breakBlock(worldIn, pos, state);
        if (state.getValue(PART) != EnumPartType.CENTER) {
            pos = getCenterPos(worldIn, pos);
        }
        if (pos != null) {
            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    if (!(x == 0 && z == 0)) {
                        if (worldIn.getBlockState(pos.add(x, 0, z)).getBlock().equals(this)) {
                            worldIn.setBlockToAir(pos.add(x, 0, z));
                        }
                    }
                }
            }
            worldIn.setBlockToAir(pos);
        }
    }


    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing enumfacing = EnumFacing.getHorizontal(meta);
        return (meta & 8) > 0 ? this.getDefaultState().withProperty(PART, EnumPartType.CENTER).withProperty(FACING, enumfacing) : this.getDefaultState().withProperty(PART, EnumPartType.OUTER).withProperty(FACING, enumfacing);//this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta & 6)).withProperty(PART, EnumPartType.CENTER.fromIndex(meta & 15));//(meta & 8) > 0 ? this.getDefaultState().withProperty(PART, EnumPartType.CENTER).withProperty(FACING, enumfacing) : this.getDefaultState().withProperty(PART, EnumPartType.OUTER).withProperty(FACING, enumfacing);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i = i | state.getValue(FACING).getHorizontalIndex();

        if (state.getValue(PART) == EnumPartType.CENTER) {
            i |= 8;
        }
        return i;
    }

    /**
     * Get the geometry of the queried face at the given position and state. This is used to decide whether things like
     * buttons are allowed to be placed on the face, or how glass panes connect to the face, among other things.
     * <p>
     * Common values are {@code SOLID}, which is the default, and {@code UNDEFINED}, which represents something that
     * does not fit the other descriptions and will generally cause other things not to connect to the face.
     *
     * @return an approximation of the form of the given face
     */
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, PART);
    }

    public BlockPos getCenterPos(World world, BlockPos pos) {
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                if (world.getBlockState(pos.add(x, 0, z)).getBlock().equals(this) && world.getBlockState(pos.add(x, 0, z)).getValue(PART).equals(EnumPartType.CENTER)) {
                    return pos.add(x, 0, z);
                }
            }
        }
        world.setBlockState(pos, Blocks.AIR.getDefaultState());
        return null;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Items.AIR;
    }

    @Override
    public Class<TileEntityOctagram> getTileEntityClass() {
        return TileEntityOctagram.class;
    }

    @Override
    public TileEntityOctagram createTileEntity(World world, IBlockState state) {
        return new TileEntityOctagram();//state.getValue(PART) == EnumPartType.CENTER ? new TileEntityOctagram() : null;
    }

    @Override
    public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
        return side == EnumFacing.UP && super.canPlaceBlockOnSide(worldIn, pos, side);
    }

    public enum EnumPartType implements IStringSerializable {
        CENTER("center", 0),
        OUTER("outer", 1);

        private final String name;
        private final int index;
        private final EnumPartType[] VALUES = new EnumPartType[9];

        EnumPartType(String name, int index) {
            this.name = name;
            this.index = index;
            VALUES[index] = this;
        }

        public String toString() {
            return this.name;
        }

        public String getName() {
            return this.name;
        }

        public int getIndex() {
            return index;
        }

        public EnumPartType fromIndex(int index) {
            return VALUES[index];
        }
    }
}