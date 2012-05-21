package com.gmail.nossr50.vspawn.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.nossr50.vspawn.SpawnPlugin;

public class Spawn implements CommandExecutor {
    private final SpawnPlugin plugin;

    public Spawn(SpawnPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(SpawnPlugin.isWaitingForTeleport(player)) {
                player.sendMessage(SpawnPlugin.infoPrefix+"You are already waiting to teleport.");
            } else {
                player.sendMessage(SpawnPlugin.infoPrefix+"Starting the teleport process...");
                player.sendMessage(SpawnPlugin.infoPrefix+"Please remain still for 30 seconds");
                plugin.scheduleForPlayer(player);
            }
        } else {
            sender.sendMessage("This command does not support console useage.");
        }

        return true;
    }
}