package com.miskatonicmysteries.common.block;

import com.miskatonicmysteries.MiskatonicMysteries;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Random;

public class BlockFluidMMWater extends BlockFluidFinite {
    public BlockFluidMMWater() {
        super(FluidRegistry.WATER, Material.WATER);
        setRegistryName(MiskatonicMysteries.MODID, "water");
        setUnlocalizedName(getRegistryName().toString());
        setCreativeTab(MiskatonicMysteries.tab);
        setTickRandomly(true);
    }

    @SideOnly(Side.CLIENT)
    public void addStateMapper() {
        ModelLoader.setCustomStateMapper(this, new StateMap.Builder().ignore(LEVEL).build());
    }

    @Override
    public Vec3d getFogColor(World world, BlockPos pos, IBlockState state, Entity entity, Vec3d originalColor, float partialTicks) {
        return new Vec3d(0, 0, 1);
    }

    @Override
    public void updateTick(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull Random rand) {
        super.updateTick(world, pos, state, rand);
        if (state.getValue(LEVEL) <= 0) {
            world.setBlockToAir(pos);
        } else {
            world.setBlockState(pos, state.withProperty(LEVEL, state.getValue(LEVEL) - 1));
        }
    }
}
