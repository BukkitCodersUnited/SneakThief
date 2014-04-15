package me.arrdev.sneakthief.util;

import me.arrdev.sneakthief.config.ConfigurationManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class Utilities {

	public static Inventory arrangeInventory(Inventory oinv) {
		Inventory inv = Bukkit.getServer().createInventory(oinv.getHolder(), InventoryType.CHEST);

		for (int i = 0; i <= 35; i++) {
			inv.setItem(i, oinv.getItem(i));
		}

		int i = 103;
		for (int j = 45; j <= 48; j++) {
			inv.setItem(j, oinv.getItem(i));
			i--;
		}

		return inv;
	}
	
	public static void notifyPlayerIfNecessary(Player player, Player pp, boolean success) {
		if (ConfigurationManager.isAlertPlayer())
			pp.sendMessage(player.getDisplayName() + ChatColor.RED + " " + (success ? "was a sneaky bastard and stole from you!" : "tried to steal from you but failed!"));
	}

}
