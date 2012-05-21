package com.gmail.nossr50.vspawn.runnables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.gmail.nossr50.vspawn.SpawnPlugin;

public class TeleportHandler implements Runnable {
    private final Player player;

    public TeleportHandler(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        player.sendMessage(SpawnPlugin.infoPrefix+"Now teleporting...");
        player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
    }
}
