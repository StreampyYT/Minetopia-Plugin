package me.Streampy.minetopia.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Streampy.minetopia.Main;
import me.Streampy.minetopia.subcommands.Plot;

public class minetopia implements CommandExecutor {

	public minetopia(Main main) {
		// TODO Auto-generated constructor stub
	}
	
	Plot plot = new Plot();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("minetopia")) {
				if (args.length == 0) {
					//help menu
				}else {
					switch(args[0]) {
						case "plot": 
							plot.onCommand(sender, cmd, label, args);
							break;
						case "": break;
						default: //help menu
					}
				}
			}else {
				player.sendMessage(ChatColor.RED + "You dont have the right permissions!");
			}
		}
		return false;
	}

}
