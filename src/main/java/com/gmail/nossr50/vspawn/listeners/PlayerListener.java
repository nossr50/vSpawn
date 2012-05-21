package com.gmail.nossr50.vspawn.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import com.gmail.nossr50.vspawn.SpawnPlugin;

public class PlayerListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void PlayerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location a = event.getFrom(), b = event.getTo();
        
        if(SpawnPlugin.isWaitingForTeleport(player)) {
            if(a.getBlockX() != b.getBlockX() || a.getBlockZ() != b.getBlockZ()) {
                player.sendMessage(SpawnPlugin.infoPrefix+"Your movement has cancelled the teleport!");
                SpawnPlugin.cancelForPlayer(player);
            }
        }
    }
}
