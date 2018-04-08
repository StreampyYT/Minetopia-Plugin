package me.Streampy.minetopia.library;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.Streampy.minetopia.Main;

public class EventsHandler implements Listener{

	public EventsHandler(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onJoinEvent(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		functions.playerCreate(player.getUniqueId().toString(), player.getName());
	}
	
}
