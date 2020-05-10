package com.example.examplemod;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.logging.log4j.Logger;

@Mod(modid = ExampleMod.MODID, name = ExampleMod.NAME, version = ExampleMod.VERSION)
public class ExampleMod
{
    public static final String MODID = "examplemod";
    public static final String NAME = "Example Mod";
    public static final String VERSION = "1.0";
    public static final String RESOURCE_INVENTORY  = "inventory";

    private static Logger logger;

    private static MonBlock targetBlock;
    private static ItemBlock itemTargetBlock;

    private static Item itemM4a1;

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
        targetBlock = new MonBlock();
        ForgeRegistries.BLOCKS.register(targetBlock);

        itemTargetBlock = new ItemBlock(targetBlock);
        itemTargetBlock.setRegistryName(targetBlock.getRegistryName());
        configureItemAndRegister(itemTargetBlock);

        itemM4a1 = new Item();
        itemM4a1.setCreativeTab(CreativeTabs.COMBAT);
        itemM4a1.setRegistryName(com.example.examplemod.ExampleMod.MODID, "m4a1");
        configureItemAndRegister(itemM4a1);

    }

    private void configureItemAndRegister(Item item) {
        ForgeRegistries.ITEMS.register(item);
        ModelResourceLocation rl = new ModelResourceLocation(item.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, 0, rl);
    }
}
