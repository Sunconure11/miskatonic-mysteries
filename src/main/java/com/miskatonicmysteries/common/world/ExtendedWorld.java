package com.miskatonicmysteries.common.world;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.entity.goo.AbstractOldOne;
import com.miskatonicmysteries.common.world.biome.GreatOldOneArea;
import com.miskatonicmysteries.registry.ModBiomes;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings({"ConstantConditions", "SameReturnValue", "NullableProblems", "WeakerAccess"})
public class ExtendedWorld extends WorldSavedData {
	private static final String TAG = MiskatonicMysteries.MODID + ".world_data";

	public final Map<BlockPos, GreatOldOneArea> GOO_AREAS = new ConcurrentHashMap<>();
	
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

	public static void addGreatOldOneArea(World world, AbstractOldOne goo, BlockPos pos, GreatOldOneArea area){
		if (area != null) {
			ExtendedWorld extendedWorld = get(world);
			extendedWorld.GOO_AREAS.putIfAbsent(new BlockPos(pos.getX(), GreatOldOneArea.STANDARD_HEIGHT, pos.getZ()), area);
			area.onAdded(goo, world, pos);
			extendedWorld.markDirty();
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		for (NBTBase tag : nbt.getTagList("gooAreas", Constants.NBT.TAG_COMPOUND)){
			GOO_AREAS.put(BlockPos.fromLong(((NBTTagCompound) tag).getLong("pos")), ModBiomes.GOO_EFFECT_MAP.get(((NBTTagCompound) tag).getString("area")));
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		NBTTagList biomeOverrides = new NBTTagList();
		GOO_AREAS.forEach((pos, area) -> {
			NBTTagCompound data = new NBTTagCompound();
			data.setLong("pos", pos.toLong());
			data.setString("area", area.getName());
			biomeOverrides.appendTag(data);
		});
		nbt.setTag("gooAreas", biomeOverrides);
		return nbt;
	}
}