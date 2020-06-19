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

import java.util.HashMap;
import java.util.Map;

public class FireArrowEvent {

    public static final int DAMAGE = 7;
    public static final float VELOCITY = 4.0F;
    public static final int NB_BULLET = 3;

    private Map<Item, ItemProperty> items = new HashMap<>();

    private static class ItemProperty {
        int nbBullet;

        public ItemProperty(int nbBullet) {
            this.nbBullet = nbBullet;
        }
    }

    public void addItem(Item item, int nbBullet) {
        items.put(item, new ItemProperty(nbBullet));
    }

    @SubscribeEvent
    public void shot (PlayerInteractEvent event) {
        EntityPlayer player = event.getEntityPlayer();
        World world = event.getWorld();

        ItemStack itemStack = player.getHeldItemMainhand();
        if (itemStack == null) {
            return; // Sort de la methode
        }
        Item item = itemStack.getItem();

        ItemProperty property = items.get(item);
        if (property == null) { // ! -> non)
            return;
        }



        Main.logger.log(Level.INFO, "Recu evenement player " + event.getClass());
        for (int i = 0; i < property.nbBullet; i++) {
            if (!player.capabilities.isCreativeMode) {
                ItemStack arrows = findArrow(player);
                if (arrows.isEmpty()) {
                    return;
                }
                arrows.shrink(1);
            }
            //EntityTippedArrow arrow = new BulletEntity(world, player.posX,player.posY + (double)player.getEyeHeight() - 0.10000000149011612D, player.posZ);
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
