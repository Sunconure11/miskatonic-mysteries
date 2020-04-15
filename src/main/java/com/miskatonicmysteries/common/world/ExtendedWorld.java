package com.miskatonicmysteries.common.world;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.entity.goo.AbstractOldOne;
import com.miskatonicmysteries.common.world.biome.GreatOldOneArea;
import com.miskatonicmysteries.common.world.gen.BiomeManipulator;
import com.miskatonicmysteries.common.world.gen.ModWorldGen;
import com.miskatonicmysteries.registry.ModBiomes;
import com.miskatonicmysteries.util.WorldGenUtil;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.util.Constants;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings({"ConstantConditions", "SameReturnValue", "NullableProblems", "WeakerAccess"})
public class ExtendedWorld extends WorldSavedData {
	private static final String TAG = MiskatonicMysteries.MODID + ".world_data";

	public final Map<BlockPos, Biome> STORED_OVERRIDE_BIOMES = new ConcurrentHashMap<>();
	
	public ExtendedWorld(String name) {
		super(name);
	}
	
	public static ExtendedWorld get(World world) {
		ExtendedWorld data = (ExtendedWorld) world.getMapStorage().getOrLoadData(ExtendedWorld.class, TAG);
		if (data == null) {
			data = new ExtendedWorld(TAG);
			world.getMapStorage().setData(TAG, data);
		}
		return data;
	}

	/**
	 * Adds a biome to the map of overridden biomes, so it may be restored, should it be replaced.
	 * @param world
	 * @param pos
	 */
	public static void addOverriddenBiome(World world, BlockPos pos){
		ExtendedWorld extendedWorld = get(world);
		extendedWorld.STORED_OVERRIDE_BIOMES.putIfAbsent(new BlockPos(pos.getX(), GreatOldOneArea.STANDARD_HEIGHT, pos.getZ()), world.getBiome(pos));
		extendedWorld.markDirty();
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		for (NBTBase tag : nbt.getTagList("gooAreas", Constants.NBT.TAG_COMPOUND)){
			STORED_OVERRIDE_BIOMES.put(BlockPos.fromLong(((NBTTagCompound) tag).getLong("pos")), Biome.getBiome(((NBTTagCompound) tag).getInteger("biomeId")));
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		NBTTagList biomeOverrides = new NBTTagList();
		STORED_OVERRIDE_BIOMES.forEach((pos, biome) -> {
			NBTTagCompound data = new NBTTagCompound();
			data.setLong("pos", pos.toLong());
			data.setInteger("biomeId", Biome.getIdForBiome(biome));
			biomeOverrides.appendTag(data);
		});
		nbt.setTag("gooAreas", biomeOverrides);
		return nbt;
	}
}