package com.miskatonicmysteries.common.handler;

import com.google.common.collect.ImmutableList;
import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.registry.ModObjects;
import net.minecraft.block.BlockCrops;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityElderGuardian;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;
import java.util.function.Predicate;

public class LootHandler {
    private static final List<String> TABLES = ImmutableList.of(
            "abandoned_mineshaft",
            "simple_dungeon",
            "village_blacksmith",
            "igloo_chest",
            "stronghold_crossing"
    );

    public LootHandler() {
        LootTableList.register(new ResourceLocation(MiskatonicMysteries.MODID, "oceanic_gold"));
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void replaceDrops(LivingDropsEvent event) {
        addDrop(event, l -> l instanceof EntityGuardian, new ItemStack(ModObjects.gold_oceanic, 0), 25, 2);
        addDrop(event, l -> l instanceof EntityElderGuardian, new ItemStack(ModObjects.gold_oceanic, 1), 25, 3);
    }

    @SubscribeEvent
    public void replaceLoot(LootTableLoadEvent event) {
        String prefix = "minecraft:chests/";
        String name = event.getName().toString();

        if (name.startsWith(prefix)) {
            String file = name.substring(name.indexOf(prefix) + prefix.length());
            if (TABLES.contains(file)) {
                event.getTable().addPool(new LootPool(new LootEntry[]{getLootTable("oceanic_gold")},
                        new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "oceanic_gold_inject"));
            }
        }
    }

    public static LootEntryTable getLootTable(String name) {
        return new LootEntryTable(new ResourceLocation(MiskatonicMysteries.MODID, name), 1, 0, new LootCondition[0], name + "_entry");
    }

    /**
     * @param event     The LivingDropsEvent
     * @param predicate The Condition
     * @param drop      The ItemStack dropped
     * @param perChance The chance (out of 100) for the item drop count to increase by one (for each)
     * @param maxDrops  the maximum amount of items that drop
     */
    public static void addDrop(LivingDropsEvent event, Predicate<EntityLivingBase> predicate, ItemStack drop, int perChance, int maxDrops) {
        if (predicate.test(event.getEntityLiving())) {
            int count = drop.getCount();
            if (count < maxDrops && event.getEntityLiving().world.rand.nextInt(100) <= perChance + event.getLootingLevel() * 10) {
                count++;
            }
            drop.setCount(count);
            event.getDrops().add(new EntityItem(event.getEntityLiving().world, event.getEntityLiving().posX, event.getEntityLiving().posY, event.getEntityLiving().posZ, drop));
        }
    }

    @SubscribeEvent
    public void dropInfestedWheat(BlockEvent.HarvestDropsEvent breakEvent) {
        if (breakEvent.getState() != null && breakEvent.getHarvester() != null) {
            if (breakEvent.getHarvester().world.rand.nextInt(40) == 0 && breakEvent.getState().getBlock() == Blocks.WHEAT && ((BlockCrops) Blocks.WHEAT).isMaxAge(breakEvent.getState())) {
                breakEvent.getDrops().clear();
                breakEvent.getDrops().add(new ItemStack(ModObjects.infested_wheat));
            }
        }
    }
}
