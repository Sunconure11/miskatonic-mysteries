package com.miskatonicmysteries.common.world.biome;

import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.entity.goo.AbstractOldOne;
import com.miskatonicmysteries.common.world.ExtendedWorld;
import com.miskatonicmysteries.registry.ModBiomes;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.EntityViewRenderEvent;

import java.awt.*;

public abstract class GreatOldOneArea<T extends AbstractOldOne>{
    public static final int STANDARD_HEIGHT = 69; //nice
    protected Blessing greatOldOne;
    protected String name;
    public GreatOldOneArea(String name, Blessing greatOldOne) {
        this.greatOldOne = greatOldOne;
        this.name = name;
        ModBiomes.GOO_EFFECT_MAP.put(name, this);
    }

    public Blessing getGreatOldOne() {
        return greatOldOne;
    }

    public String getName(){
        return name;
    }

    public abstract void onAdded(T goo, World world, BlockPos pos);

    public abstract void update(T goo, World world, BlockPos pos);

    public abstract void onRemoved(World world, BlockPos pos);

    //public abstract void directEffect(World world, BlockPos pos, T master);

    public static boolean isInGreatOldArea(World world, BlockPos pos){
        return ExtendedWorld.get(world).GOO_AREAS.containsKey(new BlockPos(pos.getX(), STANDARD_HEIGHT, pos.getZ()));
    }

    public Color getFogColor(){
        return Color.WHITE;
    }

    public GlStateManager.FogMode getFogMode(){
        return GlStateManager.FogMode.LINEAR;
    }

    public abstract Class<T> getGOOClass();
}
