package com.bigbade.blockminer.events;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.bigbade.blockminer.util.BlockMinerList;
import com.bigbade.blockminer.util.GetConfigMessage;

public class BlockBreak implements Listener {
	@EventHandler
	public void blockBreak(BlockBreakEvent event) {
		try {
			for (Location loc : BlockMinerList.getMinerList()) {
				if (event.getBlock().getLocation().equals(loc)) {
					String itemname = GetConfigMessage.getConfigMessage("MinerName");
					ItemStack item = new ItemStack(Material.DISPENSER, 1);
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName(itemname);
					item.setItemMeta(meta);
					ArrayList<Location> list = BlockMinerList.getMinerList();
					list.remove(loc);
					BlockMinerList.setMinerList(list);
					event.getBlock()
							.getWorld()
							.dropItemNaturally(event.getBlock().getLocation(),
									item);
				}
			}
		} catch (ConcurrentModificationException e) {

		}
	}
}
