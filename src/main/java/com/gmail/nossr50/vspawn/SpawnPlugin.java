package com.gmail.nossr50.vspawn;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.nossr50.vspawn.commands.Spawn;
import com.gmail.nossr50.vspawn.listeners.EntityListener;
import com.gmail.nossr50.vspawn.listeners.PlayerListener;
import com.gmail.nossr50.vspawn.runnables.TeleportHandler;

public class SpawnPlugin extends JavaPlugin {
    private static HashMap<Player, Integer> waitingToTeleport = new HashMap<Player, Integer>();
    public static final String infoPrefix = ChatColor.GOLD+"[vSpawn] "+ChatColor.RED;

    PlayerListener playerListener = null;
    EntityListener entityListener = null;

    public void onEnable() {
        playerListener = new PlayerListener();
        entityListener = new EntityListener();

        getServer().getPluginManager().registerEvents(playerListener, this);
        getServer().getPluginManager().registerEvents(entityListener, this);

        getCommand("spawn").setExecutor(new Spawn(this));

        getLogger().info("Finished Loading " + getDescription().getFullName());
    }
    
    public void onDisable() {
        getServer().getScheduler().cancelTasks(this);

        getLogger().info("Finished Unloading "+getDescription().getFullName());
    }

    public static boolean isWaitingForTeleport(Player player) {
        return waitingToTeleport.containsKey(player);
    }

    public static void cancelForPlayer(Player player) {
        if(isWaitingForTeleport(player)) {
            int cancel = waitingToTeleport.get(player);
            Bukkit.getScheduler().cancelTask(cancel);
        }
        return;
    }

    public void scheduleForPlayer(Player player) {
        int taskId = getServer().getScheduler().scheduleSyncDelayedTask(this, new TeleportHandler(player), 600L);
        SpawnPlugin.waitingToTeleport.put(player, taskId);
    }
}
