package me.Streampy.minetopia;

import org.bukkit.plugin.java.JavaPlugin;

import me.Streampy.minetopia.commands.minetopia;
import me.Streampy.minetopia.library.EventsHandler;
import me.Streampy.minetopia.library.functions;

public class Main extends JavaPlugin {

	public void onEnable() {
		functions.loadAll();
		
		getCommand("minetopia").setExecutor(new minetopia(this));
		getCommand("mt").setExecutor(new minetopia(this));
		new EventsHandler(this);
	}
	
	public void onDisable() {
		functions.saveAll();
	}
	
}
