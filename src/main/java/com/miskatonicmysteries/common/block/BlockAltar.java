package com.miskatonicmysteries.common.block;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.client.particles.ParticleOccultFlame;
import com.miskatonicmysteries.common.block.tile.BlockTileEntity;
import com.miskatonicmysteries.common.block.tile.TileEntityAltar;
import com.miskatonicmysteries.common.network.PacketHandler;
import com.miskatonicmysteries.registry.ModObjects;
import com.miskatonicmysteries.util.InventoryUtil;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEndRod;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Random;

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
        if (worldIn.isRemote) return true;
        if (playerIn.isSneaking()){
           if (!altar.inventory.getStackInSlot(0).isEmpty()){
               ItemHandlerHelper.giveItemToPlayer(playerIn, altar.inventory.extractItem(0, 1, false));
           }
        }else{
            if (altar.inventory.getStackInSlot(0).isEmpty() && TileEntityAltar.BOOK_TEXTURES.containsKey(playerIn.inventory.getCurrentItem().getItem())){
                InventoryUtil.insertCurrentItemStack(playerIn, altar.inventory, 0);
            }else
            if (playerIn.getHeldItem(hand).isEmpty() && hand == EnumHand.MAIN_HAND) {
                altar.bookOpen = !altar.bookOpen;
                altar.markDirty();
                worldIn.updateComparatorOutputLevel(pos, worldIn.getBlockState(pos).getBlock());
                PacketHandler.updateTE(altar);
                return true;
            }
        }
        return false;
    }


    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (this != ModObjects.altar_prismarine) {
            float halfBlockBit = 1F / 16F;
            EnumFacing facing = stateIn.getValue(FACING);
            boolean reverse = facing == EnumFacing.WEST || facing == EnumFacing.EAST;
            int mult = facing == EnumFacing.SOUTH || facing == EnumFacing.WEST ? -1 : 1;
            int type = this == ModObjects.altar_purpur ? 1 : 0;
            generateParticle(type, worldIn, pos, reverse, mult, 15 * halfBlockBit, 5.5 * halfBlockBit, 15 * halfBlockBit, 0.2F, rand);
            generateParticle(type, worldIn, pos, reverse, mult, 12 * halfBlockBit, 5.5 * halfBlockBit, 15 * halfBlockBit, 0.2F, rand);
            generateParticle(type, worldIn, pos, reverse, mult, 13.5 * halfBlockBit, 6.5 * halfBlockBit, 13 * halfBlockBit, 0.2F, rand);

            generateParticle(type, worldIn, pos, reverse, mult, 1.5 * halfBlockBit, 7 * halfBlockBit, 14 * halfBlockBit, 0.3F, rand);

            generateParticle(type, worldIn, pos, reverse, mult, 2.5 * halfBlockBit, 1 + 6.5 * halfBlockBit, halfBlockBit, 0.2F, rand);
            generateParticle(type, worldIn, pos, reverse, mult, 2 * halfBlockBit, 1 + 6.5 * halfBlockBit, 3.5 * halfBlockBit, 0.2F, rand);

            generateParticle(type, worldIn, pos, reverse, mult, 14 * halfBlockBit, 1 + 6.5 * halfBlockBit, 2.5 * halfBlockBit, 0.2F, rand);

            generateParticle(type, worldIn, pos, reverse, mult, 12.5 * halfBlockBit, 1 + 12.5 * halfBlockBit, 10.5 * halfBlockBit, 0.2F, rand);
            generateParticle(type, worldIn, pos, reverse, mult, 8.5 * halfBlockBit, 1 + 12.5 * halfBlockBit, 11 * halfBlockBit, 0.2F, rand);
            generateParticle(type, worldIn, pos, reverse, mult, 10.5 * halfBlockBit, 1 + 13.5 * halfBlockBit, 12 * halfBlockBit, 0.3F, rand); //in case that gets weird, remove the 1 and add it to the poses 1

        }
        super.randomDisplayTick(stateIn, worldIn, pos, rand);
    }

    @SideOnly(Side.CLIENT)
    public void generateParticle(int type, World world, BlockPos pos, boolean reversed, int mult, double xCoord, double yCoord, double zCoord, float size, Random rand){
        double x = pos.getX() + 0.5;
        double y = pos.getY();
        double z = pos.getZ() + 0.5;
        if (reversed){
            double tempZ = zCoord;
            zCoord = xCoord;
            xCoord = tempZ;
        }
        if (type == 0 && rand.nextFloat() < 0.8)
            MiskatonicMysteries.proxy.generateParticle(new ParticleOccultFlame(world, x + (xCoord - 0.5) * (reversed ? -mult : mult), y + yCoord, z + (zCoord - 0.5) * mult, 0, 0, 0).multipleParticleScaleBy(size + (float) rand.nextGaussian() / 20F));
        else if (type == 1 && rand.nextFloat() < 0.2){
            Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleEndRod(world,  x + (xCoord - 0.5 + rand.nextGaussian() / 40) * (reversed ? -mult : mult), y + yCoord - rand.nextFloat() / 10, z + (zCoord - 0.5 + rand.nextGaussian() / 40) * mult, 0, 0, 0).multipleParticleScaleBy(size + (float) rand.nextGaussian() / 10F));
        }
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