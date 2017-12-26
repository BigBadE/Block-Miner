package com.bigbade.blockminer.util;

import java.util.ArrayList;

import org.bukkit.Location;

public class BlockMinerList {
	static ArrayList<Location> minerlist = new ArrayList<Location>();
	
	public static ArrayList<Location> getMinerList() {
		return minerlist;	
	}
	
	public static void setMinerList(ArrayList<Location> list) {
		minerlist = list;
	}
}
