package com.example.examplemod.init;

import com.example.examplemod.Main;
import com.example.examplemod.entities.EntityClicker;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import util.Reference;

public class EntityInit
{
    public static void registerEntities()
    {
        registerEntity("clicker", EntityClicker.class, Reference.EntityClicker, 30, 10781555, 000000);
    }

    private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2)
    {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), entity,name ,id , Main.instance, range, 1, true , color1, color2);
    }
}
