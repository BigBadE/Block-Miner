package com.bigbade.blockminer.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.bigbade.blockminer.util.BlockMinerList;

public class ConfigSetup {
	public static File file() {
		File file = new File("plugins/BlockMiner/Messages.yml");
		return file;
	}
	
	public static File fileSetup() {
		File file = ConfigSetup.file();
		FileConfiguration messagefile = YamlConfiguration
				.loadConfiguration(file);
		if (!file.exists()) {
			try {
				messagefile.createSection("NotEnoughArgs");
				messagefile.set("NotEnoughArgs", "&c/Miner Give <Player> <Amount>");
				messagefile.createSection("MinerName");
				messagefile.set("MinerName", "&c&lMINER");
				messagefile.createSection("PlayerNotFound");
				messagefile.set("PlayerNotFound", "&cThat Player Is Not Online!");
				messagefile.createSection("NotANumber");
				messagefile.set("NotANumber", "&cThat's Not a Number!");
				messagefile.createSection("ArgTwoNotGive");
				messagefile.set("ArgTwoNotGive", "&c/Miner Give <Player> <Amount>");
				messagefile.createSection("NoPermission");
				messagefile.set("NoPermission", "&cYou do not have permission to do that!");
				messagefile.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}
	
	public static File minerListFile() {
		File file = new File("plugins/BlockMiner/minerlist.yml");
		return file;
	}
	
	@SuppressWarnings("unchecked")
	public static void minerList() {
		File file = ConfigSetup.minerListFile();
		if(!file.exists()) {
			ArrayList<Location> list = new ArrayList<Location>();
			BlockMinerList.setMinerList(list);
		} else {
			FileConfiguration messagefile = YamlConfiguration
					.loadConfiguration(file);
			ArrayList<Location> list = (ArrayList<Location>) messagefile.get("MinerLocations");
			BlockMinerList.setMinerList(list);
		}
	}
}
