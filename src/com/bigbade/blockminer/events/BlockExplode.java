package com.bigbade.blockminer.events;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.bigbade.blockminer.util.BlockMinerList;
import com.bigbade.blockminer.util.GetConfigMessage;

public class BlockExplode implements Listener {
	@EventHandler
	public void blockExplode(EntityExplodeEvent event) {
		try {
			for (Location loc : BlockMinerList.getMinerList()) {
				for (Block block : event.blockList()) {
					if (block.getLocation().equals(loc)) {
						String itemname = GetConfigMessage
								.getConfigMessage("MinerName");
						ItemStack item = new ItemStack(Material.DISPENSER, 1);
						ItemMeta metadata = item.getItemMeta();
						metadata.setDisplayName(itemname);
						item.setItemMeta(metadata);
						ArrayList<Location> list = BlockMinerList
								.getMinerList();
						list.remove(loc);
						BlockMinerList.setMinerList(list);
						block.getLocation().getWorld()
								.dropItemNaturally(block.getLocation(), item);
					}
				}
			}
		} catch (ConcurrentModificationException e) {

		}
	}
}
