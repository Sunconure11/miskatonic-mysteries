package com.miskatonicmysteries.common.misc.patchouli;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.misc.rites.OctagramRite;
import com.miskatonicmysteries.proxy.ClientProxy;
import com.miskatonicmysteries.registry.ModRegistries;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariableProvider;
import vazkii.patchouli.api.PatchouliAPI;

public class ProcessorRites implements IComponentProcessor {
    protected OctagramRite rite;

    @Override
    public void setup(IVariableProvider<String> iVariableProvider) {
        this.rite = ModRegistries.RITES.get(new ResourceLocation(iVariableProvider.get("rite")));
    }

    @Override
    public String process(String s) {
        switch (s) {
            case "octagram":
                return ClientProxy.OCTAGRAM_TEXTURES.getOrDefault(rite.octagram, new ResourceLocation(MiskatonicMysteries.MODID, "textures/block/octagram_default.png")).toString();
            case "power":
                return String.valueOf(rite.focusPower);
            case "overflow_tolerance":
                return String.valueOf(rite.overflowTolerance);
            case "ticks":
                return String.valueOf(rite.ticksNeeded);
            case "knowledge":
                return rite.unlockBook.getName();
            case "rite_name":
                return I18n.format(rite.getUnlocalizedName());
        }
        for (int i = 0; i < rite.ingredients.size(); i++) {
            if (s.equals("ingredient" + (i + 1))) {
                if (rite.ingredients.size() > i) {
                    return PatchouliAPI.instance.serializeIngredient(rite.ingredients.get(i));
                }
                return PatchouliAPI.instance.serializeItemStack(ItemStack.EMPTY);
            }
        }
        return null;
    }
}
