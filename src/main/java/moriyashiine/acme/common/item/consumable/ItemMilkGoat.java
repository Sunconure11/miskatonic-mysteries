package moriyashiine.acme.common.item.consumable;

import com.miskatonicmysteries.common.capability.blessing.BlessingCapability;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import moriyashiine.acme.common.capability.sanity.Sanity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemMilkGoat extends Item {
    public ItemMilkGoat() {
        super();
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 69; //nice
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        playerIn.setActiveHand(handIn);
        return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer && BlessingCapability.Util.hasBlessing((EntityPlayer) entityLiving, Blessing.NONE)){
            BlessingCapability.Util.setBlessing(Blessing.SHUB, (EntityPlayer) entityLiving);
            Sanity.Util.addExpansion("goo_transformation_shub", 30, (EntityPlayer) entityLiving);
        }
        entityLiving.attackEntityFrom(DamageSource.MAGIC, 5);
        stack.shrink(1);
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}
