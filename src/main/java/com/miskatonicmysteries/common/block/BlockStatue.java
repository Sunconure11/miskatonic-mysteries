package com.miskatonicmysteries.common.block;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.block.tile.BlockTileEntity;
import com.miskatonicmysteries.common.block.tile.TileEntityStatue;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.misc.IHasAssociatedBlessing;
import com.miskatonicmysteries.util.Util;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class BlockStatue extends BlockTileEntity<TileEntityStatue> implements IHasAssociatedBlessing{
    public static Map<ResourceLocation, Statue> statues = new HashMap<>();

    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public Blessing associatedGOO;
    public Statue statue;
    public BlockStatue(String name, Statue statue, Blessing goo) {
        super(Material.ROCK);
        this.associatedGOO = goo;
        this.statue = statue;
        Util.create(this, SoundType.STONE, 1.6F, 0.4F, "pickaxe", 1, name);
        setLightOpacity(0);
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
        if (world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof TileEntityStatue) {
            TileEntityStatue te = (TileEntityStatue) world.getTileEntity(pos);
            te.setBlessing(associatedGOO);
            te.statue = this.statue.name;
        }
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
        return new AxisAlignedBB(0, 0, 0, 0, 0, 0);
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return true;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
        return false;
    }

    @Override
    public Class<TileEntityStatue> getTileEntityClass() {
        return TileEntityStatue.class;
    }

    @Override
    public TileEntityStatue createTileEntity(World world, IBlockState state) {
        TileEntityStatue statue = new TileEntityStatue();
        statue.setBlessing(associatedGOO);
        statue.statue = this.statue.name;
        return statue;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED ;
    }

    @Override
    public Blessing getAssociatedBlessing() {
        return associatedGOO;
    }

    public static class Statue {
        private final String name;
        private final ResourceLocation loc;
        private final ModelBase model;
        private final boolean isSpecial;

        public Statue(String name, ResourceLocation loc, ModelBase model, boolean isSpecial) {
            this.name = name;
            this.loc = loc;
            this.model = model;
            this.isSpecial = isSpecial;
            statues.put(new ResourceLocation(MiskatonicMysteries.MODID, name), this);
        }

        public Statue(String name, ResourceLocation loc, ModelBase model) {
            this.name = name;
            this.loc = loc;
            this.model = model;
            this.isSpecial = false;
            statues.put(new ResourceLocation(MiskatonicMysteries.MODID, name), this);
        }

        public boolean isSpecial() {
            return isSpecial;
        }

        public String getName() {
            return name;
        }

        public ResourceLocation getLoc() {
            return loc;
        }

        public ModelBase getModel() {
            return model;
        }

    }
}