package com.gmail.nossr50.vspawn.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import com.gmail.nossr50.vspawn.SpawnPlugin;

public class EntityListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void EntityDamageEvent(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if(event.getDamage() >= 1 && SpawnPlugin.isWaitingForTeleport(player)) {
                player.sendMessage(SpawnPlugin.infoPrefix+"Taking damage has cancelled the teleport!");
                SpawnPlugin.cancelForPlayer(player);
            }
        }
    }

}
