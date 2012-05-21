package com.gmail.nossr50.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.nossr50.SpawnPlugin;

public class Spawn implements CommandExecutor {
    private SpawnPlugin plugin;
    
    public Spawn (SpawnPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(plugin.waitingToTeleport.containsKey(player)) {
                player.sendMessage(plugin.infoPrefix+"You are already waiting to teleport.");
            } else {
                player.sendMessage(plugin.infoPrefix+"Starting the teleport process...");
                player.sendMessage(plugin.infoPrefix+"Please remain still for 30 seconds");
                plugin.waitingToTeleport.put(player, System.currentTimeMillis()+(30 * 1000));
            }
            
        } else {
            sender.sendMessage("This command does not support console useage.");
        }
        
        return true;
    }
}