package com.gmail.nossr50.runnables;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.gmail.nossr50.SpawnPlugin;

public class TeleportHandler implements Runnable {
    SpawnPlugin plugin = null;
    
    public TeleportHandler(SpawnPlugin plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public void run() {
        ArrayList<Player> toBeRemoved = new ArrayList<Player>();
        
        for(Player player : plugin.waitingToTeleport.keySet()) {
            if(plugin.waitingToTeleport.get(player) < System.currentTimeMillis()) {
                player.sendMessage(plugin.infoPrefix+"Now teleporting...");
                player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
                toBeRemoved.add(player);
            }
        }
        
        for(Player player : toBeRemoved) {
            plugin.waitingToTeleport.remove(player);
        }
    }

}
