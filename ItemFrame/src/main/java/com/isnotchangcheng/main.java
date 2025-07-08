package com.isnotchangcheng;

import com.isnotchangcheng.event.ItemFrameListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class main extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new ItemFrameListener(),this);
        Bukkit.getLogger().info(ChatColor.DARK_AQUA+"ItemFrame已启动");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info(ChatColor.BLUE+"ItemFrame已关闭");
    }
}
