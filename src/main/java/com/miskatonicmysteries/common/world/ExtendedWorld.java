package com.miskatonicmysteries.common.world;

import com.miskatonicmysteries.MiskatonicMysteries;
import net.minecraft.init.Biomes;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.util.Constants;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings({"ConstantConditions", "SameReturnValue", "NullableProblems", "WeakerAccess"})
public class ExtendedWorld extends WorldSavedData {
	private static final String TAG = MiskatonicMysteries.MODID + ".world_data";

	public final Map<BlockPos, Biome> overridenBiomes = new ConcurrentHashMap<>();
	
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

	public static void addBiomeToOverrides(World world, BlockPos pos, Biome biome){
		ExtendedWorld extendedWorld = get(world);
		extendedWorld.overridenBiomes.putIfAbsent(pos, biome);
		extendedWorld.markDirty();
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		for (NBTBase tag : nbt.getTagList("biomeOverrides", Constants.NBT.TAG_COMPOUND)){
			overridenBiomes.put(BlockPos.fromLong(((NBTTagCompound) tag).getLong("pos")), Biome.getBiome(((NBTTagCompound) tag).getInteger("biome"), Biomes.PLAINS));
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		NBTTagList biomeOverrides = new NBTTagList();
		overridenBiomes.forEach((pos, biome) -> {
			NBTTagCompound data = new NBTTagCompound();
			data.setLong("pos", pos.toLong());
			data.setInteger("biome", Biome.getIdForBiome(biome));
			biomeOverrides.appendTag(data);
		});
		nbt.setTag("biomeOverrides", biomeOverrides);
		return nbt;
	}
}