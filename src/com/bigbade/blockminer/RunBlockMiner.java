package com.bigbade.blockminer;

import java.util.Collection;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;

import com.bigbade.blockminer.util.BlockMinerList;
import com.bigbade.blockminer.util.GetConfigMessage;

public class RunBlockMiner {
	public static void runBlockMiner() {
		BukkitScheduler scheduler = BlockMiner.getPlugin(BlockMiner.class)
				.getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(
				BlockMiner.getPlugin(BlockMiner.class), new Runnable() {
					@Override
					public void run() {
						for (Location location : BlockMinerList.getMinerList()) {
							BlockFace face = ((org.bukkit.material.Dispenser) location
									.getBlock().getState().getData())
									.getFacing();
							Block block = location.getBlock().getRelative(face);
							BlockFace oppositeface = face.getOppositeFace();
							if (!BlockMinerList.getMinerList().contains(
									location.getBlock())) {
								if (location.getBlock()
										.getRelative(oppositeface).getState() instanceof InventoryHolder) {
									InventoryHolder inventoryholder = (InventoryHolder) location
											.getBlock()
											.getRelative(oppositeface)
											.getState();
									Inventory inventory = inventoryholder
											.getInventory();
									for (ItemStack item : block.getDrops()) {
										int amount = 0;
										if (inventory.contains(item)) {
											amount = item.getAmount();
										}
										if (inventory.firstEmpty() != -1
												|| amount > 64 && amount != 0) {
											inventory.addItem(item);
										} else {
											block.getLocation()
													.getWorld()
													.dropItemNaturally(
															block.getLocation(),
															item);
										}
										location.getBlock().getRelative(face)
												.setType(Material.AIR);
									}
								} else {
									Collection<ItemStack> items = block
											.getDrops();
									block.setType(Material.AIR);
									for (ItemStack item : items) {
										block.getLocation()
												.getWorld()
												.dropItemNaturally(
														block.getLocation(),
														item);
									}
								}
							} else {
								block.setType(Material.AIR);
								String itemname = GetConfigMessage
										.getConfigMessage("MinerName");
								ItemStack item = new ItemStack(
										Material.DISPENSER, 1);
								ItemMeta meta = item.getItemMeta();
								meta.setDisplayName(itemname);
								item.setItemMeta(meta);
								Collection<ItemStack> items = block.getDrops();
								for (ItemStack dropitem : items) {
									location.getWorld().dropItemNaturally(
											block.getLocation(), dropitem);
								}
							}
						}
					}
				}, 0L, 20L);
	}
}
