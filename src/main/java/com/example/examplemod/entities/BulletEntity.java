package com.example.examplemod.entities;

import com.example.examplemod.ExampleMod;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;

public class BulletEntity extends EntityTippedArrow {

    public BulletEntity(World worldIn) {
        super(worldIn);
    }

    public BulletEntity(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public BulletEntity(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
    }

    @Override
    protected void onHit(RayTraceResult raytraceResultIn) {
        ExampleMod.logger.log(Level.INFO, "Fleche a touche un element");
        if (raytraceResultIn.entityHit == null) {
            this.setDead();
        } else {
            super.onHit(raytraceResultIn);
        }
    }
}
