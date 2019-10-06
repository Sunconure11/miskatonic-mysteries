package com.miskatonicmysteries.common.item.misc;

import com.miskatonicmysteries.MiskatonicMysteries;
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

public class ItemMMBook extends Item{
    protected ResourceLocation bookName;
    public ItemMMBook(String bookName) {
        super();
        this.bookName = new ResourceLocation(MiskatonicMysteries.MODID, bookName);
    }

    public Book getBook(){
        System.out.println(BookRegistry.INSTANCE.books.keySet());
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
}
