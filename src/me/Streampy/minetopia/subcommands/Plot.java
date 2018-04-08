package me.Streampy.minetopia.subcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Streampy.minetopia.plot.subcommands.Create;

public class Plot {

	Create create = new Create();
	
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			//Player player = (Player) sender;
			if (args.length == 1) {
				//help plot menu
			}else {
				switch(args[1]) {
					case "create":
						create.onCommand(sender, cmd, label, args);
						break;
					default: //help plot menu
				}
			}
		}
		
	}

}
