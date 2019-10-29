package com.miskatonicmysteries.common.entity.cultist;

import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.registry.ModObjects;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class EntityHasturCultist extends AbstractCultist {
    public EntityHasturCultist(World worldIn) {
        super(worldIn);
    }


    @Nullable
    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        EntityHasturCultist cultist = new EntityHasturCultist(this.world);
        cultist.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(cultist)), null);
        return cultist;
    }


    @Override
    public Blessing getAssociatedBlessing() {
        return Blessing.HASTUR;
    }


    @Override
    public List<ItemStack> getAvailableWeapons() {
        List<ItemStack> weapons = new ArrayList<>();
        weapons.add(new ItemStack(ModObjects.yellow_kings_dagger));
        weapons.add(new ItemStack(ModObjects.yellow_kings_dagger));
        weapons.add(new ItemStack(Items.IRON_SWORD));
        return weapons;
    }

    @Override
    public List<EntityVillager.ITradeList> getEquipmentTradeList() {
        List<EntityVillager.ITradeList> trades = new ArrayList<>();
        trades.add((merchant, recipeList, random) -> recipeList.add(new MerchantRecipe(new ItemStack(ModObjects.gold_oceanic, 1 + random.nextInt(3)), new ItemStack(ModObjects.blotter, 1 + random.nextInt(3)))));
        trades.add((merchant, recipeList, random) -> recipeList.add(new MerchantRecipe(new ItemStack(ModObjects.gold_oceanic, 3 + random.nextInt(3)), new ItemStack(ModObjects.tranquilizer, 1 + random.nextInt(2)))));
        trades.add((merchant, recipeList, random) -> recipeList.add(new MerchantRecipe(new ItemStack(ModObjects.gold_oceanic, 4 + random.nextInt(3)), new ItemStack(ModObjects.yellow_kings_dagger, 1))));
        trades.add((merchant, recipeList, random) -> recipeList.add(new MerchantRecipe(new ItemStack(ModObjects.gold_oceanic, 4 + random.nextInt(3)), new ItemStack(ModObjects.hastur_cultist_hood, 1))));
        trades.add((merchant, recipeList, random) -> recipeList.add(new MerchantRecipe(new ItemStack(ModObjects.gold_oceanic, 4 + random.nextInt(3)), new ItemStack(ModObjects.hastur_cultist_robes, 1))));
        trades.add((merchant, recipeList, random) -> recipeList.add(new MerchantRecipe(new ItemStack(ModObjects.gold_oceanic, 4 + random.nextInt(3)), new ItemStack(ModObjects.hastur_cultist_pants, 1))));
        return trades;
    }

    @Override
    public List<EntityVillager.ITradeList> getMiscTradeList() {
        List<EntityVillager.ITradeList> trades = new ArrayList<>();
        trades.add((merchant, recipeList, random) -> recipeList.add(new MerchantRecipe(new ItemStack(ModObjects.gold_oceanic, 5 + random.nextInt(4)), new ItemStack(ModObjects.yellow_sign, 1))));
        trades.add((merchant, recipeList, random) -> recipeList.add(new MerchantRecipe(new ItemStack(ModObjects.gold_oceanic, 8 + random.nextInt(5)), new ItemStack(ModObjects.necronomicon, 1))));
        //trades.add((merchant, recipeList, random) -> recipeList.add(new MerchantRecipe(new ItemStack(ModObjects.gold_oceanic, 8 + random.nextInt(5)), new ItemStack(ModObjects.tranquilizer, 1)))); todo add yellow king idol
        return trades;
    }
}
