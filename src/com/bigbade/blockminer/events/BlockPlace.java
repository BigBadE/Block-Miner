package com.bigbade.blockminer.events;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import com.bigbade.blockminer.util.BlockMinerList;
import com.bigbade.blockminer.util.GetConfigMessage;

public class BlockPlace implements Listener {
	@EventHandler
	public void blockPlace(BlockPlaceEvent event) {
		LivingEntity placer = event.getPlayer();
		ItemStack item = placer.getEquipment().getItemInMainHand();
		String name = item.getItemMeta().getDisplayName();
		if (item.getType().equals(Material.DISPENSER)) {
			String itemname = GetConfigMessage.getConfigMessage("MinerName");
			if (name == null) {
				return;
			}
			if (name.equals(itemname)) {
				ArrayList<Location> list = BlockMinerList.getMinerList();
				list.add(event.getBlock().getLocation());
				placer.sendMessage(ChatColor.GREEN + "You placed a " + itemname);
				BlockMinerList.setMinerList(list);
			}
		}
	}
}
