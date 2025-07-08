package com.isnotchangcheng.event;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class ItemFrameListener implements Listener {
    public void openFrameInv(ItemStack itemStack, Player player) {
        if(itemStack.getType()== Material.AIR){
            return;
        }
        player.openInventory(createInv(itemStack, player));
    }

    @EventHandler
    public void onHitFrame(EntityDamageByEntityEvent event){
        if (event.getDamager() instanceof Player && event.getEntity() instanceof ItemFrame) {
            Player player = (Player) event.getDamager();
            ItemFrame itemFrame = (ItemFrame) event.getEntity();
            if (player.getGameMode() == GameMode.CREATIVE) {
                return;
            }
            event.setCancelled(true);
            openFrameInv(itemFrame.getItem(),player);
        }
    }

    @EventHandler
    public void onRightClickFrame(PlayerInteractEntityEvent event){
        if(event.getRightClicked() instanceof ItemFrame){
            ItemFrame itemFrame = (ItemFrame) event.getRightClicked();
            if(itemFrame.getItem().getType() == Material.AIR){
                return;
            }
            event.setCancelled(true);
            openFrameInv(itemFrame.getItem(), event.getPlayer());
        }
    }

    public Inventory createInv(ItemStack itemStack, Player player){
        Inventory inv = Bukkit.createInventory(player, 9,"展示框");
        ItemStack clone = itemStack.clone();
        clone.setAmount(itemStack.getMaxStackSize());
        for (int i = 0; i <9 ; i++) {
            inv.setItem(i, clone);
        }
        return inv;
    }

    @EventHandler
    public void onOpenInv(InventoryClickEvent event) {
        if (event.getClickedInventory() instanceof PlayerInventory) {
            return;
        }
        if ("展示框".equals(event.getView().getTitle())) {
            event.setCancelled(true);
            if (event.getCurrentItem() != null) {
                ItemStack cursorItem = event.getCurrentItem().clone();
                Player player =(Player)event.getWhoClicked();
                player.getInventory().addItem(cursorItem);
                player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP,1,1);
            }
        }
    }
}
