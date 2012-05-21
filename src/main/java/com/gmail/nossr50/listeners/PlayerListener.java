package com.gmail.nossr50.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import com.gmail.nossr50.SpawnPlugin;

public class PlayerListener implements Listener {
    SpawnPlugin plugin = null;
    
    public PlayerListener(SpawnPlugin plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler(ignoreCancelled = true)
    public void PlayerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location a = event.getFrom(), b = event.getTo();
        
        if(plugin.waitingToTeleport.containsKey(player)) {
            if(a.getBlockX() != b.getBlockX() || a.getBlockZ() != b.getBlockZ()) {
                player.sendMessage(plugin.infoPrefix+"Your movement has cancelled the teleport!");
                plugin.waitingToTeleport.remove(player);
            }
        }
    }
}
