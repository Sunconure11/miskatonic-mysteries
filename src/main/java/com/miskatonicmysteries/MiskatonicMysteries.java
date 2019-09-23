package com.miskatonicmysteries;

import com.miskatonicmysteries.common.capability.blessing.BlessingCapability;
import com.miskatonicmysteries.common.capability.blessing.BlessingStorage;
import com.miskatonicmysteries.common.capability.blessing.IBlessingCapability;
import com.miskatonicmysteries.common.capability.sanity.ISanity;
import com.miskatonicmysteries.common.capability.sanity.Sanity;
import com.miskatonicmysteries.common.capability.sanity.SanityStorage;
import com.miskatonicmysteries.common.commands.CommandMiskatonicMysteries;
import com.miskatonicmysteries.common.handler.CapabilityHandler;
import com.miskatonicmysteries.common.handler.InsanityHandler;
import com.miskatonicmysteries.common.handler.LootHandler;
import com.miskatonicmysteries.common.network.PacketHandler;
import com.miskatonicmysteries.common.world.gen.ModWorldGen;
import com.miskatonicmysteries.common.world.gen.village.VillageComponentHasturShrine;
import com.miskatonicmysteries.common.world.gen.village.VillageHasturShrineHandler;
import com.miskatonicmysteries.proxy.ServerProxy;
import com.miskatonicmysteries.registry.ModObjects;
import com.miskatonicmysteries.registry.ModPotions;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.Random;

@SuppressWarnings("WeakerAccess")
@Mod(modid = MiskatonicMysteries.MODID, name = MiskatonicMysteries.NAME, version = MiskatonicMysteries.VERSION)
public class MiskatonicMysteries {
    public static final String MODID = "miskatonicmysteries", NAME = "Miskatonic Mysteries", VERSION = "1.0";

    @SidedProxy(serverSide = "com.miskatonicmysteries.proxy.ServerProxy", clientSide = "com.miskatonicmysteries.proxy.ClientProxy")
    static ServerProxy proxy;
    public static final Logger LOGGER = LogManager.getLogger(NAME);
    static CreativeTabs tab = new CreativeTabs(MODID) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModObjects.milk_black_goat);
        }
    };

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        PacketHandler.init();
        CapabilityManager.INSTANCE.register(ISanity.class, new SanityStorage(), Sanity.class);
        CapabilityManager.INSTANCE.register(IBlessingCapability.class, new BlessingStorage(), BlessingCapability.class);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
        GameRegistry.registerWorldGenerator(new ModWorldGen(), 0);//set to max int value if stuff gets shitty
        VillagerRegistry.instance().registerVillageCreationHandler(new VillageHasturShrineHandler());
        MapGenStructureIO.registerStructureComponent(VillageComponentHasturShrine.class, MiskatonicMysteries.MODID+":hasturShrineStructure");

        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
        MinecraftForge.EVENT_BUS.register(new InsanityHandler());
        new LootHandler();

        registerOreDict();
        registerFurnaceRecipes();
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandMiskatonicMysteries());
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
            } catch (Exception ignored) {
            }
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
            } catch (Exception ignored) {
            }

            ModObjects.ARMOR_HASTUR.setRepairItem(new ItemStack(Blocks.WOOL, 1, EnumDyeColor.YELLOW.getMetadata()));
            ModObjects.ARMOR_SHUBNIGGURATH.setRepairItem(new ItemStack(Blocks.WOOL, 1, EnumDyeColor.BLACK.getMetadata()));

            ModObjects.TOOL_CULTIST.setRepairItem(new ItemStack(Items.IRON_INGOT));

            ModObjects.stone_cthulhu_mural.item = new ItemStack(ModObjects.research_notes_cthulhu);//PatchouliAPI.instance.getBookStack(MODID + ":notes_cthulhu");
            ModObjects.moss_stone_cthulhu_mural.item = new ItemStack(ModObjects.research_notes_cthulhu);
            ModObjects.prismarine_cthulhu_mural.item = new ItemStack(ModObjects.research_notes_cthulhu);

            ModObjects.stone_hastur_mural.item = new ItemStack(ModObjects.research_notes_hastur); //do these with variants instead
            ModObjects.moss_stone_hastur_mural.item = new ItemStack(ModObjects.research_notes_hastur);
            ModObjects.terracotta_hastur_mural.item = new ItemStack(ModObjects.research_notes_hastur);
            ModObjects.yellow_terracotta_hastur_mural.item = new ItemStack(ModObjects.research_notes_hastur);

            ModObjects.stone_shubniggurath_mural.item = new ItemStack(ModObjects.research_notes_shubniggurath);
            ModObjects.moss_stone_shubniggurath_mural.item = new ItemStack(ModObjects.research_notes_shubniggurath);
        }

        @SubscribeEvent
        public static void registerPotions(RegistryEvent.Register<Potion> event) {
            try {
                for (Field f : ModPotions.class.getFields()) {
                    Object obj = f.get(null);
                    if (obj instanceof Potion) event.getRegistry().register((Potion) obj);
                }
            } catch (Exception ignored) {
            }
        }

        @SubscribeEvent
        public static void registerTrades(RegistryEvent.Register<VillagerRegistry.VillagerProfession> event) {
            VillagerRegistry.VillagerProfession profession = ForgeRegistries.VILLAGER_PROFESSIONS.getValue(new ResourceLocation("librarian"));
            profession.getCareer(0).addTrade(1, new EntityVillager.ITradeList() {
                @Override
                public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
                    recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 1 + random.nextInt(2)), new ItemStack(ModObjects.tranquilizer, 1 + random.nextInt(3))));
                }
            });
        }
    }

    public void registerOreDict(){
        OreDictionary.registerOre("ingotGold", ModObjects.gold_oceanic);
        OreDictionary.registerOre("oreGold", ModObjects.gold_oceanic);
    }

    public void registerFurnaceRecipes(){
        FurnaceRecipes.instance().addSmeltingRecipe(new ItemStack(ModObjects.gold_oceanic), new ItemStack(Items.GOLD_INGOT), 10);
    }
}