package com.miskatonicmysteries;

import net.minecraftforge.common.config.Config;

@Config(modid = MiskatonicMysteries.MODID)
public class ModConfig {

    @Config.LangKey("config.category_worldgen")
    @Config.Comment("Config settings on world generation.")
    public static final WorldGen worldGen = new WorldGen();

    @Config.LangKey("config.category_client")
    @Config.Comment("Client-side config settings.")
    public static final Client client = new Client();

    public static class Client{
        @Config.Comment("Set this to false to disable the shaders in this mod.")
        @Config.LangKey("config.use_shaders")
        public boolean useShaders = true;
    }

    public static class WorldGen{
        @Config.Comment("Determines the chance for the Black Goat' shrines to spawn. Set to 1 to spawn a shrine in (almost) every chunk it can spawn in, set to 0 to disable.")
        @Config.LangKey("config.chanceShubShrines")
        public float chanceShubShrines = 0.5F;
    }
}
