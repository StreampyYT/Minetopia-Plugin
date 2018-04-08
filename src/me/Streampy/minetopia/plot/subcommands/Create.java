package me.Streampy.minetopia.plot.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Streampy.minetopia.library.functions;

public class Create {

	public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length == 2) {
				functions.plotCreate(player.getLocation(), player.getLocation());
				player.sendMessage(ChatColor.GREEN + "Plot created");
			}else {
				player.sendMessage(ChatColor.RED + "Use /" + cmd.getName() + " " + args[0] + " " + args[1]);
			}
		}
	}
}
