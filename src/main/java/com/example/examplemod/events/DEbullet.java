package com.example.examplemod.events;

import com.example.examplemod.Main;
import com.example.examplemod.entities.BulletEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Level;

public class DEbullet {

    public static final int DAMAGE = 8;
    public static final float VELOCITY = 8.0F;
    public static final int NB_BULLET = 1;

    @SubscribeEvent
    public void shot(PlayerInteractEvent event) {
        EntityPlayer player = event.getEntityPlayer();
        World world = event.getWorld();

        ItemStack itemStack = player.getHeldItemMainhand();
        if (itemStack == null) {
            return;
        }
        Item item = itemStack.getItem();
        if (! item.equals(Main.itemDesertEagle)){
            return;
        }


        Main.logger.log(Level.INFO, "DE marche" + event.getClass());
        for (int i = 0; i < NB_BULLET; i++) {
            if (!player.capabilities.isCreativeMode) {
                ItemStack arrows = findArrow(player);
                if (arrows.isEmpty()) {
                    return;
                }
                arrows.shrink(1);
            }
            EntityTippedArrow arrow = new BulletEntity(world, player);
            arrow.setDamage(DAMAGE);
            arrow.shootingEntity = player;
            arrow.pickupStatus = EntityArrow.PickupStatus.DISALLOWED;
            arrow.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, VELOCITY, 0.0F);
            world.spawnEntity(arrow);
        }
    }

    private ItemStack findArrow(EntityPlayer player) {
        ItemStack arrows = ItemStack.EMPTY;
        for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
        {
            ItemStack itemstack = player.inventory.getStackInSlot(i);
            if (this.isArrow(itemstack));
            {
                arrows = itemstack;
            }
        }
        return arrows;
    }


    protected boolean isArrow(ItemStack stack) {return stack.getItem() instanceof ItemArrow;}
}
