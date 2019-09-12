package com.miskatonicmysteries.common.item.tool;

import com.google.common.collect.ImmutableList;
import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.ModConfig;
import com.miskatonicmysteries.common.world.gen.ModWorldGen;
import com.miskatonicmysteries.common.world.gen.processor.ShubStructureProcessor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemTerraform extends Item {
    private static final List<String> validBlocks = ImmutableList.of(
            "stone",
            "dirt",
            "grass",
            "sand",
            "gravel",
            "hardenedClay",
            "snowLayer",
            "mycelium",
            "podzol",
            "sandstone",
            "blockDiorite",
            "stoneDiorite",
            "blockGranite",
            "stoneGranite",
            "blockAndesite",
            "stoneAndesite",

            // Mod support
            "marble",
            "blockMarble",
            "limestone",
            "blockLimestone"
    );

    @Nonnull
    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        return EnumAction.BOW;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 72000;
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityLivingBase living, int count) {
        if (count != getMaxItemUseDuration(stack) && count % 10 == 0 && living instanceof EntityPlayer)
            terraform(stack, living.world, (EntityPlayer) living);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
        player.setActiveHand(hand);
        return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    private void terraform(ItemStack par1ItemStack, World world, EntityPlayer player) {
        if (!world.isRemote)
            ModWorldGen.growVines(world, BlockPos.getAllInBoxMutable(player.getPosition().add(- 10, -1, -10), player.getPosition().add(10, 5, 10)));
        /*int range = 16;//IManaProficiencyArmor.Helper.hasProficiency(player, par1ItemStack) ? 22 : 16;

        BlockPos startCenter = new BlockPos(player).down();

        if(startCenter.getY() < world.getSeaLevel()) // Not below sea level
            return;

        List<ModWorldGen.CoordsWithBlock> blocks = new ArrayList<>();

        for(BlockPos pos : BlockPos.getAllInBoxMutable(startCenter.add(range, -range, range), startCenter.add(2, range, 2))) {
            IBlockState state = world.getBlockState(pos);
            if(state.getBlock() == Blocks.AIR)
                continue;
            else if(Item.getItemFromBlock(state.getBlock()) == Items.AIR)
                continue;
            int[] ids = OreDictionary.getOreIDs(new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state)));
            for(int id : ids)
                if(validBlocks.contains(OreDictionary.getOreName(id))) {
                    List<BlockPos> airBlocks = new ArrayList<>();

                    for(EnumFacing dir : EnumFacing.HORIZONTALS) {
                        BlockPos pos_ = pos.offset(dir);
                        Block block_ = world.getBlockState(pos_).getBlock();
                        if(block_.isAir(world.getBlockState(pos_), world, pos_) || block_.getMaterial(world.getBlockState(pos_)).isLiquid() ||block_.isReplaceable(world, pos_) || block_ instanceof BlockFlower || block_ == Blocks.DOUBLE_PLANT) {
                            airBlocks.add(pos_);
                        }
                    }

                    if(!airBlocks.isEmpty()) {
                        if(pos.getY() > startCenter.getY())
                            blocks.add(new ModWorldGen.CoordsWithBlock(pos, Blocks.AIR));
                        else for(BlockPos coords : airBlocks) {
                            if(world.getBlockState(coords.down()).getBlock() != Blocks.AIR) {
                                if (world.getBlockState(coords.up()).getBlock() == Blocks.AIR) {
                                    blocks.add(new ModWorldGen.CoordsWithBlock(coords,  world.getBiome(coords).topBlock.getBlock()));
                                }else{
                                    blocks.add(new ModWorldGen.CoordsWithBlock(coords, world.getBiome(coords).fillerBlock.getBlock()));
                                }
                            }
                        }
                    }
                    break;
                }
        }

        //if(world.isRemote || ManaItemHandler.requestManaExactForTool(par1ItemStack, player, cost, true)) {
            if(!world.isRemote)
                for(ModWorldGen.CoordsWithBlock block : blocks)
                    world.setBlockState(block, block.block.getDefaultState());

            /*if(!blocks.isEmpty()) {
                for(int i = 0; i < 10; i++)
                    world.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_SAND_STEP, SoundCategory.BLOCKS, 1F, 0.4F);
                for(int i = 0; i < 120; i++)
                    Botania.proxy.sparkleFX(startCenter.getX() - range + range * 2 * Math.random(), startCenter.getY() + 2 + (Math.random() - 0.5) * 2, startCenter.getZ() - range + range * 2 * Math.random(), 0.35F, 0.2F, 0.05F, 2F, 5);
            }*/
        //}
        /*
        if (player.isSneaking()) {
            ModWorldGen.flatten(world, player.getPosition(), null);
        }else{
            ModWorldGen.notch(world, player.getPosition(), null);
        }
        /*BlockPos posIn = player.getPosition();
        int rangeX = 10;//template.getSize().getX() + 2;
        int rangeZ = 10;//template.getSize().getZ() + 2;
        int rangeY = 3;//template.getSize().getY() + 2;

        int dirXMultiplier = 1;//rotation == Rotation.CLOCKWISE_180 || rotation == Rotation.COUNTERCLOCKWISE_90 ? -1 : 0;//player.getAdjustedHorizontalFacing().equals(EnumFacing.EAST) ? 1: -1;//player.getAdjustedHorizontalFacing().equals(EnumFacing.WEST) || player.getAdjustedHorizontalFacing().equals(EnumFacing.NORTH)  ? 1 : -1;
        int dirZMultiplier = 1;//rotation == Rotation.CLOCKWISE_180 || rotation == Rotation.CLOCKWISE_90? -1 : 0;//player.getAdjustedHorizontalFacing().equals(EnumFacing.SOUTH) ? 1 : -1;//player.getAdjustedHorizontalFacing().equals(EnumFacing.EAST) || player.getAdjustedHorizontalFacing().equals(EnumFacing.SOUTH)  ? 1 : -1;
        List<ModWorldGen.CoordsWithBlock> blocks = new ArrayList<>();
        for (BlockPos pos : BlockPos.getAllInBoxMutable(posIn.add(rangeX * dirXMultiplier, rangeY, rangeZ * dirZMultiplier), posIn)) {
            IBlockState state = world.getBlockState(pos);
            if (state.getBlock() != Blocks.AIR)
                continue;
            if (state.getMaterial().equals(Material.AIR)){// || state.getMaterial().equals(Material.GROUND) || state.getMaterial().equals(Material.GRASS)) {
                List<BlockPos> airBlocks = new ArrayList<>();

                for (EnumFacing dir : EnumFacing.HORIZONTALS) {//moves the blocks
                    BlockPos pos_ = pos.offset(dir);
                    Block block_ = world.getBlockState(pos_).getBlock();
                    if (!block_.isAir(world.getBlockState(pos_), world, pos_)){// || !block_.isReplaceable(world, pos_)){// || !(block_ instanceof BlockFlower) || block_ != Blocks.DOUBLE_PLANT) {
                        airBlocks.add(pos_);
                    }
                }

                if (!airBlocks.isEmpty()) {
                    if (pos.getY() > posIn.getY())
                        blocks.add(new ModWorldGen.CoordsWithBlock(pos, Blocks.AIR));
                    else for (BlockPos coords : airBlocks) {
                        if (world.getBlockState(coords.up()).getBlock() == Blocks.AIR)
                            blocks.add(new ModWorldGen.CoordsWithBlock(coords, Blocks.AIR));//world.getBiome(pos).fillerBlock.getBlock()));
                    }
                }
            }
        }
        for (ModWorldGen.CoordsWithBlock block : blocks)
            world.setBlockState(block, block.block.getDefaultState());
        /*BlockPos posIn = player.getPosition();
        int rangeX = 10;
        int rangeZ = 8;
        int rangeY = 5;
        int dirXMultiplier = player.getAdjustedHorizontalFacing().equals(EnumFacing.EAST) ? 1: -1;//player.getAdjustedHorizontalFacing().equals(EnumFacing.WEST) || player.getAdjustedHorizontalFacing().equals(EnumFacing.NORTH)  ? 1 : -1;
        int dirZMultiplier = player.getAdjustedHorizontalFacing().equals(EnumFacing.SOUTH) ? 1 : -1;//player.getAdjustedHorizontalFacing().equals(EnumFacing.EAST) || player.getAdjustedHorizontalFacing().equals(EnumFacing.SOUTH)  ? 1 : -1;
        List<ModWorldGen.CoordsWithBlock> blocks = new ArrayList<>();

        for (BlockPos pos : BlockPos.getAllInBoxMutable(posIn.add(rangeX * dirXMultiplier, -rangeY, rangeZ * dirZMultiplier), posIn)) {
            IBlockState state = world.getBlockState(pos);
            if (state.getBlock() == Blocks.AIR)
                continue;
            else if (Item.getItemFromBlock(state.getBlock()) == Items.AIR)
                continue;
            //int[] ids = OreDictionary.getOreIDs(new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state)));
            // for (int id : ids) {
            if (state.getMaterial().equals(Material.ROCK) || state.getMaterial().equals(Material.GROUND) || state.getMaterial().equals(Material.GRASS)) {
                List<BlockPos> airBlocks = new ArrayList<>();

                for (EnumFacing dir : EnumFacing.HORIZONTALS) {//moves the blocks
                    BlockPos pos_ = pos.offset(dir);
                    Block block_ = world.getBlockState(pos_).getBlock();
                    if (block_.isAir(world.getBlockState(pos_), world, pos_) || block_.isReplaceable(world, pos_) || block_ instanceof BlockFlower || block_ == Blocks.DOUBLE_PLANT) {
                        airBlocks.add(pos_);
                    }
                }

                if (!airBlocks.isEmpty()) {
                    if (pos.getY() > pos.getY())
                        blocks.add(new ModWorldGen.CoordsWithBlock(pos, Blocks.AIR));
                    else for (BlockPos coords : airBlocks) {
                        if (world.getBlockState(coords.down()).getBlock() != Blocks.AIR)
                            blocks.add(new ModWorldGen.CoordsWithBlock(coords, Blocks.OBSIDIAN));//world.getBiome(pos).fillerBlock.getBlock()));
                    }
                }
                //break;
            }
            //}
        }
        for (ModWorldGen.CoordsWithBlock block : blocks)
            world.setBlockState(block, block.block.getDefaultState());

        // if (!world.isRemote) {
            /*Random rand = new Random();
            WorldServer worldServer = (WorldServer) world;
            MinecraftServer minecraftServer = world.getMinecraftServer();
            TemplateManager templateManager = worldServer.getStructureTemplateManager();
            int type = 1 + rand.nextInt(4);
            Template template = templateManager.getTemplate(minecraftServer, new ResourceLocation(MiskatonicMysteries.MODID, "shrines/shub/shrine_shubniggurath_forest_" + type));//(1 + rand.nextInt(4))));

            BlockPos pos = player.getPosition().down();

            IBlockState iBlockState = world.getBlockState(pos);
            world.notifyBlockUpdate(pos, iBlockState, iBlockState, 3);
            Rotation rotat = player.isSneaking() ? Rotation.NONE : Rotation.CLOCKWISE_180;//rand.nextBoolean() ? Rotation.NONE : rand.nextBoolean() ? Rotation.CLOCKWISE_90 : rand.nextBoolean() ? Rotation.CLOCKWISE_180 : Rotation.COUNTERCLOCKWISE_90;
            //Mirror mirror = rand.nextBoolean() ? Mirror.NONE : Mirror.LEFT_RIGHT;
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(rotat).setIgnoreEntities(false).setIgnoreStructureBlock(false);
            for (int i = 0; i < ModConfig.worldGen.chanceShubShrines; i++)
                ModWorldGen.flatten(world, pos, template, rotat, null);
            template.addBlocksToWorld(world, pos.add(0, 1, 0), new ShubStructureProcessor(pos.getY() + 1, true), placementsettings, 2);
        */}/*
            WorldServer worldServer = (WorldServer) world;
            MinecraftServer minecraftServer = world.getMinecraftServer();
            TemplateManager templateManager = worldServer.getStructureTemplateManager();
            Template template = templateManager.getTemplate(minecraftServer, new ResourceLocation(MiskatonicMysteries.MODID, "shrines/shub/shrine_shubniggurath_forest_" + 1));//(1 + rand.nextInt(4))));

            BlockPos posFrom = player.getPosition();//.add(template.getSize().getX() / 2, 0, template.getSize().getZ() / 2);
            int rangeX = template.getSize().getX();//16;//IManaProficiencyArmor.Helper.hasProficiency(player, par1ItemStack) ? 22 : 16;
            int rangeY = template.getSize().getY();//16;
            int rangeZ = template.getSize().getZ();//16;
            BlockPos startCenter = posFrom;//new BlockPos(player).down();

            if (startCenter.getY() < world.getSeaLevel()) // Not below sea level
                return;

            List<CoordsWithBlock> blocks = new ArrayList<>();

            for (BlockPos pos : BlockPos.getAllInBoxMutable(startCenter.add(-rangeX, -rangeY, -rangeZ), startCenter.add(rangeX, rangeY, rangeZ))) {
                IBlockState state = world.getBlockState(pos);
                if (state.getBlock() == Blocks.AIR)
                    continue;
                else if (Item.getItemFromBlock(state.getBlock()) == Items.AIR)
                    continue;
                //int[] ids = OreDictionary.getOreIDs(new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state)));
                //for (int id : ids)
                   // if (validBlocks.contains(OreDictionary.getOreName(id))) {
                        List<BlockPos> airBlocks = new ArrayList<>();

                        for (EnumFacing dir : EnumFacing.HORIZONTALS) {
                            BlockPos pos_ = pos.offset(dir);
                            Block block_ = world.getBlockState(pos_).getBlock();
                            if (block_.isAir(world.getBlockState(pos_), world, pos_) || block_.isReplaceable(world, pos_) || block_ instanceof BlockFlower || block_ == Blocks.DOUBLE_PLANT) {
                                airBlocks.add(pos_);
                            }
                        }

                        if (!airBlocks.isEmpty()) {
                            if (pos.getY() > startCenter.getY())
                                blocks.add(new CoordsWithBlock(pos, Blocks.AIR));
                            else for (BlockPos coords : airBlocks) {
                                if (world.getBlockState(coords.down()).getBlock() != Blocks.AIR)
                                    blocks.add(new CoordsWithBlock(coords, Blocks.DIRT));
                            }
                        }
                     //   break;
                 //   }
            }

            //int cost = COST_PER * blocks.size();

            //if(world.isRemote || ManaItemHandler.requestManaExactForTool(par1ItemStack, player, cost, true)) {
                for (CoordsWithBlock block : blocks)
                    world.setBlockState(block, block.block.getDefaultState());

            /*if(!blocks.isEmpty()) {
                for(int i = 0; i < 10; i++)
                    world.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_SAND_STEP, SoundCategory.BLOCKS, 1F, 0.4F);
                for(int i = 0; i < 120; i++)
                    Botania.proxy.sparkleFX(startCenter.getX() - rangeX + rangeX * 2 * Math.random(), startCenter.getY() + 2 + (Math.random() - 0.5) * 2, startCenter.getZ() - rangeZ + rangeZ * 2 * Math.random(), 0.35F, 0.2F, 0.05F, 2F, 5);
            */
  //  }

   /* private static class CoordsWithBlock extends BlockPos {

        private final Block block;

        private CoordsWithBlock(BlockPos pos, Block block) {
            super(pos.getX(), pos.getY(), pos.getZ());
            this.block = block;
        }

    }*/
}
