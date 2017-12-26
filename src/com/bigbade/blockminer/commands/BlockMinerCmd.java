package com.bigbade.blockminer.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.bigbade.blockminer.util.GetConfigMessage;

public class BlockMinerCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (label.equalsIgnoreCase("Miner")) {
			if (sender.hasPermission("BlockMiner.give")) {
				if (args.length != 3) {
					String error = GetConfigMessage
							.getConfigMessage("NotEnoughArgs");
					sender.sendMessage(error);
					return true;
				}

				if (args[0].equalsIgnoreCase("Give")) {
					String name = args[1];
					Player player = sender.getServer().getPlayer(name);
					if (player != null) {
						String nerror = GetConfigMessage
								.getConfigMessage("NotANumber");
						String itemname = GetConfigMessage
								.getConfigMessage("MinerName");
						try {
							String samount = args[2];
							int amount = Integer.parseInt(samount);
							ItemStack item = new ItemStack(Material.DISPENSER,
									amount);
							ItemMeta meta = item.getItemMeta();
							meta.setDisplayName(itemname);
							item.setItemMeta(meta);
							player.getInventory().addItem(item);
							return true;
						} catch (NumberFormatException
								| ArrayIndexOutOfBoundsException error) {
							sender.sendMessage(nerror);
							return true;
						}
					} else {
						String error = GetConfigMessage
								.getConfigMessage("PlayerNotFound");
						sender.sendMessage(error);
						return true;
					}
				} else {
					String error = GetConfigMessage
							.getConfigMessage("ArgTwoNotGive");
					sender.sendMessage(error);
					return true;
				}
			} else {
				String error = GetConfigMessage
						.getConfigMessage("NoPermission");
				sender.sendMessage(error);
				return true;
			}
		}
		return false;
	}
}