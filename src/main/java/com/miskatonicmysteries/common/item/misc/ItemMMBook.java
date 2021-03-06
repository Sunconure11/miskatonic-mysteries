package com.miskatonicmysteries.common.item.misc;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.capability.sanity.Sanity;
import com.miskatonicmysteries.common.misc.IHasAssociatedBlessing;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
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

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemMMBook extends Item implements IHasAssociatedBlessing{
    protected ResourceLocation bookName;
    protected Blessing blessing;
    protected List<String> tooltip = new ArrayList<>();
    public ItemMMBook(String bookName, Blessing associatedBlessing, String... tooltip) {
        super();
        this.bookName = new ResourceLocation(MiskatonicMysteries.MODID, bookName);
        this.blessing = associatedBlessing;
        this.maxStackSize = 1;
        this.tooltip.addAll(Arrays.asList(tooltip));
    }

    public Book getBook(){
        return BookRegistry.INSTANCE.books.get(bookName);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        this.tooltip.forEach(t -> tooltip.add(I18n.format(t)));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        Book book = getBook();
        if (book == null) {
            return new ActionResult(EnumActionResult.FAIL, stack);
        } else {
            if (playerIn instanceof EntityPlayerMP) {
                if (!Sanity.Util.getSanityCapability(playerIn).getExpansionMap().containsKey("expansion_" + bookName.toString() + "_read"))
                    Sanity.Util.addExpansion("expansion_" + bookName.toString() + "_read", -10, playerIn); //todo: adjust insanity stuff
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
