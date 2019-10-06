package com.miskatonicmysteries.common.block.tile;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.client.render.RenderAltar;
import com.miskatonicmysteries.common.network.PacketHandler;
import com.miskatonicmysteries.registry.ModObjects;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.HashMap;

public class TileEntityAltar extends TileEntity implements ITickable{
    public static final HashMap<Item, ResourceLocation> BOOK_TEXTURES = new HashMap<Item, ResourceLocation>();
    static {
        BOOK_TEXTURES.put(ModObjects.research_notes_shubniggurath, new ResourceLocation(MiskatonicMysteries.MODID, "textures/misc/book_shub.png"));
        BOOK_TEXTURES.put(ModObjects.research_notes_hastur, new ResourceLocation(MiskatonicMysteries.MODID, "textures/misc/book_hastur.png"));
        BOOK_TEXTURES.put(ModObjects.research_notes_cthulhu, new ResourceLocation(MiskatonicMysteries.MODID, "textures/misc/book_cthulhu.png"));
        BOOK_TEXTURES.put(ModObjects.necronomicon, new ResourceLocation(MiskatonicMysteries.MODID, "textures/misc/book_necronomicon.png"));

        BOOK_TEXTURES.put(Items.BOOK, RenderAltar.TEXTURE_BOOK);
        BOOK_TEXTURES.put(Items.ENCHANTED_BOOK, RenderAltar.TEXTURE_BOOK);
        BOOK_TEXTURES.put(Items.WRITABLE_BOOK, RenderAltar.TEXTURE_BOOK);
        BOOK_TEXTURES.put(Items.WRITTEN_BOOK, RenderAltar.TEXTURE_BOOK);

    }

    public float bookOpeningProgress;
    public boolean bookOpen = false;
    public ItemStackHandler inventory = new ItemStackHandler(1) {
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

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
        return oldState.getBlock() != newState.getBlock();
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(pos, -999, getUpdateTag());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
        IBlockState state = this.world.getBlockState(this.pos);
        this.world.notifyBlockUpdate(this.pos, state, state, 3);

        super.onDataPacket(net, pkt);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("inventory", inventory.serializeNBT());
        compound.setBoolean("opened", bookOpen);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        bookOpen = compound.getBoolean("opened");
        super.readFromNBT(compound);
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return new AxisAlignedBB(getPos(), getPos().add(1, 1.2, 1));
    }


    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T) inventory : super.getCapability(capability, facing);
    }

    @Override
    public void update() {
        if (world.isRemote) {
            if (bookOpen && bookOpeningProgress < 1) {
                bookOpeningProgress += 0.05;
            } else if (!bookOpen && bookOpeningProgress > 0) {
                bookOpeningProgress -= 0.05;
            }
        }
    }
}
