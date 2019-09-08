package com.miskatonicmysteries.client.gui;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.MiskatonicMysteries;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;

import java.util.Set;

public class GuiFactory implements IModGuiFactory {
    public GuiFactory() {
    }

    public void initialize(Minecraft minecraftInstance) {
    }

    public boolean hasConfigGui() {
        return true;
    }


    @Override
    public GuiScreen createConfigGui(GuiScreen parentScreen) {
        return new GuiConfig(parentScreen, MiskatonicMysteries.MODID, MiskatonicMysteries.NAME);
    }

    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }
}
