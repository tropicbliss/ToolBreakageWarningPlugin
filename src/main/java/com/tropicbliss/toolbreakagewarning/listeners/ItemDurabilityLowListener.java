package com.tropicbliss.toolbreakagewarning.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemDurabilityLowListener implements Listener {

  @EventHandler
  public void onItemDurabilityLow(PlayerItemDamageEvent e) {
    Player player = e.getPlayer();
    ItemStack item = e.getItem();
    ItemMeta meta = item.getItemMeta();
    if (meta instanceof Damageable) {
      Damageable tool = (Damageable) meta;
      short maxDurability = item.getType().getMaxDurability();
      int previousDurability =
          maxDurability - tool.getDamage(); // Durability before the item is degraded
      int unitOfDamage = e.getDamage();
      int currentDurability =
          previousDurability - unitOfDamage; // Durability after the item is degraded
      if (currentDurability
          <= 0) { // Do not tell user that item is going to be broken when the item is just broken
        return;
      }
      float durabilityPercent = (float) currentDurability / (float) maxDurability * 100;
      float minPercentageToWarn;
      if (item.getType().toString().startsWith("DIAMOND_")) {
        minPercentageToWarn = (float) 12.5;
      } else {
        minPercentageToWarn = (float) 25;
      }
      if (durabilityPercent <= minPercentageToWarn) {
        player.sendMessage(ChatColor.RED + "The item you're holding is about to break");
      }
    }
  }
}