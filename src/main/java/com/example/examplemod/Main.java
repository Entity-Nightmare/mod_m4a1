package com.example.examplemod;

import com.example.examplemod.events.FireArrowEvent;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.logging.log4j.Logger;
import util.Reference;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Main
{
    @Mod.Instance
    public static net.minecraft.client.main.Main instance;

    public static final String MODID = "examplemod";
    public static final String NAME = "Example Mod";
    public static final String VERSION = "1.0";
    public static final String RESOURCE_INVENTORY  = "inventory";

    public static Logger logger;

    private static Wildboar wildboar;
    private static ItemBlock itemwildboar;

    private static MonBlock targetBlock;
    private static ItemBlock itemTargetBlock;

    public static Item itemM4a1;

    public static Item itemDesertEagle;

    public static Item itemCheyTac;

    public static FireArrowEvent fireEvent = new FireArrowEvent();


    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        preInitTargetBlock();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {

        // some example code
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());

    }

    private void preInitTargetBlock() {
        MinecraftForge.EVENT_BUS.register(fireEvent);


        wildboar = new Wildboar();
        ForgeRegistries.BLOCKS.register(wildboar);

        itemwildboar = new ItemBlock(wildboar);
        itemwildboar.setRegistryName(wildboar.getRegistryName());
        configureItemAndRegister(itemwildboar);

        targetBlock = new MonBlock();
        ForgeRegistries.BLOCKS.register(targetBlock);

        itemTargetBlock = new ItemBlock(targetBlock);
        itemTargetBlock.setRegistryName(targetBlock.getRegistryName());
        configureItemAndRegister(itemTargetBlock);

        itemM4a1 = createItem("m4a1", 3);
        itemDesertEagle = createItem("01_desert_eagle", 1);
        itemCheyTac = createItem("25_chey_tac", 1);

    }

    private static Item createItem(String type, int nbBullet) {
        Item item = new Item();
        item.setCreativeTab(CreativeTabs.COMBAT);
        item.setRegistryName(Main.MODID, type);
        configureItemAndRegister(item);
        fireEvent.addItem(item, nbBullet);
        return item;
    }

    private static void configureItemAndRegister(Item item) {
        ForgeRegistries.ITEMS.register(item);
        ModelResourceLocation rl = new ModelResourceLocation(item.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, 0, rl);
    }
}
