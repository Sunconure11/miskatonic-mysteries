package com.miskatonicmysteries.common.item.misc;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.misc.IHasAssociatedBlessing;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import vazkii.patchouli.common.base.PatchouliSounds;
import vazkii.patchouli.common.book.Book;
import vazkii.patchouli.common.book.BookRegistry;
import vazkii.patchouli.common.network.NetworkHandler;
import vazkii.patchouli.common.network.message.MessageOpenBookGui;

public class ItemMMBook extends Item implements IHasAssociatedBlessing{
    protected ResourceLocation bookName;
    protected Blessing blessing;
    public ItemMMBook(String bookName, Blessing associatedBlessing) {
        super();
        this.bookName = new ResourceLocation(MiskatonicMysteries.MODID, bookName);
        this.blessing = associatedBlessing;
        this.maxStackSize = 1;
    }

    public Book getBook(){
        return BookRegistry.INSTANCE.books.get(bookName);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        Book book = getBook();
        if (book == null) {
            return new ActionResult(EnumActionResult.FAIL, stack);
        } else {
            if (playerIn instanceof EntityPlayerMP) {
                NetworkHandler.INSTANCE.sendTo(new MessageOpenBookGui(book.resourceLoc.toString()), (EntityPlayerMP)playerIn);
                SoundEvent sfx = PatchouliSounds.getSound(book.openSound, PatchouliSounds.book_open);
                worldIn.playSound(null, ((EntityPlayerMP) playerIn).posX, ((EntityPlayerMP) playerIn).posY, ((EntityPlayerMP) playerIn).posZ, sfx, SoundCategory.PLAYERS, 1.0F, (float)(0.7D + Math.random() * 0.4D));
            }

            return new ActionResult(EnumActionResult.SUCCESS, stack);
        }
    }

    @Override
    public Blessing getAssociatedBlessing() {
        return blessing;
    }
}
