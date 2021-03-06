package com.miskatonicmysteries.common.block.tile;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.client.particles.ParticleColoredSmoke;
import com.miskatonicmysteries.client.particles.ParticleOccultEnchant;
import com.miskatonicmysteries.client.particles.ParticleOccultFlame;
import com.miskatonicmysteries.common.block.BlockOctagram;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.capability.sanity.Sanity;
import com.miskatonicmysteries.common.misc.IHasAssociatedBlessing;
import com.miskatonicmysteries.common.misc.IHasOwner;
import com.miskatonicmysteries.common.misc.IInducesInsanity;
import com.miskatonicmysteries.common.misc.rites.OctagramRite;
import com.miskatonicmysteries.common.misc.rites.effect.RiteEffect;
import com.miskatonicmysteries.common.misc.rites.focus.RiteFocus;
import com.miskatonicmysteries.common.network.PacketHandler;
import com.miskatonicmysteries.registry.ModRegistries;
import com.miskatonicmysteries.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.*;

public class TileEntityOctagram extends TileEntityMod implements ITickable, IHasAssociatedBlessing, IHasOwner, IInducesInsanity {

    public ItemStackHandler inventory = new ItemStackHandler(8) {
        @Override
        public int getSlotLimit(int slot) {
            return 1;
        }
    };

    //maybe store particle emitting block poses in a list; when the foci get updated, the list is cleared and updated

    public boolean primed = false;
    public int tickCount = 0;

    public boolean canOverload = true;

    public boolean hasEnough = false;
    public boolean willOverload = false;
    public boolean hasWrongGooAddressed = false;

    public RiteEffect horribleThing = null;

    public BlockPos closestAltarPos = null;

    public float instability = 0;
    public int focusPower = 0;

    public String currentRite = "";

    public String lastPlayerUUID = "";

    public String ownerUUID = "";

    public final List<BlockPos> PARTICLE_EMMITTERS = new ArrayList<>();
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
        compound.setString("ownerUUID", ownerUUID);
        compound.setBoolean("canOverload", canOverload);
        compound.setBoolean("willOverload", willOverload);
        compound.setBoolean("hasWrongGooAddressed", hasWrongGooAddressed);
        compound.setBoolean("hasEnough", hasEnough);
        compound.setString("horribleThing", horribleThing != null ? horribleThing.getName().toString() : "");
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
        ownerUUID = compound.getString("ownerUUID");
        canOverload = compound.getBoolean("canOverload");
        willOverload = compound.getBoolean("willOverload");
        hasWrongGooAddressed = compound.getBoolean("hasWrongGooAddressed");
        hasEnough = compound.getBoolean("hasEnough");
        horribleThing = ModRegistries.RITE_EFFECTS.getOrDefault(compound.getString("horribleThing"), null);
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
        if (isValid()) {
            start();
            world.playSound(player, pos, SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 0.7F, 0.8F);
        } else if (isFilled()) {
            doFailingEffects(8);
        }
    }

    public boolean start() {
        if (tickCount > 0) return false;
        if (!isValid()) {
            doFailingEffects(20);
            return false;
        }
        primed = false;
        tickCount = 1;
        getAltar().bookOpen = true;
        PacketHandler.updateTE(getAltar());
        return true;
    }

    @Override
    public void update() {
        if (world.getTotalWorldTime() % 60 == 0) {
            updateRiteStats();
            if (getOwnerUUID() == null) {
                EntityPlayer player = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false);
                if (player != null) ownerUUID = player.getUniqueID().toString();
            }
        }
        if (!altarUsable()) {
            findNearestAltar();
        } else {
            if (getAltar().bookOpen && tickCount > 0 && isValid()) {
                flipAltarPages();
                OctagramRite rite = getCurrentRite();
                EntityPlayer caster = getLastPlayer();
                if (rite.test(this)) { //bind in instability later
                    tickCount++;
                    rite.doRitual(this, caster);
                    if (world.rand.nextFloat() < 0.05F)
                        world.playSound(null, pos, SoundEvents.ENTITY_ILLAGER_CAST_SPELL, SoundCategory.BLOCKS, 0.05F, 0.2F + world.rand.nextFloat());

                    if (world.isRemote && world.rand.nextFloat() < 0.2) {
                        spawnParticlesOnItems(false);
                    }
                } else {
                    tickCount = 0;
                    doFailingEffects(12);
                    currentRite = "";
                    return;
                }
                if (tickCount >= (rite.ticksNeeded - 60)) {
                    if (tickCount % 20 == 0)
                        checkConditions();
                    if (world.isRemote) giveNotTooSubtleHintsBecauseHolyShitYouMightMessUpYourRitualDude();
                }
                if (tickCount >= rite.ticksNeeded) {
                    updateRiteStats();
                    if (!(hasWrongGooAddressed || !hasEnough || willOverload)) {
                        world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.BLOCKS, 2F, 0.8F, false);
                        if (rite.type == OctagramRite.EnumType.PRIMED) {
                            primed = true;
                        } else {
                            rite.effect(this, caster);
                        }
                        if (!world.isRemote) {
                            int checks = (int) Math.max(instability + world.rand.nextDouble() - 1, 0) * 4; //let this depend on the instability, maybe like 10 * instability + 1 or so
                            RiteEffect effect = ModRegistries.Util.getRandomEffect(this, checks, RiteEffect.EnumTrigger.RITE_EXECUTED);
                            if (effect != null) {
                                effect.execute(this, RiteEffect.EnumTrigger.RITE_EXECUTED);
                            }
                        }
                        finish(true);
                    } else {
                        makeHorribleThingsHappen();
                        doFailingEffects(20);
                        finish(false);
                    }
                    if (world.isRemote) {
                        int amount = 6 + world.rand.nextInt(15);
                        for (int i = 0; i < amount; i++)
                            spawnParticlesOnItems(true);
                    }
                }
            } else {
                if (tickCount > 0) doFailingEffects(20);
                tickCount = 0;
            }
        }
        if (primed) {
            if (getCurrentRite() != null) {
                OctagramRite activeRite = getCurrentRite();
                if (activeRite.checkShouldTrigger(this, world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false))) {
                    activeRite.effect(this, world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false));
                    primed = false;
                    tickCount = 0;
                    currentRite = "";
                    canOverload = true;
                }
            } else {
                tickCount = 0;
                primed = false;
                currentRite = "";
                canOverload = true;
            }
        }
        if (world.isRemote && world.rand.nextFloat() < (tickCount > 0 ? 0.08F : 0.02F)) { //maybe just handle particles there
            handleParticles();
        }
        PacketHandler.updateTE(this);

    }

    public void checkConditions() {
        hasEnough = (focusPower >= getCurrentRite().focusPower);
        if (hasEnough) {
            if (canOverload)
                willOverload = focusPower > getCurrentRite().focusPower + getCurrentRite().overflowTolerance;
            hasWrongGooAddressed = !(getCurrentRite().octagram == Blessing.NONE || getAssociatedBlessing() == getCurrentRite().octagram);
        }
    }

    @SideOnly(Side.CLIENT)
    public void giveNotTooSubtleHintsBecauseHolyShitYouMightMessUpYourRitualDude() {
        int intensity = (int) (instability * 6);
        if (!hasEnough)
            for (int i = 0; i < 3; i++)
                Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleColoredSmoke(world, getPos().getX() + world.rand.nextDouble(), getPos().getY() + 0.2D + world.rand.nextDouble(), getPos().getZ() + world.rand.nextDouble(), 14737660, 0.9F));
        if (hasWrongGooAddressed)
            for (int i = 0; i < intensity; i++)
                Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleColoredSmoke(world, getPos().getX() + world.rand.nextDouble(), getPos().getY() + 0.2D + world.rand.nextDouble(), getPos().getZ() + world.rand.nextDouble(), 2359364, 1));
        if (willOverload)
            for (int i = 0; i < intensity * 2; i++) {
                if (world.rand.nextBoolean())
                    Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleColoredSmoke(world, getPos().getX() + world.rand.nextDouble(), getPos().getY() + 0.2D + world.rand.nextDouble(), getPos().getZ() + world.rand.nextDouble(), 0, 1));
                else
                    MiskatonicMysteries.proxy.generateParticle(new ParticleOccultFlame(world, getPos().getX() + world.rand.nextDouble(), getPos().getY() + 0.2D + world.rand.nextDouble(), getPos().getZ() + world.rand.nextDouble(), 0, 0, 0).multipleParticleScaleBy(0.8F + (float) world.rand.nextGaussian() / 20F));
            }

    }

    public void makeHorribleThingsHappen() {
        if (!world.isRemote) {
            int checks = 1 + (int) (instability * 10);
            if (hasWrongGooAddressed) {
                RiteEffect effect = ModRegistries.Util.getRandomEffect(this, checks, RiteEffect.EnumTrigger.GOO_CHECK);
                if (effect != null)
                    effect.execute(this, RiteEffect.EnumTrigger.GOO_CHECK);
            }
            if (willOverload) {
                RiteEffect effect = ModRegistries.Util.getRandomEffect(this, checks, RiteEffect.EnumTrigger.POWER_CHECK);
                if (effect != null)
                    effect.execute(this, RiteEffect.EnumTrigger.POWER_CHECK);
            }
        }
    }

    public boolean checkFocalPower() {
        if (!world.isRemote) {
            int checks = 1 + (int) (instability * 10);
            RiteEffect effect = ModRegistries.Util.getRandomEffect(this, checks, RiteEffect.EnumTrigger.POWER_CHECK);
            if (effect != null && canOverload) {
                effect.execute(this, RiteEffect.EnumTrigger.POWER_CHECK);
                return false;
            }
        }
        return focusPower >= getCurrentRite().focusPower;
    }

    public boolean checkGOOAdressed() {
        if (!world.isRemote) {
            int checks = 1 + (int) (instability * 10); //let this depend on the instability, maybe like 10 * instability + 1 or so
            RiteEffect effect = ModRegistries.Util.getRandomEffect(this, checks, RiteEffect.EnumTrigger.GOO_CHECK);
            if (effect != null) {
                effect.execute(this, RiteEffect.EnumTrigger.GOO_CHECK);
                return false;
            }
        }
        return getCurrentRite().octagram == Blessing.NONE || getAssociatedBlessing() == getCurrentRite().octagram;
    }

    public void finish(boolean clearInv) {
        canOverload = true;
        getAltar().bookOpen = false;
        getAltar().flipSpeed = 0;
        PacketHandler.updateTE(getAltar());
        if (!primed) {
            tickCount = 0;
            currentRite = "";
        }
        if (clearInv) {
            for (int i = 0; i < inventory.getSlots(); i++) {
                inventory.setStackInSlot(i, ItemStack.EMPTY);
            }

            world.getEntitiesWithinAABB(EntityPlayer.class, Block.FULL_BLOCK_AABB.grow(15, 5, 15).offset(getPos()), p -> p.getRNG().nextInt(4) == 0).forEach(player -> {
                Sanity.Util.setSanity(Sanity.Util.getSanity(player) - 2, player);
            });
        }
    }


    public boolean isValid() {
        if (getAltarBlessing() != null) {
            return getCurrentRite() != null && getCurrentRite().test(this) && (getCurrentRite().unlockBook == getAltarBlessing() || getCurrentRite().unlockBook == null);
        }
        return false;
    }

    public EntityPlayer getLastPlayer() {
        EntityPlayer player = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 16, false);
        if (player == null && !lastPlayerUUID.isEmpty()) {
            player = world.getPlayerEntityByUUID(UUID.fromString(lastPlayerUUID));
        }
        return player;
    }

    public OctagramRite getCurrentRite() {
        OctagramRite rite = isFilled() ? ModRegistries.Util.getRite(this) : null;
        if (rite == null && primed) {
            rite = ModRegistries.RITES.get(new ResourceLocation(currentRite));
        } else if (rite == null) {
            currentRite = "";
        }
        if (rite != null) {
            currentRite = rite.name.toString();
        }
        return rite;
    }

    public void flipAltarPages() {
        if (altarUsable() && !world.isRemote) {
            getAltar().flipSpeed = Math.min(getAltar().flipSpeed + 0.03F, 0.5F); // Math.min(getAltar().flipSpeed + 0.03F, 0.5F);
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
        if (getAltar() != null) {
            return getAltar().getAssociatedBlessing();
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
        canOverload = true;
        HELD_FOCI.forEach(focus -> {
            focusPower += focus.getConduitAmount(this, world, pos);
            instability += focus.getInstabilityRate(this, world, pos);
        });
        PLACED_FOCI.forEach(pair -> {
            focusPower += pair.second().getConduitAmount(this, world, pair.first());
            instability += pair.second().getInstabilityRate(this, world, pair.first());
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
            PARTICLE_EMMITTERS.clear();
            BlockPos.getAllInBox(pos.add(-10, -4, -10), pos.add(9, 5, 9)).forEach(blockPos ->
                    RiteFocus.getFociFor(world.getBlockState(blockPos).getBlock(), RiteFocus.EnumType.PLACED).forEach(focus -> {
                        PARTICLE_EMMITTERS.add(blockPos);

                        PLACED_FOCI.add(Pair.of(blockPos, focus));
                    }));
        }
        return PLACED_FOCI;
    }

    public List<RiteFocus> getAllFoci(boolean refresh) {
        if (refresh) {
            FOCI.clear();
            FOCI.addAll(getHeldFoci(refresh));
            for (Pair<BlockPos, RiteFocus> focus : getPlacedFoci(refresh)) {
                FOCI.add(focus.second());
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
            overhang_each.put(focus, Math.max(frequency - focus.getMaxSameType(this, world, pos), 0));
            totalMax += focus.getMaxSameType(this, world, pos);
        }
        for (Integer i : overhang_each.values()) {
            totalOverhang += i;
        }
        return 1 + (totalOverhang / totalMax);
    }


    public void doFailingEffects(int strength) {
        world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.PLAYERS, 1F, 1F);
        if (world.isRemote) {
            for (int i = 0; i < strength; i++) {
                world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX() + 0.5F + world.rand.nextGaussian() / 3, pos.getY(), pos.getZ() + 0.5F + world.rand.nextGaussian() / 3, world.rand.nextGaussian() / 10, world.rand.nextGaussian() / 20, world.rand.nextGaussian() / 10);
            }
        }
    }

    public void doOverloadEffects(int strength) {
        world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.PLAYERS, 1.5F, 1.2F);
        if (world.isRemote) {
            for (int i = 0; i < strength; i++) {
                world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, pos.getX() + 0.5F + world.rand.nextGaussian() / 3, pos.getY(), pos.getZ() + 0.5F + world.rand.nextGaussian() / 3, world.rand.nextGaussian() / 10, world.rand.nextGaussian() / 20, world.rand.nextGaussian() / 10);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void handleParticles() {
        for (BlockPos particlePos : PARTICLE_EMMITTERS) { //to do: make custom enchatning table particle that goes in the direction of the altar / a bound pos
            if (world.rand.nextFloat() < 0.1) {
                AxisAlignedBB box = world.getBlockState(particlePos).getBoundingBox(world, particlePos);
                Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleOccultEnchant(world, box.minX + particlePos.getX() + world.rand.nextFloat() * box.maxX, box.minY + particlePos.getY() + world.rand.nextFloat() * box.maxY, box.minZ + particlePos.getZ() + world.rand.nextFloat() * box.maxZ, 0, 0, 0, pos.getX() + world.rand.nextFloat(), pos.getY(), pos.getZ() + world.rand.nextFloat()));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void spawnParticlesOnItems(boolean finale) {
        for (int xP = -1; xP <= 1; xP++) {
            for (int zP = -1; zP <= 1; zP++) {
                boolean corners = Math.abs(xP) == 1 && Math.abs(zP) == 1;
                if (world.rand.nextFloat() < 0.3F) {
                    Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleOccultEnchant(world, pos.getX() + 0.5 + xP * (corners ? 0.8F : 1) + world.rand.nextGaussian() / 5, 0.05 + pos.getY() + world.rand.nextFloat() / 10, pos.getZ() + 0.5 + zP * (corners ? 0.8F : 1) + world.rand.nextGaussian() / 5, finale ? world.rand.nextGaussian() / 10 : 0, finale ? world.rand.nextFloat() / 3 : 0, finale ? world.rand.nextGaussian() / 10 : 0));
                }
            }
        }
    }

    public boolean isFilled() {
        for (int i = 0; i < inventory.getSlots(); i++) {
            if (!inventory.getStackInSlot(i).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public UUID getOwnerUUID() {
        return ownerUUID.isEmpty() ? null : UUID.fromString(ownerUUID);
    }


    @Override
    public EntityPlayer getOwner() {
        return getOwnerUUID() != null ? world.getPlayerEntityByUUID(getOwnerUUID()) : null;
    }

    @Override
    public int getInsanityPenalty() {
        return 1;
    }

    @Override
    public float getChanceForInsanity(EntityPlayer player) {
        return getOwnerUUID() != null && player.equals(getOwner()) ? 1 / 10F : 1 / 5F;
    }
}
