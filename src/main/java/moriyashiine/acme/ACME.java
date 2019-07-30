package moriyashiine.acme;

import moriyashiine.acme.proxy.ServerProxy;
import moriyashiine.acme.registry.ModObjects;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.lang.reflect.Field;

@Mod(modid = ACME.MODID, name = ACME.NAME, version = ACME.VERSION)
public class ACME
{
    static final String MODID = "acme", NAME = "Abyssalcraft: Miskatonic Edition", VERSION = "1.0";
    
    @SidedProxy(serverSide = "moriyashiine.acme.proxy.ServerProxy", clientSide = "moriyashiine.acme.proxy.ClientProxy")
    static ServerProxy proxy;
    
    static CreativeTabs tab = new CreativeTabs(MODID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModObjects.stone_cthulhu_mural);
        }
    };

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }
    
    @Mod.EventBusSubscriber
    static class Registry
    {
        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event)
        {
            try
            {
                for (Field f : ModObjects.class.getFields())
                {
                    Object obj = f.get(null);
                    if (obj instanceof Block) event.getRegistry().register((Block) obj);
                }
            }
            catch (Exception ignored) {}
        }
    
        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event)
        {
            try
            {
                for (Field f : ModObjects.class.getFields())
                {
                    Object obj = f.get(null);
                    if (obj instanceof Item)
                    {
                        Item item = (Item) obj;
                        event.getRegistry().register(item);
                        proxy.registerTexture(item);
                    }
                }
            }
            catch (Exception ignored) {}
        }
    }
}
