package me.Streampy.minetopia.library;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.Streampy.minetopia.records.records;
import me.Streampy.minetopia.records.records.cityRec;
import me.Streampy.minetopia.records.records.playerRec;
import me.Streampy.minetopia.records.records.plotMemberRec;
import me.Streampy.minetopia.records.records.plotRec;

public class functions {

	static File minetopia = files.minetopia;
	
	static File playerFile = files.playerFile;
	static File plotFile = files.plotFile;
	static File cityFile = files.cityFile;
	
	static FileConfiguration playerConfig = files.playerConfig;
	static FileConfiguration plotConfig = files.plotConfig;
	static FileConfiguration cityConfig = files.cityConfig;
	
	static ArrayList<records.playerRec> playersList = records.playersList;
	static ArrayList<records.plotRec> plotsList = records.plotsList;
	static ArrayList<records.cityRec> citysList = records.citysList;
	
	public static void saveAll() {
		savePlayers();
		savePlots();
		saveCitys();
	}
	
	public static void loadAll() {
		loadPlayers(Type.firstLoad);
		loadPlots();
		loadCitys();
		loadPlayers(Type.secondLoad);
		
	}
	
	public static void plotCreate(Location loc1, Location loc2) {
		plotRec plotRecord = new plotRec();
		plotsList.add(plotRecord);
		
		plotRecord.loc1 = loc1;
		plotRecord.loc2 = loc2;
		plotRecord.owner = null;
		plotRecord.uuid = null;
	}
	
	public static void plotCreate(Player player, Location loc1, Location loc2) {
		plotRec plotRecord = new plotRec();
		plotsList.add(plotRecord);
		
		plotRecord.loc1 = loc1;
		plotRecord.loc2 = loc2;
		plotRecord.owner = getPlayerRecFromUUID(player.getUniqueId().toString());
		plotRecord.uuid = null;
	}
	
	public static void plotCreate(String uuid, Location loc1, Location loc2) {
		plotRec plotRecord = new plotRec();
		plotsList.add(plotRecord);
		
		plotRecord.loc1 = loc1;
		plotRecord.loc2 = loc2;
		plotRecord.owner = getPlayerRecFromUUID(uuid);
		plotRecord.uuid = null;
	}
	
	
	
	public static void playerCreate(String uuid, String name) {
		playerRec playerRecord = getPlayerRecFromUUID(uuid);
		if (playerRecord == null) {
			playerRecord = new playerRec();
			playersList.add(playerRecord);
			
			playerRecord.name = name;
			playerRecord.uuid = uuid;
			playerRecord.prefix = null; //Moet uit config worden gehaald als nog niet gezet
			playerRecord.suffix = null; //Moet uit config worden gehaald als nog niet gezet
			playerRecord.winkel = null;
			playerRecord.beroep = null;
			
			playerRecord.level = 1;
			playerRecord.fitheid = 20; //Moet uit config worden gehaald als nog niet gezet
			playerRecord.geld = 10000; //Moet uit config worden gehaald als nog niet gezet
			
			playerRecord.city_inwoner = null; //Moet uit config worden gehaald als nog niet gezet
		}		
	}
	
	private static void loadPlayers(Type type) {
		if (minetopia.exists() && playerFile.exists()) {
			try {
				playerConfig.load(playerFile);
				for (int a = 0; playerConfig.contains("players." + a); a++) {
					
					playerRec playerRecord = null;
					boolean newPlayer = true;
					
					for (int b = 0; b < playersList.size(); b++) {
						playerRecord = playersList.get(b);
						if (playerConfig.get("players." + a + ".uuid").equals(playerRecord.uuid)) {
							newPlayer = false;
							break;
						}
					}
					
					if (newPlayer == true) {
						playerRecord = new playerRec();
						playersList.add(playerRecord);
					}
					
					if (type.equals(Type.firstLoad)) {
						playerRecord.name = playerConfig.getString("players." + a + ".name");
						playerRecord.uuid = playerConfig.getString("players." + a + ".uuid");
						playerRecord.prefix = playerConfig.getString("players." + a + ".prefix");
						playerRecord.suffix = playerConfig.getString("players." + a + ".suffix");
						playerRecord.winkel = playerConfig.getString("players." + a + ".winkel");
						playerRecord.beroep = playerConfig.getString("players." + a + ".beroep");
						
						playerRecord.level = playerConfig.getInt("players." + a + ".level");
						playerRecord.fitheid = playerConfig.getInt("players." + a + ".fitheid");
						playerRecord.geld = playerConfig.getInt("players." + a + ".geld");
						//first loading (player gegevens)
					}else if (type.equals(Type.secondLoad)) {
						playerRecord.city_inwoner = getCityRecFromNAME(playerConfig.getString("players." + a + ".city"));
						
						for (int c = 0; playerConfig.contains("players." + a + ".plots." + c); c++) {
							playerRecord.plotsList.add(getPlotRecFromUUID(playerConfig.getString("players." + a + ".plots." + c + ".uuid")));
						}
						//second loading (city + plot)
					}
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}else {
			//Geen data / Map gevonden of bestaat niet
		}
	}

	private static void savePlayers() {
		if (playersList.size() > 0) {
			files.makeDirectoryIfNotExist(minetopia);
			files.makeFileIfNotExist(playerFile);
			try {
				playerConfig.load(playerFile);
				for (int a = 0; a < playersList.size(); a++) {
					playerRec playerRecord = playersList.get(a);
					playerConfig.set("players." + a + ".name", playerRecord.name);
					playerConfig.set("players." + a + ".uuid", playerRecord.uuid);
					playerConfig.set("players." + a + ".prefix", playerRecord.prefix);
					playerConfig.set("players." + a + ".suffix", playerRecord.suffix);
					playerConfig.set("players." + a + ".winkel", playerRecord.winkel);
					playerConfig.set("players." + a + ".beroep", playerRecord.beroep);
					playerConfig.set("players." + a + ".level", playerRecord.level);
					playerConfig.set("players." + a + ".fitheid", playerRecord.fitheid);
					playerConfig.set("players." + a + ".geld", playerRecord.geld);
					if (playerRecord.city_inwoner == null) {
						playerConfig.set("players." + a + ".city", playerRecord.city_inwoner);
					}else {
						playerConfig.set("players." + a + ".city", playerRecord.city_inwoner.name);
					}
					for (int b = 0; b < playerRecord.plotsList.size(); b++) {
						plotRec plotRecord = playerRecord.plotsList.get(b);
						playerConfig.set("players." + a + ".plots." + b + ".uuid", plotRecord.uuid);
					}
					playerConfig.save(playerFile);
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			
		}
		
	}	
	
	private static void loadPlots() {
		if (minetopia.exists() && plotFile.exists()) {
			try {
				plotConfig.load(plotFile);
				for (int a = 0; plotConfig.contains("plots." + a); a++) {
					plotRec plotRecord = new plotRec();
					plotsList.add(plotRecord);
					Location loc1 = new Location(Bukkit.getWorld(plotConfig.getString("plots." + a + ".loc.1.world")), plotConfig.getInt("plots." + a + ".loc.1.x"), 0 , plotConfig.getInt("plots." + a + ".loc.1.z"));
					Location loc2 = new Location(Bukkit.getWorld(plotConfig.getString("plots." + a + ".loc.2.world")), plotConfig.getInt("plots." + a + ".loc.2.x"), 0 , plotConfig.getInt("plots." + a + ".loc.2.z"));
					
					plotRecord.uuid = plotConfig.getString("plots." + a + ".uuid");
					plotRecord.loc1 = loc1;
					plotRecord.loc2 = loc2;
					plotRecord.owner = getPlayerRecFromUUID(plotConfig.getString("plots." + a + ".owner"));
					
					for (int b = 0; plotConfig.contains("plots." + a + ".members." + b); b++) {
						plotMemberRec plotMemberRecord = new plotMemberRec();
						plotRecord.membersList.add(plotMemberRecord);
						
						plotMemberRecord.member = getPlayerRecFromUUID(plotConfig.getString("plots." + a + ".members." + b + ".uuid"));
						for (int c = 0; plotConfig.contains("plots." + a + ".members." + b + ".settings." + c); c++) {
							plotMemberRecord.plotAccessList.add(plotaccess.valueOf(plotConfig.getString("plots." + a + ".members." + b + ".settings." + c)));
						}
					}
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}else {
			//Geen data / Map gevonden of bestaat niet
		}
	}
	
	private static void savePlots() {
		if (plotsList.size() > 0) {
			files.makeDirectoryIfNotExist(minetopia);
			files.makeFileIfNotExist(plotFile);
			try {
				plotConfig.load(plotFile);
				for (int a = 0; a < plotsList.size(); a++) {
					plotRec plotRecord = plotsList.get(a);
					plotConfig.set("plots." + a + ".uuid", plotRecord.uuid);
					
					if (plotRecord.owner != null) {
						plotConfig.set("plots." + a + ".owner", plotRecord.owner.uuid);
					}else {
						plotConfig.set("plots." + a + ".owner", null);
					}
					plotConfig.set("plots." + a + ".loc.1.world", plotRecord.loc1.getWorld().getName());
					plotConfig.set("plots." + a + ".loc.1.x", plotRecord.loc1.getBlockX());
					plotConfig.set("plots." + a + ".loc.1.z", plotRecord.loc1.getBlockZ());
					
					plotConfig.set("plots." + a + ".loc.2.world", plotRecord.loc2.getWorld().getName());
					plotConfig.set("plots." + a + ".loc.2.x", plotRecord.loc2.getBlockX());
					plotConfig.set("plots." + a + ".loc.2.z", plotRecord.loc2.getBlockZ());
					
					for (int b = 0; b < plotRecord.membersList.size(); b++) {
						plotMemberRec plotMemberRecord = plotRecord.membersList.get(b);
						plotConfig.set("plots." + a + ".members." + b + ".uuid", plotMemberRecord.member.uuid);
						
						for (int c = 0; c < plotMemberRecord.plotAccessList.size(); c++) {
							plotConfig.set("plots." + a + ".members." + b + ".settings." + c, plotMemberRecord.plotAccessList.get(c));
						}
					}
					
					plotConfig.save(plotFile);
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private static void loadCitys() {
		if (minetopia.exists() && cityFile.exists()) {
			try {
				cityConfig.load(cityFile);
				for (int a = 0; cityConfig.contains("citys." + a); a++) {
					cityRec cityRecord = new cityRec();
					citysList.add(cityRecord);
					cityRecord.name = cityConfig.getString("citys." + a + ".name");
					cityRecord.burgerMeester = getPlayerRecFromUUID(cityConfig.getString("citys." + a + ".burgerMeester"));
					for (int b = 0; cityConfig.contains("citys." + a + ".plots." + b + ".uuid"); b++) {
						cityRecord.plotsList.add(getPlotRecFromUUID(cityConfig.getString("citys." + a + ".plots." + b + ".uuid")));
					}
					for (int c = 0; cityConfig.contains("citys." + a + ".inwoners." + c + ".uuid"); c++) {
						cityRecord.inwonersList.add(getPlayerRecFromUUID(cityConfig.getString("citys." + a + ".inwoners." + c + ".uuid")));
					}
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}else {
			//Geen data / Map gevonden of bestaat niet
		}
	}
	
	private static void saveCitys() {
		if (citysList.size() > 0) {
			files.makeDirectoryIfNotExist(minetopia);
			files.makeFileIfNotExist(cityFile);
			
			try {
				cityConfig.load(cityFile);
				for (int a = 0; a < citysList.size(); a++) {
					cityRec cityRecord = citysList.get(a);
					cityConfig.set("citys." + a + ".name", cityRecord.name);
					cityConfig.set("citys." + a + ".burgerMeester", cityRecord.burgerMeester.uuid);
					for (int b = 0; b < cityRecord.plotsList.size(); b++) {
						plotRec plotRecord = cityRecord.plotsList.get(b);
						cityConfig.set("citys." + a + ".plots." + b + ".uuid", plotRecord.uuid);
						cityConfig.save(cityFile);
					}
					
					for (int c = 0; c < cityRecord.plotsList.size(); c++) {
						playerRec inwonersRecord = cityRecord.inwonersList.get(c);
						cityConfig.set("citys." + a + ".inwoners." + c + ".uuid", inwonersRecord.uuid);
						cityConfig.save(cityFile);
					}
					cityConfig.save(cityFile);
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
	}
	
	public static playerRec getPlayerRecFromUUID(String uuid) {
		for (playerRec playerRecord : playersList) {
			if (playerRecord.uuid.equals(uuid)) {
				return playerRecord;
			}
		}
		return null;
	}
	
	public static plotRec getPlotRecFromUUID(String uuid) {
		for (plotRec plotRecord : plotsList) {
			if (plotRecord.uuid.equals(uuid)) {
				return plotRecord;
			}
		}
		return null;
	}
	
	public static cityRec getCityRecFromNAME(String name) {
		for (cityRec cityRecord : citysList) {
			if (cityRecord.name.equals(name)) {
				return cityRecord;
			}
		}
		return null;
	}
	
}
