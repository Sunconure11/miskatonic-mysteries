package com.miskatonicmysteries;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = MiskatonicMysteries.MODID)
public class ModConfig {

    @Config.LangKey("config.category_worldgen")
    @Config.Comment("Config settings on world generation.")
    public static final WorldGen worldGen = new WorldGen();

    @Config.LangKey("config.category_client")
    @Config.Comment("Client-side config settings.")
    public static final Client client = new Client();


    @Config.LangKey("config.category_sanity")
    @Config.Comment("Config settings on this mod's sanity system.")
    public static final SanitySettings sanity = new SanitySettings();

    public static class Client{
        @Config.Comment("Set this to false to disable the shaders in this mod.")
        @Config.LangKey("config.use_shaders")
        public boolean useShaders = true;


        @Config.Comment("Set this to false to disable changes to the player's model under certain circumstances, e.g. when under a special transformation.")
        @Config.LangKey("config.change_player_model")
        public boolean playerModelOverrides = true;

        @Config.Comment("Determines the amount of particles that are spawned.")
        @Config.LangKey("config.particle_amount")
        public EnumParticleAmount particleAmount = EnumParticleAmount.STANDARD;

        public enum EnumParticleAmount {
            STANDARD,
            REDUCED
        }
    }

    public static class SanitySettings{
        @Config.Comment("Sets the insanity event interval.\n\nSometimes it is hard to stay sane inside insanity.")
        @Config.LangKey("config.insanity_interval")
        public int insanityInterval = 2000;
    }

    public static class WorldGen{
        @Config.Comment("Determines the chance for the Black Goat's shrines to spawn. Set to 1 to spawn a shrine in (almost) every chunk it can spawn in, set to 0 to disable.")
        @Config.LangKey("config.chanceShubShrines")
        @Config.RangeDouble(min = 0, max = 1)
        public float chanceShubShrines = 0.01F;


        @Config.Comment("Determines the chance for Hastur shrines to spawn. Set to 1 to attempt spawning a shrine in every non-desert village, set to 0 to disable.")
        @Config.LangKey("config.chanceBananaShrines")
        @Config.RangeDouble(min = 0, max = 1)
        public float chanceHasturShrines = 0.3F;


        @Config.Comment("Determines the chance for Cthulhu shrines to spawn. Set to 1 to attempt spawning a shrine in every ocean cave, set to 0 to disable.")
        @Config.LangKey("config.chanceCthulhuShrines")
        @Config.RangeDouble(min = 0, max = 1)
        public float chanceCthulhuShrines = 0.05F;
    }

    @Mod.EventBusSubscriber(modid = MiskatonicMysteries.MODID)
    private static class SyncConfig {
        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(MiskatonicMysteries.MODID)) {
                ConfigManager.sync(MiskatonicMysteries.MODID, net.minecraftforge.common.config.Config.Type.INSTANCE);
            }
        }
    }
}
