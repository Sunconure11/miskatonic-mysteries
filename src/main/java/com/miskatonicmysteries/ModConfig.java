package com.miskatonicmysteries;

import net.minecraftforge.common.config.Config;

@Config(modid = MiskatonicMysteries.MODID)
public class ModConfig {
    @Config.LangKey("config.category_client")
    @Config.Comment("Client-side config settings.")
    public static final Client client = new Client();

    public static class Client{
        @Config.Comment("Set this to false to disable the shaders in this mod.")
        @Config.LangKey("config.use_shaders")
        public boolean useShaders = true;
    }
}
