package com.miskatonicmysteries.common.misc.rites;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.block.tile.TileEntityOctagram;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RiteManiacsMeeting extends OctagramRite {
    public RiteManiacsMeeting(Blessing associatedGOO, Ingredient... reagents) {
        super(new ResourceLocation(MiskatonicMysteries.MODID, "maniacs_meeting_" + associatedGOO.getName()), 250, 50, 200, EnumType.FOCUSED, associatedGOO, associatedGOO, reagents);
    }

    @Override
    public boolean test(TileEntityOctagram octagram) {
        return true;
    }

    @Override
    public void doRitual(TileEntityOctagram octagram, @Nullable EntityPlayer caster) {
        caster.setHealth(5);
    }

    @Override
    public void effect(TileEntityOctagram octagram, @Nullable EntityPlayer caster) {
        System.out.println("done, yayyay");
    }

    @Override
    public boolean checkShouldTrigger(TileEntityOctagram octagram, @Nullable EntityPlayer closest) {
        return true;
    }
}
