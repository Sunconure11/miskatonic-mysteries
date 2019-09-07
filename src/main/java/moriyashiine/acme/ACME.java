package moriyashiine.acme;

import moriyashiine.acme.common.capability.ISanity;
import moriyashiine.acme.common.capability.Sanity;
import moriyashiine.acme.common.capability.SanityStorage;
import moriyashiine.acme.common.handler.CapabilityHandler;
import moriyashiine.acme.common.network.PacketHandler;
import moriyashiine.acme.proxy.ServerProxy;
import moriyashiine.acme.registry.ModObjects;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;

@SuppressWarnings("WeakerAccess")
@Mod(modid = ACME.MODID, name = ACME.NAME, version = ACME.VERSION)
public class ACME
{
    public static final String MODID = "acme", NAME = "Abyssalcraft: Miskatonic Edition", VERSION = "1.0";
    
    @SidedProxy(serverSide = "moriyashiine.acme.proxy.ServerProxy", clientSide = "moriyashiine.acme.proxy.ClientProxy")
    static ServerProxy proxy;
    public static final Logger LOGGER = LogManager.getLogger(NAME);
    static CreativeTabs tab = new CreativeTabs(MODID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModObjects.stone_cthulhu_mural);
        }
    };
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        PacketHandler.init();
        CapabilityManager.INSTANCE.register(ISanity.class, new SanityStorage(), Sanity.class);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
    }
    
    @Mod.EventBusSubscriber
    static class Registry {
        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
            try {
                for (Field f : ModObjects.class.getFields()) {
                    Object obj = f.get(null);
                    if (obj instanceof Block) event.getRegistry().register((Block) obj);
                }
            }
            catch (Exception ignored) {}
        }
        
        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            try {
                for (Field f : ModObjects.class.getFields()) {
                    Object obj = f.get(null);
                    if (obj instanceof Item) {
                        Item item = (Item) obj;
                        event.getRegistry().register(item);
                        proxy.registerTexture(item);
                    }
                }
            }
            catch (Exception ignored) {}
            
            ModObjects.ARMOR_HASTUR.setRepairItem(new ItemStack(Blocks.WOOL, 1, EnumDyeColor.YELLOW.getMetadata()));
            ModObjects.ARMOR_SHUBNIGGURATH.setRepairItem(new ItemStack(Blocks.WOOL, 1, EnumDyeColor.BLACK.getMetadata()));
            
            ModObjects.TOOL_CULTIST.setRepairItem(new ItemStack(Items.IRON_INGOT));
            
            ModObjects.stone_cthulhu_mural.item = ModObjects.research_notes_cthulhu;
            ModObjects.moss_stone_cthulhu_mural.item = ModObjects.research_notes_cthulhu;
            ModObjects.prismarine_cthulhu_mural.item = ModObjects.research_notes_cthulhu;
            
            ModObjects.stone_hastur_mural.item = ModObjects.research_notes_hastur;
            ModObjects.moss_stone_hastur_mural.item = ModObjects.research_notes_hastur;
            ModObjects.terracotta_hastur_mural.item = ModObjects.research_notes_hastur;
            ModObjects.yellow_terracotta_hastur_mural.item = ModObjects.research_notes_hastur;
            
            ModObjects.stone_shubniggurath_mural.item = ModObjects.research_notes_shubniggurath;
            ModObjects.moss_stone_shubniggurath_mural.item = ModObjects.research_notes_shubniggurath;
        }
    }
}
