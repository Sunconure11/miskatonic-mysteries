package com.miskatonicmysteries.common.entity.cultist;

import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.registry.ModObjects;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class EntityHasturCultist extends AbstractCultist {
    public EntityHasturCultist(World worldIn) {
        super(worldIn);
    }

    @Override
    public Blessing getAssociatedBlessing() {
        return Blessing.HASTUR;
    }

    @Nullable
    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        EntityHasturCultist cultist = new EntityHasturCultist(this.world);
        cultist.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(cultist)), (IEntityLivingData)null);
        return cultist;
    }

    @Override
    public List<ItemStack> getAvailableWeapons() {
        List<ItemStack> weapons = new ArrayList<>();
        weapons.add(new ItemStack(ModObjects.yellow_kings_dagger));
        weapons.add(new ItemStack(ModObjects.yellow_kings_dagger));
        weapons.add(new ItemStack(Items.IRON_SWORD));
        weapons.add(new ItemStack(Items.AIR));
        return weapons;
    }
}
