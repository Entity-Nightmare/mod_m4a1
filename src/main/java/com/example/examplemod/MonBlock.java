package com.example.examplemod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MonBlock extends Block {
    public static final String NAME = "mon_block";

    public MonBlock() {
        super(Material.DRAGON_EGG);
        setRegistryName(ExampleMod.MODID, NAME);
        setUnlocalizedName(ExampleMod.MODID + "." + NAME);

        setCreativeTab(CreativeTabs.COMBAT);
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.SOLID;
    }

}
