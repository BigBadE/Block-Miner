package com.bigbade.blockminer.util;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.bigbade.blockminer.config.ConfigSetup;

public class GetConfigMessage {
	public static String getConfigMessage(String name) {
		File file = ConfigSetup.file();
		FileConfiguration messageFile = YamlConfiguration
				.loadConfiguration(file);
		String message = messageFile.getString(name);
		message = message.replace("&", "§");
		return message;
	}
}
