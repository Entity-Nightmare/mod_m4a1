package com.example.examplemod.events;

import com.example.examplemod.ExampleMod;
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

public class M4a1bullet {

    @SubscribeEvent
    public void shot (PlayerInteractEvent event) {
        EntityPlayer player = event.getEntityPlayer();
        World world = event.getWorld();

        ItemStack itemStack = player.getHeldItemMainhand();
        if (itemStack == null) {
            return; // Sort de la methode
        }
        Item item = itemStack.getItem();
        if (! item.equals(ExampleMod.itemM4a1)) { // ! -> non)
            return;
        }



        ExampleMod.logger.log(Level.INFO, "Recu evenement player " + event.getClass());
        float velocity = 1;
        for (int i = 0; i < 3;i++) {
            if (!player.capabilities.isCreativeMode) {
                ItemStack arrows = findArrow(player);
                if (arrows.isEmpty()) {
                    return;
                }
                arrows.shrink(1);
            }
            EntityTippedArrow arrow = new BulletEntity(world, player.posX,player.posY + (double)player.getEyeHeight() - 0.10000000149011612D, player.posZ);
            arrow.shootingEntity = player;
            arrow.pickupStatus = EntityArrow.PickupStatus.DISALLOWED;
            arrow.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, velocity * 3.0F, 1.0F);
            world.spawnEntity(arrow);
        }
    }

    private ItemStack findArrow(EntityPlayer player) {
        ItemStack arrows = ItemStack.EMPTY;
        for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
        {
            ItemStack itemstack = player.inventory.getStackInSlot(i);
            if (this.isArrow(itemstack))
            {
                arrows = itemstack;
            }
        }
        return arrows;
    }

    protected boolean isArrow(ItemStack stack)
    {
        return stack.getItem() instanceof ItemArrow;
    }

}
