package com.example.examplemod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Wildboar extends Block {
    public static final String NAME = "wild_boar";

    public Wildboar() {
        super(Material.DRAGON_EGG);
        setRegistryName(Main.MODID, NAME);
        setUnlocalizedName(Main.MODID + "." + NAME);

        setCreativeTab(CreativeTabs.MISC);
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.SOLID;
    }

}
