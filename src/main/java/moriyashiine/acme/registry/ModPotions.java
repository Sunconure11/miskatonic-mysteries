package moriyashiine.acme.registry;

import moriyashiine.acme.ACME;
import moriyashiine.acme.Util;
import moriyashiine.acme.common.block.BlockMural;
import moriyashiine.acme.common.item.armor.ItemHasturArmor;
import moriyashiine.acme.common.item.armor.ItemShubniggurathArmor;
import moriyashiine.acme.common.item.tool.ItemBlackGoatsGuttingDagger;
import moriyashiine.acme.common.item.tool.ItemBlackGoatsHornedDagger;
import moriyashiine.acme.common.item.tool.ItemYellowKingsDagger;
import moriyashiine.acme.common.potion.PotionTranquilized;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModPotions {
    public static PotionTranquilized potion_tranquilized = new PotionTranquilized();
}