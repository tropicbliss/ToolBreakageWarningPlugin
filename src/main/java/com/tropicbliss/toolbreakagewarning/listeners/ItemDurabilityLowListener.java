package com.tropicbliss.toolbreakagewarning.listeners;

import com.tropicbliss.toolbreakagewarning.ToolBreakageWarning;
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
      double durabilityPercent = (double) currentDurability / (double) maxDurability * 100;
      double minPercentageToWarn;
      if (item.getType().toString().startsWith("DIAMOND_")) {
        minPercentageToWarn = ToolBreakageWarning.getPlugin().getConfig()
            .getDouble("diamondToolWarnPercent");
      } else {
        minPercentageToWarn = ToolBreakageWarning.getPlugin().getConfig()
            .getDouble("nonDiamondToolWarnPercent");
      }
      if (durabilityPercent <= minPercentageToWarn) {
        player.sendMessage(ChatColor.RED + "The item you're holding is about to break");
      }
    }
  }
}