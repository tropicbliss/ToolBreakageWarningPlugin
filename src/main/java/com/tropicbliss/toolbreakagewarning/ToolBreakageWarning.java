package com.tropicbliss.toolbreakagewarning;

import com.tropicbliss.toolbreakagewarning.listeners.ItemDurabilityLowListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class ToolBreakageWarning extends JavaPlugin {
  private static ToolBreakageWarning plugin;

  @Override
  public void onEnable() {
    plugin = this;
    getConfig().options().copyDefaults();
    saveDefaultConfig();
    getServer().getPluginManager().registerEvents(new ItemDurabilityLowListener(), this);
  }

  public static ToolBreakageWarning getPlugin() {
    return plugin;
  }
}