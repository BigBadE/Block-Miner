package com.bigbade.blockminer.events;

import java.io.File;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.bigbade.blockminer.config.ConfigSetup;
import com.bigbade.blockminer.util.BlockMinerList;

public class BlockBreak implements Listener {
	public void blockBreak(BlockBreakEvent event) {
		for(Location loc : BlockMinerList.getMinerList()) {
			if(event.getBlock().getLocation().equals(loc)) {
				File file = ConfigSetup.file();
				FileConfiguration messageFile = YamlConfiguration
						.loadConfiguration(file);
				String itemname = messageFile.getString("MinerName");
				itemname.replace("&", "§");
				ItemStack item = new ItemStack(Material.DISPENSER,
						1);
				item.getItemMeta().setDisplayName(itemname);
				event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), item);
			}
		}
	}
}
