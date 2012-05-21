package com.gmail.nossr50;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.nossr50.commands.Spawn;
import com.gmail.nossr50.listeners.EntityListener;
import com.gmail.nossr50.listeners.PlayerListener;
import com.gmail.nossr50.runnables.TeleportHandler;

public class SpawnPlugin extends JavaPlugin {

    public HashMap<Player, Long> waitingToTeleport = new HashMap<Player, Long>();
    public String infoPrefix = ChatColor.GOLD+"[vSpawn] "+ChatColor.RED;

    PlayerListener playerListener = null;
    EntityListener entityListener = null;

    public void onEnable() {
        playerListener = new PlayerListener(this);
        entityListener = new EntityListener(this);

        Bukkit.getPluginManager().registerEvents(playerListener, this);
        Bukkit.getPluginManager().registerEvents(entityListener, this);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new TeleportHandler(this), 0, 20);

        getCommand("spawn").setExecutor(new Spawn(this));

        getLogger().info("Finished Loading " + getDescription().getFullName());
    }
    
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);

        getLogger().info("Finished Unloading "+getDescription().getFullName());
    }

}
