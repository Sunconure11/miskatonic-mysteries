package com.miskatonicmysteries.common.entity;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

import java.util.List;
import java.util.function.Predicate;

public interface IIgnoreMaterials {
    boolean checkIgnore(IBlockState state);
}
