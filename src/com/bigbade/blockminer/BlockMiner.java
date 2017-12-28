package com.bigbade.blockminer;

import org.bukkit.plugin.java.JavaPlugin;

import com.bigbade.blockminer.commands.BlockMinerCmd;
import com.bigbade.blockminer.config.ConfigSetup;
import com.bigbade.blockminer.config.MinerList;
import com.bigbade.blockminer.events.BlockBreak;
import com.bigbade.blockminer.events.BlockExplode;
import com.bigbade.blockminer.events.BlockPlace;

public class BlockMiner extends JavaPlugin {

	@Override
	public void onEnable() {
		ConfigSetup.fileSetup();
		ConfigSetup.minerList();
		
		this.getCommand("Miner").setExecutor(new BlockMinerCmd());
		
		getServer().getPluginManager().registerEvents(new BlockPlace(), this);
		getServer().getPluginManager().registerEvents(new BlockBreak(), this);
		getServer().getPluginManager().registerEvents(new BlockExplode(), this);
		
		RunBlockMiner.runBlockMiner();
	}

	@Override
	public void onDisable() {
		MinerList.SaveMinerLocations();
	}
}
