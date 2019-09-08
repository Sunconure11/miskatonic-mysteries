package moriyashiine.acme.common.item.consumable;

import moriyashiine.acme.registry.ModPotions;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemTranquilizer extends ItemFood{
    public ItemTranquilizer() {
        super(0, 0, false);
        setAlwaysEdible();
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 40;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        entityLiving.addPotionEffect(new PotionEffect(ModPotions.tranquilized, 1000, 0));
        stack.shrink(1);
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}
