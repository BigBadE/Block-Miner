package com.bigbade.blockminer.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.bigbade.blockminer.util.BlockMinerList;

public class MinerList {
	public static File MinerListFile() {
		File minerlistfile = new File("plugins/BlockMiner/minerlist.yml");
		return minerlistfile;
	}

	public static void SaveMinerLocations() {
		File minerlistfile = MinerList.MinerListFile();
		FileConfiguration minerlistfileconfig = YamlConfiguration
				.loadConfiguration(minerlistfile);
		try {
			if (!minerlistfile.exists()) {
				minerlistfileconfig.createSection("MinerLocations");
			}
			minerlistfileconfig.set("MinerLocations",
					BlockMinerList.getMinerList());
			minerlistfileconfig.save(minerlistfile);
		} catch (IOException e) {

		}
	}
}
