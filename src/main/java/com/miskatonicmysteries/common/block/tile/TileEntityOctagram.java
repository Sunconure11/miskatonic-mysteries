package com.miskatonicmysteries.common.block.tile;

import com.miskatonicmysteries.common.block.BlockCandles;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.misc.rites.OctagramRite;
import com.miskatonicmysteries.common.misc.rites.focus.RiteFocus;
import com.miskatonicmysteries.common.network.PacketHandler;
import com.miskatonicmysteries.registry.ModRites;
import javafx.util.Pair;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import scala.collection.mutable.MultiMap;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TileEntityOctagram extends TileEntityMod implements ITickable {

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
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        tickCount = compound.getInteger("tickCount");
        primed = compound.getBoolean("primed");
        instability = compound.getFloat("instability");
        focusPower = compound.getInteger("focusPower");
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

    public void interactCenter(World world, EntityPlayer player){
        System.out.println(ModRites.getRite(this));
    }

    @Override
    public void update() {
        updateRiteStats();

        //System.out.println(getFocusPower(true));
        if (!altarUsable()){
            findNearestAltar();
        }else{
            if (getAltar().bookOpen && tickCount > 0){
                OctagramRite rite = getCurrentRite(); //store that in NBT, namely for primed rites
                if (rite != null){ //also check if it's primed; once it *is* primed and the ticks are matching, the check primed method (which is as of now not existent) will be called
                    EntityPlayer caster = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 16, false);
                    if (rite.test(this)){
                        tickCount++;
                        rite.doRitual(this, caster);
                    }
                    if (tickCount >= rite.ticksNeeded){
                        rite.effect(this, caster);
                    }
                }
                //stuff
            }
        }
        //flipAltarPages();

        //stuff
    }

    //method to get focus power, method to calculate instability, method to check when it takes effect
    //a rite will also have a method for determining the effects of instability or when the rite is canceled(see doc)



    public void updateRiteStats(){
        focusPower = 0;
        getAllFoci(true);
        HELD_FOCI.forEach(focus -> focusPower += calculatePowerWithModificator(FOCI, focus, pos));
        PLACED_FOCI.forEach(pair -> focusPower += calculatePowerWithModificator(FOCI, pair.getValue(), pair.getKey()));

        System.out.println(focusPower);
    }

    public List<Pair<BlockPos, RiteFocus>> getPlacedFoci(boolean refresh){
        if (refresh){
            PLACED_FOCI.clear();
            BlockPos.getAllInBox(pos.add(-10, -4, -10), pos.add(9, 5, 9)).forEach(blockPos ->
                RiteFocus.getFociFor(world.getBlockState(blockPos).getBlock(), RiteFocus.EnumType.PLACED).forEach(focus ->
                        PLACED_FOCI.add(new Pair<>(blockPos, focus))));
        }
        return PLACED_FOCI;
    }

    public List<RiteFocus> getAllFoci(boolean refresh){
        if (refresh) {
            FOCI.clear();
            FOCI.addAll(getHeldFoci(refresh));
            for (Pair<BlockPos, RiteFocus> focus : getPlacedFoci(refresh)) {
                FOCI.add(focus.getValue());
            }
        }
        return FOCI;
    }

    public List<RiteFocus> getHeldFoci(boolean refresh){
        if (refresh) {
            HELD_FOCI.clear();
            world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(pos.add(-8, -4, -8), pos.add(7, 4, 7))).forEach(l -> l.getHeldEquipment().forEach(s -> {
                List<RiteFocus> possibleFoci = RiteFocus.getFociFor(l.getHeldItemMainhand(), RiteFocus.EnumType.HELD);
                if (!possibleFoci.isEmpty()) {
                    HELD_FOCI.addAll(possibleFoci);
                }
            }));
        }
        return HELD_FOCI;
    }

    private float calculatePowerWithModificator(List<RiteFocus> focusList, RiteFocus focus, BlockPos checkPos){
        int frequency = Collections.frequency(focusList, focus);
        int overhang = Math.max(frequency- focus.getMaxSameType(world, checkPos), 0);
        //Math.pow()
      //  float frequencyModifier = Math(float) focus.getMaxSameType(world, pos) / Math.max(frequency - focus.getMaxSameType(world, pos), 0);
      //  Math.sqrt() so it exponentially increases the decrease
     //   System.out.println((float) focus.getConduitAmount(world, pos) * frequencyModifier);
        System.out.println((float) Math.pow(focus.getConduitAmount(world, checkPos), -overhang));
        return (float) Math.pow(focus.getConduitAmount(world, checkPos), -overhang);// * 1; //replace that with a good modifier
    }



    public OctagramRite getCurrentRite(){
        return ModRites.getRite(this);
    }

    public boolean start(){
        if (tickCount > 0) return false;
        tickCount = 1;
        getAltar().bookOpen = true;
        return true;
    }

    public void flipAltarPages(){
        if (altarUsable()){
            getAltar().flipSpeed += 0.1;
            // getAltar().flipSpeed = Math.max(getAltar().flipSpeed + 0.1F, 2);
        }
    }

    public void findNearestAltar(){
        Iterator<BlockPos.MutableBlockPos> checkPoses = BlockPos.getAllInBoxMutable(pos.add(-4, -2, -4), pos.add(4, 2, 4)).iterator();
        while (checkPoses.hasNext()){
            BlockPos checkPos = checkPoses.next();
            if (world.getTileEntity(checkPos) instanceof TileEntityAltar){
                closestAltarPos = checkPos;
                return;
            }
        }
    }

    public boolean altarUsable(){
        return closestAltarPos != null && world.getTileEntity(closestAltarPos) instanceof TileEntityAltar;
    }

    public TileEntityAltar getAltar(){
        if (altarUsable()) {
            return (TileEntityAltar) world.getTileEntity(closestAltarPos);
        }
        return null;
    }

    public Blessing getAltarBlessing(){
        if (closestAltarPos != null && world.getTileEntity(closestAltarPos) instanceof TileEntityAltar){
            return ((TileEntityAltar) world.getTileEntity(closestAltarPos)).getAssociatedBlessing();
        }
        return Blessing.NONE; //or null if it's relevant that a book is present?
    }
}
