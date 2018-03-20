package me.Streampy.minetopia.records;

import java.util.ArrayList;

import org.bukkit.Location;

import me.Streampy.minetopia.library.plotaccess;

public class records {
	
	public static class playerRec {
		public String name;
		public String uuid;
		
		public String prefix;
		public String suffix;
		
		public String winkel;
		public String beroep;
		
		public int level;
		public int fitheid;
		public int geld;
		
		public cityRec city_inwoner; 
		public ArrayList<plotRec> plotsList = new ArrayList<plotRec>();
	}
	
	public static class plotRec {
		public String uuid;
		public Location loc1;
		public Location loc2;
		public playerRec owner;
		public ArrayList<plotMemberRec> membersList = new ArrayList<plotMemberRec>();
	}
	
	public static class plotMemberRec {
		public playerRec member;
		public ArrayList<plotaccess> plotAccessList = new ArrayList<plotaccess>();	
	}
	
	public static class cityRec {
		public String name;
		public playerRec burgerMeester;
		public ArrayList<plotRec> plotsList = new ArrayList<plotRec>();
		public ArrayList<playerRec> inwonersList = new ArrayList<playerRec>(); 
	}
	
	public static ArrayList<playerRec> playersList = new ArrayList<playerRec>();
	public static ArrayList<plotRec> plotsList = new ArrayList<plotRec>();
	public static ArrayList<cityRec> citysList = new ArrayList<cityRec>();

}
