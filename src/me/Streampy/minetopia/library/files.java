package me.Streampy.minetopia.library;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class files {

	public static File minetopia = new File("plugins/Minetopia");
	
	public static File playerFile = new File("plugins/Minetopia/playerdata.yml");
	public static File plotFile = new File("plugins/Minetopia/plotdata.yml");
	public static File cityFile = new File("plugins/Minetopia/citydata.yml");
	
	public static FileConfiguration playerConfig = new YamlConfiguration();
	public static FileConfiguration plotConfig = new YamlConfiguration();
	public static FileConfiguration cityConfig = new YamlConfiguration();
	
	public static void makeDirectoryIfNotExist(File file) {
		if (!file.exists()) {
			file.mkdir();
		}
	}
	
	public static void makeFileIfNotExist(File file) {
		if (!file.exists()) {
			try {
				file.createNewFile();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
}
