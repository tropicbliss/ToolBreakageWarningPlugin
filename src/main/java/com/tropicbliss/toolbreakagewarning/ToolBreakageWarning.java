package com.tropicbliss.toolbreakagewarning;

import com.tropicbliss.toolbreakagewarning.listeners.ItemDurabilityLowListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class ToolBreakageWarning extends JavaPlugin {

  @Override
  public void onEnable() {
    getServer().getPluginManager().registerEvents(new ItemDurabilityLowListener(), this);
  }
}