package com.miskatonicmysteries.common.block.tile;

import com.miskatonicmysteries.common.block.BlockOctagram;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.misc.IHasAssociatedBlessing;
import com.miskatonicmysteries.common.misc.rites.OctagramRite;
import com.miskatonicmysteries.common.misc.rites.effect.RiteEffect;
import com.miskatonicmysteries.common.misc.rites.focus.RiteFocus;
import com.miskatonicmysteries.common.network.PacketHandler;
import com.miskatonicmysteries.registry.ModRegistries;
import javafx.util.Pair;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.*;

public class TileEntityOctagram extends TileEntityMod implements ITickable, IHasAssociatedBlessing {

    public ItemStackHandler inventory = new ItemStackHandler(8) {
        @Override
        public int getSlotLimit(int slot) {
            return 1;
        }

        @Override
        protected void onContentsChanged(int slot) {
            PacketHandler.updateTE(world, pos);
            super.onContentsChanged(slot);
        }
    };

    //maybe store particle emitting block poses in a list; when the foci get updated, the list is cleared and updated

    public boolean primed = false;
    public int tickCount = 0;

    public BlockPos closestAltarPos = null;

    public float instability = 0;
    public int focusPower = 0;

    public String currentRite = "";

    public String lastPlayerUUID = "";

    public final List<Pair<BlockPos, RiteFocus>> PLACED_FOCI = new ArrayList<>();
    public final List<RiteFocus> HELD_FOCI = new ArrayList<>(); //possibly pass an entity id with that? (for particles, if there should be any)
    public final List<RiteFocus> FOCI = new ArrayList<>();

    //store the current rite in nbt

    //also store the last player to use this
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("inventory", inventory.serializeNBT());
        compound.setInteger("tickCount", tickCount);
        compound.setBoolean("primed", primed);
        compound.setFloat("instability", instability);
        compound.setInteger("focusPower", focusPower);
        compound.setString("currentRite", currentRite);
        compound.setString("lastPlayerUUID", lastPlayerUUID);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        tickCount = compound.getInteger("tickCount");
        primed = compound.getBoolean("primed");
        instability = compound.getFloat("instability");
        focusPower = compound.getInteger("focusPower");
        currentRite = compound.getString("currentRite");
        lastPlayerUUID = compound.getString("lastPlayerUUID");
        super.readFromNBT(compound);
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return super.getRenderBoundingBox().grow(2, 2, 2);
    }


    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T) inventory : super.getCapability(capability, facing);
    }

    public void interactCenter(World world, EntityPlayer player) {
        if (!world.isRemote) {
            if (isValid())
                start();
            else {
                world.playSound(player, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.PLAYERS, 0.8F, 0.5F);
            }
            PacketHandler.updateTE(this);
        }
    }

    public boolean start() {
        if (tickCount > 0) return false;
        tickCount = 1;
        getAltar().bookOpen = true;
        PacketHandler.updateTE(getAltar());
        return true;
    }

    @Override //todo particles n stuff
    public void update() {
        if (!world.isRemote) {
            updateRiteStats();
            if (!altarUsable()) {
                findNearestAltar();
            } else {
                if (getAltar().bookOpen && tickCount > 0 && isValid()) {
                    flipAltarPages();
                    OctagramRite rite = getCurrentRite();
                    System.out.println(tickCount);
                    EntityPlayer caster = getLastPlayer();
                    if (rite.test(this)) { //bind in instability later
                        tickCount++;
                        rite.doRitual(this, caster);
                        //also do instability check stuff
                    }else{
                        //reset? look at the doc
                    }
                    if (checkGOOAdressed() && checkFocalPower() && tickCount >= rite.ticksNeeded) {
                        rite.effect(this, caster);
                        finish();
                        int checks = 5; //let this depend on the instability, maybe like 10 * instability + 1 or so
                        RiteEffect effect = ModRegistries.Util.getRandomEffect(this, checks, RiteEffect.EnumTrigger.RITE_EXECUTED);
                        if (effect != null){
                            effect.execute(this, RiteEffect.EnumTrigger.RITE_EXECUTED);
                        }
                        //do instability stuff for the last time, maybe also regard it in the focal power part
                    }
                } else {
                    tickCount = 0;
                }
            }
            PacketHandler.updateTE(this);
        }
    }

    public boolean checkFocalPower() {
        int checks = 5; //let this depend on the instability, maybe like 10 * instability + 1 or so
        RiteEffect effect = ModRegistries.Util.getRandomEffect(this, checks, RiteEffect.EnumTrigger.POWER_CHECK);
        if (effect != null){
            effect.execute(this, RiteEffect.EnumTrigger.POWER_CHECK);
            return false;
        }
        return true;
    }

    public boolean checkGOOAdressed() {
        int checks = 5; //let this depend on the instability, maybe like 10 * instability + 1 or so
        RiteEffect effect = ModRegistries.Util.getRandomEffect(this, checks, RiteEffect.EnumTrigger.GOO_CHECK);
        if (effect != null){
            effect.execute(this, RiteEffect.EnumTrigger.GOO_CHECK);
            return false;
        }
        return true;
    }

    public void finish() {
        getAltar().bookOpen = false;
        getAltar().flipSpeed = 0;
        PacketHandler.updateTE(getAltar());
        tickCount = 0;
        currentRite = "";
        for (int i = 0; i < inventory.getSlots(); i++) {
            inventory.setStackInSlot(i, ItemStack.EMPTY);
        }
    }


    public boolean isValid() {
        if (getAltarBlessing() != null) {
            return getCurrentRite() != null && (getCurrentRite().unlockBook == getAltarBlessing() || getCurrentRite().unlockBook == Blessing.NONE);// && (getCurrentRite().octagram == getAssociatedBlessing() || getCurrentRite().octagram == Blessing.NONE); //and stuff
        }
        return false;
    }

    public EntityPlayer getLastPlayer(){
        EntityPlayer player= world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 16, false);
        if (player == null && !lastPlayerUUID.isEmpty()){
            player = world.getPlayerEntityByUUID(UUID.fromString(lastPlayerUUID));
        }
        return player;
    }

    public OctagramRite getCurrentRite() {
        OctagramRite rite = ModRegistries.Util.getRite(this);
        if (rite == null && primed && !currentRite.isEmpty()) {
            rite = ModRegistries.RITES.get(new ResourceLocation(currentRite));
        } else if (rite == null) {
            currentRite = "";
        }
        return rite;
    }

    public void flipAltarPages() {
        if (altarUsable()) {
            getAltar().flipSpeed = Math.max(getAltar().flipSpeed + 0.02F, 0.1F);
            PacketHandler.updateTE(getAltar());
        }
    }

    public void findNearestAltar() {
        Iterator<BlockPos.MutableBlockPos> checkPoses = BlockPos.getAllInBoxMutable(pos.add(-4, -2, -4), pos.add(4, 2, 4)).iterator();
        while (checkPoses.hasNext()) {
            BlockPos checkPos = checkPoses.next();
            if (world.getTileEntity(checkPos) instanceof TileEntityAltar) {
                closestAltarPos = checkPos;
                return;
            }
        }
    }

    public boolean altarUsable() {
        return closestAltarPos != null && world.getTileEntity(closestAltarPos) instanceof TileEntityAltar;
    }

    public TileEntityAltar getAltar() {
        if (altarUsable()) {
            return (TileEntityAltar) world.getTileEntity(closestAltarPos);
        }
        return null;
    }

    public Blessing getAltarBlessing() {
        if (closestAltarPos != null && world.getTileEntity(closestAltarPos) instanceof TileEntityAltar) {
            return ((TileEntityAltar) world.getTileEntity(closestAltarPos)).getAssociatedBlessing();
        }
        return null;
    }

    @Override
    public Blessing getAssociatedBlessing() {
        if (world.getBlockState(pos).getBlock() instanceof BlockOctagram) {
            return ((BlockOctagram) world.getBlockState(pos).getBlock()).getAssociatedBlessing();
        }
        return Blessing.NONE;
    }


    public void updateRiteStats() {
        getAllFoci(true);
        focusPower = 0;
        instability = 0;
        HELD_FOCI.forEach(focus -> {
            focusPower += focus.getConduitAmount(world, pos);
            instability += focus.getInstabilityRate(world, pos);
        });
        PLACED_FOCI.forEach(pair -> {
            focusPower += pair.getValue().getConduitAmount(world, pair.getKey());
            instability += pair.getValue().getInstabilityRate(world, pair.getKey());
        });
        float overhangFactor = calculateOverhangFactor();
        focusPower /= overhangFactor;
        instability *= Math.sqrt(overhangFactor);
        focusPower = Math.max(focusPower, 0);
        instability = Math.max(instability, 0);
    }

    public List<Pair<BlockPos, RiteFocus>> getPlacedFoci(boolean refresh) {
        if (refresh) {
            PLACED_FOCI.clear();
            BlockPos.getAllInBox(pos.add(-10, -4, -10), pos.add(9, 5, 9)).forEach(blockPos ->
                    RiteFocus.getFociFor(world.getBlockState(blockPos).getBlock(), RiteFocus.EnumType.PLACED).forEach(focus ->
                            PLACED_FOCI.add(new Pair<>(blockPos, focus))));
        }
        return PLACED_FOCI;
    }

    public List<RiteFocus> getAllFoci(boolean refresh) {
        if (refresh) {
            FOCI.clear();
            FOCI.addAll(getHeldFoci(refresh));
            for (Pair<BlockPos, RiteFocus> focus : getPlacedFoci(refresh)) {
                FOCI.add(focus.getValue());
            }
        }
        return FOCI;
    }

    public List<RiteFocus> getHeldFoci(boolean refresh) {
        if (refresh) {
            HELD_FOCI.clear();
            world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(pos.add(-8, -4, -8), pos.add(7, 4, 7))).forEach(l -> l.getHeldEquipment().forEach(s -> {
                HELD_FOCI.addAll(RiteFocus.getFociFor(s, RiteFocus.EnumType.HELD));
            }));
        }
        return HELD_FOCI;
    }

    private float calculateOverhangFactor() {
        Map<RiteFocus, Integer> overhang_each = new HashMap<>();
        float totalMax = 0;
        float totalOverhang = 0;
        for (RiteFocus focus : FOCI) {
            int frequency = Collections.frequency(FOCI, focus);
            overhang_each.put(focus, Math.max(frequency - focus.getMaxSameType(world, pos), 0));
            totalMax += focus.getMaxSameType(world, pos);
        }
        for (Integer i : overhang_each.values()) {
            totalOverhang += i;
        }
        return 1 + (totalOverhang / totalMax);
    }

}
