package me.Streampy.minetopia.library;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;

import me.Streampy.minetopia.records.records;
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
	
	public static void loadPlayers() {
		if (minetopia.exists()) {
			
		}else {
			//Geen data / Map gevonden of bestaat niet
		}
	}
	
	public static void savePlayers() {
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
					playerConfig.set("players." + a + ".city", playerRecord.city_inwoner.name);
					
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
	
	public static void loadPlots() {
		if (minetopia.exists()) {
			
		}else {
			//Geen data / Map gevonden of bestaat niet
		}
	}
	
	public static void savePlots() {
		if (plotsList.size() > 0) {
			files.makeDirectoryIfNotExist(minetopia);
			files.makeFileIfNotExist(plotFile);
			try {
				plotConfig.load(plotFile);
				for (int a = 0; a < plotsList.size(); a++) {
					plotRec plotRecord = plotsList.get(a);
					plotConfig.set("plots." + a + ".uuid", plotRecord.uuid);
					plotConfig.set("plots." + a + ".owner", plotRecord.owner.uuid);
					
					plotConfig.set("plots." + a + ".loc.1.world", plotRecord.loc1.getWorld().getName());
					plotConfig.set("plots." + a + ".loc.1.x", plotRecord.loc1.getBlockX());
					plotConfig.set("plots." + a + ".loc.1.y", plotRecord.loc1.getBlockY());
					plotConfig.set("plots." + a + ".loc.1.z", plotRecord.loc1.getBlockZ());
					
					plotConfig.set("plots." + a + ".loc.2.world", plotRecord.loc2.getWorld().getName());
					plotConfig.set("plots." + a + ".loc.2.x", plotRecord.loc2.getBlockX());
					plotConfig.set("plots." + a + ".loc.2.y", plotRecord.loc2.getBlockY());
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
	
	public static void loadCitys() {
		if (minetopia.exists()) {
			
		}else {
			//Geen data / Map gevonden of bestaat niet
		}
	}
	
	public static void saveCitys() {
		files.makeDirectoryIfNotExist(minetopia);
		files.makeFileIfNotExist(cityFile);
	}
	
	
}
